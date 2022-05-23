package sk.globing.infrastructure.feature.note.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import sk.globing.infrastructure.feature.note.source.local.entity.NoteEntity

@Dao
abstract class NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun saveNote(noteEntity: NoteEntity): Long

    @Query("SELECT * FROM ${NoteEntity.NOTE_TABLE_NAME}")
    abstract fun getNotes(): Flow<List<NoteEntity>>

    @Query("DELETE FROM ${NoteEntity.NOTE_TABLE_NAME} WHERE ${NoteEntity.NOTE_ID} = :noteId")
    abstract suspend fun deleteNote(noteId: Int): Int
}
