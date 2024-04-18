package com.example.attempt4

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class ContactViewModel(
    private val dao: ContactDao
): ViewModel() {

    private val _sortType = MutableStateFlow(SortType.DATE_TODAY)
    private val _contacts = _sortType
        .flatMapLatest { sortType ->
            when(sortType) {
                SortType.DATE_TODAY -> dao.getContactsOrderedByDateToday()
                SortType.TIME_TODAY -> dao.getContactsOrderedByTimeToday()
                SortType.WATER_INTAKE -> dao.getContactsOrderedByWaterIntake()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(ContactState())
    val state = combine(_state, _sortType, _contacts) { state, sortType, contacts ->
        state.copy(
            contacts = contacts,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ContactState())

    fun onEvent(event: ContactEvent) {
        when(event) {
            is ContactEvent.DeleteWater -> {
                viewModelScope.launch {
                    dao.deleteContact(event.contact)
                }
            }
            ContactEvent.HideDialog -> {
                _state.update { it.copy(
                    isAddingWater = false
                ) }
            }
            ContactEvent.SaveContact -> {
                val datetoday = state.value.dateToday
                val timetoday = state.value.timeToday
                val waterintake = state.value.waterIntake

                if(datetoday.isBlank() || timetoday.isBlank() || waterintake.isBlank()) {
                    return
                }

                val contact = Contact(
                    dateToday = datetoday,
                    timeToday = timetoday,
                    waterIntake = waterintake
                )
                viewModelScope.launch {
                    dao.upsertContact(contact)
                }
                _state.update { it.copy(
                    isAddingWater = false,
                    dateToday = "",
                    timeToday = "",
                    waterIntake = ""
                ) }
            }
            is ContactEvent.SetDateToday -> {
                _state.update { it.copy(
                    dateToday = event.dateToday
                ) }
            }
            is ContactEvent.SetTimeToday -> {
                _state.update { it.copy(
                    timeToday = event.timeToday
                ) }
            }
            is ContactEvent.SetWaterIntake -> {
                _state.update { it.copy(
                    waterIntake = event.waterIntake
                ) }
            }
            ContactEvent.ShowDialog -> {
                _state.update { it.copy(
                    isAddingWater = true
                ) }
            }
            is ContactEvent.SortWater -> {
                _sortType.value = event.sortType
            }
        }
    }
}