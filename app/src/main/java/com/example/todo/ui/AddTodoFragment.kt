package com.example.todo.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.todo.R
import com.example.todo.databinding.DialogFragmentTodoBinding
import com.example.todo.databinding.FragmentAddTodoBinding


class AddTodoFragment : DialogFragment() {

    private lateinit var binding: DialogFragmentTodoBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
//        binding = FragmentAddTodoBinding.inflate(inflater,container,false)
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_fragment_todo, container, false)
        return binding.root

    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.nextBtn.setOnClickListener {
//            val title = binding.tittleTask.text.toString()
//            val descption = binding.description.text.toString()
//
//            if(title.isNotEmpty()&&descption.isNotEmpty()){
//
//            }else{
//                toastMessage("Please Enter some Taks")
//            }
        }

//        binding.close.setOnClickListener {
//            dismiss()
//        }
//    }


//    private  fun toastMessage(message:String){
//        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
//    }
}