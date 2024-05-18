package com.example.lostandfound.presentation.createAdvert

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lostandfound.data.Advert
import com.example.lostandfound.data.MockAdvertRepository
import com.example.lostandfound.data.ProvideRepository
import com.example.lostandfound.domain.use_case.StringResources
import com.example.lostandfound.domain.use_case.ValidateDate
import com.example.lostandfound.domain.use_case.ValidateDescription
import com.example.lostandfound.domain.use_case.ValidateLocation
import com.example.lostandfound.domain.use_case.ValidateName
import com.example.lostandfound.domain.use_case.ValidatePhone
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class CreateAdvertViewModel(
    private val validateName: ValidateName = ValidateName(StringResources()),
    private val validatePhone: ValidatePhone = ValidatePhone(StringResources()),
    private val validateDescription: ValidateDescription = ValidateDescription(StringResources()),
    private val validateDate: ValidateDate = ValidateDate(StringResources()),
    private val validateLocation: ValidateLocation = ValidateLocation(StringResources())
    ): ViewModel() {

    var state by mutableStateOf(AdvertFormState())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    private val repo = ProvideRepository.getRepository()

    fun onEvent(event: AdvertFormEvent) {
        when(event) {
            is AdvertFormEvent.TypeChanged -> {
                state = state.copy(type = event.type)
            }
            is AdvertFormEvent.NameChanged -> {
                state = state.copy(name = event.name)
            }
            is AdvertFormEvent.PhoneChanged -> {
                state = state.copy(phone = event.phone)
            }
            is AdvertFormEvent.DescriptionChanged -> {
                state = state.copy(description = event.description)
            }
            is AdvertFormEvent.DateChanged -> {
                state = state.copy(date = event.date)
            }
            is AdvertFormEvent.LocationChanged -> {
                state = state.copy(location = event.location)
            }
            is AdvertFormEvent.Submit -> {
                SubmitData()
            }
        }
    }

    private fun SubmitData() {
        val nameResult = validateName.execute(state.name)
        val phoneResult = validatePhone.execute(state.phone)
        val descriptionResult = validateDescription.execute(state.description)
        val dateResult = validateDate.execute(state.date)
        val locationResult = validateLocation.execute(state.location)

        val hasError = listOf(
            nameResult,
            phoneResult,
            descriptionResult,
            dateResult,
            locationResult
        ).any{ !it.successful }

        state = state.copy(
            nameError = nameResult.errorMessage,
            phoneError = phoneResult.errorMessage,
            descriptionError = descriptionResult.errorMessage,
            dateError = dateResult.errorMessage,
            locationError = locationResult.errorMessage
        )

        if(hasError) {return}

        val advert = Advert(
            repo.getNextId(),
            state.type,
            state.name,
            state.phone,
            state.description,
            state.date,
            state.location
        )

        repo.addAdvert(advert)

        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    sealed class ValidationEvent {
        object Success: ValidationEvent()
    }
}