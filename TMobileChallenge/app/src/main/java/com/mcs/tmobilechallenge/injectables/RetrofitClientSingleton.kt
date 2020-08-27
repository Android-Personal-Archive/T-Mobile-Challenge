package com.mcs.tmobilechallenge.injectables

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientSingleton {

    private var retrofit : Retrofit? = null
    private const val baseURL = "https://private-8ce77c-tmobiletest.apiary-mock.com/"

    //create a retrofit instance, only if nonexistent.
    val retrofitInstance: Retrofit?
        get()
        {
            if(retrofit == null)
            {
                retrofit = Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return retrofit
        }
}