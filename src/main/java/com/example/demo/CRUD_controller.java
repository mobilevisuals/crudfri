/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

/*

@GetMapping hitta alla
@GetMapping("{id}") hitta en
@PostMapping
@PutMapping
@DeleteMapping("{id}")

*/

@RestController
@RequestMapping("/students")
public class CRUD_controller {
    
    private StudentRepository repository;

    @Autowired
    public CRUD_controller(StudentRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    Collection<Student> getStudents()
    {return repository.findAll();
    }
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    class StudentNotFoundException extends RuntimeException
    {

        public StudentNotFoundException() {
            super("Student not found");
        }
        
    }
    
    @GetMapping("{id}")
    Student findStudent(@PathVariable Long id)
    {return repository.findById(id).orElseThrow(StudentNotFoundException::new);
    }
    
}
