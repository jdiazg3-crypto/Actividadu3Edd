import java.util.Scanner;

/**
 * Clase principal del caso: la peluquería.
 * Contiene las tres estructuras y el menú de la aplicación:
 *  - listaGeneral: registro de TODOS los clientes
 *  - colaPendientes: clientes esperando, en orden de llegada (FIFO)
 *  - historialProcesados: clientes ya atendidos (LIFO)
 */
public class SistemaPeluqueria {
    private Lista listaGeneral;
    private Cola colaPendientes;
    private Pila historialProcesados;
    private Scanner scanner;

    public SistemaPeluqueria() {
        listaGeneral = new Lista();
        colaPendientes = new Cola();
        historialProcesados = new Pila();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        SistemaPeluqueria sistema = new SistemaPeluqueria();
        sistema.ejecutar();
    }

    // Bucle principal: muestra el menú hasta que el usuario elige salir
    private void ejecutar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");
            switch (opcion) {
                case 1:
                    registrarElemento();
                    break;
                case 2:
                    verRegistrados();
                    break;
                case 3:
                    verPendientes();
                    break;
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                    System.out.println("Opción aún no implementada.");
                    break;
                case 10:
                    System.out.println("Hasta pronto.");
                    break;
                default:
                    System.out.println("Opción inválida. Elija un número entre 1 y 10.");
            }
        } while (opcion != 10);
    }

    private void mostrarMenu() {
        System.out.println("\n===== PELUQUERÍA =====");
        System.out.println("1. Registrar elemento");
        System.out.println("2. Ver todos los elementos registrados");
        System.out.println("3. Ver elementos pendientes");
        System.out.println("4. Procesar siguiente elemento");
        System.out.println("5. Ver historial de elementos procesados");
        System.out.println("6. Buscar elemento por código");
        System.out.println("7. Cancelar elemento pendiente");
        System.out.println("8. Deshacer último procesamiento");
        System.out.println("9. Ver cantidad de elementos");
        System.out.println("10. Salir");
    }

    /**
     * Registro: valida los datos, guarda el cliente en la lista general
     * y lo encola en pendientes. Ambas estructuras guardan la MISMA
     * referencia al objeto, por eso un cambio de estado se ve en las dos.
     */
    private void registrarElemento() {
        try {
            int turno = leerEntero("Número de turno: ");
            if (turno <= 0) {
                throw new Exception("El número de turno debe ser mayor que 0");
            }
            // Objeto auxiliar solo con el turno: equals() compara únicamente por turno
            if (listaGeneral.contiene(new ClienteServicio(turno, "", "", ""))) {
                throw new Exception("Ya existe un cliente con el turno " + turno);
            }
            String nombre = leerTexto("Nombre del cliente: ");
            String servicio = leerTexto("Servicio (corte, tinte, barba...): ");

            ClienteServicio cliente = new ClienteServicio(turno, nombre, servicio, "Pendiente");
            listaGeneral.agregar(cliente);
            colaPendientes.encolar(cliente);
            System.out.println("Cliente registrado: " + cliente);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void verRegistrados() {
        try {
            System.out.println("--- Todos los clientes registrados ---");
            listaGeneral.mostrarAdelante();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void verPendientes() {
        try {
            System.out.println("--- Clientes pendientes (el 1 es el próximo) ---");
            colaPendientes.mostrar();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Lee un entero y repite la pregunta hasta que el usuario escriba un número válido
    private int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número entero válido.");
            }
        }
    }

    // Lee un texto y repite la pregunta mientras esté vacío
    private String leerTexto(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();
            if (!entrada.isEmpty()) {
                return entrada;
            }
            System.out.println("Este campo no puede estar vacío.");
        }
    }
}