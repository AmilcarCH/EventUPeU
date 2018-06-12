package pe.edu.amilcarch.servis;

import java.util.List;

import pe.edu.amilcarch.to.AsistenciaTO;
import pe.edu.amilcarch.to.EventoTO;
import pe.edu.amilcarch.to.UsuarioTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface EventoService {

    @GET("EventoUPeU/event/all")
    Call<List<EventoTO>> listarEvento();

    @GET("/EventoUPeU/user/all")
    Call<List<UsuarioTO>> listarUsuario();

    @POST("/EventoUPeU/user/login")
    Call<UsuarioTO>  login(@Body UsuarioTO usuario);

    @GET("/EventoUPeU/asistencia/all")
    Call<List<AsistenciaTO>> listarAsistencia();
}
