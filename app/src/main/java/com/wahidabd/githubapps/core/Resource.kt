package com.wahidabd.githubapps.core

data class Resource<out T>(val status: Status, val data: T?, val message: String?){
    companion object{
        fun <T> success(data: T?): Resource<T> = Resource(Status.SUCCESS, data, null)
        fun <T> error(message: String?, data: T?) = Resource(Status.ERROR, data, message)
        fun loading() = Resource(Status.LOADING, null, null)
    }
}