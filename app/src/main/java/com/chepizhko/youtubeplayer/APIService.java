package com.chepizhko.youtubeplayer;


import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {
    @GET("/youtube/v3/search?part=snippet&maxResult=20&order=date&type=video&key=AIzaSyABxoTkKiZcUkrJzV5e0qi4jt0hsrBGDK4")
    Call<JsonObject> callBack (@Query("g")String q);
}
