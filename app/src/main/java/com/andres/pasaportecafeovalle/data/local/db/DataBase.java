package com.andres.pasaportecafeovalle.data.local.db;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.*;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.andres.pasaportecafeovalle.data.local.dao.*;
import com.andres.pasaportecafeovalle.data.local.entities.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Database(
        entities = {
                ClientesEntity.class,
                CanjeEntity.class,
                BeneficioEntity.class,
                AdminEntity.class,
                ReglaEntity.class,
                ProductoEntity.class,
                VisitaEntity.class
        },
        version = 9,
        exportSchema = false
)
public abstract class DataBase extends RoomDatabase {

    public static final Executor databaseWriteExecutor = Executors.newFixedThreadPool(4);
    private static volatile DataBase INSTANCE;

    public abstract ClientesDao clientesDao();
    public abstract VisitasDao visitasDao();
    public abstract ProductosDao productosDao();
    public abstract AdminDao adminDao();
    public abstract ResenaDao resenaDao();

    public static DataBase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (DataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    DataBase.class,
                                    "pasaporte_cafe_db"
                            )
                            .fallbackToDestructiveMigration()
                            .addCallback(new Callback() {
                                @Override
                                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                                    super.onOpen(db);
                                    db.execSQL("INSERT OR IGNORE INTO admin (id, nombre, email, clave, estado) " +
                                            "VALUES (1, 'Admin Principal', 'admin@cafe.cl', '1234', 'Activo')");
                                }
                            })
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
