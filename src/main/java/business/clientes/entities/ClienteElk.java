/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.clientes.entities;

import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author cbustamante
 */
public class ClienteElk {
    
    private String postDate;
    private Integer status;
    private Clientes cliente;

    public ClienteElk(Integer status, Clientes cliente) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        this.postDate = sdf.format(new Date());
        this.status = status;
        this.cliente = cliente;
    }
    

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }
    
    public String toJson() {
        Gson gson = new Gson();
        return  gson.toJson(this, ClienteElk.class);
        // 1. Java object to JSON, and save into a file

    }
    
}
