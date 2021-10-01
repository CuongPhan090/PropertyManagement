package org.sussanacode.propertymanagement.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import org.sussanacode.propertymanagement.R
import org.sussanacode.propertymanagement.api.ApiClient
import org.sussanacode.propertymanagement.databinding.ActivityProductByUserBinding
import org.sussanacode.propertymanagement.viewmodel.PropertyViewModel
import org.sussanacode.propertymanagement.viewmodel.PropertyViewModelFactory

class PropertyByUserActivity : AppCompatActivity() {
    lateinit var binding: ActivityProductByUserBinding
    lateinit var viewModel: PropertyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_by_user)


        binding.rvProperties.layoutManager = LinearLayoutManager(this)
        initViewModel()
        viewModel.loadPropertyBy("60a2d28b62e1ec001788a646")

    }


    private fun initViewModel() {
        val factory = PropertyViewModelFactory(ApiClient.apiService)
        viewModel = ViewModelProvider(this, factory).get(PropertyViewModel::class.java)

        binding.viewmodel = viewModel


    }
}