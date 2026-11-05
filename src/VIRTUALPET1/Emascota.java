package VIRTUALPET1;

import java.util.Random;

public class Emascota {

    // Atributos
    public String nombre;
    public String tipo;
    public int energia;
    public int higiene;
    public int peso;
    public int animo;

    // Constructor  Jugador
    public Emascota(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;

        Random random = new Random();
        this.energia = 40 + random.nextInt(21);
        this.higiene = 40 + random.nextInt(21);
        this.peso = 40 + random.nextInt(21);
        this.animo = 40 + random.nextInt(21);
    }

    // Constructor Rival
    public Emascota(String nombreRival) {
        this.nombre = nombreRival;
        this.tipo = "Rival";
        reinicializarRival();
    }

    // ACCIONES DEL RIVAL
    public void reinicializarRival() {
        this.energia = 100;
        this.higiene = 100;
        this.peso = 100;
        this.animo = 100;
    }

    public void dormir() {
        boolean esPerro = this.tipo.equalsIgnoreCase("Perro");
        int incrementoEnergia = esPerro ? 25 : 20;

        this.energia += incrementoEnergia;
        this.animo += 10;

        System.out.printf("-> %s está haciendo la siesta. [Energía +%d, Ánimo +10]%n",
                nombre, incrementoEnergia);
    }

    public void jugar() {
        this.energia -= 20;
        this.animo += 20;
        this.peso -= 10;
        System.out.println("-> " + nombre + " juega en el parque. [Energía -20, Ánimo +20, Peso -10]");
    }

    public void comer() {
        this.energia += 10;
        this.higiene -= 15;
        this.peso += 10;
        System.out.println("-> " + nombre + " está almorzando. [Energía +10, Higiene -15, Peso +10]");
    }

    public void asear() {
        boolean esPerro = this.tipo.equalsIgnoreCase("Perro");
        int gastoEnergia = esPerro ? 10 : 5;    // operadores ternarios
        int cambioAnimo = esPerro ? -15 : 5;

        this.energia -= gastoEnergia;
        this.higiene += 25;
        this.animo += cambioAnimo;

        System.out.printf("-> %s se ha duchado. [Energía -%d, Higiene +25, Ánimo %+d]%n",
                nombre, gastoEnergia, cambioAnimo);
    }

    public void recibirDanio(int puntos) {
        this.energia -= puntos;
        this.animo -= puntos;
        this.higiene -= puntos;
    }

    public void atacar(Emascota rival, int numAtaques) {
        System.out.println("\n=== INICIO DE COMBATE: " + numAtaques + " ATAQUES SOLICITADOS ===");

        for (int i = 1; i <= numAtaques; i++) {
            if (this.estaMuerta()) {
                System.out.println("-> El combate se interrumpe: " + this.nombre + " ha quedado débil en el ataque " + i + ".");

            }

            this.recibirDanio(3);
            rival.recibirDanio(3);

            System.out.println(" [Ataque " + i + "/" + numAtaques + "] " + this.nombre + " ataca a " + rival.nombre +
                    " | Gastos -> [Energía -3, Higiene -3, Ánimo -3]");

            if (rival.energia <= 0 || rival.animo <= 0) {
                System.out.println("  ¡Has Ganado en el Ataque " + i + "!\n  " + rival.nombre + " ha sido derrotado.");
                System.out.println("  -> Se restablecen las estadísticas del rival a 100 puntos.");
                rival.reinicializarRival();

            }
        }
    }

    public boolean estaMuerta() {
        return (this.energia <= 0 || this.energia >= 100 ||
                this.higiene <= 0 || this.higiene >= 100 ||
                this.peso <= 0 || this.peso >= 100 ||
                this.animo <= 0 || this.animo >= 100);
    }

    public void mostrarEstado() {
        System.out.println("\n+-------------------------------------------------------------+");
        System.out.println("| ESTADO DE " + nombre.toUpperCase() + " (" + tipo.toUpperCase() + ")");
        System.out.println("+-------------------------------------------------------------+");
        System.out.printf("| Energía: %3d / 100  %s%n", energia, generarBarra(energia));
        System.out.printf("| Higiene: %3d / 100  %s%n", higiene, generarBarra(higiene));
        System.out.printf("| Peso:    %3d / 100  %s%n", peso, generarBarra(peso));
        System.out.printf("| Ánimo:   %3d / 100  %s%n", animo, generarBarra(animo));
        System.out.println("+-------------------------------------------------------------+");
    }

    public String generarBarra(int valor) {
        int bloques = Math.max(0, Math.min(10, valor / 10));
        StringBuilder estado = new StringBuilder("["); //stringbuilder construye texto optimizado

        for (int i = 0; i < 10; i++) {
            estado.append(i < bloques ? "=" : " ");
        }
        estado.append("]");
        return estado.toString();
    }
}

