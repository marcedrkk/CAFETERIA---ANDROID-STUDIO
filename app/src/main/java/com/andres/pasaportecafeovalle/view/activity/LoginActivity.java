package com.andres.pasaportecafeovalle.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.andres.pasaportecafeovalle.R;
import com.andres.pasaportecafeovalle.data.local.entities.ClientesEntity;
import com.andres.pasaportecafeovalle.view.modelo.Usuario;
import com.andres.pasaportecafeovalle.viewmodel.ClientesViewModel;
import java.util.*;

public class LoginActivity extends AppCompatActivity {

    private EditText txtCorreo, txtPassword;
    private Button btnLogin;
    private ClientesViewModel clientesViewModel;
    private final List<Usuario> usuarios = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtCorreo = findViewById(R.id.txtCorreo);
        txtPassword = findViewById(R.id.txtContraseña);
        btnLogin = findViewById(R.id.btnLogin);
        clientesViewModel = new ViewModelProvider(this).get(ClientesViewModel.class);

        cargarUsuariosPredeterminados();

        btnLogin.setOnClickListener(v -> {
            String correo = txtCorreo.getText().toString().trim();
            String clave = txtPassword.getText().toString().trim();

            if (correo.isEmpty()) { txtCorreo.setError("Ingrese su correo"); return; }
            if (clave.isEmpty()) { txtPassword.setError("Ingrese su contraseña"); return; }

            Usuario user = buscarUsuario(correo, clave);
            if (user != null) {
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("id_usuario", user.getId_usuario());
                intent.putExtra("id_sucursal", user.getId_sucursal());
                intent.putExtra("rol", user.getRol());
                intent.putExtra("correo", user.getEmail());
                startActivity(intent);
                finish();
                return;
            }

            clientesViewModel.login(correo, clave, new ClientesViewModel.LoginCallback() {
                @Override
                public void onSuccess(ClientesEntity cliente) {
                    Toast.makeText(LoginActivity.this, "Bienvenido " + cliente.getNombre(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("correo", cliente.getEmail());
                    intent.putExtra("rol", "cliente");
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onFailure(String mensajeError) {
                    Toast.makeText(LoginActivity.this, mensajeError, Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
    private void cargarUsuariosPredeterminados() {
        for (int i = 1; i <= 4; i++) {
            usuarios.add(new Usuario(i, i, "admin" + i + "@gmail.com", "1234", "admin"));
            usuarios.add(new Usuario(i + 10, i, "cajero" + i + "@gmail.com", "1234", "cajero"));
        }
        usuarios.add(new Usuario(99, 0, "superAdmin@gmail.com", "1234", "super admin"));
    }
    private Usuario buscarUsuario(String correo, String clave) {
        for (Usuario u : usuarios) {
            if (u.getEmail().equals(correo) && u.getClave().equals(clave)) {
                return u;
            }
        }
        return null;
    }
}
