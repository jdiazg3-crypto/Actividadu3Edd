import java.util.Objects;

/**
 * Entidad principal del caso: un cliente que solicita un servicio
 * en la peluquería. Se identifica por su número de turno.
 */
public class ClienteServicio {
    private int numeroTurno;        // identificador principal
    private String nombreCliente;
    private String servicio;        // corte, tinte, barba, etc.
    private String estado;          // Pendiente, Procesado, Cancelado

    public ClienteServicio(int numeroTurno, String nombreCliente, String servicio, String estado) {
        this.numeroTurno = numeroTurno;
        this.nombreCliente = nombreCliente;
        this.servicio = servicio;
        this.estado = estado;
    }

    public int getNumeroTurno() {
        return numeroTurno;
    }

    public void setNumeroTurno(int numeroTurno) {
        this.numeroTurno = numeroTurno;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Turno #" + numeroTurno
                + " | Cliente: " + nombreCliente
                + " | Servicio: " + servicio
                + " | Estado: " + estado;
    }

    /**
     * Dos clientes son iguales si tienen el mismo número de turno.
     * Es el criterio que usarán las estructuras al buscar o comparar.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ClienteServicio otro = (ClienteServicio) obj;
        return this.numeroTurno == otro.numeroTurno;
    }

    // Se mantiene coherente con equals(): mismo turno, mismo hash
    @Override
    public int hashCode() {
        return Objects.hash(numeroTurno);
    }
}