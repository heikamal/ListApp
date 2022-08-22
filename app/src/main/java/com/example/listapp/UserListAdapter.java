package com.example.listapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 *
 */
public class UserListAdapter extends
        RecyclerView.Adapter<UserListAdapter.ViewHolder>{

    /**
     *
     */
    UserList list;

    /**
     *
     * @param data
     */
    public UserListAdapter(UserList data){list = data;}

    /**
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public UserListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_entry, parent, false);
        return new UserListAdapter.ViewHolder(view);
    }

    /**
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setTag(position);

        holder.checkBox.setText(list.getList().get(position));
        holder.checkBox.setChecked(list.getCheckedItems().get(position));

        holder.checkBox.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked){
                list.getCheckedItems().set(holder.getAdapterPosition(), true);
            } else {
                list.getCheckedItems().set(holder.getAdapterPosition(), false);
            }
            int userListPos = MainActivity.lists.findList(list);
            MainActivity.lists.removeList(userListPos);
            MainActivity.lists.addList(list);
            MainActivity.lists.updateList(MainActivity.lists.lists);
            MainActivity.lists.saveLists();

        });
    }

    /**
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return list.getLength();
    }

    /**
     *
     */
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public final CheckBox checkBox;

        /**
         *
         * @param itemView
         */
        public ViewHolder(View itemView) {
            super(itemView);

            checkBox= (CheckBox) itemView.findViewById(R.id.cbTikki);

        }
    }
}
