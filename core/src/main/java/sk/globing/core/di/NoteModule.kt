package sk.globing.core.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import sk.globing.data.feature.note.repository.NoteRepositoryImpl
import sk.globing.data.feature.note.source.NoteLocalSource
import sk.globing.domain.feature.note.repository.NoteRepository
import sk.globing.domain.feature.note.usecase.DeleteNoteUseCase
import sk.globing.domain.feature.note.usecase.GetNotesUseCase
import sk.globing.domain.feature.note.usecase.SaveNoteUseCase
import sk.globing.infrastructure.feature.database.AppDatabase
import sk.globing.infrastructure.feature.note.source.local.source.NoteLocalSourceImpl

val noteModule = module {

    single<NoteRepository> { NoteRepositoryImpl(get()) }

    single<NoteLocalSource> { NoteLocalSourceImpl(get<AppDatabase>().noteDao) }

    single {
        AppDatabase.buildDatabase(androidContext(), AppDatabase.DATABASE_NAME)
    }

    factory { SaveNoteUseCase(get()) }
    factory { GetNotesUseCase(get()) }
    factory { DeleteNoteUseCase(get()) }
}
