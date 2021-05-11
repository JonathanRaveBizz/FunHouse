package com.example.funhouse

import RecordHead
import Records
import android.icu.text.AlphabeticIndex
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SearchResultsViewModel : ViewModel() {
    private val disposables : CompositeDisposable = CompositeDisposable()
    private val bookingRepository : BookingRepository = BookingRepository()
    private val recordList : MutableLiveData<List<Records>> = MutableLiveData()

    fun search(firstName: String?, lastName: String)
    {

        disposables.add(
            bookingRepository.getBookingObservable(firstName, lastName)
                .subscribeOn(Schedulers.io())
                .subscribe(this::searchSuccess, this::onError)
        )
    }

    private fun onError(t :Throwable) {
        Log.e("API_FAILURE", "Failed to get Records $t")
    }

    private fun searchSuccess(results : RecordHead) {
        var recordobj = results.records
        recordList.postValue(recordobj)

    }
    // TODO: Implement the ViewModel
}