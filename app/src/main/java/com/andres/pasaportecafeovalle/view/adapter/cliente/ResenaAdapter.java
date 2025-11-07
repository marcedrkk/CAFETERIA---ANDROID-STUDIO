package com.andres.pasaportecafeovalle.view.adapter.cliente;

import android.net.Uri;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.andres.pasaportecafeovalle.R;
import com.andres.pasaportecafeovalle.data.local.entities.ResenaEntity;
import java.util.List;

public class ResenaAdapter extends RecyclerView.Adapter<ResenaAdapter.ReseñaViewHolder> {
    private final List<ResenaEntity> lista;
    public ResenaAdapter(List<ResenaEntity> lista) {
        this.lista = lista;
    }
    @NonNull
    @Override
    public ReseñaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_resenas, parent, false);
        return new ReseñaViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull ReseñaViewHolder holder, int position) {
        ResenaEntity r = lista.get(position);
        holder.tvOpinion.setText(r.getOpinion());
        holder.tvFecha.setText(r.getFecha());
        holder.ratingGeneral.setRating(r.getValoracionGeneral());
        holder.tvDetalle.setText("Atención: " + r.getValoracionAtencion() + " | Comida: " + r.getValoracionComida() + " | Ambiente: " + r.getValoracionAmbiente());
        if (r.getFotoUri() != null) {
            holder.imgFoto.setImageURI(Uri.parse(r.getFotoUri()));
        } else {
            holder.imgFoto.setImageResource(R.drawable.placeholder); // imagen por defecto
        }
    }
    @Override
    public int getItemCount() {
        return lista != null ? lista.size() : 0;
    }
    static class ReseñaViewHolder extends RecyclerView.ViewHolder {
        TextView tvOpinion, tvFecha, tvDetalle;
        RatingBar ratingGeneral;
        ImageView imgFoto;

        public ReseñaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvOpinion = itemView.findViewById(R.id.tvOpinion);
            tvFecha = itemView.findViewById(R.id.tvFecha);
            tvDetalle = itemView.findViewById(R.id.tvDetalle);
            ratingGeneral = itemView.findViewById(R.id.ratingGeneral);
            imgFoto = itemView.findViewById(R.id.imgFoto);
        }
    }
}
