package metalgearjuego;

public abstract class Personaje {
    protected String nombre;
    protected Posicion posicion;
    protected int vida = 100;

    public Personaje(String nombre, Posicion posicion) {
        this.nombre = nombre;
        this.posicion = posicion;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public int getVida() {
        return vida;
    }

    public void recibirDanio(int danio) {
        vida -= danio;
        if (vida < 0) {
            vida = 0;
        }
    }

    public boolean estaVivo() {
        return vida > 0;
    }
}
