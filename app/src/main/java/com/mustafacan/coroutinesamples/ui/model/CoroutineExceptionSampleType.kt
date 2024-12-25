package com.mustafacan.coroutinesamples.ui.model

enum class CoroutineExceptionSampleType(val title: String) {
    WITH_JOB("Exception With Job"),
    WITH_SUPERVISORJOB("Exception With SupervisorJob"),
    WITH_SUPERVISOR_SCOPE("Exception With supervisorScope"),
    WITH_COROUTINE_SCOPE("Exception With coroutineScope"),
}