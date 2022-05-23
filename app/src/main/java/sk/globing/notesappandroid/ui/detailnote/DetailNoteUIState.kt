package sk.globing.notesappandroid.ui.detailnote

import sk.globing.domain.feature.note.model.NoteData

/**
 * UIState which represents the Detail screen of the note page
 */
sealed class DetailNoteUIState {
    object AddNewNote: DetailNoteUIState()
    data class ShowExistingNote(val noteData: NoteData): DetailNoteUIState()
    data class Error(val message: String? = null): DetailNoteUIState()
    object SuccessfulSave: DetailNoteUIState()
}