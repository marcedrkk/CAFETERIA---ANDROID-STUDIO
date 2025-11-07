package com.andres.pasaportecafeovalle.data.repository;
import android.app.Application;
import androidx.lifecycle.LiveData;
import com.andres.pasaportecafeovalle.data.local.dao.ResenaDao;
import com.andres.pasaportecafeovalle.data.local.db.DataBase;
import com.andres.pasaportecafeovalle.data.local.entities.ResenaEntity;
import java.util.List;

public class ResenaRepository {

    private final ResenaDao dao;
    public ResenaRepository(Application app) {
        dao = DataBase.getInstance(app).resenaDao();
    }
    public void insertar(ResenaEntity r) {
        DataBase.databaseWriteExecutor.execute(() -> dao.insertarResena(r));
    }
    public LiveData<List<ResenaEntity>> listarPorSucursal(int idSucursal) {
        return dao.obtenerResenasPorSucursal(idSucursal);
    }
}
