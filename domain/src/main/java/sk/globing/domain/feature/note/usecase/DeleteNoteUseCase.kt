package sk.globing.domain.feature.note.usecase

import sk.globing.common.UseCaseReturningResult
import sk.globing.common.extensions.ReturnResult
import sk.globing.domain.feature.note.repository.NoteRepository

/**
 * Deletes single note from the Database
 * @param params: The ID of the which we want to delete
 * @return Return ReturnResult.Success if the operation was successful or ReturnResult.Error if not
 */
class DeleteNoteUseCase(private val noteRepository: NoteRepository) : UseCaseReturningResult<Int, Int>() {

    override suspend fun doWork(params: Int): ReturnResult<Int> = noteRepository.deleteNote(params)
}
