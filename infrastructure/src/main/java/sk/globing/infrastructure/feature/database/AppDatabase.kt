package sk.globing.infrastructure.feature.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteOpenHelper
import sk.globing.infrastructure.feature.note.source.local.dao.NoteDao
import sk.globing.infrastructure.feature.note.source.local.entity.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract val noteDao: NoteDao

    companion object {
        const val DATABASE_NAME = "NoteDatabase"

        /**
         * Creates instance of this DB.
         *
         * @param context Context of application
         * @param openHelperFactory SupportOpenHelper factory
         * @return Instance of this DB
         */
        fun buildDatabase(
            context: Context,
            databaseName: String,
            openHelperFactory: SupportSQLiteOpenHelper.Factory? = null,
            migration: Array<Migration>? = null
        ): AppDatabase {

            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, databaseName
            )
                .openHelperFactory(openHelperFactory)
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    /**
     * Deletes all rows from all the tables that are registered to this database
     */
    fun clearDatabase() {
        this.clearAllTables()
    }
}
