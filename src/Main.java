import java.time.LocalDate;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Crear Administrador y Flota
        Administrador admin = new Administrador();

        // Añadir algunos vehículos por defecto a la flota
        Auto auto1 = new Auto("A1", "Toyota", "Corolla", 2022, 50000, "Gasolina");
        Camioneta camioneta1 = new Camioneta("C1", "Ford", "Ranger", 2020, 80000, 1000);
        Moto moto1 = new Moto("M1", "Honda", "CBR500R", 2021, 40000, 1200);
        Moto moto2 = new Moto("M2", "Suzuki", "Vstrom", 2023, 40000, 1000);
        Moto moto3 = new Moto("M3", "Yamaha", "MT09", 2024, 40000, 900);
        Autobus autobus1 = new Autobus("B1", "Mercedes-Benz", "Boxer", 2024, 120, 25);
        Autobus autobus2 = new Autobus("B2", "Mercedes-Benz", "Torino", 2022, 120, 25);
        Autobus autobus3 = new Autobus("B3", "Mercedes-Benz", "Paradiso", 2017, 120, 25);
        Camioneta camioneta2 = new Camioneta("C2", "Chevrolet", "Luv1600", 1990, 80000, 1000);
        Camioneta camioneta3 = new Camioneta("C3", "Ford", "Raptor", 2024, 80000, 1000);
        Auto auto2 = new Auto("A2", "Chevrolet", "Spark", 2016, 50000, "Gasolina");
        Auto auto3 = new Auto("A3", "Fiat", "Uno", 1987, 50000, "Gasolina");

        admin.añadirVehiculo(auto1);
        admin.añadirVehiculo(auto2);
        admin.añadirVehiculo(auto3);
        admin.añadirVehiculo(camioneta1);
        admin.añadirVehiculo(camioneta2);
        admin.añadirVehiculo(camioneta3);
        admin.añadirVehiculo(moto1);
        admin.añadirVehiculo(moto2);
        admin.añadirVehiculo(moto3);
        admin.añadirVehiculo(autobus1);
        admin.añadirVehiculo(autobus2);
        admin.añadirVehiculo(autobus3);

        // Menú principal
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Bienvenido al sistema de alquiler de vehículos");
            System.out.println("1. Cliente");
            System.out.println("2. Administrador");
            System.out.println("3. Salir");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            if (opcion == 1) {
                menuCliente(admin, scanner);  // Menú para cliente
            } else if (opcion == 2) {
                menuAdministrador(admin, scanner);  // Menú para administrador
            } else if (opcion == 3) {
                System.out.println("Gracias por usar el sistema.");
                break;
            } else {
                System.out.println("Opción no válida.");
            }
        }
    }

    // Menú para el cliente
    private static void menuCliente(Administrador admin, Scanner scanner) {
        System.out.println("Ingrese su ID de cliente:");
        String idCliente = scanner.nextLine();
        System.out.println("Ingrese su nombre:");
        String nombre = scanner.nextLine();

        if (admin.clienteTieneVehiculoAlquiladoPorNombre(nombre)) {
            System.out.println("Ya tienes un vehículo alquilado y no puedes alquilar más.");
            return;
        }

        Cliente cliente = new Cliente(idCliente, nombre);










        System.out.println("Seleccione el tipo de vehículo que desea alquilar:");
        System.out.println("1. Auto");
        System.out.println("2. Moto");
        System.out.println("3. Camioneta");
        System.out.println("4. Autobús");
        int tipoVehiculo = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        Vehiculo vehiculoSeleccionado = null;
        switch (tipoVehiculo) {
            case 1:
                vehiculoSeleccionado = elegirVehiculo(admin, Auto.class);
                break;
            case 2:
                vehiculoSeleccionado = elegirVehiculo(admin, Moto.class);
                break;
            case 3:
                vehiculoSeleccionado = elegirVehiculo(admin, Camioneta.class);
                break;
            case 4:
                vehiculoSeleccionado = elegirVehiculo(admin, Autobus.class);
                break;
            default:
                System.out.println("Opción no válida.");
                return;
        }

        if (vehiculoSeleccionado != null) {
            LocalDate fechaInicio = null;
            LocalDate fechaFin = null;

            // Captura de fecha de inicio con manejo de excepciones
            while (fechaInicio == null) {
                try {
                    System.out.println("Ingrese la fecha de inicio de la reserva (formato AAAA-MM-DD):");
                    fechaInicio = LocalDate.parse(scanner.nextLine());
                } catch (Exception e) {
                    System.out.println("Fecha de inicio inválida. Intente nuevamente.");
                }
            }

            // Captura de fecha de fin con manejo de excepciones
            while (fechaFin == null) {
                try {
                    System.out.println("Ingrese la fecha de fin de la reserva (formato AAAA-MM-DD):");
                    fechaFin = LocalDate.parse(scanner.nextLine());
                } catch (Exception e) {
                    System.out.println("Fecha de fin inválida. Intente nuevamente.");
                }
            }

            System.out.println("¿Desea incluir seguro? (true/false):");
            boolean seguro = scanner.nextBoolean();
            System.out.println("¿Desea incluir GPS? (true/false):");
            boolean gps = scanner.nextBoolean();
            scanner.nextLine();

            cliente.reservarVehiculo(vehiculoSeleccionado, fechaInicio, fechaFin, seguro, gps);

            double costoTotal = vehiculoSeleccionado.calcularPrecio((int) (fechaFin.toEpochDay() - fechaInicio.toEpochDay()), seguro, gps);
            System.out.printf("El costo total del alquiler es: %.2f%n", costoTotal);


            if (admin.verificarDisponibilidad(vehiculoSeleccionado, fechaInicio, fechaFin)) {
                cliente.reservarVehiculo(vehiculoSeleccionado, fechaInicio, fechaFin, seguro, gps);
                System.out.println("Reserva confirmada con éxito.");
            } else {
                System.out.println("El vehículo no está disponible en las fechas seleccionadas.");
            }
        }
    }

    // Menú para el administrador
    private static void menuAdministrador(Administrador admin, Scanner scanner) {
        System.out.println("Añadir un nuevo vehículo a la flota:");
        System.out.println("Ingrese el tipo de vehículo a añadir (1. Auto, 2. Moto, 3. Camioneta, 4. Autobús):");
        int tipoVehiculo = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        System.out.println("Ingrese la marca del vehículo:");
        String marca = scanner.nextLine();
        System.out.println("Ingrese el modelo del vehículo:");
        String modelo = scanner.nextLine();
        System.out.println("Ingrese el año del vehículo:");
        int año = scanner.nextInt();
        System.out.println("Ingrese el costo diario del vehículo:");
        double costoDiario = scanner.nextDouble();
        scanner.nextLine(); // Limpiar el buffer

        Vehiculo nuevoVehiculo = null;
        switch (tipoVehiculo) {
            case 1:
                System.out.println("Ingrese el tipo de combustible (Gasolina, Diésel, Eléctrico):");
                String tipoCombustible = scanner.nextLine();
                nuevoVehiculo = new Auto("ID" + (admin.listarVehiculosDisponibles().size() + 1), marca, modelo, año, costoDiario, tipoCombustible);
                break;
            case 2:
                System.out.println("Ingrese la cilindrada de la moto (en cc):");
                int cilindrada = scanner.nextInt();
                nuevoVehiculo = new Moto("ID" + (admin.listarVehiculosDisponibles().size() + 1), marca, modelo, año, costoDiario, cilindrada);
                break;
            case 3:
                System.out.println("Ingrese la capacidad de carga de la camioneta (en kg):");
                int capacidadCarga = scanner.nextInt();
                nuevoVehiculo = new Camioneta("ID" + (admin.listarVehiculosDisponibles().size() + 1), marca, modelo, año, costoDiario, capacidadCarga);
                break;
            case 4:
                System.out.println("Ingrese la capacidad de pasajeros del autobús:");
                int capacidadPasajeros = scanner.nextInt();
                nuevoVehiculo = new Autobus("ID" + (admin.listarVehiculosDisponibles().size() + 1), marca, modelo, año, costoDiario, capacidadPasajeros);
                break;
            default:
                System.out.println("Opción no válida.");
                return;
        }

        admin.añadirVehiculo(nuevoVehiculo);
        System.out.println("Vehículo añadido a la flota.");
    }


    private static Vehiculo elegirVehiculo(Administrador admin, Class<?> tipoClase) {
        List<Vehiculo> disponibles = admin.listarVehiculosDisponibles();
        List<Vehiculo> filtrados = new ArrayList<>();

        for (Vehiculo vehiculo : disponibles) {
            if (tipoClase.isInstance(vehiculo)) {
                filtrados.add(vehiculo);
            }
        }

        if (filtrados.isEmpty()) {
            System.out.println("No hay vehículos disponibles de este tipo.");
            return null;
        }

        System.out.println("Vehículos disponibles:");
        for (int i = 0; i < filtrados.size(); i++) {
            System.out.println((i + 1) + ". " + filtrados.get(i));
        }

        System.out.println("Seleccione un vehículo:");
        int seleccion = new Scanner(System.in).nextInt();
        return filtrados.get(seleccion - 1);
    }
}