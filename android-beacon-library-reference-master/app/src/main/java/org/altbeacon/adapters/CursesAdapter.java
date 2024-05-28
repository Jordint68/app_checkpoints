package org.altbeacon.adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.altbeacon.beaconreference.R;
import org.altbeacon.models.Cursa;

import java.util.List;

public class CursesAdapter extends RecyclerView.Adapter<CursesAdapter.GridViewHolder> {
    private Fragment fragHome;
    private List<Cursa> lCurses;
    private Context mContext;

    public CursesAdapter(Context c, List<Cursa> curses, Fragment frag) {
        this.lCurses = curses;
        this.mContext = c;
        this.fragHome = frag;
    }

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.vista_individual_cursa, parent, false);
        return new GridViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GridViewHolder holder, int position) {
        Cursa cursaActual = lCurses.get(position);
        holder.txvNom.setText(cursaActual.getCurNom());
        holder.txvLloc.setText(cursaActual.getCurLloc());
        holder.txvData.setText(cursaActual.getCurDataInici());
        holder.txvWebsite.setText(cursaActual.getCurWeb());
        holder.txvEsport.setText(cursaActual.getEsport().getEspNom());
        String imgUrl = cursaActual.getCurFoto();
        ImageLoader.getInstance().displayImage(imgUrl, holder.imvFoto);

        holder.btnMostrarDetalls.setOnClickListener(view -> {
            NavController nav = NavHostFragment.findNavController(fragHome);
            Bundle bundle = new Bundle();
            bundle.putSerializable("Cursa", cursaActual);
//            NavDirections n = HomeFragmentDirections.actionNavigationHomeToDetallsFragment2();
            nav.navigate(R.id.action_cursesFragment_to_infoCursaFragment, bundle);


        });
    }

    @Override
    public int getItemCount() {
        return lCurses.size();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder {
        ImageView imvFoto;
        TextView txvNom;
        TextView txvData;
        TextView txvLloc;
        TextView txvWebsite;
        TextView txvEsport;
        Button btnMostrarDetalls;

        public GridViewHolder(@NonNull View vista) {
            super(vista);
            imvFoto = vista.findViewById(R.id.imvFoto);
            txvNom = vista.findViewById(R.id.txvNom);
            txvData = vista.findViewById(R.id.txvData);
            txvLloc = vista.findViewById(R.id.txvLloc);
            txvWebsite = vista.findViewById(R.id.txvWebsite);
            txvEsport = vista.findViewById(R.id.txvEsport);
            btnMostrarDetalls = vista.findViewById(R.id.btnMostrarDetalls);
        }
    }


}
