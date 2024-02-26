package model;

public class Serie implements Entregable{
    private static final byte cantTempDefault = 3;
    private static final boolean entradaDefault = false;
    String titulo;
    byte cantTemp = cantTempDefault;
    boolean entregado = entradaDefault;
    String genero;
    String creador;

    public Serie (){}

    public Serie (String titulo, String Creador) {
        setTitulo(titulo);
        setCreador(Creador);
    }

    public Serie (String titulo, byte cantTemp, String genero, String creador) {
        setTitulo(titulo);
        setCantTemp(cantTemp);
        setGenero(genero);
        setCreador(creador);
    }
    public String imprimir () {
        return getTitulo() + " - " + isEntregado();
    }
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public byte getCantTemp() {
        return cantTemp;
    }

    public void setCantTemp(byte cantTemp) {
        this.cantTemp = cantTemp;
    }

    @Override
    public void entregar() {
        entregado = true;
    }

    @Override
    public void devolver() {
        entregado = false;
    }

    public boolean isEntregado() {
        return entregado;
    }

    public void setEntregado(boolean entregado) {
        this.entregado = entregado;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCreador() {
        return creador;
    }

    public void setCreador(String creador) {
        this.creador = creador;
    }

    @Override
    public String toString() {
        return "Serie{" +
                "titulo='" + titulo + '\'' +
                ", cantTemp=" + cantTemp +
                ", entregado=" + entregado +
                ", genero='" + genero + '\'' +
                ", creador='" + creador + '\'' +
                '}';
    }
}
