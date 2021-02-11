package com.anthonytepach.app.data.interfaces;

import com.anthonytepach.app.data.model.M_Directorio;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ComputerFormsDirectorio {

    @GET("users")
    Call<List<M_Directorio>> getUsers();
}
