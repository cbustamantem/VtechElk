/* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.clientes.boundary;

import business.clientes.entities.Clientes;
import business.dao.GenericImpl;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import py.org.jugpy.utils.UtilLogger;

/**
 *
 * @author cbustamante
 */
@Stateless
public class ClientesManager  extends GenericImpl<Clientes, Integer>{
    
    public List<Clientes> getListByRazonSocial(String razonSocial) {
        try {

            Query query = em.createNamedQuery("Clientes.findByRazonSocial").setParameter("razonSocial", razonSocial);
            return query.getResultList();
        } catch (Exception e) {
            UtilLogger.error(this.getClass().getName() + ".getListByRazonSocial", e);
            return null;
        }
    }
    
}
