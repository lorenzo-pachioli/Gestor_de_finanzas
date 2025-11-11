package app.models.extras;


import app.Interfaces.Exportable;
import app.models.transacciones.Transaccion;
import app.models.usuarios.Persona;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class ReporteGenerator {

    public static String generarResumenTransacciones(List<Transaccion> transacciones, Persona persona) {
        double totalIngresos = 0;
        double totalGastos = 0;
        int countIngresos = 0;
        int countGastos = 0;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        StringBuilder resumen = new StringBuilder();
        resumen.append("\n              REPORTE DE TRANSACCIONES                      ");
        resumen.append("\n -----------------------------------------------------------\n");
        resumen.append(String.format("Usuario: %s %s\n", persona.getNombre(), persona.getApellido()));
        resumen.append(String.format("Fecha: %s\n", LocalDateTime.now().format(formatter)));
        resumen.append("\n -----------------------------------------------------------\n");
        resumen.append("      Movimientos             ");
        resumen.append("\n -----------------------------------------------------------\n");

        int i = 0;
        for (Transaccion t : transacciones) {
            if (i==0){
                resumen.append("Tipo||");
                resumen.append(t.getCSVHeaders());
                i++;
                resumen.append("\n");
            }


            if (t.getClass().getSimpleName().equals("Ingreso")) {
                resumen.append("\n");
                resumen.append(i);
                resumen.append(") ");
                resumen.append("Ingreso||");
                resumen.append(t.toCSV());
                resumen.append("\n");
                totalIngresos += t.getMonto();
                countIngresos++;
            } else {
                resumen.append("\n");
                resumen.append(i);
                resumen.append(") ");
                resumen.append("Gasto||");
                resumen.append(t.toCSV());
                resumen.append("\n");
                totalGastos += t.getMonto();
                countGastos++;
            }
            i++;
        }
        double balance = totalIngresos - totalGastos;
        resumen.append("\n -----------------------------------------------------------\n");
        resumen.append(String.format("Total Ingresos:  $%.2f (%d transacciones)\n", totalIngresos, countIngresos));
        resumen.append(String.format("Total Gastos:    $%.2f (%d transacciones)\n", totalGastos, countGastos));
        resumen.append("-----------------------------------------------------------\n");
        resumen.append(String.format("Balance:         $%.2f %s\n",
                Math.abs(balance),
                balance >= 0 ? "(Positivo)" : "(Negativo)"));
        resumen.append("-----------------------------------------------------------\n");

        return resumen.toString();
    }

    public static boolean guardarResumen(String resumen, String nombreArchivo) {
        try {
            String rutaDescargas = System.getProperty("user.home") + "/Downloads/";
            FileWriter file = new FileWriter(rutaDescargas + nombreArchivo + ".csv");
            file.write(resumen);
            file.flush();
            file.close();
            return true;
        } catch (IOException e) {
            System.err.println("Error al guardar resumen: " + e.getMessage());
            return false;
        }
    }
}
