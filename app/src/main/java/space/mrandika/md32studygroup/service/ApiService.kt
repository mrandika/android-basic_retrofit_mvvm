package space.mrandika.md32studygroup.service

import retrofit2.Call
import retrofit2.http.GET
import space.mrandika.md32studygroup.model.UserResponse

interface ApiService {
    @GET("api")
    fun getUsers(): Call<UserResponse>
}