class Cliente {
    private String idCliente;
    private String nombre;
    private List<Reserva> reservas = new ArrayList<>();

    public Cliente(String idCliente, String nombre) {
        this.idCliente = idCliente;
        this.nombre = nombre;
    }

    public void reservarVehiculo(Vehiculo vehiculo, LocalDate fechaInicio, LocalDate fechaFin, boolean seguro, boolean gps) {
        if (!vehiculo.isDisponible()) {
            System.out.println("El vehículo no está disponible.");
            return;
        }

        int dias = fechaFin.compareTo(fechaInicio);
        double costoTotal = vehiculo.calcularPrecio(dias, seguro, gps);
        Reserva reserva = new Reserva("R" + reservas.size(), this, vehiculo, fechaInicio, fechaFin, costoTotal);
        reserva.confirmarReserva();
        reservas.add(reserva);

        System.out.println("Reserva realizada para " + nombre + ". Costo total: $" + costoTotal);
    }
}