package sk.globing.domain.feature.note.model

import java.io.Serializable

data class NoteData(
    var id: Int? = null,
    var title: String,
    var category: String?,
    var content: String?
) : Serializable
