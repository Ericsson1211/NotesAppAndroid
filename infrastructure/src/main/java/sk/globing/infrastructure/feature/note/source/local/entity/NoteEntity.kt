package sk.globing.infrastructure.feature.note.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import sk.globing.infrastructure.feature.note.source.local.entity.NoteEntity.Companion.NOTE_TABLE_NAME

@Entity(tableName = NOTE_TABLE_NAME, indices = [Index(value = [NoteEntity.NOTE_ID], unique = true)])
data class NoteEntity(

    @ColumnInfo(name = NOTE_TITLE)
    val title: String,
    @ColumnInfo(name = NOTE_CATEGORY)
    val category: String?,
    @ColumnInfo(name = NOTE_CONTENT)
    val content: String?
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = NOTE_ID)
    var id: Int? = null
    companion object {
        const val NOTE_TABLE_NAME = "noteTable"
        const val NOTE_ID = "noteID"
        const val NOTE_TITLE = "noteTitle"
        const val NOTE_CATEGORY = "noteCategory"
        const val NOTE_CONTENT = "noteContent"
    }
}
