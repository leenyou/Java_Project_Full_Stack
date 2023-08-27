/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.courses.webPF.bean;

import com.courses.webPF.dao.CoursesDAO;
import com.courses.webPF.dao.StudentCourseDAO;
import com.courses.webPF.entities.Courses;
import com.courses.webPF.entities.StudentCourse;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

@ManagedBean(name="courseBean")
@ViewScoped
public class CourseBean {
List<Courses> courselist=new ArrayList();
private List<Courses> selectedCourses;
private Courses course =new Courses();
CoursesDAO coursesdao = new CoursesDAO ();
StudentCourseDAO scd=new StudentCourseDAO();

   StudentCourse newstudentcourse = new StudentCourse (); 
 List<StudentCourse> listStudentCourses= new ArrayList<>();
 List<StudentCourse> selected_StudentCourses= new ArrayList<>();


    public Courses getCourse() {
        return course;
    }

    public void setCourse(Courses course) {
        this.course = course;
    }

    public List<Courses> getSelectedCourses() {
        return selectedCourses;
    }

    public void setSelectedCourses(List<Courses> selectedCourses) {
        this.selectedCourses = selectedCourses;
    }

    public List<StudentCourse> getListStudentCourses() {
        return listStudentCourses;
    }

    public void setListStudentCourses(List<StudentCourse> listStudentCourses) {
        this.listStudentCourses = listStudentCourses;
    }

    public List<StudentCourse> getSelected_StudentCourses() {
        return selected_StudentCourses;
    }

    public void setSelected_StudentCourses(List<StudentCourse> selected_StudentCourses) {
        this.selected_StudentCourses = selected_StudentCourses;
    }
    


    public List<Courses> getCourselist() {
           List<Courses> cList=new ArrayList<>();
         try{
        cList= coursesdao.findAll();
         }catch(Exception e){
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot getCourse",e.getMessage()));
             
        }
         return cList;
    }

    
    public void fetchStudentCourses(){
        try {
              newstudentcourse = new StudentCourse (); 
       if(this.course ==null  ) throw new Exception ("No course Information");
           listStudentCourses=scd.getStudentfromCourses(course);
   
       
       } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Student Courses",ex.getMessage()));
            Logger.getLogger(CourseBean.class.getName()).log(Level.SEVERE, null, ex);
       } 
    }
    
    
    
    public CourseBean() {
    }
    
    public void savecourse()  {
    try {
        if(this.course ==null) throw new Exception ("No course Information");
        if (this.course.getCourseId() == null) {
            coursesdao.Add(course);
            this.courselist.add(this.course);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("course Added"));
        }
        else {
            coursesdao.edit(course);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("course Updated"));
        }

        PrimeFaces.current().executeScript("PF('managedcourseDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-courses");
    } catch (Exception ex) {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot Save course",ex.getMessage()));
        Logger.getLogger(CourseBean.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
      
        
     
      
      public void openNew() {
        this.course = new Courses();
    }
      public void deleteCourse() {
        try {
         //        this.studentlist.remove(this.student);
       //  this.selectedStudents.remove(this.student);
    //        this.selectedStudents = null;
            coursesdao.delete(course);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Course Removed"));
             PrimeFaces.current().executeScript("PF('deleteCourseDialog').hide()");
            PrimeFaces.current().ajax().update("form:messages", "form:dt-courses");
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot delete Course",ex.getMessage()));
            Logger.getLogger(CourseBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
      
       public String getDeleteButtonMessage() {
        if (hasSelectedCourses()) {
            int size = this.selectedCourses.size();
            return size > 1 ? size + " Courses selected" : "1 Courses selected";
        }

        return "Delete";
    }

    public boolean hasSelectedCourses() {
        return this.selectedCourses != null && !this.selectedCourses.isEmpty();
    }
    
    
    public void deleteSelectedCourses() {
        try {
          if (selectedCourses !=null && !selectedCourses.isEmpty())
          {
              int num=selectedCourses.size();
              for(Courses s:selectedCourses)
              {
                    coursesdao.delete(s);
              }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(num+" course was Removed"));
            PrimeFaces.current().ajax().update("form:messages", "form:dt-courses");
          
             PrimeFaces.current().executeScript("PF('dtCourses').clearFilters()");
          }
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot Remove course(s)",ex.getMessage()));
            Logger.getLogger(StudentBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
