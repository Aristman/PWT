package ru.marslab.pocketwordtranslator.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single
import ru.marslab.pocketwordtranslator.data.model.LikeTranslationDB

@Dao
interface LikeDao {

    @Insert(onConflict = REPLACE)
    fun addLikeTranslation(translation: LikeTranslationDB): Completable

    @Query("SELECT * FROM like_translations")
    fun getAllLikes(): Single<List<LikeTranslationDB>>

    @Query("SELECT * FROM like_translations WHERE id=:id")
    fun getLikeWordById(id: Int): Single<LikeTranslationDB>

    @Delete
    fun deleteLike(translation: LikeTranslationDB): Completable

    @Query("DELETE FROM like_translations")
    fun clearLikes(): Completable
}
