/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.courses.webPF.entities;


import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Leen
 */
@Entity
@Table(name = "student_course")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentCourse.findAll", query = "SELECT s FROM StudentCourse s")
    , @NamedQuery(name = "StudentCourse.findById", query = "SELECT s FROM StudentCourse s WHERE s.id = :id")
    , @NamedQuery(name = "StudentCourse.findByDegree", query = "SELECT s FROM StudentCourse s WHERE s.degree = :degree")
        , @NamedQuery(name = "StudentCourse.findByStudent", query = "SELECT s FROM StudentCourse s WHERE s.student = :student")
         , @NamedQuery(name = "StudentCourse.findByCourse", query = "SELECT s FROM StudentCourse s WHERE s.course = :course")
    , @NamedQuery(name = "StudentCourse.findByJoiningDate", query = "SELECT s FROM StudentCourse s WHERE s.joiningDate = :joiningDate")})
public class StudentCourse implements Serializable {

    private static final long serialVersionUID = 1L;
      @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "degree")
    private Integer degree;
    @Column(name = "joining_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date joiningDate;
    @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    @ManyToOne(optional = false)
    private Courses course;
    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    @ManyToOne(optional = false)
    private Students student;

    public StudentCourse() {
    }

    public StudentCourse(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDegree() {
        return degree;
    }

    public void setDegree(Integer degree) {
        this.degree = degree;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public Courses getCourse() {
        return course;
    }

    public void setCourse(Courses courseId) {
        this.course= courseId;
    }

    public Students getStudent() {
        return student;
    }

    public void setStudent(Students studentId) {
        this.student= studentId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentCourse)) {
            return false;
        }
        StudentCourse other = (StudentCourse) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.courses.webPF.entities.StudentCourse[ id=" + id + " ]";
    }
    
}
