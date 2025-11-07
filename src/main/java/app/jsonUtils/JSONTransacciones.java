package app.jsonUtils;

import app.enums.FuenteIngreso;
import app.enums.MetodoDePago;
import app.enums.MotivoGasto;
import app.models.colecciones.ListaGenerica;
import app.models.colecciones.ListaTransacciones;
import app.models.transacciones.Gasto;
import app.models.transacciones.Ingreso;
import app.models.transacciones.Transaccion;
import org.json.JSONArray;
import org.json.JSONObject;
import java.time.LocalDateTime;
import java.util.UUID;


public class JSONTransacciones extends JSONUtiles{
    private static final String archivo = "Transacciones.json";

    public static void grabarTransacciones(JSONArray array){
        grabar(array, archivo);
    }

    public static void grabarTransacciones(ListaTransacciones listaTransacciones){
        JSONArray array = escribirTransaccionArray(listaTransacciones);
        grabar(array, archivo);
    }

    public static JSONArray leerTransacciones(){
        return new JSONArray(leer(archivo));
    }

    public static ListaGenerica<Transaccion> leerTransaccionesArray(){
        ListaGenerica<Transaccion> listaTransacciones = new ListaGenerica<>();
        JSONArray JSONTransacciones = leerTransacciones();
        for(int i=0 ; i<JSONTransacciones.length() ; i++){
            listaTransacciones.agregar(jsonATransaccion(JSONTransacciones.getJSONObject(i)));
        }
        return listaTransacciones;
    }

    public static boolean registrarTransaccion(Transaccion transaccion){
        JSONArray array = leerTransacciones();
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

    public static void borrarTransaccion(Transaccion transaccion){
        JSONArray jsonLista = leerTransacciones();
        JSONArray jsonListaNueva = new JSONArray();
        for(int i=0 ; i<jsonLista.length() ; i++){
            JSONObject o = jsonLista.getJSONObject(i);
            if(!o.getString("id").equals(String.valueOf(transaccion.getId()))){
                jsonListaNueva.put(o);
            }
        }
        grabar(jsonListaNueva, archivo);
    }

    public static JSONArray escribirTransaccionArray(ListaTransacciones listaTransaccion){
        JSONArray jsonListaTransacciones = new JSONArray();
        for(Transaccion transaccion : listaTransaccion.getListaTransacciones()){
            jsonListaTransacciones.put(transaccionAJson(transaccion));
        }
        return jsonListaTransacciones;
    }

    public static Transaccion jsonATransaccion(JSONObject transaccion){

        String id = transaccion.getString("id");
        String personaID = transaccion.getString("personaID");
        double monto = transaccion.getDouble("monto");
        LocalDateTime fecha = LocalDateTime.parse(transaccion.getString("fecha"));
        String descripcion = transaccion.getString("descripcion");
        MetodoDePago metodoDePago = MetodoDePago.valueOf(transaccion.getString("metodoDePago"));

        if(transaccion.getString("fuenteIngreso") != null){
            return new Ingreso(UUID.fromString(id), UUID.fromString(personaID), monto, fecha, descripcion, metodoDePago, FuenteIngreso.valueOf(transaccion.getString("fuenteIngreso")));
        }
        return new Gasto(UUID.fromString(id), UUID.fromString(personaID), monto, fecha, descripcion, metodoDePago, MotivoGasto.valueOf(transaccion.getString("motivoGasto")));
    }

    public static JSONObject transaccionAJson(Transaccion transaccion){
        JSONObject jsonTransaccion = new JSONObject();
        jsonTransaccion.put("id", transaccion.getId());
        jsonTransaccion.put("presonaID", transaccion.getPersonaID());
        jsonTransaccion.put("fecha", transaccion.getFecha());
        jsonTransaccion.put("monto", transaccion.getMonto());
        jsonTransaccion.put("metodoDePago", transaccion.getMetodoDePago());
        jsonTransaccion.put("descripcion", transaccion.getDescripcion());
        if(transaccion instanceof Ingreso i){
            jsonTransaccion.put("fuente", i.getFuenteIngreso());
        }else if(transaccion instanceof Gasto g){
            jsonTransaccion.put("motivo", g.getMotivoGasto());
        }
        return jsonTransaccion;
    }

}
