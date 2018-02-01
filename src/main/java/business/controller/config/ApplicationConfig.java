/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.controller.config;


import business.clientes.resources.ClientesRs;
import com.github.phillipkruger.apiee.ApieeService;
import io.swagger.annotations.SwaggerDefinition;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;

import javax.ws.rs.core.Application;

/**
 * Enable REST
 * @author Phillip Kruger (apiee@phillip-kruger.com)
 */
@SwaggerDefinition(basePath = "/api")
@ApplicationPath("/api")
public class ApplicationConfig extends Application {

    // Or autoscan need to be enabled.
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(ClientesRs.class);
        classes.add(ApieeService.class);
        return classes;
    }
}