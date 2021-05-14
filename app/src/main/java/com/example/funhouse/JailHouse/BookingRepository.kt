package com.example.funhouse.JailHouse

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class BookingRepository {
    private val BASE_URL = "http://www.JailBase.com/api/1/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    private val bookingAPIService: BookingAPIService = retrofit.create((BookingAPIService::class.java))

    fun getBookingObservable(first_name: String?, last_name: String): Single<RecordHead> {
        var recordhead = bookingAPIService.getBookings("ga-fcso", first_name, last_name);
        return recordhead
    }

    fun getRecentObservable(): Single<RecordHead> {
        var recordHead = bookingAPIService.getRecent();
        return recordHead
    }
}
