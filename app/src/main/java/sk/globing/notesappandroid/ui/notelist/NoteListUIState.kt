package sk.globing.notesappandroid.ui.notelist

import sk.globing.domain.feature.note.model.NoteData

sealed class NoteListUIState {
    object Empty: NoteListUIState()
    object LoadingData: NoteListUIState()
    data class Error(val message: String? = null): NoteListUIState()
    data class DataLoaded(val notes: List<NoteData>): NoteListUIState()
}