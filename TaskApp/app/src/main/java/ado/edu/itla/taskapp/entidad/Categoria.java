package ado.edu.itla.taskapp.entidad;

import java.io.Serializable;

public class Categoria implements Serializable {
   private Integer id;           // se protegen estos metodos encapsulandolos
   private String nombre;

    public Integer getId() { //Para obtener ID de la Categoria
        return id;
    }

    public Categoria setId (Integer id) { //Retorna la categoria del Id especificado
        this.id = id;
        return this;
    }

    public Categoria (Integer id) { // Constructor, que asigna un ID a la categoria

        this.id = id;
    }

    public String getNombre() { // Para obtener el nombre de la categoria
        return nombre;
    }

    public Categoria() { // Constructor vacio
    }

    public Categoria(Integer id, String nombre) { // Constructor con id y nombre
        this.id = id;
        this.nombre = nombre;
    }


    public Categoria setNombre(String nombre) { //Para asignar un nombre a la categoria
        this.nombre = nombre;
        return this;
    }

    @Override               // se hereda un metodo de la clase object
    public String toString() {
        final StringBuffer sb = new StringBuffer("Categoria{");
        sb.append("id=").append(id);
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
