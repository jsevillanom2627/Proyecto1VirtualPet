package VIRTUALPET1;


import java.util.Scanner;


public class MainVP1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("===============================================================");
        System.out.println("       SIMULADOR — TAMASCOTCHI GUAU STREET");
        System.out.println("===============================================================");

        // 1. LECTURA Y ELECCIÓN DE ESPECIE
        int tipoOpcion = 0;
        String tipoMascota = "";

        do {
            System.out.println("\nSeleccionar tipo mascota:");
            System.out.println("  1. Perro");
            System.out.println("  2. Gato");
            System.out.print("Ingresar opción (1 o 2): ");

            if (scanner.hasNextInt()) {
                tipoOpcion = scanner.nextInt();
                if (tipoOpcion == 1) {
                    tipoMascota = "Perro";
                } else if (tipoOpcion == 2) {
                    tipoMascota = "Gato";
                } else {
                    System.out.println("-> Opción no válida. Ingrese 1 o 2.");
                }
            } else {
                System.out.println("-> entrada erronea.Ingresar un número entero.");
                scanner.next();
            }
        } while (tipoOpcion != 1 && tipoOpcion != 2);

        scanner.nextLine(); // /next line detiene programa hasta que se escriba algo

        // 2. LECTURA DEL NOMBRE
        String nombreMascota;
        do {
            System.out.print("\nIngrese el nombre de su " + tipoMascota + ": ");
            nombreMascota = scanner.nextLine().trim();

            if (nombreMascota.isEmpty()) {
                System.out.println("-> El nombre no puede estar vacío.");
            }
        } while (nombreMascota.isEmpty());

        // 3. INSTANCIACIÓN DE OBJETOS INDEPENDIENTES
        Mascota jugador = new Mascota(nombreMascota, tipoMascota);
        Mascota rival = new Mascota("(Rival)");

        System.out.println("\n¡Haz creado  a " + jugador.getNombre() + " (" + jugador.getTipo() + ")");
        System.out.println(" Rival " + rival.getNombre() + " inicializada a 100 puntos en sus valores.");

        // 4. BUCLE PRINCIPAL DEL MENÚ (WHILE / DO-WHILE CON CONTADOR DE TURNOS)
        int opcion = -1;
        int contadorTurnos = 0;
        boolean salir = false;

        do {
            contadorTurnos++;
            System.out.println("\n===============================================================");
            System.out.println(" TURNO " + contadorTurnos + " — GESTIÓN DE ACCIONES");
            System.out.println("===============================================================");
            jugador.mostrarEstado();

            System.out.println("\nESTADO DEL RIVAL (" + rival.getNombre() + "):");
            System.out.println("  Energía: " + rival.getEnergia() + " | Higiene: " + rival.getHigiene() + " | Ánimo: " + rival.getAnimo());

            System.out.println("\nMenú de Opciones:");
            System.out.println("  1. Dormir (Sleep)");
            System.out.println("  2. Jugar (Play)");
            System.out.println("  3. Comer (Eat)");
            System.out.println("  4. Aseo / Ducha (Clean)");
            System.out.println("  5. Atacar a Rival (Attack)");
            System.out.println("  0. Salir de la aplicación (Exit)");
            System.out.print("Elija una opción (0-5): ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        jugador.dormir();
                        break;
                    case 2:
                        jugador.jugar();
                        break;
                    case 3:
                        jugador.comer();
                        break;
                    case 4:
                        jugador.asear();
                        break;
                    case 5:
                        System.out.print("Ingrese el numero de ataques N a realizar: ");
                        if (scanner.hasNextInt()) {
                            int numAtaques = scanner.nextInt();
                            if (numAtaques > 0) {
                                jugador.atacar(rival, numAtaques);
                            } else {
                                System.out.println("-> el número de ataques debe ser mayor que 0.");
                            }
                        } else {
                            System.out.println("-> debe ingresar un número entero para los ataques.");
                            scanner.next();
                        }
                        break;
                    case 0:
                        salir = true;
                        System.out.println("\nGracias por participar en el simulador Virtual Pet de Guau Street.");
                        break;
                    default:
                        System.out.println("-> Opción fuera de rango (0-5).");
                        break;
                }
            } else {
                System.out.println("-> Entrada inválida. Por favor, introduzca un número del 0 al 5.");
                scanner.next();
            }

            // COMPROBACIÓN GENERAL DE LÍMITES
            if (jugador.estaMuerta()) {
                System.out.println("\n===============================================================");
                System.out.println(" ¡GAME OVER! " + jugador.getNombre() + " HA MUERTO EN EL TURNO " + contadorTurnos + ".");
                System.out.println(" Uno de sus atributos ha caído a <= 0 o ha superado >= 100.");
                System.out.println("===============================================================");

            }

        } while (!salir);

        scanner.close();
    }
}

