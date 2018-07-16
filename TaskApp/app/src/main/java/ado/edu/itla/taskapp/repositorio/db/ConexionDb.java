package ado.edu.itla.taskapp.repositorio.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ConexionDb extends SQLiteOpenHelper { // SQLITEOPENHELPER: PARA ADMINISTRAR Y HACER REQUESTS A UNA DB LOCAL
    private static final String LOG_TAG="ConexionDb"; //NOMBRE DE LA CLASE
    private static final String NOMBRE_DB = "taskapp.db"; // SE COLOCA LA INFO DE LA BASE DE DATOS, NOMBRE Y VERSION
    private static final int VERSION_DB=1; // version 1 de base de datos

    public ConexionDb(Context context){ //

        super(context, NOMBRE_DB, null, VERSION_DB);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {  // PARA CREAR LA BASE DE DATOS por primera vez
        Log.i(LOG_TAG,  "Creando la base de datos"); //imprimir mensaje por consola

        db.execSQL(EstructuraDb.TABLA_CATEGORIA); // para ejecutar una sentencia sql
        db.execSQL(EstructuraDb.TABLA_USUARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // TODO: LO VEREMOS EN UNA SEGUNDA ETAPA DBUPDATE


    }




}
