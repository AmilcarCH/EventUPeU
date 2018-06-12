package pe.edu.amilcarch.mapsupeurest;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import pe.edu.amilcarch.servis.EventoService;
import pe.edu.amilcarch.to.EventoTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    public final String TAG=this.getClass().getSimpleName();
    EventoService eventoService;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        retrofit=new Retrofit.Builder()
                .baseUrl("http://192.168.43.13:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        eventoService=retrofit.create(EventoService.class);

        Call<List<EventoTO>> listarEventoTodos=eventoService.listarEvento();
        listarEventoTodos.enqueue(new Callback<List<EventoTO>>() {
            @Override
            public void onResponse(Call<List<EventoTO>> call, Response<List<EventoTO>> response) {
                // mostrarUsuarios(response.body().get(0));
                List<EventoTO> lista=response.body();
                for(EventoTO eventoxx:lista){
                    LatLng sydney = new LatLng(eventoxx.getLatitud(), eventoxx.getLongitud());
                    mMap.addMarker(new MarkerOptions().position(sydney).title(eventoxx.getLugarevento()));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                }
                Log.e(TAG,"Llego.......!"+response.body().size());
            }

            @Override
            public void onFailure(Call<List<EventoTO>> call, Throwable t) {
                Log.e(TAG,"Error al recuperar el Servicio Rest de Usuario!");
            }
        });
    }
}
