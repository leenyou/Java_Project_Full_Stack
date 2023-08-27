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

public class StudentCourseDAO extends AbstractFacade<StudentCourse>  {

   

    public StudentCourseDAO() {
        super(StudentCourse.class);
    }
    public List<StudentCourse> getStudentCourses(Students student)
    {
        EntityManager em =null;  
        List<StudentCourse> list=null;
        
          try{
      
            em= getEntityManager();
           Session session= (Session)em.getDelegate();
          Query query = session.getNamedQuery("StudentCourse.findByStudent");
                query.setParameter("student", student);
                list=query.list();
                
           
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
        return list;
    }
    
     public List<StudentCourse> getStudentfromCourses(Courses course)
    {
        EntityManager em =null;  
        List<StudentCourse> list=null;
        
          try{
      
            em= getEntityManager();
           Session session= (Session)em.getDelegate();
          Query query = session.getNamedQuery("StudentCourse.findByCourse");
                query.setParameter("course", course);
                list=query.list();
                
           
            }
          catch(Exception e)
          {
               System.out.println ("Error on getting students");
              
              
          }
          finally
          {
             em= CloseEntityManager(em);
          }
        return list;
    }
}
