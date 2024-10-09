class Camioneta extends Vehiculo {
    private int capacidadCarga;

    public Camioneta(String idVehiculo, String marca, String modelo, int año, double costoDiario, int capacidadCarga) {
        super(idVehiculo, marca, modelo, año, costoDiario);
        this.capacidadCarga = capacidadCarga;
    }

    @Override
    public String toString() {
        return "Camioneta - " + super.toString() + ", Capacidad de carga: " + capacidadCarga + "kg";
    }
}
