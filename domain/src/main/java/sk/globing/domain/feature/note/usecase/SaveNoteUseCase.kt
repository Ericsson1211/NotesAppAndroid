package sk.globing.domain.feature.note.usecase

import sk.globing.common.UseCaseReturningResult
import sk.globing.common.extensions.ErrorResult
import sk.globing.common.extensions.ReturnResult
import sk.globing.domain.feature.note.model.NoteData
import sk.globing.domain.feature.note.repository.NoteRepository

/**
 * Saves single note to the Database
 * @param params: The Note object which you want to save
 * @return Return ReturnResult.Success if the operation was successful or ReturnResult.Error if not
 */
class SaveNoteUseCase(private val noteRepository: NoteRepository) : UseCaseReturningResult<NoteData, Long>() {

    override suspend fun doWork(params: NoteData): ReturnResult<Long> {
        return if (params.title.isEmpty() || params.content.isNullOrEmpty()) {
            ReturnResult.Error(ErrorResult(message = "Note cannot be saved, the title or content cannot be empty"))
        } else {
            noteRepository.saveNote(params)
        }
    }
}
