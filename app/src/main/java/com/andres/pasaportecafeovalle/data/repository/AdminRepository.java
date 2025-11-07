package com.andres.pasaportecafeovalle.data.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.andres.pasaportecafeovalle.data.local.dao.AdminDao;
import com.andres.pasaportecafeovalle.data.local.db.DataBase;
import com.andres.pasaportecafeovalle.data.local.entities.AdminEntity;
import java.util.List;

public class AdminRepository {

    private final AdminDao dao;

    public AdminRepository(Application app) {
        dao = DataBase.getInstance(app).adminDao();
    }
    public void insertar(AdminEntity a) {
        DataBase.databaseWriteExecutor.execute(() -> dao.insertar(a));
    }
    public void actualizar(AdminEntity a) {
        DataBase.databaseWriteExecutor.execute(() -> dao.actualizar(a));
    }
    public LiveData<List<AdminEntity>> listar() {
        return dao.listar();
    }
    public LiveData<AdminEntity> obtenerPorId(int id) {
        return dao.obtenerPorId(id);
    }
    public LiveData<Integer> contar() {
        return dao.contar();
    }
}
