package org.sussanacode.propertymanagement.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import org.sussanacode.propertymanagement.R
import org.sussanacode.propertymanagement.api.ApiClient
import org.sussanacode.propertymanagement.databinding.ActivityAddPropertyBinding
import org.sussanacode.propertymanagement.viewmodel.PropertyViewModel
import org.sussanacode.propertymanagement.viewmodel.PropertyViewModelFactory

class AddPropertyActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddPropertyBinding
    lateinit var viewModel: PropertyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_property)

        setupevents()
        setUpViewModel()
        setUpObservers()

    }

    private fun setupevents() {

        binding.tvaddress.setOnClickListener {
            binding.llAddress.visibility = VISIBLE
        }

        binding.tvpopertydetails.setOnClickListener {
            binding.llAddress.visibility = GONE
            binding.llPropertyDetails.visibility = VISIBLE

        }

        binding.tvmortgageinfo.setOnClickListener {
            binding.llPropertyDetails.visibility = GONE
            binding.llMortgageInfo.visibility = VISIBLE
        }

    }


    private fun setUpObservers() {
        viewModel.addproductResponse.observe(this ){

            it.error?.let { hasError ->
                if(hasError){
                    it?.message?.let { message -> Toast.makeText(baseContext, message, Toast.LENGTH_LONG).show() }

                }
            }
        }
    }

    private fun setUpViewModel() {
        val factory = PropertyViewModelFactory(ApiClient.apiService)
        viewModel = ViewModelProvider(this, factory).get(PropertyViewModel::class.java)
        binding.viewmodel = viewModel
    }
}