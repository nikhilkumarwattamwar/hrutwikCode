package org.example;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private StudentDetail studentDetail;


    public void setStudentDetail(StudentDetail detail) {
        this.studentDetail = detail;
        if (detail != null) {
            detail.setStudent(this);
        }
    }


    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public StudentDetail getStudentDetail() { return studentDetail; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
}

