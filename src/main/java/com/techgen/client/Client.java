package com.techgen.client;

import java.util.Set;

import com.techgen.entity.Guide;
import com.techgen.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Client {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("student-guide");

			entityManager = entityManagerFactory.createEntityManager();

			EntityTransaction transaction = entityManager.getTransaction();

			transaction.begin();

			// persistStudentGuide(entityManager, transaction);
			// getStudent(entityManager, 2l);
			// getGuide(entityManager, 2l);
			// removeStudent(entityManager, transaction);
			// detachStudent(entityManager );
			// detachToPersistentStudent(entityManager, transaction);
			// updateStudent(entityManager, transaction);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (entityManager != null && entityManager.isOpen()) {
				entityManager.close();
			}
			if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
				entityManagerFactory.close();
			}
		}
	}

	private static void persistStudentGuide(EntityManager entityManager, EntityTransaction transaction) {
		Guide guide1 = new Guide("GC-877", "Ram");

		Student student1 = new Student("ET-1234", "Sunil");
		Student student2 = new Student("ET-1235", "Anil");
		System.out.println(student1.equals(student2));

		guide1.addStudent(student1);
		guide1.addStudent(student2);

		entityManager.persist(guide1);
		transaction.commit();
	}

	private static Student getStudent(EntityManager entityManager, long id) {
		Student student = entityManager.find(Student.class, id);
		System.out.println(student);
		return student;
	}

	private static Guide getGuide(EntityManager entityManager, long id) {
		Guide guide = entityManager.find(Guide.class, id);
		System.out.println(guide);
		Set<Student> students = guide.getStudents();
		for (Student student : students) {
			System.out.println(student);
		}
		return guide;
	}

	private static void updateStudent(EntityManager entityManager, EntityTransaction transaction) {
		Student student = entityManager.find(Student.class, 3l);
		System.out.println(student);
		student.setName("Sumit");
		transaction.commit();
	}

	private static void removeStudent(EntityManager entityManager, EntityTransaction transaction) {
		Student student = getStudent(entityManager, 3l);
		entityManager.remove(student);
		transaction.commit();
		System.out.println();
	}

	private static void detachStudent(EntityManager entityManager) {
		Student student = getStudent(entityManager, 3l);
		entityManager.detach(student);
		System.out.println();
	}

	private static void detachToPersistentStudent(EntityManager entityManager, EntityTransaction transaction) {
		Student student = getStudent(entityManager, 5l);
		entityManager.detach(student);
		Student mergeStudent = entityManager.merge(student);
		mergeStudent.setName("Kuraa");
		transaction.commit();
		System.out.println();
	}

}
