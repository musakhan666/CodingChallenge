package com.android.designpattern.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.designpattern.R
import com.android.designpattern.di.Injectable
import com.android.designpattern.model.Status
import com.android.designpattern.util.EndlessRecyclerOnScrollListener
import com.android.designpattern.util.toast
import kotlinx.android.synthetic.main.fragment_repo.*
import javax.inject.Inject


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class RepoHome : Fragment(), Injectable {
    private var param1: String? = null
    private var param2: String? = null
    private var TAG = "RepoHome"
    private var adapter: MyRecyclerViewAdapter? = null
    private var PAGE: Int = 1
    private var layoutManger: LinearLayoutManager? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var dashboardViewModel: RepoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_repo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetechData()

    }

    private fun fetechData() {
        dashboardViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(RepoViewModel::class.java)
        dashboardViewModel.fetchContacts("created:>2017-10-22", "stars", "desc", PAGE.toString())
            .observe(viewLifecycleOwner, androidx.lifecycle.Observer { listResources ->

                when (listResources?.status) {

                    Status.SUCCESS -> {
                        loader?.visibility = View.GONE
                        Log.i(TAG, listResources.data.toString())
                        activity.let {
                            if (adapter == null) {
                                adapter =
                                    MyRecyclerViewAdapter(listResources.data?.items, it)
                                contactRecycler?.adapter = adapter

                                layoutManger = LinearLayoutManager(it)
                                contactRecycler?.layoutManager = layoutManger

                                contactRecycler.addOnScrollListener(object: EndlessRecyclerOnScrollListener(layoutManger,1){

                                    override fun onLoadMore() {
                                        PAGE++
                                        fetechData()
                                    }
                                })



                            } else {
                                activity?.toast("Page ${PAGE}")
                                adapter?.updateList(listResources.data?.items)
                            }


                        }


                    }
                    Status.LOADING -> {
                        loader?.visibility = View.VISIBLE
                    }
                    Status.ERROR -> {
                        loader?.visibility = View.GONE
                        activity?.toast(listResources.message.toString())
                    }
                }

            })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Contacts.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RepoHome().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}