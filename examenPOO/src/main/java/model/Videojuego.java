package model;

public class Videojuego implements Entregable{
    String titulo;
    byte horasAprox = 10;
    boolean entregado = false;
    String genero;
    String companyia;

    public Videojuego () {}

    public Videojuego (String titulo, byte horasAprox) {
        setTitulo(titulo);
        setHorasAprox(horasAprox);
    }

    public Videojuego (String titulo, byte horasAprox, String genero, String companyia) {
        setTitulo(titulo);
        setHorasAprox(horasAprox);
        setGenero(genero);
        setCompanyia(companyia);
    }

    public String imprimir () {
        return getTitulo() + " - " + isEntregado();
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
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public byte getHorasAprox() {
        return horasAprox;
    }

    public void setHorasAprox(byte horasAprox) {
        this.horasAprox = horasAprox;
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

    public String getCompanyia() {
        return companyia;
    }

    public void setCompanyia(String companyia) {
        this.companyia = companyia;
    }

    @Override
    public String toString() {
        return "Videojuego{" +
                "titulo='" + titulo + '\'' +
                ", horasAprox=" + horasAprox +
                ", entregado=" + entregado +
                ", genero='" + genero + '\'' +
                ", companyia='" + companyia + '\'' +
                '}';
    }
}
