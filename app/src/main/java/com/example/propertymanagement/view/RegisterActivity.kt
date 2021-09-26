package com.example.propertymanagement.view

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Patterns
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.propertymanagement.R
import com.example.propertymanagement.databinding.ActivityRegisterBinding
import com.example.propertymanagement.model.mainRepository.RegisterRequestData
import com.example.propertymanagement.viewModel.RegisterActivityViewModel

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    lateinit var viewModel: RegisterActivityViewModel
    lateinit var pd: ProgressDialog

    lateinit var email: String
    lateinit var password: String
    lateinit var type: String
    private var landlordEmail: String? = null
    lateinit var name: String
    lateinit var confirmPassword: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        modifyTextView()
        setupEvents()
        initViewHolders()
        initObservers()
    }

    private fun initViewHolders() {
        viewModel = ViewModelProvider(this).get(RegisterActivityViewModel::class.java)
    }


    private fun initObservers() {
        viewModel.error.observe(this) {
            pd.dismiss()
            Toast.makeText(baseContext, it, Toast.LENGTH_LONG).show()
        }

        viewModel.registerResponse.observe(this) {
            pd.dismiss()
            Toast.makeText(baseContext, "Register successfully, please sign in", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    private fun setupEvents() {
        binding.radioTenant.setOnClickListener{
            binding.etLandlordEmail.visibility = View.VISIBLE
        }

        binding.radioLandlord.setOnClickListener {
            binding.etLandlordEmail.visibility = View.GONE
        }

        binding.btnRegister.setOnClickListener{
                if (!binding.radioTenant.isChecked && !binding.radioLandlord.isChecked) {
                    AlertDialog.Builder(this).apply{
                        setMessage("Please select either landlord or tenant")
                        setPositiveButton("Acknowledge") {
                            dialog, _ -> dialog.dismiss()
                        }
                        create()
                        show()
                    }
                    return@setOnClickListener
                }

                email = binding.etEmailAddress.text.toString()
                password = binding.etPassword.text.toString()
                confirmPassword = binding.etConfirmPassword.text.toString()
                name = binding.etName.text.toString()

                if (binding.radioTenant.isChecked) {
                    type = "tenant"
                    landlordEmail = binding.etLandlordEmail.text.toString()
                } else if (binding.radioLandlord.isChecked) {
                    type = "landlord"
                }

                if(validateInput()) {
                    showProgressBar()
                    val newUser = RegisterRequestData(email, name, password, type, landlordEmail)
                    viewModel.register(newUser)
                }

        }
    }

    private fun validateInput(): Boolean {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.etEmailAddress.error = "Invalid email address"
            return false
        }
        if (password.length < 6) {
            binding.etPassword.error = "Password length must be at least 6 characters"
            return false
        }
        if (password != confirmPassword) {
            binding.etConfirmPassword.error = "Password not matched"
            return false
        }
        if (name.isEmpty()) {
            binding.etName.error = "Invalid name"
            return false
        }
        if (binding.radioTenant.isChecked) {
            if (!Patterns.EMAIL_ADDRESS.matcher(landlordEmail).matches()) {
                binding.etLandlordEmail.error = "Invalid email address"
                return false
            }
        }
        return true
    }
    private fun modifyTextView() {
        val text = binding.tvLogIn.text.toString()
        val spannable = SpannableString(text)
        val clickable = object: ClickableSpan() {
            override fun onClick(p0: View) {
                startActivity(Intent(baseContext, LoginActivity::class.java))
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.DKGRAY
                ds.isUnderlineText = false
            }
        }

        spannable.setSpan(
            clickable,
            text.length - "Log in".length, text.length,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        binding.tvLogIn.text = spannable
        binding.tvLogIn.movementMethod = LinkMovementMethod.getInstance()
        binding.tvLogIn.highlightColor = Color.TRANSPARENT
    }
    private fun showProgressBar() {
        pd = ProgressDialog(this).apply{
            setMessage("Processing ...")
            setCancelable(false)
            show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}