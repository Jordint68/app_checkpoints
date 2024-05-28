package org.altbeacon.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import org.altbeacon.beaconreference.R;
import org.altbeacon.models.Circuit;
import org.altbeacon.selectedListeners.ICircuitSelectedListener;
import org.altbeacon.ui.infoCursaFragment;
import org.altbeacon.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class CircuitsAdapter extends RecyclerView.Adapter<CircuitsAdapter.GridViewHolder> {
    private Utils utils = new Utils();

    private List<Circuit> lCircuits = new ArrayList<>();
    private Context mContext;
    private ICircuitSelectedListener mCircuitListener;

    private infoCursaFragment frParent;

    private int idxCircuitSeleccionat = -1;


    public CircuitsAdapter(Context c, List<Circuit> lc, infoCursaFragment fr) {
        this.mContext = c;
        lCircuits = lc;
        this.frParent = fr;

//        if ((c instanceof ICircuitSelectedListener) == false) {
//            throw new RuntimeException("El context no és un ICircuitSelectedListener");
//        }
//        mCircuitListener = (ICircuitSelectedListener)c;
    }

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.vista_individual_circuit, parent, false);
        return new GridViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GridViewHolder holder, int position) {
        Circuit circuitActual = lCircuits.get(position);

        String s_data_incorrecte  = circuitActual.getCirTempsEstimat();
        String s_data = utils.formatarData(s_data_incorrecte);

        holder.txvNum.setText(circuitActual.getCirNum().toString());
        holder.txvNom.setText(circuitActual.getCirNom().toString());
        holder.txvDistancia.setText(circuitActual.getCirDistancia() + "");
        holder.txvTemps.setText(s_data);
        holder.txvPreu.setText(circuitActual.getCirPreu().toString());

        holder.itemView.setOnClickListener(view -> {
            if (holder.getAdapterPosition() != this.idxCircuitSeleccionat) {
                int posicioAnterior = this.idxCircuitSeleccionat;
                this.idxCircuitSeleccionat = holder.getAdapterPosition();

                this.notifyItemChanged(idxCircuitSeleccionat);
                this.notifyItemChanged(posicioAnterior);

                frParent.onCircuitSelected(lCircuits.get(this.idxCircuitSeleccionat));
            }

        });

        if (position == idxCircuitSeleccionat) {
            holder.clyCircuit.setBackgroundColor(Color.parseColor("#d98080"));
        } else {
            holder.clyCircuit.setBackgroundTintList(null);
        }
    }

    @Override
    public int getItemCount() {
        return  lCircuits.size();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder {
        TextView txvNum;
        TextView txvNom;
        TextView txvDistancia;
        TextView txvTemps;
        TextView txvPreu;

        ConstraintLayout clyCircuit;

        public GridViewHolder(@NonNull View vista) {
            super(vista);
            txvNum = vista.findViewById(R.id.txvNum);
            txvNom = vista.findViewById(R.id.txvNom);
            txvDistancia = vista.findViewById(R.id.txvDistancia);
            txvTemps = vista.findViewById(R.id.txvTemps);
            txvPreu = vista.findViewById(R.id.txvPreu);
            clyCircuit = vista.findViewById(R.id.clyCircuit);
        }
    }
}
