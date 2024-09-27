import java.util.Random;
import java.util.Scanner;

// Clase base para las habilidades
class Habilidad {
    private String nombre;
    private int nivel;

    public Habilidad(String nombre, int nivel) {
        this.nombre = nombre;
        this.nivel = nivel;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public void aumentarNivel(int cantidad) {
        nivel += cantidad;
        if (nivel < 0) {
            nivel = 0; // Asegura que el nivel no sea negativo
        }
    }

    @Override
    public String toString() {
        return nombre + ": Nivel " + nivel;
    }
}

// Clase Héroe
class Heroe {
    private String nombre;
    private Habilidad fuerza;
    private Habilidad agilidad;
    private Habilidad inteligencia;

    public Heroe(String nombre) {
        this.nombre = nombre;
        this.fuerza = new Habilidad("Fuerza", 50);
        this.agilidad = new Habilidad("Agilidad", 50);
        this.inteligencia = new Habilidad("Inteligencia", 50);
    }

    public int getFuerza() {
        return fuerza.getNivel();
    }

    public int getAgilidad() {
        return agilidad.getNivel();
    }

    public int getInteligencia() {
        return inteligencia.getNivel();
    }

    public void entrenarFuerza() {
        fuerza.aumentarNivel(10);
    }

    public void entrenarAgilidad() {
        agilidad.aumentarNivel(10);
    }

    public void entrenarInteligencia() {
        inteligencia.aumentarNivel(10);
    }

    // Actividades que suben una habilidad y bajan otra
    public void hacerEjercicioFisico() {
        fuerza.aumentarNivel(15);
        inteligencia.aumentarNivel(-5);
        System.out.println("Has hecho ejercicio físico. Fuerza +15, Inteligencia -5.");
    }

    public void resolverProblemasMatematicos() {
        inteligencia.aumentarNivel(15);
        agilidad.aumentarNivel(-5);
        System.out.println("Has resuelto problemas matemáticos. Inteligencia +15, Agilidad -5.");
    }

    public void practicarParkour() {
        agilidad.aumentarNivel(15);
        fuerza.aumentarNivel(-5);
        System.out.println("Has practicado parkour. Agilidad +15, Fuerza -5.");
    }

    public void mostrarEstado() {
        System.out.println("\nEstado actual del héroe: " + nombre);
        System.out.println(fuerza);
        System.out.println(agilidad);
        System.out.println(inteligencia);
    }

    // Método para iniciar una batalla
    public void batalla(Enemigo enemigo) {
        System.out.println("\n¡Comienza la batalla contra " + enemigo.getNombre() + "!");
        System.out.println("Estadísticas del enemigo:");
        enemigo.mostrarEstado();

        // Determinar ganador basado en estadísticas
        Random random = new Random();
        int fuerzaTotal = this.getFuerza() + random.nextInt(20);  // Se añade un factor de azar
        int agilidadTotal = this.getAgilidad() + random.nextInt(20);
        int inteligenciaTotal = this.getInteligencia() + random.nextInt(20);

        int fuerzaEnemigoTotal = enemigo.getFuerza() + random.nextInt(20);
        int agilidadEnemigoTotal = enemigo.getAgilidad() + random.nextInt(20);
        int inteligenciaEnemigoTotal = enemigo.getInteligencia() + random.nextInt(20);

        int puntuacionHeroe = fuerzaTotal + agilidadTotal + inteligenciaTotal;
        int puntuacionEnemigo = fuerzaEnemigoTotal + agilidadEnemigoTotal + inteligenciaEnemigoTotal;

        System.out.println("\nPuntuación del héroe: " + puntuacionHeroe);
        System.out.println("Puntuación del enemigo: " + puntuacionEnemigo);

        if (puntuacionHeroe > puntuacionEnemigo) {
            System.out.println("\n¡Has ganado la batalla!");
        } else {
            System.out.println("\nHas perdido la batalla...");
        }
    }
}

// Clase Enemigo
class Enemigo {
    private String nombre;
    private Habilidad fuerza;
    private Habilidad agilidad;
    private Habilidad inteligencia;

    public Enemigo(String nombre, int fuerza, int agilidad, int inteligencia) {
        this.nombre = nombre;
        this.fuerza = new Habilidad("Fuerza", fuerza);
        this.agilidad = new Habilidad("Agilidad", agilidad);
        this.inteligencia = new Habilidad("Inteligencia", inteligencia);
    }

    public String getNombre() {
        return nombre;
    }

    public int getFuerza() {
        return fuerza.getNivel();
    }

    public int getAgilidad() {
        return agilidad.getNivel();
    }

    public int getInteligencia() {
        return inteligencia.getNivel();
    }

    public void mostrarEstado() {
        System.out.println(fuerza);
        System.out.println(agilidad);
        System.out.println(inteligencia);
    }
}

// Clase principal del simulador de entrenamiento
public class SimuladorEntrenamientoHeroe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el nombre de tu héroe:");
        String nombreHeroe = scanner.nextLine();
        Heroe heroe = new Heroe(nombreHeroe);

        boolean salir = false;

        while (!salir) {
            System.out.println("\n¿Qué deseas hacer?");
            System.out.println("1. Entrenar Fuerza");
            System.out.println("2. Entrenar Agilidad");
            System.out.println("3. Entrenar Inteligencia");
            System.out.println("4. Hacer ejercicio físico (Aumenta Fuerza, Reduce Inteligencia)");
            System.out.println("5. Resolver problemas matemáticos (Aumenta Inteligencia, Reduce Agilidad)");
            System.out.println("6. Practicar Parkour (Aumenta Agilidad, Reduce Fuerza)");
            System.out.println("7. Iniciar batalla contra un enemigo");
            System.out.println("8. Mostrar estado del héroe");
            System.out.println("9. Salir");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    heroe.entrenarFuerza();
                    System.out.println("Has entrenado Fuerza.");
                    break;
                case 2:
                    heroe.entrenarAgilidad();
                    System.out.println("Has entrenado Agilidad.");
                    break;
                case 3:
                    heroe.entrenarInteligencia();
                    System.out.println("Has entrenado Inteligencia.");
                    break;
                case 4:
                    heroe.hacerEjercicioFisico();
                    break;
                case 5:
                    heroe.resolverProblemasMatematicos();
                    break;
                case 6:
                    heroe.practicarParkour();
                    break;
                case 7:
                    Enemigo enemigo = new Enemigo("Dragón Maligno", 60, 40, 30);
                    heroe.batalla(enemigo);
                    break;
                case 8:
                    heroe.mostrarEstado();
                    break;
                case 9:
                    salir = true;
                    System.out.println("Saliendo del simulador.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }

        scanner.close();
    }
}
