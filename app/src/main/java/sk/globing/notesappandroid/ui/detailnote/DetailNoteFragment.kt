package sk.globing.notesappandroid.ui.detailnote

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ie.noh2o.washapp.app.core.BaseBindingFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import sk.globing.domain.feature.note.model.NoteData
import sk.globing.notesappandroid.R
import sk.globing.notesappandroid.databinding.FragmentDetailnoteBinding
import sk.globing.notesappandroid.ui.viewmodel.NoteViewModel

class DetailNoteFragment : BaseBindingFragment<FragmentDetailnoteBinding>() {

    private val viewModel: NoteViewModel by sharedViewModel()
    private lateinit var currentNote: NoteData
    val args: DetailNoteFragmentArgs by navArgs()
    override fun fragmentsViewBinding() = FragmentDetailnoteBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args.existingNote?.let {
            viewModel.updateDetailUIState(DetailNoteUIState.ShowExistingNote(it))
        } ?: run {
            viewModel.updateDetailUIState(DetailNoteUIState.AddNewNote)
        }
        listenUIStates()
        setOnCLickListeners()

    }

    private fun setOnCLickListeners() {
        binding.floatingButtonSaveNote.setOnClickListener {
            viewModel.saveNote(
                NoteData(
                    id = if (viewModel.detailNoteUiState.value is DetailNoteUIState.ShowExistingNote && ::currentNote.isInitialized) currentNote.id else null,
                    title = binding.editTextNoteTitle.text.toString(),
                    category = binding.editTextNoteCategory.text.toString(),
                    content = binding.editTextNoteContent.text.toString()
                )
            )
        }
        lifecycleScope.launchWhenResumed {
            viewModel.categories.collect {
                if (it.categoriesList.isNotEmpty()) {
                    binding.editTextNoteCategory.setOnClickListener {
                        ChooseCategoryDialog {
                            binding.editTextNoteCategory.setText(it)
                        }.show(childFragmentManager, null)
                    }
                }
            }
        }
    }

    private fun listenUIStates() {
        lifecycleScope.launchWhenResumed {
            viewModel.detailNoteUiState.collect { uiState ->
                when (uiState) {
                    is DetailNoteUIState.ShowExistingNote -> {
                        currentNote = uiState.noteData
                        binding.editTextNoteTitle.setText(currentNote.title)
                        binding.editTextNoteCategory.setText(currentNote.category)
                        binding.editTextNoteContent.setText(currentNote.content)
                    }
                    is DetailNoteUIState.SuccessfulSave -> {
                        findNavController().navigateUp()
                    }
                    is DetailNoteUIState.Error -> {
                        val builder = AlertDialog.Builder(
                            requireActivity()
                        )
                        builder.setTitle("Error loading data")
                            .setMessage(uiState.message)
                            .setNegativeButton(R.string.ok) { dialog, id -> }.create().show()
                    }
                    DetailNoteUIState.AddNewNote -> {

                    }
                }
            }
        }
    }
}