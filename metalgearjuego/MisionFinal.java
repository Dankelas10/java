package metalgearjuego;

import java.util.Random;
import java.util.Scanner;

public class MisionFinal extends Mision {
    @Override
    public void iniciar(Snake s, Mapa m) {
        Snake snake = new Snake("Snake", new Posicion(0, 0));
        MetalGear rex = new MetalGear("REX", new Posicion(0, 0));
        snake.resetearVida();
        rex.recibirDanio(0); // asegurar vida 100
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        while (snake.estaVivo() && rex.estaVivo()) {
            System.out.println("Vida Snake: " + snake.getVida());
            System.out.println("Vida REX: " + rex.getVida());
            snake.setEsquivando(false);
            System.out.println("1) Disparar");
            System.out.println("2) Esquivar");
            String opt = sc.nextLine();
            if (opt.equals("1")) {
                int dmg = 10 + rand.nextInt(21);
                rex.recibirDanio(dmg);
                System.out.println("Disparas y causas " + dmg + " de daño.");
            } else {
                snake.setEsquivando(true);
                System.out.println("Te preparas para esquivar.");
            }
            if (!rex.estaVivo()) {
                break;
            }
            int dmg = rex.atacar();
            if (snake.isEsquivando()) {
                double factor = rand.nextDouble() * 0.5; // restante 0-50%
                dmg = (int) (dmg * factor);
            }
            System.out.println("REX ataca causando " + dmg + " de daño.");
            snake.recibirDanio(dmg);
        }
        if (snake.estaVivo()) {
            System.out.println("¡Has derrotado a Metal Gear!");
        } else {
            System.out.println("Has sido derrotado...");
        }
    }
}
