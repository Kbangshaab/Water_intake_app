package com.example.attempt4

data class ContactState(
    val contacts: List<Contact> = emptyList(),
    val dateToday: String = "",
    val timeToday: String = "",
    val waterIntake: String = "",
    val isAddingWater: Boolean = false,
    val sortType: SortType = SortType.DATE_TODAY
)

