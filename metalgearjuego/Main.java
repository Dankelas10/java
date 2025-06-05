package metalgearjuego;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int siguienteIntermedia = 1;
        while (true) {
            System.out.println("1) Iniciar MisionIntermedia");
            System.out.println("2) Iniciar MisionFinal");
            System.out.println("0) Salir");
            String op = sc.nextLine();
            if (op.equals("0")) {
                break;
            } else if (op.equals("1")) {
                if (siguienteIntermedia <= 2) {
                    MisionIntermedia mi = new MisionIntermedia(siguienteIntermedia);
                    mi.iniciar(null, null);
                    if (siguienteIntermedia == 1) {
                        siguienteIntermedia = 2;
                    }
                } else {
                    System.out.println("Ya completaste las misiones intermedias.");
                }
            } else if (op.equals("2")) {
                MisionFinal mf = new MisionFinal();
                mf.iniciar(null, null);
            }
        }
    }
}
