package sk.globing.infrastructure.feature.note.source.local.source

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import sk.globing.common.extensions.ErrorResult
import sk.globing.common.extensions.ReturnResult
import sk.globing.data.feature.note.source.NoteLocalSource
import sk.globing.domain.feature.note.model.NoteData
import sk.globing.infrastructure.feature.note.source.local.dao.NoteDao
import sk.globing.infrastructure.feature.note.source.local.mapper.toDataList
import sk.globing.infrastructure.feature.note.source.local.mapper.toEntity

class NoteLocalSourceImpl(private val noteDao: NoteDao) : NoteLocalSource {

    override suspend fun saveNote(noteData: NoteData): ReturnResult<Long> {
        val result = noteDao.saveNote(noteData.toEntity())
        return if (result > 0) {
            ReturnResult.Success(result)
        } else {
            ReturnResult.Error(ErrorResult("Note with id: ${noteData.id} and title: ${noteData.title} was cannot be saved"))
        }
    }

    override suspend fun getNotes(): Flow<ReturnResult<List<NoteData>>> {
        return noteDao.getNotes().transform {
            emit(
                ReturnResult.Success(it.toDataList().sortedByDescending { noteData -> noteData.id })
            )
        }
    }

    override suspend fun deleteNote(noteId: Int): ReturnResult<Int> {
        val deleteResult = noteDao.deleteNote(noteId)
        return if (deleteResult > 0) {
            ReturnResult.Success(deleteResult)
        } else {
            ReturnResult.Error(ErrorResult("Note with id: $noteId was not saved"))
        }
    }
}
