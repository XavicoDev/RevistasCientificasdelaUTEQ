package com.example.revistascientficasdelauteq.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.revistascientficasdelauteq.R;
import com.example.revistascientficasdelauteq.modelo.Seccion2;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorSeccion extends  RecyclerView.Adapter<AdaptadorSeccion.ViewHolder> implements View.OnClickListener{
    private Context mContext ;
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private List<Seccion2> seccion2s;
    private View.OnClickListener listener;

    public AdaptadorSeccion(Context context, ArrayList<Seccion2> lista){
        mContext= context;
        seccion2s = lista;
    }
    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }

    @NonNull
    @Override
    public AdaptadorSeccion.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View item = inflater.inflate(R.layout.list_categorias,parent , false);
        //View v = LayoutInflater.from(mcontext).inflate(R.layout.ly_itemsempleo,parent,false);
        item.setOnClickListener(this);
        return new AdaptadorSeccion.ViewHolder(item);
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorSeccion.ViewHolder holder, int position) {
        Seccion2 item = seccion2s.get(position);
        holder.seccion.setText(item.getSection());
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                holder.rvArticulo.getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        layoutManager.setInitialPrefetchItemCount(item.getArticulos().size());

        AdaptadorArticulos adaptadorArticulos = new AdaptadorArticulos(item.getArticulos());
        holder.rvArticulo.setLayoutManager(layoutManager);
        holder.rvArticulo.setAdapter(adaptadorArticulos);
        holder.rvArticulo.setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount() {
        return seccion2s.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView seccion;
        private RecyclerView rvArticulo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            seccion=itemView.findViewById(R.id.tv_item_title);
            rvArticulo=itemView.findViewById(R.id.rv_sub_item);
        }
    }
}
