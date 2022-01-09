package ru.marslab.marsbaselibrary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

private typealias Inflater<B> = (LayoutInflater, ViewGroup?, Boolean) -> B

private const val ERROR_INFLATE_BINDING = "Binding is not inflate"

abstract class BaseBindingFragment<VM : ViewModel, B : ViewBinding>(
    private val bindingInflater: Inflater<B>
) : Fragment() {

    protected abstract val fragmentViewModel: VM

    private var _binding: B? = null
    protected val binding: B
        get() = checkNotNull(_binding) { ERROR_INFLATE_BINDING }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater, container, false)
        return _binding?.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
