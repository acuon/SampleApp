package com.acuon.bindingadapter

import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.acuon.sampleapp.utils.extensions.setupClearButtonWithAction

object BindingAdapters {

    /**
     *binding adapter for TextView to set other data type to the textview
     * @param view - TextView
     * @param text - this can be of any data type(string, int, long, double)
     */
    @JvmStatic
    @BindingAdapter("text")
    fun setText(view: TextView, text: Any?) {
        view.text = text.toString()
    }

    /**
     *binding adapter for EditText for clearing the edittext of texts
     * @param view - EditText
     * @param boolean - depending on this value, I will show the clear button option in edittext
     */
    @JvmStatic
    @BindingAdapter("clearButton")
    fun showClearButton(view: EditText, boolean: Boolean?) {
        if (boolean == true) {
            view.setupClearButtonWithAction()
        }
    }

}