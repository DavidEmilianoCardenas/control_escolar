package model;

public interface Entregable {
    public void entregar();

    public void devolver();

    default public boolean isEntregado() {
        return false;
    }

    default public String imprimir(){
        return "";
    }

}
