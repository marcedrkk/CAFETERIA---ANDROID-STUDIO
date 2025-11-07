package com.andres.pasaportecafeovalle.view.fragment.superadmin;

import com.andres.pasaportecafeovalle.data.local.entities.AdminEntity;
import com.andres.pasaportecafeovalle.view.modelo.AdminModel;

public class AdminMapper {

    public static AdminEntity toEntity(AdminModel m) {
        return new AdminEntity(m.getId(), m.getNombre(), m.getEmail(), m.getClave(), m.getEstado());
    }
    public static AdminModel toModel(AdminEntity e) {
        return new AdminModel(e.getId(), e.getNombre(), e.getEmail(), e.getClave(), e.getEstado());
    }
}
