package sk.globing.domain.feature.note.usecase

import kotlinx.coroutines.flow.Flow
import sk.globing.common.UseCaseNoParams
import sk.globing.common.extensions.ReturnResult
import sk.globing.domain.feature.note.model.NoteData
import sk.globing.domain.feature.note.repository.NoteRepository

/**
 * Observe all notes from database
 * @return Flow ReturnResult with list of NoteData
 */
class GetNotesUseCase(
    private val noteRepository: NoteRepository
) : UseCaseNoParams<Flow<ReturnResult<List<NoteData>>>>() {

    override suspend fun doWork(): Flow<ReturnResult<List<NoteData>>> =
        noteRepository.getNotes()
}
