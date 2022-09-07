package com.example.listapp;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Päälistan adapteri RecyclerView-oliolle.
 */
public class MainListAdapter extends
        RecyclerView.Adapter<MainListAdapter.ViewHolder>{

    /**
     * Päälista, sisältää kaikki käyttäjän listat.
     */
    ArrayList<UserList> lists;

    /**
     * Parametrillinen alustaja. Asettaa päälistan saamansa listan mukaiseksi.
     *
     * @param data Luonnissa tullut päälista.
     */
    public MainListAdapter(ArrayList<UserList> data){lists = data;}

    /**
     * Adapterin luonnissa ajettava metodi. Käyttää layoutinflateria asettaakseen päälistan
     * entry-fragmentin tyylin listan alkioihin ja palauttaa ViewHolderin.
     *
     * @param parent "vanhempana" toimiva ViewGroup.
     * @param viewType View-olion tyyppi kokonaislukuna.
     * @return MainListAdapter-luokan ViewHolder
     */
    @NonNull
    @Override
    public MainListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_list_entry, parent, false);
        return new MainListAdapter.ViewHolder(view);
    }

    /**
     * onBindViewHolder-metodi, joka ajetaan adapteria luodessa. Asettaa jokaisella RecyclerViewin
     * alkiolle näiden indeksiluvun, painalluksien kuuntelijat ja listan esitettävän nimen.
     *
     * @param holder ViewHolder-olio, joka pitää sisällään listan alkoioiden tyylit
     * @param position Listan alkion järjestysluku listalla.
     */
    @Override
    public void onBindViewHolder(@NonNull MainListAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(mainListClickListener);
        holder.itemView.setOnLongClickListener(mainListLongClickListener);

        holder.textView.setText(lists.get(position).getName());
    }

    /**
     * Palauttaa listan koon.
     *
     * @return Päälistan pituus.
     */
    @Override
    public int getItemCount() {
        return lists.size();
    }

    View.OnClickListener mainListClickListener = new View.OnClickListener() {
        /**
         * onClick-metodi, joka hakee valitun käyttäjälistan ja avaa sen näkyviin. Hakee oikean
         * käyttäjälistan tämän indeksiluvun perusteella ja pakkaa bundleen mikä lähetetään
         * NavController-oliolla seuraavalle fragmentille.
         *
         * @param v Painettu listan kohta.
         */
        @Override
        public void onClick(View v) {
            int position = (int) v.getTag();
            Bundle bundle = new Bundle();
            bundle.putSerializable("userList", lists.get(position));
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.userListFragment, bundle);
        }
    };

    View.OnLongClickListener mainListLongClickListener = new View.OnLongClickListener() {
        /**
         * onLongClick-metodi pitkän painalluksen kuuntelijalle. Avaa AlertDialogin kysyäkseen
         * käyttäjältä haluaako tämä poistaa listan mitä painoi. Jos käyttäjä painaa ok-painiketta,
         * metodi poistaa valitun käyttäjälistan ja päivittää pääistan notifyItemRemoved-metodilla.
         *
         * @param v Listan kohta mitä painetaan.
         * @return Totuusarvomuuttuja. Tässä tapauksessa palauttaa aina toden.
         */
        @Override
        public boolean onLongClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle(R.string.remove_prompt);

            builder.setPositiveButton(R.string.ok, (dialog, which) -> {
                int itemPos = (int) v.getTag();
                lists.remove(itemPos);
                notifyItemRemoved(itemPos);

                MainActivity.lists.removeList(itemPos);
                MainActivity.lists.updateList(MainActivity.lists.lists);
                MainActivity.lists.saveLists();
            });

            builder.setNegativeButton(R.string.nay, (dialog, which) -> dialog.cancel());
            builder.show();

            return true;
        }
    };

    /**
     * Aliluokka ViewHolderia vasten. Perii RecyclerView.ViewHolder-luokan.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder{
        /**
         * Holderissa oleva TextView listojen nimien esittämiseen
         */
        public final TextView textView;

        /**
         * Parametrillinen alustaja. Hakee oikean TextView-olion asettaakseen sen muuttujaan.
         *
         * @param itemView
         */
        public ViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.tvListName);
        }
    }
}
