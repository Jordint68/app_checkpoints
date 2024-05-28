package org.altbeacon.API;


import org.altbeacon.models.ResponseGetCurses;
import org.altbeacon.models.getInscripcions.InscripcionsExample;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {
    @GET("get_all_curses")
    Call<ResponseGetCurses> getAllCurses();

    @GET("get_all_inscripcions")
    Call<InscripcionsExample> getAllInscripcions();

    @POST("store_registre")
    Call<InscripcionsExample> postRegistre(
            @Body String json
    );

}
