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
import org.altbeacon.beaconreference.databinding.FragmentInfoCursaBinding;
import org.altbeacon.models.Category;
import org.altbeacon.ui.infoCursaFragment;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.GridViewHolder>{
    private List<Category> lCategories;
    private Context mContext;

    private int idxCategoriaSeleccionada = -1;

    private infoCursaFragment frParent;

    public CategoriesAdapter(Context c, List<Category> lCategories, infoCursaFragment fr) {
        this.lCategories = lCategories;
        mContext = c;
        this.frParent = fr;
    }

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.vista_individual_categoria, parent, false);
        return new GridViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GridViewHolder holder, int position) {
        String ca = lCategories.get(position).getCategoria().getCatNom();

        holder.txvNom.setText(ca);

        holder.itemView.setOnClickListener(view -> {
            if (holder.getAdapterPosition() != this.idxCategoriaSeleccionada) {
                int posicioAnterior = this.idxCategoriaSeleccionada;
                this.idxCategoriaSeleccionada = holder.getAdapterPosition();
                this.notifyItemChanged(idxCategoriaSeleccionada);
                this.notifyItemChanged(posicioAnterior);

                frParent.onCategoriaSelected(lCategories.get(this.idxCategoriaSeleccionada));
            }

        });

        if (position == idxCategoriaSeleccionada) {
            holder.clyCategoria.setBackgroundColor(Color.parseColor("#d98080"));
        } else {
            holder.clyCategoria.setBackgroundTintList(null);
        }
    }


    @Override
    public int getItemCount() {
        return lCategories.size();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder {
        TextView txvNom;
        ConstraintLayout clyCategoria;
        public GridViewHolder(@NonNull View itemView) {
            super(itemView);
            txvNom = itemView.findViewById(R.id.txvNom);
            clyCategoria = itemView.findViewById(R.id.clyCategories);
        }
    }
}
