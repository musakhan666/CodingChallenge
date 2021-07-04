package com.android.designpattern.ui.smartHome

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.android.designpattern.R
import com.android.designpattern.db.DatabaseOpenHelper
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivityHome : AppCompatActivity(), HasSupportFragmentInjector {

    var databaseHelper: DatabaseOpenHelper? = null

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        databaseHelper = DatabaseOpenHelper(this)
//
//        try {
//            databaseHelper?.createDatabase()
//            databaseHelper?.openDatabase()
//
//        } catch (EX: Exception) {
//
//        }
//
//        databaseHelper?.getSoldProducts("ItemToSell")?.observe(this, Observer {
//
//                it ->
//        })

    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector

}