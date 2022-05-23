package sk.globing.notesappandroid.ui.notelist

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import ie.noh2o.washapp.app.core.BaseBindingFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import sk.globing.domain.feature.note.model.NoteData
import sk.globing.notesappandroid.R
import sk.globing.notesappandroid.databinding.FragmentNotelistBinding
import sk.globing.notesappandroid.ui.viewmodel.NoteViewModel

class NoteListFragment: BaseBindingFragment<FragmentNotelistBinding>() {

    private lateinit var notesAdapter : NotesAdapter
    private val viewModel: NoteViewModel by sharedViewModel()

    override fun fragmentsViewBinding() =  FragmentNotelistBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listenUIStates()
        notesAdapter = NotesAdapter(clickListener = {
            findNavController().navigate(NoteListFragmentDirections.toDetailNoteFragment(it))
        }, deleteClickListener =  {
            it?.let {
                viewModel.deleteNote(it)
            } ?: run {
                showErrorDialog()
            }
        })
        binding.recyclerView.adapter = notesAdapter
        setupClickListener()
    }

    private fun setupClickListener() {
        binding.buttonNewNote.setOnClickListener {
            findNavController().navigate(NoteListFragmentDirections.toDetailNoteFragment(null))
        }
        binding.buttonReload.setOnClickListener {
            viewModel.loadNotes()
        }
    }

    /**
     * Observe UI states
     */
    private fun listenUIStates() {
        lifecycleScope.launchWhenResumed {
            viewModel.notesUiState.collect { uiState ->
                when(uiState){
                    is NoteListUIState.DataLoaded -> {
                        binding.buttonReload.visibility = View.GONE
                        binding.progressBar.visibility = View.GONE
                        if(uiState.notes.isEmpty()){
                            binding.textViewNoNotes.visibility = View.VISIBLE
                        } else {
                            binding.textViewNoNotes.visibility = View.GONE
                            onNotesLoaded(uiState.notes)
                        }
                    }
                    is NoteListUIState.Empty -> {
                        binding.textViewNoNotes.visibility = View.GONE
                        binding.buttonReload.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                    }
                    is NoteListUIState.LoadingData -> {
                        binding.textViewNoNotes.visibility = View.GONE
                        binding.buttonReload.visibility = View.GONE
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is NoteListUIState.Error -> {
                        binding.textViewNoNotes.visibility = View.GONE
                        binding.buttonReload.visibility = View.GONE
                        binding.progressBar.visibility = View.GONE
                        showErrorDialog(uiState.message)
                    }
                }
            }
        }
    }

    /**
     * Show error dialog with the message from the parameters or the default if thats empty
     */
    private fun showErrorDialog(message: String? = null) {
        val builder = AlertDialog.Builder(
            requireActivity()
        )
        builder.setTitle("Error loading data")
            .setMessage(message ?: "Please try again later")
            .setNegativeButton(R.string.ok) { dialog, id -> }.create().show()
    }

    private fun onNotesLoaded(notes: List<NoteData>) {
        notesAdapter.setList(notes.toList())
    }
}