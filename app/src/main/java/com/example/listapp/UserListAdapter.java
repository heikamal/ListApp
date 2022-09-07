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
 * Käyttäjälistan Adapteri RecyclerView-oliolle. Perii luokan RecyclerView.Adapter<UserListAdapter.ViewHolder>.
 */
public class UserListAdapter extends
        RecyclerView.Adapter<UserListAdapter.ViewHolder>{

    /**
     * Käyttäjälista, mikä on käsittelyssä.
     */
    UserList list;

    /**
     * Parametrillinen alustaja. Asettaa käsiteltäväksi listaksi parametrinaan saamansa käyttäjälistan.
     *
     * @param data Userlist-luokan olio, käyttäjän tekemä lista.
     */
    public UserListAdapter(UserList data){list = data;}

    /**
     * onCreateViewHolder-metodi, joka alustaa Viewholderin ja sen sisältämän viewin.
     *
     * @param parent "vanhempana" toimiva Viewgroup
     * @param viewType Viewin tyyppi kokonaislukuna
     * @return ViewHolder, joka sisältää viewinä listan alkion fragmentin.
     */
    @NonNull
    @Override
    public UserListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_entry, parent, false);
        return new UserListAdapter.ViewHolder(view);
    }

    /**
     * onBindViewHolder-metodi, joka ajetaan adapteria luodessa listan jokaiselle alkiolle. Katsoo
     * käyttäjälistasta joka alkiolle ollaanko niiden valintaruudut merkattu ja merkkaa boksin sen
     * mukaan. Asettaa tässä vaiheessa myös onCheckedChangeListenerin, joka merkkaa valintaruudun
     * ja tallettaa valinnan listaan että myös onLongClickListenerin, joka avaa dialogin kohdan
     * poiston varmennukselle.
     *
     * @param holder Käsiteltävä viewholderin ilmentymä.
     * @param position Alkion järjestysluku kokonaislukuna.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setTag(position);

        holder.checkBox.setText(list.getList().get(position));
        holder.checkBox.setChecked(list.getCheckedItems().get(position));

        holder.checkBox.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                list.getCheckedItems().set(holder.getAdapterPosition(), true);
            } else {
                list.getCheckedItems().set(holder.getAdapterPosition(), false);
            }
            UserListAdapter.this.updateMaster();
        });

        holder.checkBox.setOnLongClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle(R.string.remove_prompt);

            builder.setPositiveButton(R.string.ok, (dialog, which) -> {
                int itemPos = holder.getAdapterPosition();
                list.removeItem(itemPos);
                UserListAdapter.this.notifyItemRemoved(itemPos);
                UserListAdapter.this.updateMaster();
            });

            builder.setNegativeButton(R.string.nay, (dialog, which) -> dialog.cancel());
            builder.show();

            return true;
        });
    }

    /**
     * Metodi palauttamaan listan pituus.
     *
     * @return Käyttäjälistan, ja näin myös RecyclerViewin pituus.
     */
    @Override
    public int getItemCount() {
        return list.getLength();
    }

    /**
     * Metodi käyttäjälistan päivitykselle päälistassa.
     */
    public void updateMaster(){
        int userListPos = MainActivity.lists.findList(list);
        MainActivity.lists.removeList(userListPos);
        MainActivity.lists.addList(list);
        MainActivity.lists.updateList(MainActivity.lists.lists);
        MainActivity.lists.saveLists();
    }

    /**
     * Aliluokka ViewHolderille, perii RecyclerView.ViewHolder-luokan.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder{
        /**
         * Valintaruutu toteutettu CheckBox-oliona
         */
        public final CheckBox checkBox;

        /**
         * Parametrillinen alustaja. Hakee viittauksen valintaruutuun.
         * @param itemView ViewHolderin sisältävä View.
         */
        public ViewHolder(View itemView) {
            super(itemView);

            checkBox= (CheckBox) itemView.findViewById(R.id.cbTikki);

        }
    }
}
