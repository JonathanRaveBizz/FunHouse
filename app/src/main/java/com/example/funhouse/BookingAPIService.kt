package com.example.funhouse

import RecordHead
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

// http://www.JailBase.com/api/1/search/

interface BookingAPIService {
    @GET("search/")
    fun getBookings(
        @Query("source_id") source_id: String? = "az_mcso",
        @Query("first_name") first_name: String? ="",
        @Query("last_name") last_name: String
        ): Single<RecordHead>


}
