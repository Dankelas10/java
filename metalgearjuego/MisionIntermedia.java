package metalgearjuego;

import java.util.Scanner;

public class MisionIntermedia extends Mision {
    private int numero;

    public MisionIntermedia(int numero) {
        this.numero = numero;
    }

    @Override
    public void iniciar(Snake s, Mapa m) {
        int filas = numero == 1 ? 7 : 9;
        int columnas = numero == 1 ? 7 : 9;
        char puerta = numero == 1 ? 'H' : 'P';
        int puertaX = numero == 1 ? 0 : filas - 1;
        int puertaY = columnas - 1;

        Mapa mapa = new Mapa(filas, columnas);
        if (numero == 2) {
            mapa.colocarItem("C4");
        }

        Snake snake = mapa.getSnake();
        Scanner sc = new Scanner(System.in);
        boolean enJuego = true;
        while (enJuego) {
            imprimirMapa(mapa, puertaX, puertaY, puerta);
            System.out.print("Movimiento (WASD): ");
            String line = sc.nextLine();
            if (line.isEmpty()) {
                continue;
            }
            snake.mover(line.charAt(0), mapa);

            for (Guardia g : mapa.getGuardias()) {
                if (g.detectar(snake)) {
                    System.out.println("¡Has sido capturado!");
                    enJuego = false;
                    break;
                }
            }
            if (!enJuego) {
                break;
            }

            Item obj = mapa.getObjetivo();
            if (obj != null && snake.getPosicion().getX() == obj.getPosicion().getX()
                    && snake.getPosicion().getY() == obj.getPosicion().getY()) {
                System.out.println("Has recogido el " + obj.getNombre() + "!");
                mapa.quitarItem();
            }

            if (numero == 1) {
                if (mapa.getObjetivo() == null && snake.getPosicion().getX() == puertaX
                        && snake.getPosicion().getY() == puertaY) {
                    System.out.println("Misión completada!");
                    enJuego = false;
                }
            } else {
                if (mapa.getObjetivo() == null && snake.getPosicion().getX() == puertaX
                        && snake.getPosicion().getY() == puertaY) {
                    boolean cerca = false;
                    for (Guardia g : mapa.getGuardias()) {
                        int d = Math.abs(g.getPosicion().getX() - puertaX)
                                + Math.abs(g.getPosicion().getY() - puertaY);
                        if (d <= 3) {
                            cerca = true;
                            break;
                        }
                    }
                    if (!cerca) {
                        System.out.println("Misión completada!");
                        enJuego = false;
                    } else {
                        System.out.println("Hay guardias cerca de la puerta!");
                    }
                }
            }
        }
    }

    private void imprimirMapa(Mapa mapa, int px, int py, char puerta) {
        for (int i = 0; i < mapa.getFilas(); i++) {
            for (int j = 0; j < mapa.getColumnas(); j++) {
                String s = ".";
                if (mapa.getSnake().getPosicion().getX() == i && mapa.getSnake().getPosicion().getY() == j) {
                    s = "S";
                } else if (i == px && j == py) {
                    s = String.valueOf(puerta);
                } else {
                    boolean puesto = false;
                    for (Guardia g : mapa.getGuardias()) {
                        if (g.getPosicion().getX() == i && g.getPosicion().getY() == j) {
                            s = "G";
                            puesto = true;
                            break;
                        }
                    }
                    if (!puesto) {
                        Item obj = mapa.getObjetivo();
                        if (obj != null && obj.getPosicion().getX() == i && obj.getPosicion().getY() == j) {
                            if (obj.getNombre().equals("L")) {
                                s = "L";
                            } else {
                                s = "C4";
                            }
                        }
                    }
                }
                System.out.print(String.format("%-2s", s));
            }
            System.out.println();
        }
        Item obj = mapa.getObjetivo();
        if (obj != null) {
            System.out.println("Objeto a buscar: " + obj.getNombre());
        }
    }
}
