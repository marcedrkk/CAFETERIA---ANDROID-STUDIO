package com.andres.pasaportecafeovalle.view.fragment.cliente.reseñas;

import android.os.Bundle;
import android.view.*;
import androidx.annotation.*;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import com.andres.pasaportecafeovalle.R;
import com.andres.pasaportecafeovalle.view.adapter.cliente.ResenaAdapter;
import com.andres.pasaportecafeovalle.viewmodel.ResenaViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ResenasFragment extends Fragment {

    private ResenaViewModel viewModel;
    private RecyclerView rvResenas;
    private FloatingActionButton flbtnCrear;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cliente_resenas, container, false);

        rvResenas = view.findViewById(R.id.rvReseñas);
        flbtnCrear = view.findViewById(R.id.flbtnCrear);

        viewModel = new ViewModelProvider(this).get(ResenaViewModel.class);

        int sucursalId = 1; // ← debes obtenerlo de la sucursal activa
        viewModel.obtenerPorSucursal(sucursalId).observe(getViewLifecycleOwner(), lista -> {
            // Aquí debes conectar tu adapter
            // rvResenas.setAdapter(new ReseñaAdapter(lista));
        });
        flbtnCrear.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new FragmentClienteCrearResena())
                    .addToBackStack(null)
                    .commit();
        });
        viewModel.obtenerPorSucursal(sucursalId).observe(getViewLifecycleOwner(), lista -> {
            rvResenas.setAdapter(new ResenaAdapter(lista));
        });
        return view;
    }
}
