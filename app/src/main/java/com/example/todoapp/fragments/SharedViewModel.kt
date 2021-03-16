package com.example.todoapp.fragments

import android.app.Application
import android.graphics.Color.red
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import com.example.todoapp.R
import com.example.todoapp.data.models.Priority

class SharedViewModel(application: Application): AndroidViewModel(application) {

	val listener: AdapterView.OnItemSelectedListener = object:
			AdapterView.OnItemSelectedListener {
				override fun onItemSelected(
					parent: AdapterView<*>?,
					view: View?,
					position: Int,
					id: Long
				) {
					when(position) {
						0 -> { (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.red))}
						1 -> { (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.yellow))}
						2 -> { (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.green))}
					}
				}

				override fun onNothingSelected(
					parent: AdapterView<*>?
				) {

				}
			}

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