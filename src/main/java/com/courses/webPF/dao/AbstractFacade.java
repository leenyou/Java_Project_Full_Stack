
package com.courses.webPF.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    protected static EntityManagerFactory emf=null;
    
    protected EntityManagerFactory getEntityManagerFactory(){
        if (emf==null){
            emf=Persistence.createEntityManagerFactory("hiberTestPU");
        }
        return emf ;
    }
   
    public  EntityManager getEntityManager()
    {
       
      return   getEntityManagerFactory().createEntityManager();
       
    }
    
    public  EntityManager CloseEntityManager(EntityManager em)
    {
       
      if(em !=null && em.isOpen())
          em.close();
      em=null;
      return em;
       
    }
   
    public void Add(T entity) throws Exception{
       EntityManager em =null;   
        
          try{
      
            em= getEntityManager();
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            }
         
          finally
          {
            em= CloseEntityManager(em);
          }
        
    }

    public void edit(T entity) throws Exception {
        EntityManager em =null;   
        
          try{
      
            em= getEntityManager();
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
          }
         
          finally
          {
            em= CloseEntityManager(em);
          }
       
    }

    public void delete (T entity) throws Exception{
        EntityManager em =null;   
        
          try{
      
            em= getEntityManager();
            em.getTransaction().begin();
            em.remove(em.merge(entity));
            em.getTransaction().commit();
           }
         
          finally
          {
            em= CloseEntityManager(em);
          }
       
    }

    public T find(Object id) throws Exception{
        EntityManager em =null;   
        
          try{
      
            em= getEntityManager();
            return em.find(entityClass, id);
          }
         
          finally
          {
            em= CloseEntityManager(em);
          }
        
    }

    public List<T> findAll() throws Exception{
        EntityManager em =null;   
        
          try{
            em= getEntityManager();
            
            javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(entityClass));
            return em.createQuery(cq).getResultList();
            }
         
          finally
          {
            em= CloseEntityManager(em);
          }
        
    }

    public List<T> findRange(int[] range) throws Exception{
        EntityManager em =null;   
        
          try{
      
            em= getEntityManager();
       
            javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(entityClass));
            javax.persistence.Query q = em.createQuery(cq);
            q.setMaxResults(range[1] - range[0] + 1);
            q.setFirstResult(range[0]);
            return q.getResultList();
           }
         
          finally
          {
            em= CloseEntityManager(em);
          }
        
    }

    public int count() throws Exception{
        EntityManager em =null;   
        
          try{
      
            em= getEntityManager();
            javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
            cq.select(em.getCriteriaBuilder().count(rt));
            javax.persistence.Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
            }
         
          finally
          {
            em= CloseEntityManager(em);
          }
        
    }
  
    
}
