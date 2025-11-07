package com.andres.pasaportecafeovalle.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.andres.pasaportecafeovalle.data.local.entities.AdminEntity;
import java.util.List;

@Dao
public interface AdminDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertar(AdminEntity admin);
    @Update
    void actualizar(AdminEntity admin);
    @Query("SELECT * FROM admin ORDER BY nombre ASC")
    LiveData<List<AdminEntity>> listar();
    @Query("SELECT * FROM admin WHERE id = :id")
    LiveData<AdminEntity> obtenerPorId(int id);
    @Query("SELECT COUNT(*) FROM admin")
    LiveData<Integer> contar();
}
