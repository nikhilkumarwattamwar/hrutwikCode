package org.example;

import jakarta.persistence.*;

@Entity
@Table(name = "student_details")
public class StudentDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phone;
    private String address;

    @OneToOne
    @JoinColumn(name = "student_id", unique = true, nullable = false)
    private Student student;

    public Long getId() { return id; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public Student getStudent() { return student; }

    public void setId(Long id) { this.id = id; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setAddress(String address) { this.address = address; }
    public void setStudent(Student student) { this.student = student; }
}
