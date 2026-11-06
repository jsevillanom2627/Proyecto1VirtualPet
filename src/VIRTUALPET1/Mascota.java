package VIRTUALPET1;


import java.util.Random;

public class Mascota {

    // Atributos
    public String nombre;
    public String tipo;
    public int energia;
    public int higiene;
    public int peso;
    public int animo;

    //Constructores
     //mascota jugador Constructor 40-60

    public Mascota(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;

        Random random = new Random();
        this.energia = 40 + random.nextInt(21);
        this.higiene = 40 + random.nextInt(21); //nextInt numero entero en rand,o de 0 a 20 (siempre uno menos)
        this.peso = 40 + random.nextInt(21);    // es decir  40+ un numero entre 0 y 20
        this.animo = 40 + random.nextInt(21);
    }

    //Rival a 100
    public Mascota(String nombreRival) {
        this.nombre = nombreRival;
        this.tipo = "Rival ";
        this.energia = 100;
        this.higiene = 100;
        this.peso = 100;
        this.animo = 100;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public String getTipo() { return tipo; }
    public int getEnergia() { return energia; }
    public int getHigiene() { return higiene; }
    public int getPeso() { return peso; }
    public int getAnimo() { return animo; }


   // rerinicializar rival al derrotarlo
    public void reinicializarRival() {
        this.energia = 100;
        this.higiene = 100;
        this.peso = 100;
        this.animo = 100;
    }

    //FUNCIONES
    public void dormir() {
        if (this.tipo.equalsIgnoreCase("Perro")) {  // equalsIgnoreCase ignora la forma en la que escribes la palabra pero
            this.energia += 25;                                 //confirma que lo sea
            System.out.println("-> " + nombre + "  esta haciendo la siesta. [Energía +25, Ánimo +10]");
        } else {
            this.energia += 20;
            System.out.println("-> " + nombre + "  esta haciendo la siesta . [Energía +20, Ánimo +10]");
        }
        this.animo += 10;  //ánimo+10 le sube a cualquiera de los dos si duermen
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
        System.out.println("-> " + nombre + " esta almorzando. [Energía +10, Higiene -15, Peso +10]");
    }


    public void asear() {
        if (this.tipo.equalsIgnoreCase("Perro")) {
            this.energia -= 10;
            this.higiene += 25;
            this.animo -= 15;
            System.out.println("-> " + nombre + "  se ha duchado. [Energía -10, Higiene +25, Ánimo -15]");
        } else {
            this.energia -= 5;
            this.higiene += 25;
            this.animo += 5;
            System.out.println("-> " + nombre + "  se ha duchado. [Energía -5, Higiene +25, Ánimo +5]");
        }
    }


    public void atacar(Mascota rival, int numAtaques) {
        System.out.println("\n=== INICIO DE COMBATE: " + numAtaques + " ATAQUES SOLICITADOS ===");

        for (int i = 1; i <= numAtaques; i++) {
            if (this.estaMuerta()) {
                System.out.println("-> El combate se interrumpe: " + this.nombre + " ha quedado debil en el ataque " + i + ".");
                break;
            }

            // impacto de ataque (jugaor)
            this.energia -= 3;
            this.higiene -= 3;
            this.animo -= 3;

            // impacto rival
            rival.energia -= 3;
            rival.animo -= 3;
            rival.higiene -= 3;

            System.out.println(" [Ataque " + i + "/" + numAtaques + "] " + this.nombre + " ataca a " + rival.getNombre() +
                    " | Gastos -> [Energía -3, Higiene -3, Ánimo -3]");

            // comprobar muerte rival del rival
            if (rival.getEnergia() <= 0 || rival.getAnimo() <= 0) {
                System.out.println("  ¡Has Ganado en el Ataque " + i + "\n" + rival.getNombre() + " ha sido derrotada.");
                System.out.println("  -> Se restablecen las estadísticas del rival a 100 puntos.");
                rival.reinicializarRival();
            }
        }
    }

    public void bailar() {
        System.out.println(this.nombre + " está bailando un mambo! 💃🕺");
        this.energia -= 10;
    }

   //limites de supervivencia
    public boolean estaMuerta() {
        return (this.energia <= 0 || this.energia >= 100 ||
                this.higiene <= 0 || this.higiene >= 100 ||
                this.peso <= 0    || this.peso >= 100    ||
                this.animo <= 0   || this.animo >= 100);
    }



   //valores actuales con barras     toUpperCase()convierte los Valores a MAYUSCULAS
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

    //Barra de progreso de 10 blques(=)
    public String generarBarra(int valor) {
        int bloques = Math.max(0, Math.min(10, valor / 10));    //Valor  entre 10 para poner limite de bloques

        //constructor
        StringBuilder estado = new StringBuilder("[");   //Stringbuilder para modificar datos

        for (int i = 0; i < 10; i++) {
            if (i < bloques) estado.append("=");
            else estado.append(" ");
        }
        estado.append("]");
        return estado.toString();
    }
}
