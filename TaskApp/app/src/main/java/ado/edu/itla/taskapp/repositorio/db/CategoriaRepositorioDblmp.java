package ado.edu.itla.taskapp.repositorio.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ado.edu.itla.taskapp.entidad.Categoria;
import ado.edu.itla.taskapp.repositorio.CategoriaRepositorio;

public class CategoriaRepositorioDblmp implements CategoriaRepositorio {

    private ConexionDb conexionDb;
    private static final String CAMPO_NOMBRE= "nombre";
    private static final String TABLA_CATEGORIA= "categoria";

    public CategoriaRepositorioDblmp(Context context)
    {
      conexionDb = new ConexionDb(context);
    }


    @Override
    public boolean guardar(Categoria categoria) {  // al pulsar guardar la categoria
         if (categoria.getId()!=null && categoria.getId()>0)   // Si la categoria que se esta guardando es diferente de null
         {                                                      // Si ya existe la categoria id>0
            return actualizar(categoria);               // No se guarda nuevamente sino que se actualiza
    }


        ContentValues cv = new ContentValues(); // valores de las columnas de las tablas
        cv.put(CAMPO_NOMBRE, categoria.getNombre());
        SQLiteDatabase db= conexionDb.getWritableDatabase();
        Long id= db.insert(TABLA_CATEGORIA, null, cv);
        db.close(); //cerramos la base de datos
        if (id.intValue() >0)
        {

            categoria.setId(id.intValue()); // si el ID que se genero en la base de datos es mayor que cero, es porque se guardo

            return true;
        }
        return false; // si el ID que se genero es menor que 0, no se guardo, retorna falso
    }

    @Override
    public boolean actualizar(Categoria categoria) {

        ContentValues cv = new ContentValues(); // para colocar valor en las columnas de las filas
        cv.put(CAMPO_NOMBRE, categoria.getNombre()); // se asigna en el nombre de la categoria
        SQLiteDatabase db = conexionDb.getWritableDatabase(); // se obtiene la base de datos
        int cantidad = db.update(TABLA_CATEGORIA, cv, "id= ?", new String[]{categoria.getId().toString()});
        db.close();
        return cantidad > 0; // retorna si se actualizo

           }

    @Override
    public Categoria buscar(int id) {
        return null;
    }

    @Override
    public List<Categoria> buscar(String buscar)

    {
        //TODO: buscar las categorias por su nombre
       List<Categoria> categorias= new ArrayList<>();
        SQLiteDatabase db= conexionDb.getReadableDatabase(); //leemos la base de datos
        String [] columnas = {"id", CAMPO_NOMBRE};
       Cursor cr= db.query(TABLA_CATEGORIA, columnas, null, null,null, null, null);
       cr.moveToFirst(); //nos ponemos en la primera fila de la db

      while (!cr.isAfterLast()) //nos movemos a la proxima fila en la base de datos
      {
          int id = cr.getInt(cr.getColumnIndex("id"));
          String nombre = cr.getString(cr.getColumnIndex(CAMPO_NOMBRE));
//          Categoria c = new Categoria();
//          c.setId(id);
//          c.setNombre(nombre);
          categorias.add(new Categoria(id, nombre));
          cr.moveToNext();
      };
      cr.close();
      db.close();
        return categorias;
    }
}
