import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class Administrador {
    private List<Vehiculo> flota;
    private List<Cliente> clientesConVehiculoAlquilado;


    public Administrador() {
        this.flota = new ArrayList<>();
        this.clientesConVehiculoAlquilado = new ArrayList<>();
    }


    public void añadirVehiculo(Vehiculo vehiculo) {
        flota.add(vehiculo);
        System.out.println("Vehículo añadido a la flota: " + vehiculo);
    }


    public boolean verificarDisponibilidad(Vehiculo vehiculo, LocalDate fechaInicio, LocalDate fechaFin) {
        return vehiculo.isDisponible();
    }


    public List<Vehiculo> listarVehiculosDisponibles() {
        List<Vehiculo> disponibles = new ArrayList<>();
        for (Vehiculo vehiculo : flota) {
            if (vehiculo.isDisponible()) {
                disponibles.add(vehiculo);
            }
        }
        return disponibles;
    }

    public boolean clienteTieneVehiculoAlquiladoPorNombre(String nombre) {
        for (Cliente cliente : clientesConVehiculoAlquilado) {
            if (cliente.getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }


}