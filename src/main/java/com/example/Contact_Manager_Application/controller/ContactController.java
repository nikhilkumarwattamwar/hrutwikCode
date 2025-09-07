package com.example.Contact_Manager_Application.controller;

import com.example.Contact_Manager_Application.entity.Contact;
import com.example.Contact_Manager_Application.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
   private ContactService service;

    @GetMapping
    public ResponseEntity<List<Contact>> getAllContacts() {

       List<Contact> contactList=service.getAll();
        return ResponseEntity.ok(contactList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getById(@PathVariable long id){
        Contact contact= service.getContactById(id);
        return ResponseEntity.ok(contact);
    }

    @PostMapping
    public ResponseEntity<Contact> addContact(@RequestBody  Contact contact){
        Contact saved=service.save(contact);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable long id, @RequestBody Contact contact){
        Contact update=service.updateContact(id,contact);
        return ResponseEntity.ok(update);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable Long id) {
        service.deleteContact(id);
        return ResponseEntity.ok("Contact deleted with id: " + id); // 200 OK
    }
}
