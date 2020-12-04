package ru.sbrf.cu;

import hibernate.model.Avatar;
import hibernate.model.Course;
import hibernate.model.Email;
import hibernate.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class StudentModelDB {

    /*drop table STUDENT_COURSE ;
    drop table COURSE;
    drop table STUDENT;
    drop table AVATAR;
    drop table EMAIL;*/

    private static final String DB_URL = "jdbc:h2:tcp://localhost/~/test;AUTO_SERVER=TRUE";
    private static final String DB_Driver = "org.h2.Driver";

    public static void main(String[] args) {

        // Начало работы Hibernate
        EntityManagerFactory emf = Persistence.createEntityManagerFactory( "JavaStudy" );
        EntityManager em = emf.createEntityManager();

        // Начало транзакции
        em.getTransaction().begin();

        // Создаем аватар
        Avatar ava = new Avatar();
        ava.setPhotoUrl( "http://imagebank.ru/ava1.png" );
        em.persist(ava);

        // Создаем 1 курс
        Course course1 = new Course();
        course1.setName( "1-й курс" );
        em.persist(course1);

        // Создаем 2 курс
        Course course2 = new Course();
        course2.setName( "2-й курс" );
        em.persist(course2);

        // Создаем студента Billy
        Student student1 = new Student();
        student1.setAvatar(ava);
        student1.setName( "Billy" );
        student1.addCourse(course1);
        em.persist(student1);

        // Создаем студента Timmy
        Student student2 = new Student();
        student2.setAvatar(ava);
        student2.setName( "Timmy" );
        student2.addCourse(course1);
        em.persist(student2);

        // Создаем студента Jimmy
        Student student3 = new Student();
        student3.setAvatar(ava);
        student3.setName( "Jimmy" );
        student3.addCourse(course2);
        em.persist(student3);

        // Создадим запись в базе Email
        Email email = new Email();
        email.setEmail( "foo@mail.com" );
        email.setStudent(student1);
        em.persist(email);

        // Коммит транзакции
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }

        // Выведем список студентов 1 курса
        Query query = em.createQuery("SELECT s from Student s join s.courses c where c.name = '1-й курс'" );
        List<Student> studentList1 = query.getResultList();
        if ( studentList1.size()>0 ) {
            System.out.println( "Список студентов на 1 курсе:" );
            studentList1.forEach( studentOnCourse -> System.out.println( studentOnCourse.getName() ));
        }

        // Выведем список студентов 2 курса
        query = em.createQuery("SELECT s from Student s join s.courses c where c.name = '2-й курс'" );
        List<Student> studentList2 = query.getResultList();
        if ( query.getResultList().size()>0 ) {
            System.out.println( "Список студентов на 2 курсе:" );
            studentList2.forEach( studentOnCourse -> System.out.println( studentOnCourse.getName() ));
        }

        em.getEntityManagerFactory().close();
        em.close();

    }
}
