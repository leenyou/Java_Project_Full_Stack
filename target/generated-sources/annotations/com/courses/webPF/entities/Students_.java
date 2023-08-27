package com.courses.webPF.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Students.class)
public abstract class Students_ {

	public static volatile SingularAttribute<Students, Integer> studentId;
	public static volatile SingularAttribute<Students, String> firstName;
	public static volatile SingularAttribute<Students, String> lastName;
	public static volatile SingularAttribute<Students, String> gender;
	public static volatile SingularAttribute<Students, Integer> Degree;
	public static volatile ListAttribute<Students, StudentCourse> studentCourseList;
	public static volatile SingularAttribute<Students, Date> dateOfBrith;
	public static volatile SingularAttribute<Students, Integer> Age;

}

