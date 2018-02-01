/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.clientes.resources;

import business.clientes.boundary.ClientesManager;
import business.clientes.entities.Clientes;
import business.utils.ElkLogger;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import business.utils.UtilLogger;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import javax.ws.rs.Consumes;
import org.slf4j.Logger;

/**
 *
 * @author cbustamante
 */
@Path("/clientes")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
@Api(value = "Servicio de Clientes")
public class ClientesRs implements Serializable {

    @Inject
    ClientesManager clientsMgr;

    @Inject
    ElkLogger elkLogger;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Obtener datos del clientes",
            notes = "Se obtienen los datos de los clientes por razon social")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 404, message = "Error clientes no encontrado"),
        @ApiResponse(code = 500, message = "Error interno")})
    public Response getCliente(@QueryParam("nombre") String nombre) {

        UtilLogger.info("getCliente :" + nombre);
        Gson gson = new Gson();
        try {

            List<Clientes> listaClientes = clientsMgr.getListByRazonSocial(nombre);
//            UserLdap user = userCtrl.getAllAttributes(username);

            if (listaClientes == null || listaClientes.isEmpty()) {
                JsonObject value = Json.createObjectBuilder()
                        .add("error", "404")
                        .add("message", "Error, cliente no encontrado")
                        .build();
                Clientes cliente = new Clientes();
                cliente.setRazonSocial(nombre);
                logElk(404, cliente.toJson());
                return Response.status(404).entity(value).build();

            } else {

                /**
                 * *
                 * filtrar un solo cliente
                 */
                Clientes cliente = new Clientes();
                for (Clientes cl : listaClientes) {
                    cliente = cl;
                }

                logElk(200, cliente.toJson());
                return Response.status(200).entity(cliente.toJson()).build();

            }
        } catch (Exception e) {
            JsonObject value = Json.createObjectBuilder()
                    .add("error", "500")
                    .add("message", e.getLocalizedMessage())
                    .build();
            Clientes cliente = new Clientes();
            cliente.setRazonSocial(nombre);
            logElk(500, cliente.toJson());
            return Response.status(500).entity(value).build();
        }

    }

    /**
     * *
     * Mapa de datos a registrar en ElasticSearch
     *
     * @param status
     * @param json
     * @return
     */
    private Map<String, String> elkResponse(Integer status, String jsonData) {
        Map<String, String> mapaDatos = new HashMap();
        mapaDatos.put("post_response", String.valueOf(new Date().getTime())); //Fecha y hora del request
        mapaDatos.put("status", status.toString()); //Fecha y hora del request
        mapaDatos.put("cliente", jsonData); //Fecha y hora del request

        return mapaDatos;

    }

    private void logElk(Integer status, String jsonData) {

        try {
            elkLogger.info("api", "clientes", null, elkResponse(status, jsonData));
        } catch (IOException ex) {
//          logger.info("Error en la operacion  logElk");
        }
    }

}
