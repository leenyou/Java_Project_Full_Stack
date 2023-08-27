package com.courses.webPF.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Courses.class)
public abstract class Courses_ {

	public static volatile SingularAttribute<Courses, Integer> cost;
	public static volatile SingularAttribute<Courses, Integer> code;
	public static volatile SingularAttribute<Courses, Integer> totalDegree;
	public static volatile ListAttribute<Courses, StudentCourse> studentCourseList;
	public static volatile SingularAttribute<Courses, String> title;
	public static volatile SingularAttribute<Courses, Integer> courseId;

}

