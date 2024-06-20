package com.acuon.sampleapp.ui.details.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.acuon.sampleapp.common.BaseViewModel
import com.acuon.sampleapp.domain.model.UserItem
import com.acuon.sampleapp.domain.repository.IHomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: IHomeRepository) :
    BaseViewModel() {

    val editMode = ObservableBoolean()

    val userItem = ObservableField<UserItem?>()

    val name = ObservableField<String>()
    val username = ObservableField<String>()
    val phone = ObservableField<String>()
    val email = ObservableField<String>()
    val website = ObservableField<String>()
    val street = ObservableField<String>()
    val suite = ObservableField<String>()
    val city = ObservableField<String>()
    val zipcode = ObservableField<String>()
    val longitude = ObservableField<String>()
    val latitude = ObservableField<String>()
    val companyBs = ObservableField<String>()
    val companyName = ObservableField<String>()
    val companyCatchphrase = ObservableField<String>()

    fun setUpdateButton() {
        editMode.set(!editMode.get())
    }

    fun updateUser() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.updateUser(getUserItem())
            }
        }
    }

    private fun getUserItem(): UserItem {
        return UserItem(
            id = userItem.get()?.id,
            isFavorite = userItem.get()?.isFavorite ?: false,
            name = name.get(),
            username = username.get(),
            phone = phone.get(),
            email = email.get(),
            website = website.get(),
            street = street.get(),
            suite = suite.get(),
            longitude = longitude.get(),
            latitude = latitude.get(),
            city = city.get(),
            zipcode = zipcode.get(),
            companyBs = companyBs.get(),
            companyName = companyName.get(),
            companyCatchPhrase = companyCatchphrase.get()
        )
    }

}