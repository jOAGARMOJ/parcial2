import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String idCliente;
    private String nombre;
    private List<Reserva> reservas;

    public Cliente(String idCliente, String nombre) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.reservas = new ArrayList<>();
    }

    public String getIdCliente() {
        return idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void reservarVehiculo(Vehiculo vehiculo, LocalDate fechaInicio, LocalDate fechaFin, boolean seguro, boolean gps) {
        // Verificar que el vehículo esté disponible
        if (!vehiculo.isDisponible()) {
            System.out.println("El vehículo no está disponible para la reserva.");
            return;
        }

        // Crear una nueva reserva
        Reserva nuevaReserva = new Reserva(vehiculo, fechaInicio, fechaFin, seguro, gps);
        reservas.add(nuevaReserva);
        vehiculo.setDisponibilidad(false); // Marcar el vehículo como no disponible

        System.out.println("Reserva confirmada: " + nuevaReserva);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente='" + idCliente + '\'' +
                ", nombre='" + nombre + '\'' +
                ", reservas=" + reservas +
                '}';
    }
}