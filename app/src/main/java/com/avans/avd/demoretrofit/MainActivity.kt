package com.avans.avd.demoretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.avans.avd.demoretrofit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val model: TodoViewModel by viewModels()
        model.todoResponse.observe(this) {
            binding.result.text = model.todoResponse.value
        }

        binding.get.setOnClickListener {
            model.getTodoItems()
        }

        binding.delete.setOnClickListener {
            model.deleteTodoItem(1)
        }

        // todo: introduce Moshi converter
        binding.post.setOnClickListener {
            val todoItem = TodoItem(userId = 1, title = "Learn Moshi", completed = false)
            model.postTodoItem(todoItem)
        }

    }
}