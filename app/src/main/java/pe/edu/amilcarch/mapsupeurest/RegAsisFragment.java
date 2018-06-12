package pe.edu.amilcarch.mapsupeurest;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class RegAsisFragment extends Fragment {
    Button btnRegistrar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myFragmentView= inflater.inflate(R.layout.fragment_reg_asis, container, false);

        btnRegistrar=myFragmentView.findViewById(R.id.idBtnRegistrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Context context=v.getContext();
                Intent intent=new Intent();
                intent.putExtra("idUsuario", 1);
                intent.setClass(v.getContext(), SimpleScannerActivity.class);
                startActivity(intent);
            }
        });
        return myFragmentView;
    }


}
