package com.infowebmentsolution.wedusuccess.Utils;

import com.infowebmentsolution.wedusuccess.Response.AllPlanResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestApi {


    @GET("Allplans/index_get")
    Call<AllPlanResponse> subscriptionApi(
    );
}