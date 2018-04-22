package com.example.marin.triplicetour.util;

import com.example.marin.triplicetour.model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by marin on 03/04/2018.
 */

public interface CadastroService {

    @POST("WebServicePI/rest/usuario/inserir")
    Call<Void> inserirUsuario(@Body Usuario usuario);

    @GET("WebServicePI/rest/usuario/pegartodos")
    Call<List<Usuario>> pegarTodos();

    @POST("WebServicePI/rest/usuario/atualizar")
    Call<Void> alterarUsuario(@Body Usuario usuario);

    @GET("WebServicePI/rest/usuario/pegarporid/{id}")
    Call<Usuario> pegarPorId(@Path("id") int id);

    @GET("WebServicePI/rest/usuario/deletar/{id}")
    Call<Void> excluirUsuario(@Path("id") int id);

    @GET("WebServicePI/rest/usuario/checarlogin/{login}/{senha}")
    Call<Boolean> logar(@Path("login") String login, @Path("senha") String senha);

    public static final Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create()).build();

}
