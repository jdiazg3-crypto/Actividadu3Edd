/**
 * Cola doblemente enlazada (FIFO: el primero en entrar es el primero en salir).
 * Guarda los clientes pendientes por atender, en orden de llegada.
 * "frente" es el próximo en ser atendido y "fin" es el último que llegó.
 */
public class Cola {
    private int cuenta;
    private Nodo frente;
    private Nodo fin;

    public Cola() {
        cuenta = 0;
        frente = null;
        fin = null;
    }

    public boolean esVacia() {
        return cuenta == 0;
    }

    public int tamanio() {
        return cuenta;
    }

    /**
     * Encolar: agrega SIEMPRE al final de la cola.
     * Si estaba vacía, el nuevo nodo es frente y fin a la vez.
     * Si no, se enlaza a la derecha de "fin" y pasa a ser el nuevo "fin".
     */
    public void encolar(Object dato) throws Exception {
        if (dato == null) {
            throw new Exception("El dato no puede ser nulo");
        }
        Nodo nuevo = new Nodo(dato);
        if (esVacia()) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.setDerecha(nuevo);
            nuevo.setIzquierda(fin);
            fin = nuevo;
        }
        ++cuenta;
    }

    /**
     * Desencolar: saca SIEMPRE del frente y devuelve su dato.
     * El frente avanza al nodo de la derecha; si no queda nadie,
     * la cola queda vacía y "fin" también pasa a null.
     */
    public Object desencolar() throws Exception {
        if (esVacia()) {
            throw new Exception("La cola está vacía");
        }
        Object dato = frente.getDato();
        frente = frente.getDerecha();
        if (frente != null) {
            frente.setIzquierda(null);
        } else {
            fin = null;
        }
        --cuenta;
        return dato;
    }

    /**
     * Peek: consulta quién está al frente sin sacarlo de la cola.
     */
    public Object peek() throws Exception {
        if (esVacia()) {
            throw new Exception("La cola está vacía");
        }
        return frente.getDato();
    }
    /**
     * Recorre la cola desde el frente comparando con equals().
     * No altera la cola: solo mira, no saca nada.
     */
    public boolean contiene(Object dato) {
        if (dato == null) {
            return false;
        }
        Nodo actual = frente;
        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                return true;
            }
            actual = actual.getDerecha();
        }
        return false;
    }

    // Vacía la cola soltando las referencias al frente y al fin
    public void limpiar() {
        frente = null;
        fin = null;
        cuenta = 0;
    }

    /**
     * Muestra la cola en orden de atención: del frente (próximo)
     * hacia el fin (último en llegar).
     */
    public void mostrar() throws Exception {
        if (esVacia()) {
            throw new Exception("La cola está vacía");
        }
        Nodo actual = frente;
        int posicion = 1;
        while (actual != null) {
            System.out.println(posicion + ". " + actual.getDato());
            actual = actual.getDerecha();
            posicion++;
        }
    }
}