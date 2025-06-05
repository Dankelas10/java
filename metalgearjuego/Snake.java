package metalgearjuego;

public class Snake extends Personaje {
    private boolean esquivando = false;

    public Snake(String nombre, Posicion posicion) {
        super(nombre, posicion);
    }

    public void mover(char tecla, Mapa mapa) {
        int dx = 0;
        int dy = 0;
        switch (Character.toUpperCase(tecla)) {
            case 'W': dx = -1; break;
            case 'S': dx = 1; break;
            case 'A': dy = -1; break;
            case 'D': dy = 1; break;
            default: return;
        }
        int nx = posicion.getX() + dx;
        int ny = posicion.getY() + dy;
        if (mapa.estaDentroLimites(nx, ny)) {
            posicion.setX(nx);
            posicion.setY(ny);
        }
        mapa.moverGuardias();
    }

    public void resetearVida() {
        vida = 100;
    }

    public void setEsquivando(boolean e) {
        this.esquivando = e;
    }

    public boolean isEsquivando() {
        return esquivando;
    }
}
