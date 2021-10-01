package org.sussanacode.propertymanagement.viewmodel

import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseMethod
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.sussanacode.propertymanagement.R
import org.sussanacode.propertymanagement.adapter.PropertyAdapter
import org.sussanacode.propertymanagement.entity.response.Property
import java.lang.Exception
import java.lang.NumberFormatException

class CommonBinding {

    companion object{

        @JvmStatic
        fun convertBooleanToString(n: Boolean):String{
            return "$n"
        }


        @JvmStatic
        @InverseMethod("convertBooleanToString")
        fun convertStringToBoolean(str: String):Boolean{
            try{
                return str.toBoolean()
            }catch (e: NumberFormatException){
                e.printStackTrace()
                return false
            }
        }



        @JvmStatic
        @BindingAdapter("remote_source", "place_holder", requireAll = false)

        fun loadPropertyImage(imageView: ImageView, url: String?, placeholder: Drawable?){

            url?.let {

                if(placeholder == null){
                    Picasso.get().load(it).into(imageView)

                }else {
                    try{
                        Picasso.get().load(it).placeholder(placeholder).into(imageView)
                    }catch (e: Exception){
                        e.printStackTrace()
                    }
                }
            }

        }

        @JvmStatic
        @BindingAdapter("properties")
        fun setProperties(recyclerView: RecyclerView, list: List<Property>?) {
            list?.let {
                recyclerView.adapter = PropertyAdapter(it)
            }
        }


//        @JvmStatic
//        @BindingAdapter(value = ["city", "state"], requireAll = false)
//        fun bindtwoproperties(city: String?, state: String?){
//
//            if(city != null && state != null){
//
//
//            }
//
//        }

    }
}