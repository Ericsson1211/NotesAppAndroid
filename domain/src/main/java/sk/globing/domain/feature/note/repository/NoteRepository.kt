package sk.globing.domain.feature.note.repository

import kotlinx.coroutines.flow.Flow
import sk.globing.common.extensions.ReturnResult
import sk.globing.domain.feature.note.model.NoteData

interface NoteRepository {
    suspend fun saveNote(noteData: NoteData): ReturnResult<Long>
    suspend fun getNotes(): Flow<ReturnResult<List<NoteData>>>
    suspend fun deleteNote(noteId: Int): ReturnResult<Int>
}
