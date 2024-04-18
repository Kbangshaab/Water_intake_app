package com.example.attempt4

sealed interface ContactEvent {
    object SaveContact: ContactEvent
    data class SetDateToday(val dateToday: String): ContactEvent
    data class SetTimeToday(val timeToday: String): ContactEvent
    data class SetWaterIntake(val waterIntake: String): ContactEvent
    object ShowDialog: ContactEvent
    object HideDialog: ContactEvent
    data class SortWater(val sortType: SortType): ContactEvent
    data class DeleteWater(val contact: Contact): ContactEvent
}

