package com.example.listapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserListAdapter extends
        RecyclerView.Adapter<UserListAdapter.ViewHolder>{

    UserList list;

    public UserListAdapter(UserList data){list = data;}

    @NonNull
    @Override
    public UserListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_entry, parent, false);
        return new UserListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setTag(position);

        holder.checkBox.setText(list.getList().get(position));
    }

    @Override
    public int getItemCount() {
        return list.getLength();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public final CheckBox checkBox;

        public ViewHolder(View itemView) {
            super(itemView);

            checkBox= (CheckBox) itemView.findViewById(R.id.cbTikki);
        }
    }
}
