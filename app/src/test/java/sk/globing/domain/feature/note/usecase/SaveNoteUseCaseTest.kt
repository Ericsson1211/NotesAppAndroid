package sk.globing.domain.feature.note.usecase

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test
import sk.globing.common.extensions.ReturnResult
import sk.globing.domain.feature.note.model.NoteData
import sk.globing.domain.feature.note.repository.NoteRepository

class SaveNoteUseCaseTest {

    @Test
    fun `test the save use case returning Error`() {
        runBlocking {
            val noteRepo = mockk<NoteRepository>()
            val useCase = SaveNoteUseCase(noteRepo)
            val invalidNote = NoteData(null, "", null, "")
            val result = useCase(invalidNote)
            assert(result is ReturnResult.Error)
            assert((result as ReturnResult.Error).error.message == "Note cannot be saved, the title or content cannot be empty")
        }
    }

    @Test
    fun `test the save use case successful save`() {
        runBlocking {
            val noteRepo = mockk<NoteRepository>() {
                coEvery { saveNote(any()) } returns ReturnResult.Success(1)
            }
            val useCase = SaveNoteUseCase(noteRepo)
            val invalidNote = NoteData(title = "Title", category = "Category", content = "Content")
            val result = useCase(invalidNote)
            assert(result is ReturnResult.Success)
            assert((result as ReturnResult.Success).data > 0)
        }
    }
}