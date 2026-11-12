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
                    procesarSiguiente();
                    break;
                case 5:
                    verHistorial();
                    break;
                case 6:
                    buscarElemento();
                    break;
                case 7:
                    cancelarPendiente();
                    break;
                case 8:
                    deshacerProcesamiento();
                    break;
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
    /**
     * Procesar: saca de la cola al cliente que lleva más tiempo esperando (FIFO),
     * le cambia el estado y lo apila en el historial (LIFO).
     * La lista general no se toca: sigue teniendo al cliente, y como guarda
     * la misma referencia al objeto, también verá el nuevo estado.
     */
    private void procesarSiguiente() {
        try {
            if (colaPendientes.esVacia()) {
                throw new Exception("No hay clientes pendientes por procesar");
            }
            ClienteServicio procesado = (ClienteServicio) colaPendientes.desencolar();
            procesado.setEstado("Procesado");
            historialProcesados.apilar(procesado);
            System.out.println("Cliente procesado: " + procesado);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private void verHistorial() {
        try {
            System.out.println("--- Historial de procesados (el 1 es el más reciente) ---");
            historialProcesados.mostrar();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    /**
     * Busca en la lista general (que tiene a todos los clientes).
     * Se crea un cliente auxiliar solo con el turno: equals() compara por
     * turno, así que la lista lo encuentra y devuelve su posición.
     * Con esa posición se recupera el cliente real.
     */
    private void buscarElemento() {
        try {
            int turno = leerEntero("Número de turno a buscar: ");
            ClienteServicio buscado = new ClienteServicio(turno, "", "", "");
            int posicion = listaGeneral.buscarDato(buscado);
            ClienteServicio encontrado = (ClienteServicio) listaGeneral.buscarDato(posicion);
            System.out.println("Encontrado en la posición " + posicion + " de la lista: " + encontrado);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    /**
     * Cancelar: la guía no permite borrar del centro de la cola, así que se usa
     * una cola auxiliar. Se vacía la cola de pendientes enviando cada cliente
     * a la auxiliar (excepto el cancelado) y luego se devuelven todos a la cola
     * original. Como se respeta el orden FIFO en ambos viajes, los demás
     * clientes conservan su orden de llegada.
     * El cliente cancelado sigue en la lista general, con estado "Cancelado".
     */
    private void cancelarPendiente() {
        try {
            if (colaPendientes.esVacia()) {
                throw new Exception("No hay clientes pendientes para cancelar");
            }
            int turno = leerEntero("Número de turno a cancelar: ");
            ClienteServicio buscado = new ClienteServicio(turno, "", "", "");
            if (!colaPendientes.contiene(buscado)) {
                throw new Exception("El turno " + turno + " no está en la cola de pendientes");
            }

            Cola auxiliar = new Cola();
            ClienteServicio cancelado = null;

            // 1) Vaciar la cola de pendientes hacia la auxiliar, saltando al cancelado
            while (!colaPendientes.esVacia()) {
                ClienteServicio actual = (ClienteServicio) colaPendientes.desencolar();
                if (actual.equals(buscado)) {
                    actual.setEstado("Cancelado");
                    cancelado = actual;
                } else {
                    auxiliar.encolar(actual);
                }
            }

            // 2) Devolver los clientes restantes a la cola de pendientes
            while (!auxiliar.esVacia()) {
                colaPendientes.encolar(auxiliar.desencolar());
            }

            System.out.println("Cliente cancelado: " + cancelado);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    /**
     * Deshacer: la pila entrega al último cliente procesado (LIFO),
     * se le devuelve el estado "Pendiente" y se encola de nuevo.
     * Siguiendo la regla de la guía, vuelve a entrar por el FINAL de la cola.
     */
    private void deshacerProcesamiento() {
        try {
            if (historialProcesados.esVacia()) {
                throw new Exception("No hay procesamientos para deshacer");
            }
            ClienteServicio ultimo = (ClienteServicio) historialProcesados.desapilar();
            ultimo.setEstado("Pendiente");
            colaPendientes.encolar(ultimo);
            System.out.println("Procesamiento deshecho. Vuelve a pendientes: " + ultimo);
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