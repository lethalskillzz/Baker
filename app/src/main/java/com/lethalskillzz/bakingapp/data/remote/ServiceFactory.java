package com.lethalskillzz.bakingapp.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceFactory {

  public static <T> T createFrom(Class<T> serviceClass, String endpoint) {


      HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
      interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
      OkHttpClient client = new OkHttpClient.Builder()
              .addInterceptor(interceptor)
              .readTimeout(60, TimeUnit.SECONDS)
              .connectTimeout(60, TimeUnit.SECONDS)
              .build();

    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(endpoint)
        .client(client)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();

    return retrofit.create(serviceClass);
  }
}
