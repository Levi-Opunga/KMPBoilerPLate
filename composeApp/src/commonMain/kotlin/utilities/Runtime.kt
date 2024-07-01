package utilities

class CallOnceFunction<T>(private val function: () -> T) {
    private var hasBeenCalled = false

    operator fun invoke(): T? {
        return if (!hasBeenCalled) {
            hasBeenCalled = true
            function()
        } else {
            null
        }
    }
}

fun <T> runOnce(function: () -> T): CallOnceFunction<T> {
    return CallOnceFunction(function)
}

