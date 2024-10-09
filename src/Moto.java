
class Moto extends Vehiculo {
    private int cilindrada;

    public Moto(String idVehiculo, String marca, String modelo, int año, double costoDiario, int cilindrada) {
        super(idVehiculo, marca, modelo, año, costoDiario);
        this.cilindrada = cilindrada;
    }

    @Override
    public String toString() {
        return "Moto - " + super.toString() + ", Cilindrada: " + cilindrada + "cc";
    }
}
