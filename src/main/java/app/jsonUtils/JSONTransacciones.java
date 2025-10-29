package app.jsonUtils;

import app.models.transacciones.Gasto;
import app.models.transacciones.Ingreso;
import app.models.transacciones.Transaccion;
import org.json.JSONArray;
import org.json.JSONObject;

public class JSONTransacciones extends JSONUtiles{
    private static final String archivo = "Transacciones.json";

    public static void grabarTransacciones(JSONArray array){
        grabar(array, archivo);
    }

    public static boolean registrarTransaccion(Transaccion transaccion){
        JSONArray array = new JSONArray();
        JSONObject t = new JSONObject();
        t.put("id", transaccion.getId());
        t.put("presonaID", transaccion.getPersonaID());
        t.put("fecha", transaccion.getFecha());
        t.put("monto", transaccion.getMonto());
        t.put("metodoDePago", transaccion.getMetodoDePago());
        t.put("descripcion", transaccion.getDescripcion());
        if(transaccion instanceof Ingreso i){
            t.put("fuente", i.getFuenteIngreso());
        }else if(transaccion instanceof Gasto g){
            t.put("motivo", g.getMotivoGasto());
        }
        array.put(t);
        grabarTransacciones(array);
        return true;
    }
}
