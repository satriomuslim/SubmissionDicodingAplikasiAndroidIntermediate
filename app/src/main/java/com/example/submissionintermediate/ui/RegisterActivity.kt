package com.example.submissionintermediate.ui

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.submissionintermediate.R
import com.example.submissionintermediate.data.data.Data
import com.example.submissionintermediate.databinding.ActivityRegisterBinding
import com.example.submissionintermediate.model.UserModelFactory
import com.example.submissionintermediate.utils.animateVisibility
import com.example.submissionintermediate.viewmodel.RegisterViewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        setupAction()
        setupViewModel()
        playAnimation()
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.imgRegis, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()
    }

    private fun setupViewModel() {
        val factory: UserModelFactory = UserModelFactory.getInstance(this)
        registerViewModel = ViewModelProvider(this, factory)[RegisterViewModel::class.java]
    }

    private fun setupAction() {
        binding.registerButton.setOnClickListener {
            val name = binding.nameEditText.text.toString().trim()
            val email = binding.emailEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString().trim()
            when {
                name.isEmpty() -> {
                    binding.nameEditText.error = resources.getString(R.string.message_validation, "name")
                }
                email.isEmpty() -> {
                    binding.emailEditText.error = resources.getString(R.string.message_validation, "email")
                }
                password.isEmpty() -> {
                    binding.passwordEditText.error = resources.getString(R.string.message_validation, "password")
                }
                else -> {
                    registerViewModel.register(name, email, password).observe(this){ data ->
                        if (data != null){
                            when(data) {
                                is Data.Loading -> {
                                    showLoading(true)
                                }
                                is Data.Success -> {
                                    showLoading(false)
                                    val user = data.data
                                    if (user.error){
                                        Toast.makeText(this@RegisterActivity, user.message, Toast.LENGTH_SHORT).show()
                                    }else{
                                        AlertDialog.Builder(this@RegisterActivity).apply {
                                            setTitle("Yeah!")
                                            setMessage("Your account successfully created!")
                                            setPositiveButton("Next") { _, _ ->
                                                finish()
                                            }
                                            create()
                                            show()
                                        }
                                    }
                                }
                                is Data.Error -> {
                                    showLoading(false)
                                    Toast.makeText(
                                        this,
                                        resources.getString(R.string.signup_error),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    }
                }
            }
        }

        binding.actionLogin.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.apply {
            nameEditText.isEnabled = !isLoading
            emailEditText.isEnabled = !isLoading
            passwordEditText.isEnabled = !isLoading
            registerButton.isEnabled = !isLoading

            if (isLoading) {
                progressBar.animateVisibility(true)
            } else {
                progressBar.animateVisibility(false)
            }
        }
    }
}