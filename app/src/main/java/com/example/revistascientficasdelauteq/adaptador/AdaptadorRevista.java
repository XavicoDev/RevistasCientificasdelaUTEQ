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
import com.example.revistascientficasdelauteq.modelo.Revista;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdaptadorRevista extends RecyclerView.Adapter<AdaptadorRevista.ViewHolder> implements View.OnClickListener {
    private Context mContext ;
    private ArrayList<Revista> mData ;
    private View.OnClickListener listener;

    public AdaptadorRevista(Context context, ArrayList<Revista> lista){
        mContext= context;
        mData= lista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View item = inflater.inflate(R.layout.list_revista,parent , false);
        //View v = LayoutInflater.from(mcontext).inflate(R.layout.ly_itemsempleo,parent,false);
        item.setOnClickListener(this);
        //
        return new ViewHolder(item);
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(mData.get(position).getName());
        Picasso.with(mContext)
                .load(mData.get(position).getPortada())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, descripJ;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imgjrn);
            name= itemView.findViewById(R.id.txtJournalN);
            //descripJ= itemView.findViewById(R.id.txtJournalD);
        }
    }
}
