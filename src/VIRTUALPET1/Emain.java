package VIRTUALPET1;

import java.util.Scanner;

public class Emain {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            mostrarCabecera();

            // 1. Elección de especie y nombre (delegado a métodos auxiliares)
            String tipoMascota = seleccionarEspecie(scanner);
            String nombreMascota = solicitarNombre(scanner, tipoMascota);

            // 2. Creación de objetos
            Mascota jugador = new Mascota(nombreMascota, tipoMascota);
            Mascota rival = new Mascota("(Rival)");

            System.out.println("\n¡Has creado a " + jugador.nombre + " (" + jugador.tipo + ")!");
            System.out.println("Rival " + rival.nombre + " inicializado a 100 puntos en sus valores.");

            // 3. Bucle principal de juego
            iniciarJuego(scanner, jugador, rival);

            scanner.close();
        }

     //METODOS AUXILIARES

        public static void mostrarCabecera() {
            System.out.println("===============================================================");
            System.out.println("       SIMULADOR — TAMASCOTCHI GUAU STREET");
            System.out.println("===============================================================");
        }

        public static String seleccionarEspecie(Scanner scanner) {
            int tipoOpcion = 0;
            do {
                System.out.println("\nSeleccionar tipo mascota:");
                System.out.println("  1. Perro");
                System.out.println("  2. Gato");
                tipoOpcion = pedirEntero(scanner, "Ingresar opción (1 o 2): ");

                if (tipoOpcion != 1 && tipoOpcion != 2) {
                    System.out.println("-> Opción no válida. Ingrese 1 o 2.");
                }
            } while (tipoOpcion != 1 && tipoOpcion != 2);

            return (tipoOpcion == 1) ? "Perro" : "Gato";
        }

        public static String solicitarNombre(Scanner scanner, String tipoMascota) {
            String nombre = "";
            do {
                System.out.print("\nIngrese el nombre de su " + tipoMascota + ": ");
                nombre = scanner.nextLine().trim();

                if (nombre.isEmpty()) {
                    System.out.println("-> El nombre no puede estar vacío.");
                }
            } while (nombre.isEmpty());

            return nombre;
        }

        public static void iniciarJuego(Scanner scanner, Mascota jugador, Mascota rival) {
            boolean salir = false;
            int contadorTurnos = 0;

            do {
                contadorTurnos++;
                System.out.println("\n===============================================================");
                System.out.println(" TURNO " + contadorTurnos + " — GESTIÓN DE ACCIONES");
                System.out.println("===============================================================");

                jugador.mostrarEstado();

                System.out.println("\nESTADO DEL RIVAL (" + rival.nombre + "):");
                System.out.println("  Energía: " + rival.energia + " | Higiene: " + rival.higiene + " | Ánimo: " + rival.animo);

                mostrarMenu();
                int opcion = pedirEntero(scanner, "Elija una opción (0-5): ");

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
                        procesarAtaque(scanner, jugador, rival);
                        break;
                    case 0:
                        salir = true;
                        System.out.println("\nGracias por participar en el simulador Virtual Pet de Guau Street.");
                        break;
                    default:
                        System.out.println("-> Opción fuera de rango (0-5).");
                        break;
                }

                if (jugador.estaMuerta()) {
                    System.out.println("\n===============================================================");
                    System.out.println(" ¡GAME OVER! " + jugador.nombre + " HA MUERTO EN EL TURNO " + contadorTurnos + ".");
                    System.out.println(" Uno de sus atributos ha caído a <= 0 o ha superado >= 100.");
                    System.out.println("===============================================================");
                    salir = true;
                }

            } while (!salir);
        }

        public static void mostrarMenu() {
            System.out.println("\nMenú de Opciones:");
            System.out.println("  1. Dormir (Sleep)");
            System.out.println("  2. Jugar (Play)");
            System.out.println("  3. Comer (Eat)");
            System.out.println("  4. Aseo / Ducha (Clean)");
            System.out.println("  5. Atacar a Rival (Attack)");
            System.out.println("  0. Salir de la aplicación (Exit)");
        }

        public static void procesarAtaque(Scanner scanner, Mascota jugador, Mascota rival) {
            int numAtaques = pedirEntero(scanner, "Ingrese el número de ataques a realizar: ");
            if (numAtaques > 0) {
                jugador.atacar(rival, numAtaques);
            } else {
                System.out.println("-> El número de ataques debe ser mayor que 0.");
            }
        }

        // lEER ENTEROS PARA EVITAR CORTES
        public static int pedirEntero(Scanner scanner, String mensaje) {
            while (true) {
                System.out.print(mensaje);
                String entrada = scanner.nextLine().trim();
                try {
                    return Integer.parseInt(entrada);
                } catch (NumberFormatException e) {
                    System.out.println("-> Error de entrada. Por favor, ingrese un número entero.");
                }
            }
        }
    }


