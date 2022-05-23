package sk.globing.infrastructure.feature.note.source.local.mapper

import sk.globing.domain.feature.note.model.NoteData
import sk.globing.infrastructure.feature.note.source.local.entity.NoteEntity

fun NoteData.toEntity() = NoteEntity(
    title = title,
    category = category,
    content = content
)

fun NoteEntity.toData() = NoteData(
    id = id,
    title = title,
    category = category,
    content = content
)

fun List<NoteEntity>.toDataList(): List<NoteData> {
    val noteDataList = ArrayList<NoteData>()
    this.forEach {
        noteDataList.add(it.toData())
    }
    return noteDataList
}
