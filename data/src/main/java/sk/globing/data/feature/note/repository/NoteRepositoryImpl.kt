package sk.globing.data.feature.note.repository

import sk.globing.data.feature.note.source.NoteLocalSource
import sk.globing.domain.feature.note.model.NoteData
import sk.globing.domain.feature.note.repository.NoteRepository

class NoteRepositoryImpl(private val noteLocalSource: NoteLocalSource) : NoteRepository {

    override suspend fun saveNote(noteData: NoteData) = noteLocalSource.saveNote(noteData)

    override suspend fun getNotes() = noteLocalSource.getNotes()

    override suspend fun deleteNote(noteId: Int) = noteLocalSource.deleteNote(noteId)
}
