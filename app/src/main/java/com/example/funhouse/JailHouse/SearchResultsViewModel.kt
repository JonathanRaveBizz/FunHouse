package com.example.funhouse.JailHouse

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SearchResultsViewModel : ViewModel() {
    private val disposables : CompositeDisposable = CompositeDisposable()
    private val bookingRepository : BookingRepository = BookingRepository()
    private val recordList : MutableLiveData<List<Records>> = MutableLiveData()

    fun search(firstName: String, lastName: String) : LiveData<List<Records>>
    {
        disposables.add(
            bookingRepository.getBookingObservable(firstName, lastName)
                .subscribeOn(Schedulers.io())
                .subscribe(this::searchSuccess, this::onError)
        )
        return recordList
    }

    fun recent(): LiveData<List<Records>>
    {
        disposables.add(
            bookingRepository.getRecentObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(this::searchSuccess, this::onError)
        )
        return recordList
    }


    private fun onError(t :Throwable) {
        Log.e("API_FAILURE", "Failed to get com.example.funhouse.JailHouse.Records $t")
    }

    private fun searchSuccess(results : RecordHead) {
        var recordobj = results.records
        Log.d("_DEBUG", "Got ${recordobj.size} records")
        recordList.postValue(recordobj)

    }
}