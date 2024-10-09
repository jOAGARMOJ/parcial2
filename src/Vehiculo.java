abstract class Vehiculo {
    protected String idVehiculo;
    protected String marca;
    protected String modelo;
    protected int año;
    protected double costoDiario;
    protected boolean disponibilidad = true;

    public Vehiculo(String idVehiculo, String marca, String modelo, int año, double costoDiario) {
        this.idVehiculo = idVehiculo;
        this.marca = marca;
        this.modelo = modelo;
        this.año = año;
        this.costoDiario = costoDiario;
    }

    public boolean isDisponible() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public double calcularPrecio(int dias, boolean seguro, boolean gps) {
        double total = costoDiario * dias;
        if (seguro) {
            total += total * 0.1; // 10% adicional por seguro
        }
        if (gps) {
            total += 5 * dias; // $5 por día de GPS
        }
        return total;
    }

    @Override
    public String toString() {
        return marca + " " + modelo + " (" + año + "), Costo diario: $" + costoDiario;
    }
}
