package sk.globing.notesappandroid.app.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import sk.globing.notesappandroid.ui.viewmodel.NoteViewModel

/**
 * Koin module for defining presentation view
 */
val notesAppModule = module {
    viewModel { NoteViewModel(get(), get(), get(), get()) }
}