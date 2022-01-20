package cat.copernic.jmendezv.coroutinedemo2

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.lang.Exception
import kotlin.random.Random

sealed class Resultado {
    data class Exitoso(val data: String): Resultado()
    data class Error(val ex: Exception): Resultado()
}

/*
* The suspend keyword is the Kotlin's way to enforce a function to be called from within a
* coroutine.
*
* withContext(Dispatchers.IO) moves the execution of the coroutine to an I/O thread, making our
* calling function main-safe and enabling the UI to update as needed.
*
* A suspending function is simply a function that can be paused and resumed at a later time.
* They can execute a long running operation and wait for it to complete without blocking.
* */
suspend fun doLogin(user: String, password: String): Resultado {
    // thread DefaultDispatcher-worker-1
    return withContext(Dispatchers.Main) {
        Log.d(TAG, "doLogin ${Thread.currentThread().name}")
        delay(10_000)
        if (Random.nextBoolean()) {
            Resultado
                .Exitoso("Logeado")
        } else {
            Resultado
                .Error(IllegalAccessException("El login no fue correcto"))
        }
    }

}

fun doLoginBlock(user: String, password: String): Resultado {
    // thread DefaultDispatcher-worker-1

        Log.d(TAG, "doLogin ${Thread.currentThread().name}")
        Thread.sleep(10_000)
        return if (Random.nextBoolean()) {
            Resultado
                .Exitoso("Logeado")
        } else {
            Resultado
                .Error(IllegalAccessException("El login no fue correcto"))
        }


}



