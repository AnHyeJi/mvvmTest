package com.daou.common.network.exception


import com.and.mvvmtest.network.UnsuccessfulException
import retrofit2.Response

object ErrorMessage {

    /**
     * response.isSuccessful 메소드가 true 이나 response.body 가 null 인 경우 이 예외를 던진다
     *
     * @param response
     * @return response string
     */
    fun <T> makeNullBodyException(response: Response<T>): Throwable {
        return NullBodyException(getNullBodyMessage(response))
    }

    /**
     * response.isSuccessful 메소드가 false 를 리턴하는 경우에 이 예외를 던진다
     *
     * @param response
     * @return response string
     */
    fun <T> makeUnsuccessfulException(response: Response<T>): Throwable {
        return if (response.errorBody() != null) {
            UnsuccessfulException(getErrorBodyMessage(response))
        } else UnsuccessfulException(response.toString())
    }

    private fun bodyString(response: Response<*>): String? {
        try {
            return response.raw().body()!!.string()
        } catch (e: Exception) {
            return null
        }

    }

    private fun errorBodyString(response: Response<*>): String? {
        try {
            return response.errorBody()!!.string()
        } catch (e: Exception) {
            return null
        }

    }

    fun getNullBodyMessage(response: Response<*>): String {
        val bodyString = bodyString(response)
        return response.toString() + if (bodyString != null) "\n" + bodyString else ""
    }

    fun getErrorBodyMessage(response: Response<*>): String {
        val bodyString = errorBodyString(response)
        return response.toString() + if (bodyString != null) "\n" + bodyString else ""
    }

    fun getSimplifiedMessage(response: Response<*>?, t: Throwable): String {
        return if (response != null) {
            getHttpCode(response)
        } else {
            getSimplifiedMessage(t)
        }
    }

    fun getHttpCode(response: Response<*>): String {
        return response.code().toString()
    }

    fun getSimplifiedMessage(t: Throwable?): String {
        return t?.javaClass?.simpleName ?: "UnknownException"
    }

    fun getDetailMessage(t: Throwable?): String {
        if (t == null) {
            return "UnknownException"
        }
        val builder = StringBuilder()
        builder.append(t.javaClass.name).append(" : ").append(t.message)
        val trace = t.stackTrace
        if (trace != null) {
            var count = 0
            for (i in trace.indices) {
                builder.append("\n")
                builder.append(trace[i].toString())
                if (count > 10) {
                    break
                }
                count++
            }
        }
        return builder.toString()
    }
}
