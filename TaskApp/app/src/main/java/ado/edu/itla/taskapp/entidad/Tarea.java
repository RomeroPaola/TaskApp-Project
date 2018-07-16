package ado.edu.itla.taskapp.entidad;

import java.util.Date;

public class Tarea {
    public enum TareaEstado{ PENDIENTE,EN_PROCESO, TERMINADO}
   private Integer id;
   private String nombre;
   private String descripcion;
   private Date fecha;
   private Date fechaTerminado;
   private Categoria categoria;
   private Usuario usuarioCreador;
   private Usuario Asignado;

    public Integer getId() { //Para obtener Id de la tarea

        return id;
    }

    public void setId(Integer id) { // Para asignar un ID a una tarea

        this.id = id;
    }

    public String getNombre() { // Para obtener nombre

        return nombre;
    }

    public void setNombre(String nombre) { //Para asignar un nombre a la tarea

        this.nombre = nombre;
    }

    public String getDescripcion() { // Para obtener la descripcion de la tarea

        return descripcion;
    }

    public void setDescripcion(String descripcion) // para colocarle una descripcion
    {
        this.descripcion = descripcion;
    }

    public Date getFecha() {

        return fecha;
    }

    public void setFecha(Date fecha)
    {
        this.fecha = fecha;
    }

    public Date getFechaTerminado()
    {
        return fechaTerminado;
    }

    public void setFechaTerminado(Date fechaTerminado)
    {
        this.fechaTerminado = fechaTerminado;
    }

    public Categoria getCategoria() {

        return categoria;
    }

    public void setCategoria(Categoria categoria) {

        this.categoria = categoria;
    }

    public Usuario getUsuarioCreador() {

        return usuarioCreador;
    }

    public void setUsuarioCreador(Usuario usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
    }

    public Usuario getAsignado() {

        return Asignado;
    }

    public void setAsignado(Usuario asignado) {
        Asignado = asignado;
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fecha=" + fecha +
                ", fechaTerminado=" + fechaTerminado +
                ", categoria=" + categoria +
                ", usuarioCreador=" + usuarioCreador +
                ", Asignado=" + Asignado +
                '}';
    }
}
