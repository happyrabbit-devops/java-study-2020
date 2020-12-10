package hibernate.model;

import javax.persistence.*;

@Entity
public class Email {

    @Id
    @GeneratedValue
    private Long id;

    private String email;


    @ManyToOne
    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
