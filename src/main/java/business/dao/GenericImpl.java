/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.slf4j.Logger;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templtes
 * and open the template in the editor.
 */
/**
 *
 * @author cbustamante
 * @param <ET>
 * @param <PK>
 */
public class GenericImpl<ET, PK extends Serializable>
        implements GenericDao<ET, PK> {
    
    
    @PersistenceContext
    public EntityManager em;

    @Override
    public ET add(ET entity) {
        try {
            em.persist(entity);
            return entity;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public ET update(ET entity) {
        try {
            em.merge(entity);
            return entity;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public ET getById(PK key) {
        try {
            Query query = em.createNamedQuery(this.getClass().getName() + ".findById").setParameter("id", key);
            return ((ET) query.getSingleResult());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ET> getAll() {
        try {
            return (List<ET>) em.createNamedQuery(getEntityName() + ".findAll").getResultList();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public void delete(ET entity) {
        try {
            em.remove(entity);
        } catch (Exception e) {
        }
    }

    private String getEntityName() {
        
        return this.getGenericName().replace("Manager", "");
    }

    protected String getGenericName() {
        return ((Class<ET>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]).getSimpleName();
    }
}
