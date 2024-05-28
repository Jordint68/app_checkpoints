package org.altbeacon.API;


import org.altbeacon.models.ResponseGetCurses;
import org.altbeacon.models.getInscripcions.InscripcionsExample;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIManager {
    // Constant amb la la base url
//    private final String BASE_URL = "https://raw.githubusercontent.com/acalvet2k23/racemanagerjson/main/";
    private final String BASE_URL = "http://10.0.2.2/projecteApp/public/api/";

    private static APIManager mInstance;
    private API mApiService;


    private APIManager(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApiService=retrofit.create(API.class);
    }

    public static synchronized APIManager getInstance(){
        if(mInstance == null){
            mInstance = new APIManager();
        }

        return mInstance;
    }


    public void getCurses(int codi_cursa, Callback<ResponseGetCurses> cb) {
        Call<ResponseGetCurses> call = mApiService.getAllCurses();
        call.enqueue(cb);
    }

    public void getAllCurses(Callback<ResponseGetCurses> cb) {
        Call<ResponseGetCurses> call = mApiService.getAllCurses();
        call.enqueue(cb);
    }

    public void getAllInscripcions(Callback<InscripcionsExample> cb) {
        Call<InscripcionsExample> call = mApiService.getAllInscripcions();
        call.enqueue(cb);
    }

}
