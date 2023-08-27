/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.courses.webPF.bean;

import com.courses.webPF.dao.CoursesDAO;
import com.courses.webPF.entities.Courses;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 *
 * @author Leen
 */

@ManagedBean
@RequestScoped
public class CoursesConverter implements Converter {

   
    private CoursesDAO coursesDAO =new CoursesDAO() ;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        try {
            return coursesDAO.findByTitle(value);
        } catch (NumberFormatException e) {
            throw new ConverterException(new FacesMessage(String.format("%s is not a valid User ID", value)), e);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
        if (modelValue == null) {
            return "";
        }
        if (modelValue instanceof Courses) {
            return String.valueOf(((Courses) modelValue).getTitle());
        } else {
            throw new ConverterException(new FacesMessage(String.format("%s is not a valid Courses", modelValue)), null);
        }
    }
}