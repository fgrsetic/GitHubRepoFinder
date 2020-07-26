package com.franjo.github.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment<T : ViewDataBinding, VM: ViewModel> : Fragment() {

    protected lateinit var binding: T

    @Inject
    lateinit var modelFactory: ViewModelProvider.Factory

    protected val viewModel: VM by lazy {
        ViewModelProvider(this, modelFactory).get(getViewModel())
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getFragmentView(), container, false)
        // Set the lifecycleOwner so DataBinding can observe LiveData
        binding.lifecycleOwner = this

      //  viewModel = ViewModelProvider(this, modelFactory).get(getViewModel())

        return binding.root
    }

    abstract fun getFragmentView(): Int
    abstract fun getViewModel(): Class<VM>
}