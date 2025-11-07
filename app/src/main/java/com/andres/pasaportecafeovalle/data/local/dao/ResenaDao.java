package com.andres.pasaportecafeovalle.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.andres.pasaportecafeovalle.data.local.entities.ResenaEntity;
import java.util.List;

@Dao
public interface ResenaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertarResena(ResenaEntity resena);
    @Query("SELECT * FROM resenas WHERE id_sucursal = :idSucursal ORDER BY fecha DESC")
    LiveData<List<ResenaEntity>> obtenerResenasPorSucursal(int idSucursal);
}
