package com.example.miconsumo20;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miconsumo20.Room.Plasticos;

import java.util.ArrayList;
import java.util.List;

public class PlasticoAdapter extends  RecyclerView.Adapter<PlasticoAdapter.MyViewHolder>{

    private Context context;
    private List<Plasticos> plasticosList;

    private AdapterListener adapterListener;

    public PlasticoAdapter(Context context, AdapterListener listener) {
        this.context = context;
        plasticosList = new ArrayList<>();
        this.adapterListener = listener;
    }

    public void addPlastico(Plasticos plasticos){
        plasticosList.add(plasticos);
        notifyDataSetChanged();
    }

    public void removePlastico(int position){
        plasticosList.remove(position);
        notifyDataSetChanged();
    }

    //recuperar
    public Plasticos getItem(int position){
        return plasticosList.get(position);
    }

    public void clearData(){
        plasticosList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plastico_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Plasticos plasticos = plasticosList.get(position);
        holder.nombre.setText(plasticos.getNombre());
        holder.descripcion.setText(plasticos.getDescripcion());

        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterListener.OnUpdate(plasticos.getId(),position);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterListener.OnDelete(plasticos.getId(),position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return plasticosList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView nombre, descripcion;
        private ImageView update, delete;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombre);
            descripcion = itemView.findViewById(R.id.descripcion);
            update = itemView.findViewById(R.id.update);
            delete = itemView.findViewById(R.id.delete);
        }
    }

}