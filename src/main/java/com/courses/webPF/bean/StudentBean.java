/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.courses.webPF.bean;

import com.courses.webPF.dao.CoursesDAO;
import com.courses.webPF.dao.StudentCourseDAO;
import com.courses.webPF.dao.StudentsDAO;
import com.courses.webPF.entities.Courses;

import com.courses.webPF.entities.StudentCourse;
import com.courses.webPF.entities.Students;
import java.util.ArrayList;

import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;


/**
 *
 * @author Leen
 */
@ManagedBean(name="studentBean")
@ViewScoped
public class StudentBean {
   
     
List<Students> studentlist=new ArrayList();
private List<Students> selectedStudents;
private Students student =new Students();
private StudentCourse studentcourse = new StudentCourse();
StudentsDAO studentsdao=new StudentsDAO();
CoursesDAO coursesdao = new CoursesDAO ();
StudentCourseDAO scd=new StudentCourseDAO();

List<StudentCourse> listStudentCourses= new ArrayList<>();
List<StudentCourse> selected_StudentCourses= new ArrayList<>();
    





    public StudentBean() {
        
    }
    public List<Students> getSelectedStudents() {
        return selectedStudents;
    }

    public void setSelectedStudents(List<Students> selectedStudents) {
        this.selectedStudents = selectedStudents;
    }

    public List<Students> getStudentlist() {
        
         List<Students> sList=new ArrayList<>();
         try{
        sList= studentsdao.findAll();
         }catch(Exception e){
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot getStudent",e.getMessage()));
             e.printStackTrace();
        }
         return sList;
    }

   
    public Students getStudent() {
        return student;
    }

    public void setStudent(Students student) {
        this.student = student;
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

    public StudentCourse getNewstudentcourse() {
        return newstudentcourse;
    }

    public void setNewstudentcourse(StudentCourse newstudentcourse) {
        this.newstudentcourse = newstudentcourse;
    }
       
     
    
    public void fetchStudentCourses(){
        try {
              newstudentcourse = new StudentCourse (); 
       if(this.student ==null || this.student.getStudentId() ==null ) throw new Exception ("No Student Information");
           listStudentCourses=scd.getStudentCourses(student);
       
  
       
       } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Student Courses",ex.getMessage()));
            Logger.getLogger(StudentBean.class.getName()).log(Level.SEVERE, null, ex);
       } 
    }
     
    
    
    public void savestudent()  {
    try {
        if(this.student ==null) throw new Exception ("No Student Information");
        if (this.student.getStudentId() == null) {
            studentsdao.Add(student);
            this.studentlist.add(this.student);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Student Added"));
        }
        else {
            studentsdao.edit(student);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Student Updated"));
        }

        PrimeFaces.current().executeScript("PF('managedstudentDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-students");
    } catch (Exception ex) {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot Save Student",ex.getMessage()));
        Logger.getLogger(StudentBean.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    public void deleteStudent() {
        try {
         //        this.studentlist.remove(this.student);
       // 
    //        this.selectedStudents = null;
            studentsdao.delete(student);
             this.selectedStudents.remove(this.student);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Student Removed"));
             PrimeFaces.current().executeScript("PF('deleteStudentDialog').hide()");
            PrimeFaces.current().ajax().update("form:messages", "form:dt-students");
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot delete Student",ex.getMessage()));
            Logger.getLogger(StudentBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String getDeleteButtonMessage() {
        if (hasSelectedStudents()) {
            int size = this.selectedStudents.size();
            return size > 1 ? size + " Students selected" : "1 Students selected";
        }

        return "Delete";
    }

    public boolean hasSelectedStudents() {
        return this.selectedStudents != null && !this.selectedStudents.isEmpty();
    } 
       
    public void deleteSelectedStudents() {
        try {
          if (selectedStudents !=null && !selectedStudents.isEmpty())
          {
              int num=selectedStudents.size();
              for(Students s:selectedStudents)
              {
                    studentsdao.delete(s);
              }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(num+" Student was Removed"));
            PrimeFaces.current().ajax().update("form:messages", "form:dt-students");
          
             PrimeFaces.current().executeScript("PF('dtStudents').clearFilters()");
          }
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot Remove Student(s)",ex.getMessage()));
            Logger.getLogger(StudentBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
   public void openNew() {
        this.student = new Students();
    }     
        
    
    
    public void openNewsc() {
        this.studentcourse = new StudentCourse();
        
    }
   StudentCourse newstudentcourse = new StudentCourse (); 
    
  
       
        public void savestudentcourse(){
         try {
        if(this.student ==null) throw new Exception ("No course Information");
        if (newstudentcourse==null)throw new Exception ("No student course Information");
        if (newstudentcourse.getCourse()==null)throw new Exception ("No student course Information");
        if (newstudentcourse.getJoiningDate()==null)throw new Exception ("No student course date Information");
        newstudentcourse.setStudent(student);
        scd.Add(newstudentcourse);
        
        this.listStudentCourses.add(this.newstudentcourse);
        PrimeFaces.current().ajax().update("form:messages", "dialogs3:dt-studentcourses");
          
             PrimeFaces.current().executeScript("PF('AddStudentcourseDialog').hide()");
        
        }catch (Exception ex) {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot Save Student",ex.getMessage()));
        Logger.getLogger(StudentBean.class.getName()).log(Level.SEVERE, null, ex);
    } 
     
}
public List<Courses> getCourses (){
    
    try {
        return   coursesdao.findAll();
    } catch (Exception ex) {
        Logger.getLogger(StudentBean.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return null;
}

}