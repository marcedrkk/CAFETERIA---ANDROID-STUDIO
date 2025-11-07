package com.andres.pasaportecafeovalle.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.andres.pasaportecafeovalle.data.local.entities.AdminEntity;
import com.andres.pasaportecafeovalle.data.repository.AdminRepository;
import java.util.List;
public class AdminViewModel extends AndroidViewModel {

    private final AdminRepository repo;
    public AdminViewModel(@NonNull Application app) {
        super(app);
        repo = new AdminRepository(app);
    }
    public void insertar(AdminEntity a) { repo.insertar(a); }
    public void actualizar(AdminEntity a) { repo.actualizar(a); }
    public LiveData<List<AdminEntity>> listar() { return repo.listar(); }
    public LiveData<AdminEntity> obtenerPorId(int id) { return repo.obtenerPorId(id); }
    public LiveData<Integer> contar() { return repo.contar(); }
}
