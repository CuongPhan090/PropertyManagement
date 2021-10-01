package org.sussanacode.propertymanagement.holder

import androidx.recyclerview.widget.RecyclerView
import org.sussanacode.propertymanagement.databinding.HolderPropertyBinding
import org.sussanacode.propertymanagement.entity.response.Property

class PropertyListHolder(val binding: HolderPropertyBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(property: Property){
        binding.properties = property
    }

}