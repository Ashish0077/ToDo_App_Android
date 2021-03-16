package com.example.todoapp.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.todoapp.data.models.Priority

class SharedViewModel(application: Application): AndroidViewModel(application) {

	fun verifyDataFromUser(title: String, desc: String): Boolean {
		return !(title.isNullOrEmpty() || desc.isNullOrEmpty())
	}

	fun parsePriority(priority: String): Priority {
		return when(priority) {
			"Priority High" -> Priority.High
			"Priority Low" -> Priority.Low
			"Priority Medium" -> Priority.Medium
			else -> Priority.Low
		}
	}
}