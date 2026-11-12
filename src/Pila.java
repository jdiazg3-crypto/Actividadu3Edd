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
}