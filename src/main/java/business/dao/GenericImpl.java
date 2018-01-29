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
import org.apache.log4j.Logger;


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
    @Inject  
    private transient Logger logger; 
    
    @PersistenceContext
    public EntityManager em;

    @Override
    public ET add(ET entity) {
        try {
            em.persist(entity);
            return entity;
        } catch (Exception ex) {
            logger.error(getEntityName() + ".add", ex);
            return null;
        }
    }

    @Override
    public ET update(ET entity) {
        try {
            em.merge(entity);
            return entity;
        } catch (Exception ex) {
            logger.error(getEntityName() + ".update", ex);
            return null;
        }
    }

    @Override
    public ET getById(PK key) {
        try {
            Query query = em.createNamedQuery(this.getClass().getName() + ".findById").setParameter("id", key);
            return ((ET) query.getSingleResult());
        } catch (Exception e) {
            logger.error(getEntityName() + ".getById", e);
            return null;
        }
    }

    @Override
    public List<ET> getAll() {
        try {
            logger.info("GetAll >" + getEntityName() + ".findAll");
            return (List<ET>) em.createNamedQuery(getEntityName() + ".findAll").getResultList();
        } catch (Exception ex) {
            logger.error(getEntityName() + ".getAll", ex);
            return null;
        }
    }

    @Override
    public void delete(ET entity) {
        try {
            em.remove(entity);
        } catch (Exception e) {
            logger.error(this.getClass().getName() + ".update", e);
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
