import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class Administrador {
    private List<Vehiculo> flota;

    // Constructor que inicializa la lista de vehículos
    public Administrador() {
        this.flota = new ArrayList<>();
    }

    // Método para añadir un nuevo vehículo a la flota
    public void añadirVehiculo(Vehiculo vehiculo) {
        flota.add(vehiculo);
        System.out.println("Vehículo añadido a la flota: " + vehiculo);
    }

    // Método para verificar la disponibilidad de un vehículo en un rango de fechas
    public boolean verificarDisponibilidad(Vehiculo vehiculo, LocalDate fechaInicio, LocalDate fechaFin) {
        return vehiculo.isDisponible();
    }

    // Método para listar todos los vehículos disponibles en la flota
    public List<Vehiculo> listarVehiculosDisponibles() {
        List<Vehiculo> disponibles = new ArrayList<>();
        for (Vehiculo vehiculo : flota) {
            if (vehiculo.isDisponible()) {
                disponibles.add(vehiculo);
            }
        }
        return disponibles;
    }
}