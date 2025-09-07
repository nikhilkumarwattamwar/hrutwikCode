package com.example.Contact_Manager_Application.repository;

import com.example.Contact_Manager_Application.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository< Contact,Long> {
}
