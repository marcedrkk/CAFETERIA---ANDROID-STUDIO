package com.andres.pasaportecafeovalle.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.andres.pasaportecafeovalle.data.local.entities.ResenaEntity;
import com.andres.pasaportecafeovalle.data.repository.ResenaRepository;
import java.util.List;

public class ResenaViewModel extends AndroidViewModel {
    private final ResenaRepository repo;
    public ResenaViewModel(@NonNull Application app) {
        super(app);
        repo = new ResenaRepository(app);
    }
    public void insertar(ResenaEntity r) {
        repo.insertar(r);
    }
    public LiveData<List<ResenaEntity>> obtenerPorSucursal(int idSucursal) {
        return repo.listarPorSucursal(idSucursal);
    }
}
