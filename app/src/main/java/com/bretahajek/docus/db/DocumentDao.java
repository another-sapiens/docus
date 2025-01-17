package com.bretahajek.docus.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DocumentDao {
    @Query("SELECT * FROM document")
    LiveData<List<Document>> getAll();

    @Query("SELECT * FROM document WHERE id IN (:documentIds)")
    LiveData<List<Document>> loadAllByIds(int[] documentIds);

    @Query("SELECT * FROM document WHERE name LIKE :name LIMIT 1")
    Document findByName(String name);

    @Query("SELECT * FROM document WHERE name LIKE '%' || :query || '%'")
    LiveData<List<Document>> searchAll(String query);

    @Query("SELECT d.id, d.name, d.folder, d.page_count, d.creation_date FROM document AS d " +
            "INNER JOIN ( " +
            "SELECT document_id FROM document_tag_join WHERE tag_id IN (:tagIds) " +
            "GROUP BY document_id HAVING COUNT(*) > :minMatchCount - 1) AS j " +
            "ON j.document_id = id")
    LiveData<List<Document>> getAllWithTags(int[] tagIds, int minMatchCount);

    @Query("SELECT d.id, d.name, d.folder, d.page_count, d.creation_date FROM document AS d " +
            "INNER JOIN ( " +
            "SELECT document_id FROM document_tag_join WHERE tag_id IN (:tagIds) " +
            "GROUP BY document_id HAVING COUNT(*) > :minMatchCount - 1) AS j " +
            "ON j.document_id = id " +
            "WHERE d.name LIKE '%' || :query || '%'")
    LiveData<List<Document>> searchAllWithTags(String query, int[] tagIds, int minMatchCount);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Document... documents);

    @Delete
    void delete(Document document);

    @Update
    void update(Document document);
}
