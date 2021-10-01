package org.sussanacode.propertymanagement.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.sussanacode.propertymanagement.R
import org.sussanacode.propertymanagement.databinding.HolderPropertyBinding
import org.sussanacode.propertymanagement.entity.response.Property
import org.sussanacode.propertymanagement.holder.PropertyListHolder

class PropertyAdapter(val properties: List<Property>?) : RecyclerView.Adapter<PropertyListHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyListHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding:HolderPropertyBinding = DataBindingUtil.inflate(layoutInflater, R.layout.holder_property, parent, false)
        return PropertyListHolder(binding)
    }

    override fun onBindViewHolder(holder: PropertyListHolder, position: Int) {
        properties?.let {
            holder.bind(it[position])
            //holder.binding.properties = it[position]
        }

    }


    override fun getItemCount() = properties?.size?:0


}