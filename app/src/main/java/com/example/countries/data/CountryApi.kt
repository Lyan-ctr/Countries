package com.example.countries.data

//import com.example.Countries.model.Country
import com.example.countries.model.Country
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryApi {
    @GET("v3.1/all") // Tous les pays
    suspend fun getAllCountries(): List<Country>

    @GET("v3.1/region/{region}") // Par région (ex: africa)
    suspend fun getCountriesByRegion(@Path("region") region: String): List<Country>

    companion object {
        fun create(): CountryApi {
            return Retrofit.Builder()
                .baseUrl("https://restcountries.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CountryApi::class.java)
        }
    }
}
