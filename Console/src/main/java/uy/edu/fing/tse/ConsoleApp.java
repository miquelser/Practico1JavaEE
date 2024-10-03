package uy.edu.fing.tse;

import jakarta.ejb.EJB;
import tse.practico1.models.Clasificacion;
import tse.practico1.models.HechosModel;
import tse.practico1.service.Interface.IHechosRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class ConsoleApp {

    @EJB
    private static IHechosRemote hechosService;

    static {
        try {
            final Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
            props.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
            props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
            Context ctx = new InitialContext(props);
            hechosService = (IHechosRemote) ctx.lookup("ejb:practico1/practico1-ejb/HechosService!tse.practico1.service.Interface.IHechosRemote");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("1. Agregar Hecho");
            System.out.println("2. Ver Hechos");
            System.out.println("3. Buscar Hechos");
            System.out.println("4. Eliminar Hecho");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (option) {
                case 1:
                    agregarHecho(scanner);
                    break;
                case 2:
                    verHechos();
                    break;
                case 3:
                    buscarHechos(scanner);
                    break;
                case 4:
                    eliminarHecho(scanner);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (option != 0);
    }

    private static void agregarHecho(Scanner scanner) {
        System.out.print("Ingrese la fecha (dd/MM/yyyy): ");
        String fechaInput = scanner.nextLine();
        Date fecha = null;

        // Convertir String a Date
        try {
            fecha = new SimpleDateFormat("dd/MM/yyyy").parse(fechaInput);
        } catch (ParseException e) {
            System.out.println("Formato de fecha incorrecto. Intente nuevamente.");
            return;
        }

        System.out.print("Ingrese la descripción: ");
        String descripcion = scanner.nextLine();

        System.out.println("Seleccione la clasificación:");
        Clasificacion[] clasifiaciones = Clasificacion.values();
        for (int i = 0; i < clasifiaciones.length; i++) {
            System.out.println((i + 1) + ". " + clasifiaciones[i]);
        }

        int seleccion = scanner.nextInt();
        if (seleccion < 1 || seleccion > clasifiaciones.length) {
            System.out.println("Selección inválida. Intente nuevamente.");
            return;
        }

        Clasificacion clasificacion = clasifiaciones[seleccion - 1];

        hechosService.agregarHecho(fecha, descripcion, clasificacion);
        System.out.println("Hecho agregado.");
    }

    private static void verHechos() {
        List<HechosModel> hechosList = hechosService.getHechos();
        for (HechosModel hecho : hechosList) {
            System.out.println(hecho);
        }
    }

    private static void buscarHechos(Scanner scanner) {
        System.out.print("Ingrese texto a buscar: ");
        String buscar = scanner.nextLine();
        List<HechosModel> resultados = hechosService.buscarHechos(buscar);
        for (HechosModel hecho : resultados) {
            System.out.println(hecho);
        }
    }

    private static void eliminarHecho(Scanner scanner) {
        System.out.print("Ingrese el número del hecho a eliminar: ");
        int numero = scanner.nextInt();
        if (hechosService.eliminarHecho(numero)) {
            System.out.println("Hecho eliminado.");
        } else {
            System.out.println("No se encontró el hecho.");
        }
    }
}