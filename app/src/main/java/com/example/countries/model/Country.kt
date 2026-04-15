package com.example.countries.model

data class Country (
    val name : Name,
    val flags : Flag,
    val capital : List<String>?,
    val population : Long,
    val continents: List<String>?

)
data class Flag (
    val png : String
)
data class Name (
    val common : String
)