package org.altbeacon.API;


import org.altbeacon.models.ResponseGetCurses;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {

//    @GET("getCurses/{cursaId}")
//    Call<ResponseGetCurses> getCurses(
////            @Query("cursaID") int id
//            @Path("cursaId") int id
//    );

    @GET("get_all_curses")
    Call<ResponseGetCurses> getAllCurses();



    @POST("storeInscripcio")
    Call postInscripcio(

    );
}
