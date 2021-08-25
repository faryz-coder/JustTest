package com.faryz.testapp.first

import android.app.Application
import android.util.Log.d
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.json.JSONArray
import org.json.JSONException

class UserListViewModel(application: Application) : AndroidViewModel(application) {
    var json: String? = null

    private val _users = MutableLiveData<String>().apply {
        value = "it"
        val inputStream = application.assets.open("data.json")
        json = inputStream.bufferedReader().use{ it.readText() }

        try {
            var jsonArray = JSONArray(json)
        } catch (e: JSONException) {
            d("bomoh", "json error : $e")
        }
    }
    val users : LiveData<String> = _users

}