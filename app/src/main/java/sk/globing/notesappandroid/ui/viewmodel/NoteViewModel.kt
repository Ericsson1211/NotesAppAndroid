package sk.globing.notesappandroid.ui.viewmodel

import android.graphics.Region
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import sk.globing.common.extensions.ReturnResult
import sk.globing.domain.feature.category.model.Categories
import sk.globing.domain.feature.category.usecase.GetCategoriesUseCase
import sk.globing.domain.feature.note.model.NoteData
import sk.globing.domain.feature.note.usecase.DeleteNoteUseCase
import sk.globing.domain.feature.note.usecase.GetNotesUseCase
import sk.globing.domain.feature.note.usecase.SaveNoteUseCase
import sk.globing.notesappandroid.ui.detailnote.DetailNoteUIState
import sk.globing.notesappandroid.ui.notelist.NoteListUIState

class NoteViewModel(
    private val saveNoteUseCase: SaveNoteUseCase,
    private val getNotesUseCase: GetNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel(), KoinComponent {

    private val _notesUiState = MutableStateFlow<NoteListUIState>(NoteListUIState.Empty)
    val notesUiState: StateFlow<NoteListUIState> = _notesUiState

    private val _detailNoteUiState = MutableStateFlow<DetailNoteUIState>(DetailNoteUIState.AddNewNote)
    val detailNoteUiState: StateFlow<DetailNoteUIState> = _detailNoteUiState

    private val _categories = MutableStateFlow(Categories(emptyList()))
    val categories: StateFlow<Categories> = _categories

    init {
        loadNotes()
        getCategories()
    }

    /**
     * Load locally saved notes from database and updates the UI
     */
    fun loadNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            updateUIState(NoteListUIState.LoadingData)
            getNotesUseCase().collect { notesResult ->
                if (notesResult.isSuccess()) {
                    notesResult.getOrNull()?.let {
                        updateUIState(NoteListUIState.DataLoaded(it))
                    } ?: updateUIState(NoteListUIState.Error("Data loaded but not notes where returned"))
                } else {
                    if (notesResult.isError()) {
                        updateUIState(NoteListUIState.Error((notesResult as ReturnResult.Error).error.message))
                    } else {
                        updateUIState(NoteListUIState.Error(("Data NOT loaded with no Error message returned")))
                    }
                }
            }

        }
    }

    /**
     * Get Categories list from API
     */
    private fun getCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            val categories = getCategoriesUseCase()
            categories.getOrNull()?.let {
                _categories.emit(it)
            }
        }
    }

    /**
     * Calls delete Note from the database
     */
    fun deleteNote(noteId: Int) = viewModelScope.launch(Dispatchers.IO) {
        val deleteResult = deleteNoteUseCase(noteId)
        if (deleteResult.isError()) {
            updateUIState(NoteListUIState.Error((deleteResult as ReturnResult.Error).error.message))
        } else {
            loadNotes()
        }
    }

    /**
     * Calls save Note from the database, and updates the DetailScreen UI and call a refresh on a database
     */
    fun saveNote(noteData: NoteData) = viewModelScope.launch(Dispatchers.IO) {
        val saveResult = saveNoteUseCase(noteData)
        if (saveResult.isError()) {
            updateDetailUIState(DetailNoteUIState.Error((saveResult as ReturnResult.Error).error.message))
        }else {
            updateDetailUIState(DetailNoteUIState.SuccessfulSave)
            loadNotes()
        }
    }

    /**
     * Updates the Main screen UI state with the notes list
     */
    private fun updateUIState(state: NoteListUIState) {
        viewModelScope.launch {
            _notesUiState.emit(state)
        }
    }
    /**
     * Updates the Detail screen UI state with the details of the notes, its called after save
     */
    fun updateDetailUIState(state: DetailNoteUIState) {
        viewModelScope.launch {
            _detailNoteUiState.emit(state)
        }
    }
}