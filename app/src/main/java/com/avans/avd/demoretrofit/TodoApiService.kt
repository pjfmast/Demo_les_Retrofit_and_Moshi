package com.avans.avd.demoretrofit

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.*

private const val BASE_URL =
    "https://jsonplaceholder.typicode.com/"

// For parsing the json result: add a Moshi builder
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    // A converter for strings and both primitives and their boxed types to text/plain bodies.
    .addConverterFactory(ScalarsConverterFactory.create())
//    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

// Here we define how Retrofit interacts with the webservice
// we create 'suspend' fun, so we can call the function from a coroutine scope
interface TodoApiService {
    @GET("todos")
    suspend fun getTodos(): String //With Mosh we can convert to: List<TodoItem>

    @DELETE("todos/{id}")
    suspend fun deleteItem(@Path("id") todoId: Int)

    @POST(value = "todos/")
    suspend fun postItem(@Body todoItem: TodoItem): TodoItem
}

object TodoApi {
    val retrofitService: TodoApiService by lazy {
        retrofit.create(TodoApiService::class.java)
    }
}