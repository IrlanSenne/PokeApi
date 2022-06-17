package com.santander.pokeapi.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.santander.pokeapi.presentation.listener.OnSelectListener

abstract class BaseFragment<viewBinding : ViewBinding, VM: ViewModel> : Fragment() {

    private var _binding: viewBinding? = null
    private lateinit var _onSelectListener: OnSelectListener
    private lateinit var _mViewModel: VM

    protected val binding get() = _binding!!
    protected val onSelectListener get() = _onSelectListener!!
    protected val mViewModel get() = _mViewModel!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeUi()
    }

    protected abstract fun getViewBinding(): viewBinding

    protected abstract fun getViewModel(): VM

    protected abstract fun initializeUi()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getViewBinding()
        _mViewModel = getViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _onSelectListener = activity as OnSelectListener
        return _binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    protected fun setOnBackPressedCallBack(backPressAction: () -> Unit = {}) {

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    backPressAction()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }
}