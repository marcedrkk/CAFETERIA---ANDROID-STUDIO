package com.andres.pasaportecafeovalle.view.fragment.superadmin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.andres.pasaportecafeovalle.R;
import com.andres.pasaportecafeovalle.view.adapter.admin.SucursalAdapter;
import com.andres.pasaportecafeovalle.view.modelo.AdminModel;
import com.andres.pasaportecafeovalle.viewmodel.AdminViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FragmentAdminAdmin extends Fragment {

        private View view;
        // Button btnListar, btnCrear, btnEditar, btnEliminar;

        private AdminViewModel adminViewModel;
        // List<SucursalModel> lista = new ArrayList<>();
        private SucursalAdapter adapter;
        private FloatingActionButton fabCrear;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater,
                @Nullable ViewGroup container,
                @Nullable Bundle savedInstanceState) {

            view = inflater.inflate(R.layout.fragment_admin_admin, container, false);

            adminViewModel = new ViewModelProvider(this).get(AdminViewModel.class);

            fabCrear = view.findViewById(R.id.fabCrear);

            String rol = getActivity().getIntent().getStringExtra("rol");
            int Id_sucursal = getActivity().getIntent().getIntExtra("id_sucursal", -1);
            if (rol.equals("super admin")){
                fabCrear.setVisibility(View.VISIBLE);
                listarSucursal();
            } else if (rol.equals("admin")){
                fabCrear.setVisibility(View.GONE);
                listarSucursalPorId(Id_sucursal);
            }
            Toast.makeText(requireContext(), "id: " + Id_sucursal , Toast.LENGTH_SHORT).show();
            // Es mejor llamar a setUpRecyclerView() antes de observar los datos
            setUpRecyclerView();


            fabCrear.setOnClickListener(v -> {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, new FragmentAdminCrearAdmin());
                transaction.addToBackStack(null); // Permite volver con el botón de atrás
                transaction.commit();
            });

            // La observacipn de los datos se configura aqui
            return view;
        }

        private void setUpRecyclerView() {
            RecyclerView recyclerView = view.findViewById(R.id.rvSucursales);
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

            // se inicializa el adapter con una lista vacía, se llenará con el LiveData.
            adapter = new SucursalAdapter(new ArrayList<>(), new SucursalAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(AdminModel sucursalModel) {
                    Toast.makeText(requireContext(), "Sucursal: " + sucursalModel.getNombre(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onEditClick(AdminModel sucursalModel) {
                    // Navegar al fragmento de edición de sucursal ene ste caso
                    FragmentAdminEditarAdmin editFragment = new FragmentAdminEditarAdmin();

                    // Pasar todos los datos de la sucursal al fragmento de edición
                    Bundle bundle = new Bundle();
                    // --- RECORDAR Pasar el ID de la sucursal ---
                    bundle.putInt("sucursal_id", sucursalModel.getId());
                    bundle.putString("sucursal_nombre", sucursalModel.getNombre());
                    bundle.putString("sucursal_direccion", sucursalModel.getDireccion());
                    bundle.putDouble("sucursal_latitud", sucursalModel.getLat());
                    bundle.putDouble("sucursal_longitud", sucursalModel.getLot());
                    bundle.putString("sucursal_horario", sucursalModel.getHorario());
                    bundle.putString("sucursal_estado", sucursalModel.getEstado());
                    editFragment.setArguments(bundle);

                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, editFragment);
                    transaction.addToBackStack(null); // Permite volver atrás
                    transaction.commit();
                }

                // El método onToggleStatusClick se elimina ya que el botón no existe
            });

            recyclerView.setAdapter(adapter);
        }

        private void listarSucursal() {
            // observar los cambios en la lista de sucursales desde el ViewModel
            adminViewModel.listarSucursalLive().observe(getViewLifecycleOwner(), sucursales -> {
                // El nombre 'sucursales' (plural) es un poco más descriptivo
                if (sucursales != null) {
                    // Usar el método 'submitList' del adapter es más eficiente que limpiar y añadir
                    adapter.submitList(sucursales);
                }
            });
        }

        private void listarSucursalPorId(int id) {
            // observar los cambios en la lista de sucursales desde el ViewModel
            adminViewModel.listarSucursalPorId(id).observe(getViewLifecycleOwner(), sucursal -> {
                // El nombre 'sucursales' (plural) es un poco más descriptivo
                if (sucursal != null) {
                    // Usar el método 'submitList' del adapter es más eficiente que limpiar y añadir
                    List<AdminModel> lista = Collections.singletonList(sucursal);
                    adapter.submitList(lista);
                }else {
                    adapter.submitList(Collections.emptyList());
                }
            });
        }
}
