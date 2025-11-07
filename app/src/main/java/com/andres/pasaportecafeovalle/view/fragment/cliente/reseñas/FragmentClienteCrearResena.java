package com.andres.pasaportecafeovalle.view.fragment.cliente.reseñas;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.*;
import android.widget.*;
import androidx.annotation.*;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.andres.pasaportecafeovalle.R;
import com.andres.pasaportecafeovalle.data.local.entities.ResenaEntity;
import com.andres.pasaportecafeovalle.utils.Utils;
import com.andres.pasaportecafeovalle.viewmodel.ResenaViewModel;

public class FragmentClienteCrearResena extends Fragment {

    private static final int REQUEST_CAMERA = 100;
    private ImageView imgFoto;
    private Uri fotoUri;
    private ResenaViewModel viewModel;

    private RatingBar ratingGeneral, ratingAtencion, ratingComida, ratingAmbiente;
    private EditText etOpinion;
    private Button btnGuardar, btnTomarFoto;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cliente_crear_resena, container, false);

        viewModel = new ViewModelProvider(this).get(ResenaViewModel.class);

        ratingGeneral = view.findViewById(R.id.ratingGeneral);
        ratingAtencion = view.findViewById(R.id.ratingAtencion);
        ratingComida = view.findViewById(R.id.ratingComida);
        ratingAmbiente = view.findViewById(R.id.ratingAmbiente);
        etOpinion = view.findViewById(R.id.etOpinion);
        imgFoto = view.findViewById(R.id.imgFoto);
        btnTomarFoto = view.findViewById(R.id.btnTomarFoto);
        btnGuardar = view.findViewById(R.id.btnGuardarResena);

        btnTomarFoto.setOnClickListener(v -> abrirCamara());

        btnGuardar.setOnClickListener(v -> guardarResena());

        return view;
    }

    private void abrirCamara() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(),
                    new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
        } else {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, REQUEST_CAMERA);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CAMERA && resultCode == Activity.RESULT_OK && data != null) {
            Bitmap foto = (Bitmap) data.getExtras().get("data");
            imgFoto.setImageBitmap(foto);
            fotoUri = data.getData(); // opcional, puedes guardar Bitmap como archivo si prefieres
        }
    }

    private void guardarResena() {
        int clienteId = 1; // se debe obtener desde el logeo
        int sucursalId = 1; // ← se debera obtener desde la sucursal activa (se manejaran por id)

        ResenaEntity resena = new ResenaEntity();
        resena.setIdCliente(clienteId);
        resena.setIdSucursal(sucursalId);
        resena.setValoracionGeneral((int) ratingGeneral.getRating());
        resena.setValoracionAtencion((int) ratingAtencion.getRating());
        resena.setValoracionComida((int) ratingComida.getRating());
        resena.setValoracionAmbiente((int) ratingAmbiente.getRating());
        resena.setOpinion(etOpinion.getText().toString().trim());
        resena.setFecha(Utils.obtenerFechaActual());
        resena.setFotoUri(fotoUri != null ? fotoUri.toString() : null);

        viewModel.insertar(resena);
        Toast.makeText(requireContext(), "Reseña guardada", Toast.LENGTH_SHORT).show();
        requireActivity().onBackPressed();
    }
}
