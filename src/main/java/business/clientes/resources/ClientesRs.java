/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.clientes.resources;

import business.clientes.boundary.ClientesManager;
import business.clientes.entities.Clientes;
import com.google.gson.Gson;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
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
import py.org.jugpy.utils.UtilLogger;

/**
 *
 * @author cbustamante
 */
@Path("/clientes")
@Api(value = "/clientes", description = "Servicios de administracion de clientes")
public class ClientesRs implements Serializable {

    @Inject
    ClientesManager clientsMgr;

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

            if (listaClientes == null) {
                JsonObject value = Json.createObjectBuilder()
                        .add("error", "404")
                        .add("message", "Error, usuario no encontrado")
                        .build();
                return Response.status(404).entity(value).build();

            } else {
                StringBuffer jsonResponse = new StringBuffer();
                for (Clientes cl : listaClientes) {
                        jsonResponse.append(cl.toJson());
                }
                return Response.status(200).entity(jsonResponse).build();

            }
        } catch (Exception e) {
            JsonObject value = Json.createObjectBuilder()
                    .add("error", "500")
                    .add("message", e.getLocalizedMessage())
                    .build();
            return Response.status(500).entity(value).build();
        }

    }

}
