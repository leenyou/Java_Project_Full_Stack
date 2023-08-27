/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.courses.webPF.dao;


import com.courses.webPF.entities.*;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Leen
 */

public class CoursesDAO extends AbstractFacade<Courses>  {

    
    public CoursesDAO() {
        super(Courses.class);
    }
    public Courses findByTitle(String title)
    {
      EntityManager em =null;  
        List<Courses> list=null;
        
          try{
      
            em= getEntityManager();
           Session session= (Session)em.getDelegate();
          Query query = session.getNamedQuery("Courses.findByTitle");
                query.setParameter("title", title);
                list=query.list();
                if(list !=null && !list.isEmpty())
                    return list.get(0);
           
            }
          catch(Exception e)
          {
               System.out.println ("Error on getting courses");
              e.printStackTrace();
              
          }
          finally
          {
             em= CloseEntityManager(em);
          }
        return null;  
    }
    
}
