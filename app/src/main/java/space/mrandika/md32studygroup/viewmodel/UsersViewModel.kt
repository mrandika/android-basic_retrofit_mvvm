package space.mrandika.md32studygroup.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import space.mrandika.md32studygroup.config.ApiConfig
import space.mrandika.md32studygroup.model.UserResponse

class UsersViewModel: ViewModel() {
    // Loading
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    // Results
    private val _users = MutableLiveData<UserResponse>()
    val users: LiveData<UserResponse> = _users

    init {
        getUsers()
    }

    private fun getUsers() {
        _isLoading.value = true

        val client = ApiConfig.getApiService().getUsers()

        client.enqueue(object: Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                _isLoading.value = false

                if (response.isSuccessful) {
                    _users.value = response.body()
                } else {
                    Log.e("UVM", "Failure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("UVM", "Failure: ${t.message.toString()}")
            }
        })
    }
}