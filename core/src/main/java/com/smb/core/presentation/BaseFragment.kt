package com.smb.core.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class BaseFragment<S : BaseState, DB : ViewDataBinding, out VM : BaseViewModel<S>>(
    @LayoutRes val layoutResID: Int,
    private val viewModelReference: Int
): Fragment() {

    private var _binding: DB? = null
    abstract val viewModel: VM
    val binding: DB get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutResID, container, false)
        return _binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            setVariable(viewModelReference, viewModel)
        }?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}