package com.courses.webPF.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(StudentCourse.class)
public abstract class StudentCourse_ {

	public static volatile SingularAttribute<StudentCourse, Students> student;
	public static volatile SingularAttribute<StudentCourse, Integer> degree;
	public static volatile SingularAttribute<StudentCourse, Courses> course;
	public static volatile SingularAttribute<StudentCourse, Date> joiningDate;
	public static volatile SingularAttribute<StudentCourse, Integer> id;

}

