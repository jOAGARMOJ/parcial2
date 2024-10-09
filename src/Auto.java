class Auto extends Vehiculo {
    private String tipoCombustible;

    public Auto(String idVehiculo, String marca, String modelo, int año, double costoDiario, String tipoCombustible) {
        super(idVehiculo, marca, modelo, año, costoDiario);
        this.tipoCombustible = tipoCombustible;
    }

    @Override
    public String toString() {
        return "Auto - " + super.toString() + ", Combustible: " + tipoCombustible;
    }
}