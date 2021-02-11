package com.anthonytepach.app.data.interfaces;

import com.anthonytepach.app.data.model.M_Personal;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ComputerFormsUsers {

    @GET("user/{email}")
    Call<M_Personal> getInfoPersona(@Path("email") String email);


}
