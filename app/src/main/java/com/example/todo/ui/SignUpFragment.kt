package com.example.todo.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.todo.R
import com.example.todo.databinding.FragmentSignUpBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth


class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private lateinit var  auth: FirebaseAuth
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)

        binding.nextBtn.setOnClickListener {
            registerForSignUp()
        }

        binding.textViewSignIn.setOnClickListener {
            navController.navigate(R.id.action_signUpFragment_to_singInFragment)
        }
    }
    private  fun init(view: View){
         navController = Navigation.findNavController(view)
        auth = FirebaseAuth.getInstance()
    }
    private fun registerForSignUp(){
        val email = binding.emailEt.text.toString().trim()
        val password = binding.passEt.text.toString().trim()
        val confirmPassword = binding.verifyPassEt.text.toString().trim()
        if(checkEditText(email,password,confirmPassword)){
            if(password == confirmPassword){
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(
                    OnCompleteListener { task ->
                        if(task.isSuccessful){
                            navController.navigate(R.id.action_signUpFragment_to_homeFragment)
                        }else{
                            toastMessage(task.exception.toString())
                        }

                    })
            }else{
                toastMessage("password and confirm password not match")
            }
        }else{
            toastMessage("Enter The required fields")
        }
    }

    private fun checkEditText(email:String,password:String,confirmPassword:String):Boolean{
        return email.isNotEmpty()&&password.isNotEmpty()&&confirmPassword.isNotEmpty()
    }

    private  fun toastMessage(message:String){
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }
}