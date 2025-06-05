package metalgearjuego;

import java.util.Random;

public class MetalGear extends Personaje implements Enemigo {
    private Random rand = new Random();

    public MetalGear(String nombre, Posicion posicion) {
        super(nombre, posicion);
    }

    public int atacar() {
        return 15 + rand.nextInt(26); // 15-40
    }
}
