package com.example.listapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;


public class MainListAdapter extends
        RecyclerView.Adapter<MainListAdapter.ViewHolder>{

    UserList[] lists;

    public MainListAdapter(UserList[] data){lists = data;}

    @NonNull
    @Override
    public MainListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_list_entry, parent, false);
        return new MainListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainListAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(MainListClickListener);

        holder.textView.setText(lists[position].getName());
    }

    @Override
    public int getItemCount() {
        return lists.length;
    }

    View.OnClickListener MainListClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = (int) v.getTag();
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("userList", lists[position].getList());
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.userListFragment, bundle);
        }
    };

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public final TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.tvListName);
        }
    }
}
