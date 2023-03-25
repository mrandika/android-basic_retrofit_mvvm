package space.mrandika.md32studygroup.config

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import space.mrandika.md32studygroup.service.ApiService

class ApiConfig {
    companion object {
        fun getApiService(): ApiService {
            val client = OkHttpClient.Builder()
                .addInterceptor {
                    val request = it.request().newBuilder()
                        .addHeader("Authorization", "Bearer <API_KEY>")

                    it.proceed(request.build())
                }.build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://randomuser.me/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}