package com.vitoksmile.reminder.domain.livedata

import androidx.lifecycle.LiveData
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

/**
 * LiveData to collect values from [Flow]
 */
class FlowLiveData<T>(
    private val flow: Flow<T>
) : LiveData<T>(), CoroutineScope {

    private val job = SupervisorJob()
    override val coroutineContext =
        Dispatchers.IO + job + CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        }

    override fun onActive() {
        super.onActive()

        launch {
            flow.collect {
                withContext(Dispatchers.Main) {
                    value = it
                }
            }
        }
    }

    override fun onInactive() {
        super.onInactive()
        if (!hasActiveObservers()) job.cancelChildren()
    }
}