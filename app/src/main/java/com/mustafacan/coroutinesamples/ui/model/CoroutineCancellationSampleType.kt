package com.mustafacan.coroutinesamples.ui.model

enum class CoroutineCancellationSampleType(val title: String) {
    JOB_CANCELLATION("Job Cancellation"),
    PARENT_JOB_CANCELLATION("Parent Job Cancellation"),
    WITHCONTEXT_NONCANCELLABLE("withContext(NonCancellable)"),
    INVOKE_ON_COMPLETION("invokeOnCompletion")
}