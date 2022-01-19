package cat.copernic.jmendezv.coroutinedemo2

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/*
*
* viewModelScope is a predefined CoroutineScope that is included with the
* ViewModel KTX extensions.
* Note that all coroutines must run in a scope.
* A CoroutineScope manages one or more related coroutines.
*
* launch is a function that creates a coroutine and dispatches the execution of its function
* body to the corresponding dispatcher.
*
* Dispatchers.IO indicates that this coroutine should be executed on a thread reserved
* for I/O operations.
*
* Since this coroutine is started with viewModelScope, it is executed in the scope of the ViewModel.
* If the ViewModel is destroyed because the user is navigating away from the screen, viewModelScope
* is automatically cancelled, and all running coroutines are canceled as well.
*
* the coroutine is still needed here, since makeLoginRequest is a suspend function, and all suspend
* functions must be executed in a coroutine.
*
* Flow:
*
* 1.- The app calls the login() function from the View layer on the main thread.
*
* 2.- launch creates a new coroutine to make the network request on the main thread, and the
*     coroutine begins execution.
*
* 3.- Within the coroutine, the call to doLogin() now suspends further execution of the coroutine
*     until the withContext block in doLogin() finishes running.
*
* 4.- Once the withContext block finishes, the coroutine in login() resumes execution on the main
*     thread with the result of the network request.
*
* */
class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    var job: Job? = null

    fun login(user: String, password: String) {
        Log.d(TAG, "login ${Thread.currentThread().name}")
//         job = viewModelScope.launch(Dispatchers.IO) {
        // launch doesn't take a Dispatchers.IO parameter. When you don't pass a Dispatcher to
        // launch, any coroutines launched from viewModelScope run in the main thread.
        job = viewModelScope.launch {
            val resultado: Resultado = try {
                // doLogin() is a suspend function
                doLogin(user, password)
            } catch (e: Exception) {
                Resultado.Error(IllegalAccessException("Wrong credentials"))
            }
            when (resultado) {
                is Resultado.Exitoso -> {}
                else -> {}
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        if (job != null) {
            job?.cancel()
        }
    }


}