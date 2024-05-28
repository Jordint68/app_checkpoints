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
import org.altbeacon.models.Checkpoint;
import org.altbeacon.ui.infoCursaFragment;

import java.util.List;

public class CheckpointsAdapter extends RecyclerView.Adapter<CheckpointsAdapter.GridViewHolder>{
    private List<Checkpoint> lChecks;
    private Context mContext;

    private int idxCheckpointSeleccionat = -1;

    private infoCursaFragment frParent;

    public CheckpointsAdapter(Context c, List<Checkpoint> lChecks, infoCursaFragment fr) {
        this.lChecks = lChecks;
        this.mContext = c;
        this.frParent = fr;
    }

    @NonNull
    @Override
    public CheckpointsAdapter.GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.vista_individual_checkpoint, parent, false);
        return new GridViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckpointsAdapter.GridViewHolder holder, int position) {
        Checkpoint chkActual = lChecks.get(position);

        holder.txvPosicio.setText((position+1) + "-. ");
        holder.txvKms.setText(chkActual.getChkKm() + " kms");

        holder.itemView.setOnClickListener(view -> {
            if (holder.getAdapterPosition() != this.idxCheckpointSeleccionat) {
                int posicioAnterior = this.idxCheckpointSeleccionat;
                this.idxCheckpointSeleccionat = holder.getAdapterPosition();

                this.notifyItemChanged(idxCheckpointSeleccionat);
                this.notifyItemChanged(posicioAnterior);

                frParent.onCheckpointSelected(lChecks.get(this.idxCheckpointSeleccionat));
            }

        });

        if (position == idxCheckpointSeleccionat) {
            holder.clyCheckpoint.setBackgroundColor(Color.parseColor("#d98080"));
        } else {
            holder.clyCheckpoint.setBackgroundTintList(null);
        }
    }

    @Override
    public int getItemCount() {
        return lChecks.size();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder {
        TextView txvPosicio;
        TextView txvKms;
        ConstraintLayout clyCheckpoint;
        public GridViewHolder(@NonNull View vista) {
            super(vista);
            txvPosicio = vista.findViewById(R.id.txvNum);
            txvKms = vista.findViewById(R.id.txvKms);
            clyCheckpoint = vista.findViewById(R.id.clyCheckpoint);
        }
    }
}
