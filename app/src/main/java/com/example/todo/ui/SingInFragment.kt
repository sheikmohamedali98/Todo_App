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
import com.example.todo.databinding.FragmentSingInBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth


class SingInFragment : Fragment() {


    private lateinit var binding: FragmentSingInBinding
    private lateinit var  auth: FirebaseAuth
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSingInBinding.inflate(inflater,container,false)

        return binding.root
    }

    private  fun init(view: View){
        navController = Navigation.findNavController(view)
        auth = FirebaseAuth.getInstance()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        if(auth.currentUser!=null){
            navController.navigate(R.id.homeFragment)
        }else {
            binding.nextBtn.setOnClickListener {
                signInOperation()
            }

            binding.textViewSignUp.setOnClickListener {
                navController.navigate(R.id.action_singInFragment_to_signUpFragment)
            }
        }
    }

    private  fun  signInOperation(){
        val email = binding.emailEt.text.toString()
        val password = binding.passEt.text.toString()
        if(checkEditTextField(email,password)){
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(
                OnCompleteListener { task ->
                if(task.isSuccessful){
                    navController.navigate(R.id.action_singInFragment_to_homeFragment)
                }else{
                    toastMessage(task.exception.toString())
                }
                }
            )

        }else{
            toastMessage("Enter Valid Inputs")
        }
    }

    private  fun checkEditTextField(email:String,password:String):Boolean{
        return email.isNotEmpty()&&password.isNotEmpty()
    }

    private  fun toastMessage(message:String){
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

}