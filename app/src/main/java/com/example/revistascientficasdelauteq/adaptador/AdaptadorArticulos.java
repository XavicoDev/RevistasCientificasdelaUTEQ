package com.example.revistascientficasdelauteq.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.revistascientficasdelauteq.R;
import com.example.revistascientficasdelauteq.modelo.SeccionArticulo;

import java.util.List;

public class AdaptadorArticulos extends RecyclerView.Adapter<AdaptadorArticulos.ViewHolder> {
    private List<SeccionArticulo> seccionArticuloLista;

    AdaptadorArticulos(List<SeccionArticulo> subItemList) {
        this.seccionArticuloLista = subItemList;
    }

    @NonNull
    @Override
    public AdaptadorArticulos.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sublist_articulos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorArticulos.ViewHolder holder, int position) {
        SeccionArticulo subItem = seccionArticuloLista.get(position);
        holder.title.setText(subItem.getTitle());
        holder.descripcion.setText(subItem.getAbstract());
    }

    @Override
    public int getItemCount() {
        return seccionArticuloLista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, descripcion;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.tv_sub_item_title);
            descripcion=itemView.findViewById(R.id.tv_sub_item_resumen);
        }
    }
}
