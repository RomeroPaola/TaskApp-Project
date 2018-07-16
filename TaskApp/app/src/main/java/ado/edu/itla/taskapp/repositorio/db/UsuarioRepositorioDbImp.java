package ado.edu.itla.taskapp.repositorio.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NavUtils;
import android.util.Log;

import java.sql.SQLTransactionRollbackException;
import java.util.ArrayList;
import java.util.List;

import ado.edu.itla.taskapp.entidad.Usuario;
import ado.edu.itla.taskapp.repositorio.UsuarioRepositorio;

import static ado.edu.itla.taskapp.vista.CategoriaActivity.LOG_TAG;

public class UsuarioRepositorioDbImp implements UsuarioRepositorio{

    private ConexionDb conexionDb; // Para crear conexion con BD
    private static final String CAMPO_NOMBRE= "nombre";
    private static final String CAMPO_EMAIL= "email";
    private static final String CAMPO_CONTRASENA="contrasena";
    private static final String CAMPO_TIPO_USUARIO = "tipoUsuario";

     private static final String TABLA_USUARIO= "usuario";
    private static final String LOG_TAG="ConexionDb";

    public UsuarioRepositorioDbImp(Context context)
    {


            conexionDb = new ConexionDb(context);
    }

    @Override
    public boolean guardar(Usuario usuario) {
         if (usuario.getId()!=null && usuario.getId()>0) {
             return actualizar(usuario);
         }
         Log.i(LOG_TAG, "Guardando usuario");

        ContentValues cv = new ContentValues();
         cv.put(CAMPO_CONTRASENA, usuario.getContrasena());
         cv.put(CAMPO_EMAIL, usuario.getEmail());
         cv.put(CAMPO_NOMBRE, usuario.getNombre());
         cv.put(CAMPO_TIPO_USUARIO, usuario.getTipoUsuario().name());

        SQLiteDatabase db = conexionDb.getWritableDatabase();
        Long id= db.insert(TABLA_USUARIO, null, cv); //devuelve el id de la fila recien creada
            db.close();
            if (id.intValue()>0)
            {
                usuario.setId(id.intValue()); //se guardo correctamente

            }
            return false; // si retorna -1 , no se guardo
    }

    @Override
    public boolean actualizar(Usuario usuario) {

        ContentValues cv = new ContentValues(); // para colocar valor en las columnas de las filas
        cv.put(CAMPO_NOMBRE, usuario.getNombre());
        cv.put(CAMPO_EMAIL, usuario.getEmail());
        cv.put(CAMPO_CONTRASENA, usuario.getContrasena());
        cv.put(CAMPO_TIPO_USUARIO, usuario.getTipoUsuario().toString());
        SQLiteDatabase db = conexionDb.getWritableDatabase(); // se obtiene la base de datos
        int cantidad = db.update(TABLA_USUARIO, cv, "id= ?", new String[]{usuario.getId().toString()});
        db.close();

        return cantidad > 0; // retorna true si se actualizo y falso si no

    }

     @Override //buscar por ID
    public Usuario buscar(int id)

    {
        if (id>0) {
            SQLiteDatabase db = conexionDb.getReadableDatabase();
            String[] columnas = {"id", CAMPO_NOMBRE, CAMPO_EMAIL, CAMPO_CONTRASENA, CAMPO_TIPO_USUARIO};
            Cursor cr = db.query(TABLA_USUARIO, columnas, null, null, null, null, null);
            cr.moveToFirst(); //nos ponemos en la primera fila de la db
            while (!cr.isAfterLast()) //nos movemos a la proxima fila en la base de datos
            {
                int idFila = cr.getInt(cr.getColumnIndex("id"));
                if (id==idFila)
                {
                    Usuario usuario = new Usuario(idFila, cr.getString(cr.getColumnIndex(CAMPO_NOMBRE)),cr.getString(cr.getColumnIndex(CAMPO_EMAIL)),cr.getString(cr.getColumnIndex(CAMPO_CONTRASENA)), Usuario.TipoUsuario.valueOf(cr.getString(cr.getColumnIndex(CAMPO_TIPO_USUARIO))));
                    cr.close();
                    db.close();
                return usuario;
                }
                cr.moveToNext();
            }
        }
        return null;
    }

    @Override
    public Usuario buscarXnombre(String nombre) {

        SQLiteDatabase db = conexionDb.getReadableDatabase();
        String[] columnas = {"id", CAMPO_NOMBRE, CAMPO_EMAIL, CAMPO_CONTRASENA, CAMPO_TIPO_USUARIO};
        Cursor cr = db.query(TABLA_USUARIO, columnas, null, null, null, null, null);
        cr.moveToFirst(); //nos ponemos en la primera fila de la db
        while (!cr.isAfterLast()) //nos movemos a la proxima fila en la base de datos
        {

            if (cr.getString(cr.getColumnIndex(CAMPO_NOMBRE))==nombre)
            {
                Usuario usuario = new Usuario(cr.getInt(cr.getColumnIndex("id")), cr.getString(cr.getColumnIndex(CAMPO_NOMBRE)),cr.getString(cr.getColumnIndex(CAMPO_EMAIL)),cr.getString(cr.getColumnIndex(CAMPO_CONTRASENA)), Usuario.TipoUsuario.valueOf(cr.getString(cr.getColumnIndex(CAMPO_TIPO_USUARIO))));
                cr.close();
                db.close();
                return usuario;
            }
            cr.moveToNext();
        }

        return null;
    }

    @Override
    public List<Usuario> buscar(String buscar) {
        //TODO: buscar los usuarios s por su nombre

        List<Usuario> usuarios= new ArrayList<>();
        SQLiteDatabase db= conexionDb.getReadableDatabase(); //leemos la base de datos
        String [] columnas = {"id", CAMPO_NOMBRE, CAMPO_EMAIL, CAMPO_CONTRASENA, CAMPO_TIPO_USUARIO};
        Cursor cr= db.query(TABLA_USUARIO, columnas,null,null, null, null, null);
        cr.moveToFirst(); //nos ponemos en la primera fila de la db

        while (!cr.isAfterLast()) //nos movemos a la proxima fila en la base de datos
        {
            int id = cr.getInt(cr.getColumnIndex("id"));
            String nombre = cr.getString(cr.getColumnIndex(CAMPO_NOMBRE));
            String email = cr.getString(cr.getColumnIndex(CAMPO_EMAIL));
            String contrasena = cr.getString(cr.getColumnIndex(CAMPO_CONTRASENA));
            String tipoUsuario = cr.getString(cr.getColumnIndex(CAMPO_TIPO_USUARIO));

            //          Categoria c = new Categoria();
//          c.setId(id);
//          c.setNombre(nombre);

            usuarios.add(new Usuario(id,nombre,email,contrasena,Usuario.TipoUsuario.valueOf(tipoUsuario)));
                    cr.moveToNext();
        };
        cr.close();
        db.close();
        return usuarios;
        //return null;
    }
}
