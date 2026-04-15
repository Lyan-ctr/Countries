package com.example.countries.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countries.data.CountryApi
import com.example.countries.model.Country
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CountryViewModel : ViewModel() {
    private val api = CountryApi.create()

    // 1. Définition des sources de données (Privées)
    private val _allCountries = MutableStateFlow<List<Country>>(emptyList())
    private val _searchQuery = MutableStateFlow("")
    private val _isLoading = MutableStateFlow(false)

    // 2. Exposition des données pour la vue (Publiques)
    val searchQuery = _searchQuery.asStateFlow()
    val isLoading = _isLoading.asStateFlow()

    // 3. Calcul automatique de la liste filtrée
    // combine() regarde _allCountries ET _searchQuery. Si l'un change, il recalcule.
    val filteredCountries = combine(_allCountries, _searchQuery) { list, query ->
        if (query.isBlank()) {
            list
        } else {
            list.filter { it.name.common.contains(query, ignoreCase = true) }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    // 4. Fonctions d'action
    fun onSearchQueryChanged(newQuery: String) {
        _searchQuery.value = newQuery
    }

    fun loadCountries(type: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _searchQuery.value = "" // On réinitialise la recherche au changement de catégorie
            try {
                val result = if (type == "all") {
                    api.getAllCountries()
                } else {
                    api.getCountriesByRegion("africa")
                }
                _allCountries.value = result
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
}
