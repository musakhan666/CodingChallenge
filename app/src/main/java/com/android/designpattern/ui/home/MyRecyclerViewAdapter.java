package com.android.designpattern.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.android.designpattern.BR;
import com.android.designpattern.R;
import com.android.designpattern.databinding.CardForRepoBinding;
import com.android.designpattern.model.repoItem.Item;

import org.jetbrains.annotations.Nullable;

import java.util.List;


public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> implements CustomClickListener {

    private List<Item> dataModelList;
    private Context context;

    public MyRecyclerViewAdapter(List<Item> dataModelList, Context ctx) {
        this.dataModelList = dataModelList;
        context = ctx;
    }

    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        CardForRepoBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.card_for_repo, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item dataModel = dataModelList.get(position);
        holder.bind(dataModel);
        holder.itemRowBinding.setItemClickListener(this);
    }


    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    @Override
    public void cardClicked(Object object) {

    }

    public void updateList(@Nullable List<Item> items) {
        dataModelList.addAll(items);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public CardForRepoBinding itemRowBinding;

        public ViewHolder(CardForRepoBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.itemRowBinding = itemRowBinding;
        }

        public void bind(Object obj) {
            itemRowBinding.setVariable(BR.data, obj);
            itemRowBinding.executePendingBindings();
        }
    }

    public void cardClicked(RepoHome f) {
        Toast.makeText(context, "You clicked " + f,
                Toast.LENGTH_LONG).show();
    }
}
