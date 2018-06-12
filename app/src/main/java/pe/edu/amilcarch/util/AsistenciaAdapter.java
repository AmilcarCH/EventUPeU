package pe.edu.amilcarch.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import pe.edu.amilcarch.mapsupeurest.R;

import pe.edu.amilcarch.to.AsistenciaTO;

public class AsistenciaAdapter extends RecyclerView.Adapter<AsistenciaAdapter.AsistenciaViewHolder> {
    private List<AsistenciaTO> asistencia;

    public static class AsistenciaViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNombres;
        private TextView txtCodigo;
        private TextView txtFecha;

        private AsistenciaTO asistencia;


        public AsistenciaViewHolder(View itemView) {
            super(itemView);
            this.txtNombres=(TextView) itemView.findViewById(R.id.txtNombres);
            this.txtCodigo=(TextView) itemView.findViewById(R.id.txtCodigo);
            this.txtFecha=(TextView) itemView.findViewById(R.id.txtFecha);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Context context=v.getContext();
                    Toast.makeText(context,asistencia.getNombres(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void setAsistencia(AsistenciaTO asistencia){
            this.asistencia=asistencia;
            this.txtNombres.setText(asistencia.getNombres());
            this.txtCodigo.setText(asistencia.getCodigo());
            this.txtFecha.setText(asistencia.getFechahora());
        }

    }


    public AsistenciaAdapter(List<AsistenciaTO> asistencias){
        this.asistencia=asistencias;
    }

    @Override
    public AsistenciaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        View view= LayoutInflater.from(context).inflate(R.layout.lista_asis, parent, false);
        AsistenciaViewHolder viewHolder=new AsistenciaViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AsistenciaViewHolder holder, int position) {
        AsistenciaTO asistenciax=asistencia.get(position);

        holder.setAsistencia(asistenciax);
    }

    @Override
    public int getItemCount() {
        return asistencia.size();
    }



}
