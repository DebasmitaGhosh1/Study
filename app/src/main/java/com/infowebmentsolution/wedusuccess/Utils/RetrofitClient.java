package com.infowebmentsolution.wedusuccess.Utils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl) {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(new OkHttpClient.Builder()
                            .addInterceptor(interceptor)
                            .connectTimeout(90, TimeUnit.SECONDS)
                            .readTimeout(90, TimeUnit.SECONDS)
                            .writeTimeout(90, TimeUnit.SECONDS)
                            .build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }
}
