package com.uriel.tejeiro.parcial2cobrad2.servicio;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.uriel.tejeiro.parcial2cobrad2.modelos.Deuda;
import com.uriel.tejeiro.parcial2cobrad2.modelos.Usuario;

public class API {
    public static final String URL_BASE = "http://urieltejeiro.eu5.org/servicios/";
    public static Retrofit retrofit = null;
    public static Retrofit getAPI(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Usuario.class,new MyDeserializerUsuario());
        gsonBuilder.registerTypeAdapter(Deuda.class,new MyDeserializerDeuda());

        if(retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                    .build();

        }
        return retrofit;
    }

}
