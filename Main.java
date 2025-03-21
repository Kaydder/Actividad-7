import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        ArrayList<String> historial = new ArrayList<>();
        boolean continuar = true;

        while (continuar) {
            try {
                System.out.println("\nSeleccione una figura geométrica:");
                System.out.println("1. Círculo");
                System.out.println("2. Cuadrado");
                System.out.println("3. Triángulo");
                System.out.println("4. Rectángulo");
                System.out.println("5. Pentágono");
                System.out.println("6. Potencia");
                System.out.println("7. Ver Historial");
                System.out.println("8. Salir");
                System.out.print("Opción: ");
                int figura = entrada.nextInt();

                if (figura == 8) {
                    continuar = false;
                    break;
                }

                if (figura == 7) {
                    mostrarHistorial(historial);
                    continue;
                }

                if (figura == 6) {
                    System.out.print("Ingrese la base: ");
                    double base = entrada.nextDouble();
                    System.out.print("Ingrese el exponente: ");
                    int exponente = entrada.nextInt();
                    double resultado = calcularPotencia(base, exponente);
                    String descripcion = "Potencia: " + base + " ^ " + exponente + " = " + resultado;
                    historial.add(descripcion);
                    System.out.println(descripcion);
                    continue;
                }

                System.out.println("\nSeleccione la operación:");
                System.out.println("1. Área");
                System.out.println("2. Perímetro");
                System.out.print("Opción: ");
                int operacion = entrada.nextInt();

                double resultado = calcularOperacion(figura, operacion, entrada);
                if (resultado != -1) {
                    String descripcion = obtenerDescripcion(figura, operacion, resultado);
                    historial.add(descripcion);
                    System.out.println(descripcion);
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Entrada inválida. Intente de nuevo.");
                entrada.next();
            }
        }

        System.out.println("¡Hasta luego!");
        entrada.close();
    }

    public static double calcularOperacion(int figura, int operacion, Scanner entrada) {
        double resultado = -1;
        switch (figura) {
            case 1: // Círculo
                System.out.print("Ingrese el radio: ");
                double radio = entrada.nextDouble();
                resultado = (operacion == 1) ? Math.PI * radio * radio : 2 * Math.PI * radio;
                break;
            case 2: // Cuadrado
                System.out.print("Ingrese el lado: ");
                double lado = entrada.nextDouble();
                resultado = (operacion == 1) ? lado * lado : 4 * lado;
                break;
            case 3: // Triángulo (equilátero para perímetro)
                if (operacion == 1) {
                    System.out.print("Ingrese la base: ");
                    double base = entrada.nextDouble();
                    System.out.print("Ingrese la altura: ");
                    double altura = entrada.nextDouble();
                    resultado = (base * altura) / 2;
                } else {
                    System.out.print("Ingrese la longitud de un lado: ");
                    double ladoTriangulo = entrada.nextDouble();
                    resultado = 3 * ladoTriangulo;
                }
                break;
            case 4: // Rectángulo
                System.out.print("Ingrese la base: ");
                double baseR = entrada.nextDouble();
                System.out.print("Ingrese la altura: ");
                double alturaR = entrada.nextDouble();
                resultado = (operacion == 1) ? baseR * alturaR : 2 * (baseR + alturaR);
                break;
            case 5: // Pentágono (regular)
                System.out.print("Ingrese la longitud de un lado: ");
                double ladoPentagono = entrada.nextDouble();
                if (operacion == 1) {
                    System.out.print("Ingrese la apotema: ");
                    double apotema = entrada.nextDouble();
                    resultado = (5 * ladoPentagono * apotema) / 2;
                } else {
                    resultado = 5 * ladoPentagono;
                }
                break;
            default:
                System.out.println("Opción inválida.");
        }
        return resultado;
    }

    public static double calcularPotencia(double base, int exponente) {
        if (exponente == 0) {
            return 1;
        }
        if (exponente < 0) {
            return 1 / calcularPotencia(base, -exponente);
        }
        return base * calcularPotencia(base, exponente - 1);
    }

    public static String obtenerDescripcion(int figura, int operacion, double resultado) {
        String tipoOperacion = (operacion == 1) ? "Área" : "Perímetro";
        String nombreFigura = switch (figura) {
            case 1 -> "Círculo";
            case 2 -> "Cuadrado";
            case 3 -> "Triángulo";
            case 4 -> "Rectángulo";
            case 5 -> "Pentágono";
            default -> "Desconocido";
        };
        return nombreFigura + " - " + tipoOperacion + ": " + resultado;
    }

    public static void mostrarHistorial(ArrayList<String> historial) {
        System.out.println("\nHistorial de operaciones:");
        if (historial.isEmpty()) {
            System.out.println("No hay operaciones registradas.");
        } else {
            for (String operacion : historial) {
                System.out.println(operacion);
            }
        }
    }
}
