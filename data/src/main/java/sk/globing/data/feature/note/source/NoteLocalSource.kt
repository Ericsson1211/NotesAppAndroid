package sk.globing.data.feature.note.source

import kotlinx.coroutines.flow.Flow
import sk.globing.common.extensions.ReturnResult
import sk.globing.domain.feature.note.model.NoteData

interface NoteLocalSource {
    suspend fun saveNote(noteData: NoteData): ReturnResult<Long>
    suspend fun getNotes(): Flow<ReturnResult<List<NoteData>>>
    suspend fun deleteNote(noteId: Int): ReturnResult<Int>
}
