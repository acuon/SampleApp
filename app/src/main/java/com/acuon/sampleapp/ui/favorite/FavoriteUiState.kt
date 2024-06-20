package com.acuon.sampleapp.ui.favorite

import android.content.Context
import com.acuon.sampleapp.common.ResultOf
import com.acuon.sampleapp.domain.model.UserItem
import com.acuon.sampleapp.R

data class FavoriteUiState(
    private val state: ResultOf<List<UserItem?>?>
) {

    fun isLoading() = state is ResultOf.Loading

    /**
     * Checks if the UI state represents success.
     */
    fun isSuccess() = state is ResultOf.Success

    /**
     * Checks if the UI state represents an error.
     */
    fun isError() = state is ResultOf.Error

    /**
     * Retrieves the error message if the UI state represents an error, or a default message otherwise.
     *
     * @param context The context used to access string resources.
     * @return The error message or a default message.
     */
    fun getErrorMessage(context: Context) = if (state is ResultOf.Error) {
        state.message ?: context.getString(R.string.something_went_wrong)
    } else ""

    /**
     * Checks if the UI state represents an empty result.
     */
    fun isEmpty() = state is ResultOf.Success && state.value.isNullOrEmpty()

    /**
     * Retrieves the data if the UI state represents success, or null otherwise.
     */
    fun getData() = if (state is ResultOf.Success) state.value else null

}