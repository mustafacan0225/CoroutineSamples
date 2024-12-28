package com.mustafacan.coroutinesamples.ui.samplelist

import androidx.lifecycle.ViewModel
import com.mustafacan.coroutinesamples.ui.model.CoroutineCancellationSampleType
import com.mustafacan.coroutinesamples.ui.model.CoroutineExceptionSampleType
import com.mustafacan.coroutinesamples.ui.model.ParallelCallAsyncSampleType
import com.mustafacan.coroutinesamples.ui.navigation.NavDestinationItem

class SampleListViewModel : ViewModel() {
    val sampleList = listOf(
        NavDestinationItem.DispatcherSample,
        NavDestinationItem.ParallelCallWithJob,
        NavDestinationItem.ParallelCallWithAsync(
            ParallelCallAsyncSampleType.ASYNC.name,
            ParallelCallAsyncSampleType.ASYNC.title
        ),
        NavDestinationItem.ParallelCallWithAsync(
            ParallelCallAsyncSampleType.ASYNC_WITH_COROUTINE_SCOPE.name,
            ParallelCallAsyncSampleType.ASYNC_WITH_COROUTINE_SCOPE.title
        ),
        NavDestinationItem.WithContext,
        NavDestinationItem.MultipleWithContext,
        NavDestinationItem.CoroutineExceptionHandler(
            CoroutineExceptionSampleType.WITH_JOB.name,
            CoroutineExceptionSampleType.WITH_JOB.title
        ),
        NavDestinationItem.CoroutineExceptionHandler(
            CoroutineExceptionSampleType.WITH_SUPERVISORJOB.name,
            CoroutineExceptionSampleType.WITH_SUPERVISORJOB.title
        ),
        NavDestinationItem.CoroutineExceptionHandler(
            CoroutineExceptionSampleType.WITH_COROUTINE_SCOPE.name,
            CoroutineExceptionSampleType.WITH_COROUTINE_SCOPE.title
        ),
        NavDestinationItem.CoroutineExceptionHandler(
            CoroutineExceptionSampleType.WITH_SUPERVISOR_SCOPE.name,
            CoroutineExceptionSampleType.WITH_SUPERVISOR_SCOPE.title
        ),
        NavDestinationItem.CoroutineCancellationSample(
            CoroutineCancellationSampleType.JOB_CANCELLATION.name,
            CoroutineCancellationSampleType.JOB_CANCELLATION.title
        ),
        NavDestinationItem.CoroutineCancellationSample(
            CoroutineCancellationSampleType.PARENT_JOB_CANCELLATION.name,
            CoroutineCancellationSampleType.PARENT_JOB_CANCELLATION.title
        ),
        NavDestinationItem.CoroutineCancellationSample(
            CoroutineCancellationSampleType.WITHCONTEXT_NONCANCELLABLE.name,
            CoroutineCancellationSampleType.WITHCONTEXT_NONCANCELLABLE.title
        ),
        NavDestinationItem.CoroutineCancellationSample(
            CoroutineCancellationSampleType.INVOKE_ON_COMPLETION.name,
            CoroutineCancellationSampleType.INVOKE_ON_COMPLETION.title
        )
    )
}