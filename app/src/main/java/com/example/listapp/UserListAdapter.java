package com.example.listapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

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
            if (isChecked) {
                list.getCheckedItems().set(holder.getAdapterPosition(), true);
            } else {
                list.getCheckedItems().set(holder.getAdapterPosition(), false);
            }
            updateMaster();

        });

        holder.checkBox.setOnLongClickListener(pView -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(pView.getContext());
            builder.setTitle(R.string.remove_prompt);

            builder.setPositiveButton(R.string.ok, (dialog, which) -> {
                int itemPos = holder.getAdapterPosition();
                list.removeItem(itemPos);
                notifyItemRemoved(itemPos);
                updateMaster();
            });

            builder.setNegativeButton(R.string.nay, (dialog, which) -> dialog.cancel());
            builder.show();

            return true;
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

    public void updateMaster(){
        int userListPos = MainActivity.lists.findList(list);
        MainActivity.lists.removeList(userListPos);
        MainActivity.lists.addList(list);
        MainActivity.lists.updateList(MainActivity.lists.lists);
        MainActivity.lists.saveLists();
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
