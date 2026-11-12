/**
 * Lista doblemente enlazada.
 * Registra TODOS los clientes del sistema (lista general).
 * Mantiene referencias al primer y último nodo, y un contador de elementos.
 */
public class Lista {
    private int cuenta;
    private Nodo primero;
    private Nodo ultimo;

    public Lista() {
        cuenta = 0;
        primero = null;
        ultimo = null;
    }

    public boolean esVacia() {
        return cuenta == 0;
    }

    public int cuentaElementos() {
        return cuenta;
    }

    /**
     * Agrega un dato al final de la lista.
     * Si está vacía, el nuevo nodo es a la vez primero y último.
     * Si no, se enlaza a la derecha del último y se actualiza "ultimo".
     */
    public void agregar(Object dato) throws Exception {
        if (dato == null) {
            throw new Exception("El dato no puede ser nulo");
        }
        Nodo objetivo = new Nodo(dato);
        if (esVacia()) {
            objetivo.setIzquierda(null);
            objetivo.setDerecha(null);
            primero = objetivo;
            ultimo = objetivo;
        } else {
            ultimo.setDerecha(objetivo);
            objetivo.setIzquierda(ultimo);
            objetivo.setDerecha(null);
            ultimo = objetivo;
        }
        ++cuenta;
    }

    /**
     * Método privado: recorre desde el primero hacia la derecha
     * hasta llegar al nodo que está en la posición indicada.
     */
    private Nodo buscar(int indice) throws Exception {
        if (esVacia()) {
            throw new Exception("La lista está vacía");
        }
        if (indice < 0 || indice >= cuenta) {
            throw new Exception("La posición " + indice + " está fuera del rango 0 - " + (cuenta - 1));
        }
        Nodo actual = primero;
        for (int i = 0; i < indice; i++) {
            actual = actual.getDerecha();
        }
        return actual;
    }

    /**
     * Método privado: recorre la lista comparando con equals().
     * Como ClienteServicio compara por número de turno, busca por turno.
     * Devuelve la posición donde se encontró el dato.
     */
    private int buscar(Object dato) throws Exception {
        if (esVacia()) {
            throw new Exception("La lista está vacía");
        }
        Nodo actual = primero;
        for (int i = 0; i < cuenta; i++) {
            if (actual.getDato().equals(dato)) {
                return i;
            }
            actual = actual.getDerecha();
        }
        throw new Exception("El elemento no está en la lista");
    }

    // Devuelve el dato que está en una posición
    public Object buscarDato(int indice) throws Exception {
        Nodo objetivo = buscar(indice);
        return objetivo.getDato();
    }

    // Devuelve la posición en la que está un dato
    public int buscarDato(Object dato) throws Exception {
        return buscar(dato);
    }

    // Devuelve true o false sin lanzar excepción si no lo encuentra
    public boolean contiene(Object dato) {
        if (dato == null) {
            return false;
        }
        Nodo actual = primero;
        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                return true;
            }
            actual = actual.getDerecha();
        }
        return false;
    }
}