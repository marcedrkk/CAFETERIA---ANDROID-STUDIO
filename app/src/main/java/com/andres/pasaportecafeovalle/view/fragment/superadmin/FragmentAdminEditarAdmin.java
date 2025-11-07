package com.andres.pasaportecafeovalle.view.fragment.superadmin;

import android.os.Bundle;
import android.view.*;
import android.widget.*;
import androidx.annotation.*;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.andres.pasaportecafeovalle.R;
import com.andres.pasaportecafeovalle.data.local.entities.AdminEntity;
import com.andres.pasaportecafeovalle.utils.Utils;
import com.andres.pasaportecafeovalle.viewmodel.AdminViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FragmentAdminEditarAdmin extends Fragment {

    private EditText etNombre, etEmail, etClave;
    private Spinner spEstado;
    private Button btnGuardar;
    private FloatingActionButton fabVolver;
    private AdminViewModel viewModel;
    private int adminId = -1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_admin_crear_admin, container, false);

        etNombre = v.findViewById(R.id.etNombreAdmin);
        etEmail = v.findViewById(R.id.etEmailAdmin);
        etClave = v.findViewById(R.id.etClaveAdmin);
        spEstado = v.findViewById(R.id.spEstado);
        btnGuardar = v.findViewById(R.id.btnCrear);
        fabVolver = v.findViewById(R.id.fabVolver);
        viewModel = new ViewModelProvider(this).get(AdminViewModel.class);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireContext(), R.array.estado_sucursal, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spEstado.setAdapter(adapter);

        btnGuardar.setText("Guardar Cambios");
        fabVolver.setOnClickListener(vv -> requireActivity().onBackPressed());

        Bundle args = getArguments();
        if (args != null) {
            adminId = args.getInt("admin_id", -1);
            etNombre.setText(args.getString("admin_nombre", ""));
            etEmail.setText(args.getString("admin_email", ""));
            etClave.setText(""); // nunca mostrar clave
            establecerEstado(args.getString("admin_estado", ""));
        }

        btnGuardar.setOnClickListener(vv -> {
            if (!validar()) return;
            AdminEntity admin = new AdminEntity(
                    adminId,
                    etNombre.getText().toString().trim(),
                    etEmail.getText().toString().trim(),
                    Utils.hashPassword(etClave.getText().toString().trim()),
                    spEstado.getSelectedItem().toString()
            );
            viewModel.actualizar(admin);
            Toast.makeText(requireContext(), "Administrador actualizado", Toast.LENGTH_SHORT).show();
            requireActivity().onBackPressed();
        });

        return v;
    }

    private boolean validar() {
        if (etNombre.getText().toString().trim().isEmpty()) { etNombre.setError("Requerido"); return false; }
        if (etEmail.getText().toString().trim().isEmpty()) { etEmail.setError("Requerido"); return false; }
        return true;
    }

    private void establecerEstado(String estado) {
        for (int i = 0; i < spEstado.getAdapter().getCount(); i++) {
            if (spEstado.getAdapter().getItem(i).toString().equalsIgnoreCase(estado)) {
                spEstado.setSelection(i); break;
            }
        }
    }
}
