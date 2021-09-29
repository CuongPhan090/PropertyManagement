package com.example.propertymanagement.ui

import android.widget.EditText
import androidx.databinding.BindingAdapter

class CommonBinding {

    companion object{

        @JvmStatic
        @BindingAdapter("text_error")
        fun setError(editText: EditText, errorText: String?) {
            editText.error = errorText
        }
    }
}