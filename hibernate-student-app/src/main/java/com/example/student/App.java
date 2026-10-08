package com.example.student;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class App {

    public static void main(String[] args) {
        int studentId = 1001;

        try {
            // 1. Create a Student object.
            Student student = new Student(
                    studentId,
                    "Meenakshi",
                    "meenakshi1001@example.com",
                    "Electronics and Communication Engineering"
            );

            // 2. INSERT the Student using Hibernate.
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                session.persist(student);
                transaction.commit();
                System.out.println("\nINSERT SUCCESS: " + student);
            }

            // 3. READ the inserted record to verify it exists.
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                Student savedStudent = session.get(Student.class, studentId);
                System.out.println("READ SUCCESS: " + savedStudent);
            }

            // 4. UPDATE the record using Hibernate.
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                Student studentToUpdate = session.get(Student.class, studentId);

                if (studentToUpdate == null) {
                    throw new IllegalStateException("Student with ID " + studentId + " was not found.");
                }

                studentToUpdate.setCourse("Computer Engineering");
                transaction.commit();
                System.out.println("UPDATE SUCCESS: Course changed to Computer Engineering");
            }

            // 5. READ again to verify the update.
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                Student updatedStudent = session.get(Student.class, studentId);
                System.out.println("FINAL DATABASE RECORD: " + updatedStudent);
            }

        } catch (Exception e) {
            System.err.println("\nAPPLICATION ERROR: " + e.getMessage());
            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
