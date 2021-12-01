package com.avans.avd.demoretrofit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


private const val TAG = "TodoViewModel"

class TodoViewModel : ViewModel() {

    private val _todoResponse: MutableLiveData<String> = MutableLiveData()

    val todoResponse: LiveData<String> // todo: voor demo String, later List<TodoItem>
        get() = _todoResponse

    init {
        getTodoItems()
    }

    fun getTodoItems() {
        viewModelScope.launch {
            try {
                Log.i(TAG, "getTodoItems: launch started")
                _todoResponse.value = TodoApi.retrofitService.getTodos()
            } catch (e: Exception) {
                _todoResponse.value = e.message.toString()
            }
        }
    }

    fun deleteTodoItem(todoId: Int) {
        viewModelScope.launch {
            try {
                TodoApi.retrofitService.deleteItem(todoId)
                Log.i(TAG, "deleteTodoItem: ${todoId} deleted")
                _todoResponse.value = "deleteTodoItem: ${todoId} deleted"
            } catch (e: Exception) {
                _todoResponse.value = e.message.toString()
            }
        }
    }

    fun postTodoItem(todoItem: TodoItem) {
        viewModelScope.launch {
            try {
                TodoApi.retrofitService.postItem(todoItem)
                Log.i(TAG, "postTodoItem: ${todoItem} posted")
                _todoResponse.value = "postTodoItem: ${todoItem} posted"
            } catch (e: Exception) {
                _todoResponse.value = e.message.toString()
            }
        }
    }

}