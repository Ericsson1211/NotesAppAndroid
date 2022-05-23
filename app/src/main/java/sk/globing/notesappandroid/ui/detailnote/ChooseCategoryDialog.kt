package sk.globing.notesappandroid.ui.detailnote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import sk.globing.notesappandroid.R
import sk.globing.notesappandroid.databinding.SimpleTextChooserDialogFragmentBinding
import sk.globing.notesappandroid.ui.viewmodel.NoteViewModel

class ChooseCategoryDialog(private val clickListener: (String) -> Unit): DialogFragment(R.layout.simple_text_chooser_dialog_fragment) {

    private val viewModel by sharedViewModel<NoteViewModel>()

    private var _binding: SimpleTextChooserDialogFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: SimpleTextAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SimpleTextChooserDialogFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = SimpleTextAdapter {
            clickListener(it)
            this.dismiss()
        }
        binding.recyclerWorkData.adapter = adapter
        adapter.submitList(
            viewModel.categories.value.categoriesList
        )
    }

}