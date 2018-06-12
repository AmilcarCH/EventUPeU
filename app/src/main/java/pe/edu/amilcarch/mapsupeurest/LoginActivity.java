package pe.edu.amilcarch.mapsupeurest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pe.edu.amilcarch.servis.EventoService;
import pe.edu.amilcarch.to.UsuarioTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    Context contex;
    public final String TAG=this.getClass().getSimpleName();
    Retrofit retrofit;
    EventoService eventoService;

    @BindView(R.id.idUser)
    TextView txtUsuario;

    @BindView(R.id.idPassword)
    TextView txtPassword;

//    //prubeas
//    private TextView txtDni, txtUseres, txtNombres, txtApellidos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        contex=getApplication();

    }

    @OnClick(R.id.idLogin)
    public void irPaginaPrincipal(){
        validar(txtUsuario.getText().toString(),txtPassword.getText().toString());
    }

    public void validar(String usuario, String passwprd){
        retrofit=new Retrofit.Builder()
                .baseUrl("http://192.168.43.13:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        eventoService=retrofit.create(EventoService.class);
        UsuarioTO user=new UsuarioTO();
        user.setUsuario(usuario);
        user.setClave(passwprd);
        Call<UsuarioTO> call=eventoService.login(user);
        call.enqueue(new Callback<UsuarioTO>() {
            @Override
            public void onResponse(Call<UsuarioTO> call, Response<UsuarioTO> response) {
                Toast.makeText(LoginActivity.this, "Bienvenido al sistema ", Toast.LENGTH_SHORT).show();
                startActivity(new Intent().setClass(LoginActivity.this,HomeActivity.class));
            }

            @Override
            public void onFailure(Call<UsuarioTO> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"El usuario o la contraseña con incorrectos", Toast.LENGTH_SHORT).show();
                Log.e(TAG, t.toString());
            }
        });

    }



}
