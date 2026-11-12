/**
 * Nodo doblemente enlazado.
 * Guarda un dato (Object, para poder almacenar cualquier tipo de objeto)
 * y dos referencias: una al nodo de la izquierda (anterior) y otra al
 * nodo de la derecha (siguiente).
 */
public class Nodo {
    private Object dato;
    private Nodo izquierda;
    private Nodo derecha;

    // Constructor vacío: nodo sin dato y sin vecinos
    public Nodo() {
    }

    // Constructor con dato: los vecinos quedan en null hasta que se enlacen
    public Nodo(Object dato) {
        this.dato = dato;
    }

    public Object getDato() {
        return dato;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

    public Nodo getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(Nodo vecino) {
        this.izquierda = vecino;
    }

    public Nodo getDerecha() {
        return derecha;
    }

    public void setDerecha(Nodo vecino) {
        this.derecha = vecino;
    }
}