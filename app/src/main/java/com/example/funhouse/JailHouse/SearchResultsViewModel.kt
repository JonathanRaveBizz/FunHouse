package com.example.funhouse.JailHouse

import android.util.Log
import androidx.lifecycle.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class SearchResultsViewModel(private val jailRepository: JailRepository) : ViewModel() {
    private val disposables : CompositeDisposable = CompositeDisposable()
    private val bookingRepository : BookingRepository = BookingRepository()
    private val recordList : MutableLiveData<List<Records>> = MutableLiveData()
    val allJails : LiveData<List<JailObject>> = jailRepository.allJails.asLiveData()

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

    fun insert(jailHouse: JailHouse) =viewModelScope.launch {
//        var jailObject : JailObject = JailObject(
//            state= jailHouse.state,
//            dept_full = jailHouse.name,
//            dept_source =  jailHouse.source_id)
//        jailRepository.insert(jailObject)
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