package app.jsonUtils;

import app.App;
import app.enums.FuenteIngreso;
import app.enums.MetodoDePago;
import app.enums.MotivoGasto;
import app.models.colecciones.ListaGenerica;
import app.models.colecciones.ListaTransacciones;
import app.models.transacciones.Gasto;
import app.models.transacciones.Ingreso;
import app.models.transacciones.Transaccion;
import app.models.usuarios.Persona;
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

    public static void grabarUnaTransaccion(Transaccion transaccion){
        JSONArray jsonLista = leerTransacciones();
        for(int i=0 ; i<jsonLista.length() ; i++){
            JSONObject o = jsonLista.getJSONObject(i);
            if(o.getString("id").equals(String.valueOf(transaccion.getId()))){
                jsonLista.put(i, transaccionAJson(transaccion));
            }
        }
        grabar(jsonLista, archivo);
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

    public static ListaGenerica<Transaccion> leerTransaccionesArrayPorUsuario(){
        ListaGenerica<Transaccion> listaTransacciones = new ListaGenerica<>();
        JSONArray JSONTransacciones = leerTransacciones();
        for(int i=0 ; i<JSONTransacciones.length() ; i++){
            JSONObject transaccion = JSONTransacciones.getJSONObject(i);
            if(UUID.fromString(transaccion.getString("personaID")).equals(App.persona.getId())){
                listaTransacciones.agregar(jsonATransaccion(JSONTransacciones.getJSONObject(i)));
            }
        }
        return listaTransacciones;
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

        String fuente = transaccion.optString("fuente", null);
        String motivo = transaccion.optString("motivo", null);

        if (fuente != null && !fuente.isEmpty()) {
            return new Ingreso(
                    UUID.fromString(id),
                    UUID.fromString(personaID),
                    monto,
                    fecha,
                    descripcion,
                    metodoDePago,
                    FuenteIngreso.valueOf(fuente)
            );
        }

        return new Gasto(
                UUID.fromString(id),
                UUID.fromString(personaID),
                monto,
                fecha,
                descripcion,
                metodoDePago,
                MotivoGasto.valueOf(motivo)
        );
    }

    public static JSONObject transaccionAJson(Transaccion transaccion){
        JSONObject jsonTransaccion = new JSONObject();
        jsonTransaccion.put("id", transaccion.getId());
        jsonTransaccion.put("personaID", transaccion.getPersonaID());
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
