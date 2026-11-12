# Sistema de gestión de la Peluquería (Lista, Cola y Pila)

Actividad Unidad 2 - Estructuras de datos
Universidad de Cartagena - Ingeniería del software
Ejercicio 21: Peluquería | Entidad: ClienteServicio | Identificador: número de turno

## Descripción
Aplicación Java de consola que gestiona los turnos de una peluquería usando
tres estructuras de datos propias, construidas con nodos doblemente enlazados
(sin usar las colecciones de Java):

- **Lista:** registro general de todos los clientes.
- **Cola (FIFO):** clientes pendientes, atendidos en orden de llegada.
- **Pila (LIFO):** historial de clientes procesados y mecanismo para deshacer.

## Estructura del proyecto
| Archivo | Responsabilidad |
|---|---|
| `Nodo.java` | Guarda un dato y las referencias izquierda y derecha |
| `Lista.java` | Lista doblemente enlazada |
| `Cola.java` | Cola FIFO |
| `Pila.java` | Pila LIFO |
| `ClienteServicio.java` | Entidad: turno, nombre, servicio y estado |
| `SistemaPeluqueria.java` | Menú y reglas del sistema |
| `PruebaEstructuras.java` | Pruebas independientes de las tres estructuras |

## Menú
1. Registrar elemento
2. Ver todos los elementos registrados
3. Ver elementos pendientes
4. Procesar siguiente elemento
5. Ver historial de elementos procesados
6. Buscar elemento por código
7. Cancelar elemento pendiente
8. Deshacer último procesamiento
9. Ver cantidad de elementos
10. Salir

## Flujo del sistema
- **Registrar:** el cliente se agrega a la lista general y se encola en pendientes.
- **Procesar:** se desencola el primero de pendientes y se apila en el historial.
- **Cancelar:** se usa una cola auxiliar para sacar al cliente sin alterar el orden de los demás.
- **Deshacer:** se desapila el último procesado y se encola de nuevo en pendientes.

## Cómo ejecutar
Ejecutar la clase `SistemaPeluqueria` (tiene el método `main`) desde IntelliJ IDEA.
Para probar solo las estructuras, ejecutar `PruebaEstructuras`.