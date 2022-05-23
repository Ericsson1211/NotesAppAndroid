package ie.noh2o.washapp.app.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

abstract class BaseBindingFragment<T : ViewBinding> : Fragment(), CoroutineScope {

    override val coroutineContext = Dispatchers.Main + SupervisorJob()

    private var _binding: T? = null

    /** This property is for providing ViewBinding for child Fragments and it's
     * only valid between onCreateView and onDestroyView.
     **/
    val binding get() = _binding!!

    abstract fun fragmentsViewBinding(): T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = fragmentsViewBinding()
        return _binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineContext.cancel()
    }
}
