package metalgearjuego;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mapa {
    private int filas;
    private int columnas;
    private Snake snake;
    private List<Guardia> guardias = new ArrayList<>();
    private Item objetivo;
    private Random rand = new Random();

    public Mapa(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.snake = new Snake("Snake", new Posicion(0, 0));
        generarGuardias(3);
        generarObjetivo("L");
    }

    private void generarGuardias(int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            Posicion pos;
            do {
                pos = new Posicion(rand.nextInt(filas), rand.nextInt(columnas));
            } while (distanciaManhattan(pos, snake.getPosicion()) < 2 || existeGuardiaEn(pos));
            guardias.add(new Guardia("G" + i, pos));
        }
    }

    private boolean existeGuardiaEn(Posicion p) {
        for (Guardia g : guardias) {
            if (g.getPosicion().getX() == p.getX() && g.getPosicion().getY() == p.getY()) {
                return true;
            }
        }
        return false;
    }

    private void generarObjetivo(String nombre) {
        Posicion pos;
        do {
            pos = new Posicion(rand.nextInt(filas), rand.nextInt(columnas));
        } while (distanciaManhattan(pos, snake.getPosicion()) < 2 || existeGuardiaEn(pos));
        objetivo = new Item(nombre, pos);
    }

    public void colocarItem(String nombre) {
        generarObjetivo(nombre);
    }

    public boolean estaDentroLimites(int x, int y) {
        return x >= 0 && y >= 0 && x < filas && y < columnas;
    }

    public void moverGuardias() {
        for (Guardia g : guardias) {
            g.patrullar(this);
        }
    }

    private int distanciaManhattan(Posicion a, Posicion b) {
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    }

    public void imprimir() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                String s = ".";
                if (snake.getPosicion().getX() == i && snake.getPosicion().getY() == j) {
                    s = "S";
                } else {
                    for (Guardia g : guardias) {
                        if (g.getPosicion().getX() == i && g.getPosicion().getY() == j) {
                            s = "G";
                            break;
                        }
                    }
                    if (objetivo != null && objetivo.getPosicion().getX() == i && objetivo.getPosicion().getY() == j) {
                        if (objetivo.getNombre().equals("L")) {
                            s = "L";
                        } else {
                            s = "C4";
                        }
                    }
                }
                System.out.print(String.format("%-2s", s));
            }
            System.out.println();
        }
        if (objetivo != null) {
            System.out.println("Objeto a buscar: " + objetivo.getNombre());
        }
    }

    public Snake getSnake() {
        return snake;
    }

    public List<Guardia> getGuardias() {
        return guardias;
    }

    public Item getObjetivo() {
        return objetivo;
    }

    public void quitarItem() {
        objetivo = null;
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }
}
