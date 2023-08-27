/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.courses.webPF.entities;

import  com.courses.webPF.dao.*;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Leen
 */
@Entity
@Table(name = "courses")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Courses.findAll", query = "SELECT c FROM Courses c")
    , @NamedQuery(name = "Courses.findByTitle", query = "SELECT c FROM Courses c WHERE c.title = :title")
    , @NamedQuery(name = "Courses.findByCourseId", query = "SELECT c FROM Courses c WHERE c.courseId = :courseId")
    , @NamedQuery(name = "Courses.findByCost", query = "SELECT c FROM Courses c WHERE c.cost = :cost")
    , @NamedQuery(name = "Courses.findByCode", query = "SELECT c FROM Courses c WHERE c.code = :code")
    , @NamedQuery(name = "Courses.findByTotalDegree", query = "SELECT c FROM Courses c WHERE c.totalDegree = :totalDegree")})
public class Courses implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "Title")
    private String title;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "course_id")
    private Integer courseId;
    @Column(name = "cost")
    private Integer cost;
    @Column(name = "code")
    private Integer code;
    @Column(name = "total_degree")
    private Integer totalDegree;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private List<StudentCourse> studentCourseList;

    public Courses() {
    }

    public Courses(Integer courseId) {
        this.courseId = courseId;
    }

    public Courses( String title,Integer totalDegree) {
        this.title = title;
        this.totalDegree = totalDegree;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getTotalDegree() {
        return totalDegree;
    }

    public void setTotalDegree(Integer totalDegree) {
        this.totalDegree = totalDegree;
    }

    @XmlTransient
    public List<StudentCourse> getStudentCourseList() {
        return studentCourseList;
    }

    public void setStudentCourseList(List<StudentCourse> studentCourseList) {
        this.studentCourseList = studentCourseList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (courseId != null ? courseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Courses)) {
            return false;
        }
        Courses other = (Courses) object;
        if ((this.courseId == null && other.courseId != null) || (this.courseId != null && !this.courseId.equals(other.courseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.courses.webPF.entities.Courses[ courseId=" + courseId + " ]";
    }
    
}
