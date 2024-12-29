#### Coroutine Samples - Jetpack Compose - MVI(MVVM with state management)
<ul>
   <li>Dispatchers</li>
   <li>Parallel Call With Job</li>
   <li>Parallel Call With Async</li>
   <li>Async With coroutineScope</li>
   <li>Exception With Job</li>
   <li>Exception With SupervisorJob</li>
   <li>Exception With supervisorScope</li>
   <li>Exception With coroutineScope</li>
   <li>Job Cancellation</li>
   <li>Parent Job Cancellation</li>
   <li>invokeOnCompletion</li>
   <li>withContext Sample</li>
   <li>Multiple withContext</li>
   <li>withContext(NonCancellable)</li>
</ul>
<b>Note:</b> Since this application focuses on coroutines, the coroutine codes are located in the viewmodel, but in real scenarios they may be located in the data layer or in different classes depending on the architectural structure of the project.
<br><br>
<table>
   <tr>
      <td width="50%">
         <b>Dispatchers:</b> Which dispatcher runs on which thread? <br>
         <ul>
            <li>Show <a target="_blank" href="https://github.com/mustafacan0225/CoroutineSamples/blob/main/app/src/main/java/com/mustafacan/coroutinesamples/ui/samples/dispatchers/DispatcherViewModel.kt">ViewModel Code</a></li>
            <li>Show <a target="_blank" href="https://github.com/mustafacan0225/CoroutineSamples/blob/main/app/src/main/java/com/mustafacan/coroutinesamples/ui/samples/dispatchers/DispatcherScreen.kt">Composable Code</a></li>
         </ul>
      </td>
      <td width="50%">
         <b>Parallel Call With Job</b> <br>
         <ul>
            <li>Show <a target="_blank" href="https://github.com/mustafacan0225/CoroutineSamples/blob/main/app/src/main/java/com/mustafacan/coroutinesamples/ui/samples/parallelcallwithjob/ParallelCallWithJobViewModel.kt">ViewModel Code</a></li>
            <li>Show <a target="_blank" href="https://github.com/mustafacan0225/CoroutineSamples/blob/main/app/src/main/java/com/mustafacan/coroutinesamples/ui/samples/parallelcallwithjob/ParallelCallWithJobScreen.kt">Composable Code</a></li>
         </ul>
      </td>
   </tr>
   <tr>
      <td><img width="75%" src="https://github.com/user-attachments/assets/c0f8eaf0-11f3-4198-a55e-53fe4f059350" ></td>
      <td><img width="75%" src="https://github.com/user-attachments/assets/7dc01a0c-dafe-47ac-a098-bd5b47413b08" ></td>
   </tr>
</table>

<table>
   <tr>
      <td width="50%">
         <b>Parallel Call With Async</b> <br>
         <ul>
            <li>Show <a target="_blank" href="https://github.com/mustafacan0225/CoroutineSamples/blob/main/app/src/main/java/com/mustafacan/coroutinesamples/ui/samples/parallelcallwithasync/ParallelCallWithAsyncViewModel.kt">ViewModel Code</a></li>
            <li>Show <a target="_blank" href="https://github.com/mustafacan0225/CoroutineSamples/blob/main/app/src/main/java/com/mustafacan/coroutinesamples/ui/samples/parallelcallwithasync/ParallelCallWithAsyncScreen.kt">Composable Code</a></li>
         </ul>
      </td>
      <td width="50%">
         <b>Async With coroutineScope</b> <br>
         <ul>
            <li>Show <a target="_blank" href="https://github.com/mustafacan0225/CoroutineSamples/blob/main/app/src/main/java/com/mustafacan/coroutinesamples/ui/samples/parallelcallwithasync/ParallelCallWithAsyncViewModel.kt">ViewModel Code</a></li>
            <li>Show <a target="_blank" href="https://github.com/mustafacan0225/CoroutineSamples/blob/main/app/src/main/java/com/mustafacan/coroutinesamples/ui/samples/parallelcallwithasync/ParallelCallWithAsyncScreen.kt">Composable Code</a></li>
         </ul>
      </td>
   </tr>
   <tr>
      <td><img width="75%" src="https://github.com/user-attachments/assets/84f35dd3-bca1-4073-ab61-f7c7f14e49a6" ></td>
      <td><img width="75%" src="https://github.com/user-attachments/assets/49875e59-59b4-4a4d-8c62-efcdc5b65809" ></td>
   </tr>
</table>

<table>
   <tr>
      <td width="50%">
         <b>Exception With Job</b> <br>Since an error occurred in the child jobs, other ongoing child jobs are cancelled(For example: Dogs Content).
         <ul>
            <li>Show <a target="_blank" href="https://github.com/mustafacan0225/CoroutineSamples/blob/main/app/src/main/java/com/mustafacan/coroutinesamples/ui/samples/exceptionhandler/CoroutineExceptionHandlerViewModel.kt">ViewModel Code</a></li>
            <li>Show <a target="_blank" href="https://github.com/mustafacan0225/CoroutineSamples/blob/main/app/src/main/java/com/mustafacan/coroutinesamples/ui/samples/exceptionhandler/CoroutineExceptionHandlerScreen.kt">Composable Code</a></li>
         </ul>
      </td>
      <td width="50%">
         <b>Exception With SupervisorJob</b> <br>Although there was an error in the child job, the other child job was not cancelled and was completed. Because SupervisorJob was used.
         <ul>
            <li>Show <a target="_blank" href="https://github.com/mustafacan0225/CoroutineSamples/blob/main/app/src/main/java/com/mustafacan/coroutinesamples/ui/samples/exceptionhandler/CoroutineExceptionHandlerViewModel.kt">ViewModel Code</a></li>
            <li>Show <a target="_blank" href="https://github.com/mustafacan0225/CoroutineSamples/blob/main/app/src/main/java/com/mustafacan/coroutinesamples/ui/samples/exceptionhandler/CoroutineExceptionHandlerScreen.kt">Composable Code</a></li>
         </ul>
      </td>
   </tr>
   <tr>
      <td><img width="75%" src="https://github.com/user-attachments/assets/d8db6dd7-6bef-476e-9800-96e287635d2c" ></td>
      <td><img width="75%" src="https://github.com/user-attachments/assets/f80cf31c-3411-417a-a1ea-1704d6a34c21" ></td>
   </tr>
</table>

<table>
   <tr>
      <td width="50%">
         <b>Exception With coroutineScope</b> <br>Since an error occurred in the child jobs, other ongoing child jobs are cancelled before completion(For example: Dogs Content).
         <ul>
            <li>Show <a target="_blank" href="https://github.com/mustafacan0225/CoroutineSamples/blob/main/app/src/main/java/com/mustafacan/coroutinesamples/ui/samples/exceptionhandler/CoroutineExceptionHandlerViewModel.kt">ViewModel Code</a></li>
            <li>Show <a target="_blank" href="https://github.com/mustafacan0225/CoroutineSamples/blob/main/app/src/main/java/com/mustafacan/coroutinesamples/ui/samples/exceptionhandler/CoroutineExceptionHandlerScreen.kt">Composable Code</a></li>
         </ul>
      </td>
      <td width="50%">
         <b>Exception With supervisorScope</b> <br>Although there was an error in the child job, the other child job was not cancelled and was completed. Because supervisorScope was used.
         <ul>
            <li>Show <a target="_blank" href="https://github.com/mustafacan0225/CoroutineSamples/blob/main/app/src/main/java/com/mustafacan/coroutinesamples/ui/samples/exceptionhandler/CoroutineExceptionHandlerViewModel.kt">ViewModel Code</a></li>
            <li>Show <a target="_blank" href="https://github.com/mustafacan0225/CoroutineSamples/blob/main/app/src/main/java/com/mustafacan/coroutinesamples/ui/samples/exceptionhandler/CoroutineExceptionHandlerScreen.kt">Composable Code</a></li>
         </ul>
      </td>
   </tr>
   <tr>
      <td><img width="75%" src="https://github.com/user-attachments/assets/d9ba639f-885d-4752-9ff9-ecb6bbd27397" ></td>
      <td><img width="75%" src="https://github.com/user-attachments/assets/6aaf3bef-dec4-4dda-b648-e2744dcc018a" ></td>
   </tr>
</table>

<table>
   <tr>
      <td width="50%">
         <b>Job Cancellation</b> <br>In this example, the job for cats will be canceled 1.5 seconds after the button is clicked. Since the job for dogs was not canceled, that job will be completed.
         <ul>
            <li>Show <a target="_blank" href="https://github.com/mustafacan0225/CoroutineSamples/blob/main/app/src/main/java/com/mustafacan/coroutinesamples/ui/samples/cancellation/CoroutineCancellationViewModel.kt">ViewModel Code</a></li>
            <li>Show <a target="_blank" href="https://github.com/mustafacan0225/CoroutineSamples/blob/main/app/src/main/java/com/mustafacan/coroutinesamples/ui/samples/cancellation/CoroutineCancellationScreen.kt">Composable Code</a></li>
         </ul>
      </td>
      <td width="50%">
         <b>Parent Job Cancellation</b> <br>In this example, the parent job will be canceled 1.5 seconds after the button is clicked. Since the parent job is canceled, all child jobs are canceled. 
         <ul>
            <li>Show <a target="_blank" href="https://github.com/mustafacan0225/CoroutineSamples/blob/main/app/src/main/java/com/mustafacan/coroutinesamples/ui/samples/cancellation/CoroutineCancellationViewModel.kt">ViewModel Code</a></li>
            <li>Show <a target="_blank" href="https://github.com/mustafacan0225/CoroutineSamples/blob/main/app/src/main/java/com/mustafacan/coroutinesamples/ui/samples/cancellation/CoroutineCancellationScreen.kt">Composable Code</a></li>
         </ul>
      </td>
   </tr>
   <tr>
      <td><img width="75%" src="https://github.com/user-attachments/assets/a91f8ef6-a05e-4600-abc0-9d5b34a55215" ></td>
      <td><img width="75%" src="https://github.com/user-attachments/assets/92ffdc50-e6ff-4ea6-af8d-9d455b568627" ></td>
   </tr>
</table>



<table>
   <tr>
      <td width="50%">
         <b>withContext(NonCancellable)</b> <br> We should use withContext(NonCancellable) for all suspend calls that need to be executed even when a coroutine is in a "Cancelling" state.
         <ul>
            <li>Show <a target="_blank" href="https://github.com/mustafacan0225/CoroutineSamples/blob/main/app/src/main/java/com/mustafacan/coroutinesamples/ui/samples/cancellation/CoroutineCancellationViewModel.kt">ViewModel Code</a></li>
            <li>Show <a target="_blank" href="https://github.com/mustafacan0225/CoroutineSamples/blob/main/app/src/main/java/com/mustafacan/coroutinesamples/ui/samples/cancellation/CoroutineCancellationScreen.kt">Composable Code</a></li>
         </ul>
      </td>
      <td width="50%">
         <b>invokeOnCompletion</b> <br> Another way to handle coroutine cancellation or completion is to use the invokeOnCompletion function from Job, which is used to set a handler to be called when the job reaches a terminal state, namely either "Completed" or "Cancelled".
         <ul>
            <li>Show <a target="_blank" href="https://github.com/mustafacan0225/CoroutineSamples/blob/main/app/src/main/java/com/mustafacan/coroutinesamples/ui/samples/cancellation/CoroutineCancellationViewModel.kt">ViewModel Code</a></li>
            <li>Show <a target="_blank" href="https://github.com/mustafacan0225/CoroutineSamples/blob/main/app/src/main/java/com/mustafacan/coroutinesamples/ui/samples/cancellation/CoroutineCancellationScreen.kt">Composable Code</a></li>
         </ul>
      </td>
   </tr>
   <tr>
      <td><img width="75%" src="https://github.com/user-attachments/assets/500733f0-bf40-4671-b367-815b24f8e31d" ></td>
      <td><img width="75%" src="https://github.com/user-attachments/assets/9d5f5ebf-3bb4-41c5-b8cb-54f2560cd7c6" ></td>
   </tr>
</table>

<table>
   <tr>
      <td width="50%">
         <b>withContext Sample</b> <br>
         <ul>
            <li>Show <a target="_blank" href="https://github.com/mustafacan0225/CoroutineSamples/blob/main/app/src/main/java/com/mustafacan/coroutinesamples/ui/samples/withcontext/WithContextViewModel.kt">ViewModel Code</a></li>
            <li>Show <a target="_blank" href="https://github.com/mustafacan0225/CoroutineSamples/blob/main/app/src/main/java/com/mustafacan/coroutinesamples/ui/samples/withcontext/WithContextScreen.kt">Composable Code</a></li>
         </ul>
      </td>
      <td width="50%">
         <b>Multiple withContext</b> <br>Multiple withContext, runs sequentially.
         <ul>
            <li>Show <a target="_blank" href="https://github.com/mustafacan0225/CoroutineSamples/blob/main/app/src/main/java/com/mustafacan/coroutinesamples/ui/samples/withcontext/WithContextViewModel.kt">ViewModel Code</a></li>
            <li>Show <a target="_blank" href="https://github.com/mustafacan0225/CoroutineSamples/blob/main/app/src/main/java/com/mustafacan/coroutinesamples/ui/samples/withcontext/MultipleWithContextScreen.kt">Composable Code</a></li>
         </ul>
      </td>
   </tr>
   <tr>
      <td><img width="75%" src="https://github.com/user-attachments/assets/8ce8da71-7060-49ff-83bc-0231d336ef1a" ></td>
      <td><img width="75%" src="https://github.com/user-attachments/assets/397a0bc8-7cef-4d06-a46c-0472ad5a93cc" ></td>
   </tr>
</table>




