package clases;

public class Personaje {
    protected int vida;
    protected Arma arma;
    protected String nombre;
    public int getVida() {
        return vida;
    }
    public Arma getArma() {
        return arma;
    }
    @Override
    public String toString() {
        return nombre;
    }
    public Personaje(String nombre){
        this.nombre=nombre;
    }
    public boolean esAtacado(int danyo){
        this.vida-=danyo;
        return this.vida>0;
    }
}
