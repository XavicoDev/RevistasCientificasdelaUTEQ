package com.example.revistascientficasdelauteq.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.revistascientficasdelauteq.R;
import com.example.revistascientficasdelauteq.modelo.Edicion;
import com.example.revistascientficasdelauteq.modelo.Revista;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdaptadorEdicion extends RecyclerView.Adapter<AdaptadorEdicion.ViewHolder> implements View.OnClickListener {
    private Context mContext ;
    private ArrayList<Edicion> ediciones ;
    private View.OnClickListener listener;

    public AdaptadorEdicion(Context context, ArrayList<Edicion> lista){
        mContext= context;
        ediciones= lista;
    }

    @NonNull
    @Override
    public AdaptadorEdicion.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View item = inflater.inflate(R.layout.list_edicion,parent , false);
        item.setOnClickListener(this);
        return new ViewHolder(item);
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorEdicion.ViewHolder holder, int position) {
        holder.volume.setText("Volumen "+ediciones.get(position).getVolume());
        holder.year.setText("Año : "+ediciones.get(position).getYear());
        holder.doi.setText("Doi : "+ediciones.get(position).getDoi());
        holder.datePublished.setText("Fecha de publicacion : "+ediciones.get(position).getDatePublished());
        holder.number.setText("Numero : "+ediciones.get(position).getNumber());
        Picasso.with(mContext)
                .load(ediciones.get(position).getCover())
                .into(holder.cover);
    }

    @Override
    public void onClick(View view) {
    }


    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView issueId, volume, number, year, datePublished,title, doi;
        ImageView cover;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            volume=itemView.findViewById(R.id.volumen);
            number=itemView.findViewById(R.id.numero);
            year=itemView.findViewById(R.id.anio);
            datePublished=itemView.findViewById(R.id.fecha);
            doi=itemView.findViewById(R.id.doi);
            cover=itemView.findViewById(R.id.portada);
        }
    }
}
