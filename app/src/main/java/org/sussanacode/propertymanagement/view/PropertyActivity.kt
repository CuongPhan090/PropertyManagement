package org.sussanacode.propertymanagement.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import org.sussanacode.propertymanagement.R
import org.sussanacode.propertymanagement.adapter.PropertyAdapter
import org.sussanacode.propertymanagement.api.ApiClient
import org.sussanacode.propertymanagement.databinding.ActivityPropertyBinding
import org.sussanacode.propertymanagement.viewmodel.PropertyViewModel
import org.sussanacode.propertymanagement.viewmodel.PropertyViewModelFactory

class PropertyActivity : AppCompatActivity() {
    lateinit var binding: ActivityPropertyBinding
    lateinit var viewModel: PropertyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_property)


        binding.rvProperties.layoutManager = LinearLayoutManager(this)
        initViewModel()
        viewModel.loadProperties()

    }


    private fun initViewModel() {
        val factory = PropertyViewModelFactory(ApiClient.apiService)
        viewModel = ViewModelProvider(this, factory).get(PropertyViewModel::class.java)

        binding.viewmodel = viewModel
    }
}