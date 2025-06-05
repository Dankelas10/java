package metalgearjuego;

import java.util.Random;

public class Guardia extends Personaje implements Enemigo {
    private Random rand = new Random();

    public Guardia(String nombre, Posicion posicion) {
        super(nombre, posicion);
    }

    public void patrullar(Mapa mapa) {
        int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
        int[] d = dirs[rand.nextInt(dirs.length)];
        int nx = posicion.getX() + d[0];
        int ny = posicion.getY() + d[1];
        if (mapa.estaDentroLimites(nx, ny)) {
            posicion.setX(nx);
            posicion.setY(ny);
        }
    }

    public boolean detectar(Snake snake) {
        int dx = Math.abs(posicion.getX() - snake.getPosicion().getX());
        int dy = Math.abs(posicion.getY() - snake.getPosicion().getY());
        return dx + dy == 1;
    }
}
