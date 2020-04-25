package com.gzeinnumer.sharedviewmodelfragmentkt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gzeinnumer.sharedviewmodelfragmentkt.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    lateinit var model: SharedVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProvider(activity!!)[SharedVM::class.java]
    }

    private lateinit var binding: FragmentFirstBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnFr1.setOnClickListener { v -> model.select(binding.tvFr1.text.toString()) }
    }
}
