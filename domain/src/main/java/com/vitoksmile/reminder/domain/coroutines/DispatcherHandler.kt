package com.vitoksmile.reminder.domain.coroutines

import com.vitoksmile.reminder.domain.coroutines.DispatcherHandler.BG
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object DispatcherHandler {
    val BG = Dispatchers.IO
}

/**
 * Invoke the [block] in [Dispatchers.IO] dispatcher
 */
suspend fun <T> onBG(block: suspend CoroutineScope.() -> T) = withContext(BG, block)