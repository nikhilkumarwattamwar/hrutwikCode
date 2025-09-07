package com.example.Contact_Manager_Application.service;

import com.example.Contact_Manager_Application.entity.Contact;
import com.example.Contact_Manager_Application.exception.ContactNotFoundException;
import com.example.Contact_Manager_Application.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository repository;

    public Contact save(Contact contact){
       return repository.save(contact);
    }

    public Contact getContactById(long id){
       return repository.findById(id).orElseThrow(()->new ContactNotFoundException("Contact Not found : "+ id));
    }

    public List<Contact> getAll(){
        return repository.findAll();
    }

    public void deleteContact(long id){
        if (!repository.existsById(id)){
            throw new ContactNotFoundException("Cannot delete, contact not found with id: " + id);
        }
    }

    public Contact updateContact(Long id, Contact contact) {
        Contact existing = repository.findById(id)
                .orElseThrow(() -> new ContactNotFoundException("Cannot update, contact not found with id: " + id));

        existing.setName(contact.getName());
        existing.setPhone(contact.getPhone());
        existing.setEmail(contact.getEmail());

        return repository.save(existing);
    }

}
