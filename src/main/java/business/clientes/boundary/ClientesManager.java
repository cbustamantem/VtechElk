/* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.clientes.boundary;

import business.clientes.entities.Clientes;
import business.dao.GenericImpl;
import java.io.Serializable;
import javax.ejb.Stateless;

/**
 *
 * @author cbustamante
 */
@Stateless
public class ClientesManager  extends GenericImpl<Clientes, Integer>{
    
}
