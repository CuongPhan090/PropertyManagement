package com.example.propertymanagement.UI

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
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.propertymanagement.R
import com.example.propertymanagement.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var viewModel: LoginActivityViewModel
    lateinit var pd: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupEvents()
        initViewModels()
        setupObservers()
        modifyTextView()
    }


    private fun setupObservers() {
        viewModel.error.observe(this) {
            pd.dismiss()
            Toast.makeText(baseContext, it, Toast.LENGTH_LONG).show()
        }

        viewModel.loginResponse.observe(this) { response ->
            pd.dismiss()
            response.token?.let{
                // todo - store the user info in the local database
                startActivity(Intent(baseContext, DashboardActivity::class.java))
            }
        }

    }

    private fun initViewModels() {
        viewModel = ViewModelProvider(this).get(LoginActivityViewModel::class.java)
    }

    private fun setupEvents() {
        binding.btnLogIn.setOnClickListener {
            val email = binding.etEmailAddress.text.toString()
            val password = binding.etPassword.text.toString()

            if (validateInput(email, password)) {
                showProgressBar()
                viewModel.login(email, password)
            }
        }

        binding.btnFacebookLogin.setOnClickListener {

        }

        binding.btnGoogleSignIn.setOnClickListener {

        }
    }

    private fun validateInput(email: String, password: String): Boolean {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.etEmailAddress.error = "Invalid email address"
            return false
        }
        if (password.length < 6) {
            binding.etPassword.error = "Invalid password"
            return false
        }
        return true
    }

    private fun modifyTextView() {
        val modForgotPassword = binding.tvForgotPassword.text.toString()
        val forgotPasswordSpannable = SpannableString(modForgotPassword)
        val forgotPasswordClickable = object : ClickableSpan() {
            override fun onClick(p0: View) {
                startActivity(Intent(baseContext, ResetPasswordActivity::class.java))
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.DKGRAY
                ds.isUnderlineText = false
            }
        }

        forgotPasswordSpannable.setSpan(
            forgotPasswordClickable,
            0, binding.tvForgotPassword.length(),
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )

        binding.tvForgotPassword.text = forgotPasswordSpannable
        binding.tvForgotPassword.movementMethod = LinkMovementMethod.getInstance()
        binding.tvForgotPassword.highlightColor = Color.TRANSPARENT


        val modSignUp = binding.tvRegister.text.toString()
        val signUpSpannable = SpannableString(modSignUp)
        val signUpClickable = object : ClickableSpan() {
            override fun onClick(p0: View) {
                startActivity(Intent(baseContext, RegisterActivity::class.java))
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.DKGRAY
                ds.isUnderlineText = false
            }
        }
        signUpSpannable.setSpan(
            signUpClickable,
            24, binding.tvRegister.length(),
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )

        binding.tvRegister.text = signUpSpannable
        binding.tvRegister.movementMethod = LinkMovementMethod.getInstance()
        binding.tvRegister.highlightColor = Color.TRANSPARENT

    }

    private fun showProgressBar() {
        pd = ProgressDialog(this).apply{
            setMessage("Logging in...")
            setCancelable(false)
            show()
        }
    }
}