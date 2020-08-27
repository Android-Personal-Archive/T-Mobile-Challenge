package com.mcs.tmobilechallenge.interfaces

import com.mcs.tmobilechallenge.pokos.PagePOKO
import retrofit2.Call
import retrofit2.http.GET

private const val endpointURL = "test/home"

interface IGetPageService {
    @GET(endpointURL)
    fun getPage() : Call<PagePOKO>
}