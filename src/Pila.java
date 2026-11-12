/**
 * Pila doblemente enlazada (LIFO: el último en entrar es el primero en salir).
 * Guarda el historial de clientes procesados.
 * "cima" es el tope: el último cliente procesado.
 * La referencia izquierda de cada nodo apunta al nodo de abajo.
 */
public class Pila {
    private int cuenta;
    private Nodo cima;

    public Pila() {
        cuenta = 0;
        cima = null;
    }

    public boolean esVacia() {
        return cuenta == 0;
    }

    public int tamanio() {
        return cuenta;
    }

    /**
     * Apilar: el nuevo nodo se coloca SIEMPRE encima.
     * Queda enlazado con el nodo de abajo (izquierda) y pasa a ser la cima.
     */
    public void apilar(Object dato) throws Exception {
        if (dato == null) {
            throw new Exception("El dato no puede ser nulo");
        }
        Nodo nuevo = new Nodo(dato);
        if (esVacia()) {
            cima = nuevo;
        } else {
            nuevo.setIzquierda(cima);
            cima.setDerecha(nuevo);
            cima = nuevo;
        }
        ++cuenta;
    }

    /**
     * Desapilar: quita SIEMPRE la cima y devuelve su dato.
     * La nueva cima es el nodo de abajo, que deja de apuntar hacia arriba.
     */
    public Object desapilar() throws Exception {
        if (esVacia()) {
            throw new Exception("La pila está vacía");
        }
        Object dato = cima.getDato();
        cima = cima.getIzquierda();
        if (cima != null) {
            cima.setDerecha(null);
        }
        --cuenta;
        return dato;
    }

    /**
     * Peek: consulta la cima sin quitarla.
     */
    public Object peek() throws Exception {
        if (esVacia()) {
            throw new Exception("La pila está vacía");
        }
        return cima.getDato();
    }
    /**
     * Recorre la pila desde la cima hacia abajo (getIzquierda)
     * comparando con equals(). No modifica la pila.
     */
    public boolean contiene(Object dato) {
        if (dato == null) {
            return false;
        }
        Nodo actual = cima;
        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                return true;
            }
            actual = actual.getIzquierda();
        }
        return false;
    }

    // Vacía la pila soltando la referencia a la cima
    public void limpiar() {
        cima = null;
        cuenta = 0;
    }

    /**
     * Muestra la pila de arriba hacia abajo: primero el más
     * reciente (cima) y al final el más antiguo.
     */
    public void mostrar() throws Exception {
        if (esVacia()) {
            throw new Exception("La pila está vacía");
        }
        Nodo actual = cima;
        int posicion = 1;
        while (actual != null) {
            System.out.println(posicion + ". " + actual.getDato());
            actual = actual.getIzquierda();
            posicion++;
        }
    }

    /**
     * Busca un dato y devuelve su posición contada desde la cima
     * (1 = cima). Lanza excepción si la pila está vacía o no lo encuentra.
     */
    public int buscar(Object dato) throws Exception {
        if (esVacia()) {
            throw new Exception("La pila está vacía");
        }
        Nodo actual = cima;
        int posicion = 1;
        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                return posicion;
            }
            actual = actual.getIzquierda();
            posicion++;
        }
        throw new Exception("El elemento no está en la pila");
    }
}