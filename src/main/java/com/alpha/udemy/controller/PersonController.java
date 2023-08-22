package com.alpha.udemy.controller;

import com.alpha.udemy.entity.Address;
import com.alpha.udemy.entity.Authority;
import com.alpha.udemy.entity.Person;
import com.alpha.udemy.entity.Role;
import com.alpha.udemy.repository.AuthorityRepository;
import com.alpha.udemy.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class PersonController {

    private final PersonRepository personRepository;
    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping("/save")
    public Person savePerson(@RequestBody Person person)
    {
        Person saved = personRepository.save(person);
        return saved;
    }

    @GetMapping("/get")
    public Person getPersonById(@RequestParam long id)
    {
        Optional<Person> person = personRepository.findById(id);
        return person.isPresent()?person.get():null;
    }

    @PutMapping("/update")
    public Person updatePerson(@RequestBody Person person)
    {
        Optional<Person> personById = personRepository.findById(person.getId());
        if(personById.isPresent())
        {
            Person updatedPerson = personRepository.save(person);
            return updatedPerson;
        }
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public Person deletePerson(@PathVariable Long id)
    {
        Optional<Person> personById = personRepository.findById(id);
        if(personById.isPresent())
        {
            personRepository.deleteById(id);
            return personById.get();
        }

        return null;
    }
    @GetMapping("/test")
    public void testing(@PathVariable Long id)
    {

    }



    @GetMapping("/getdummy")
    public Person getdummy()
    {
        Address address=new Address();
        address.setId(63L);
        address.setAddress("2-63");
        address.setCity("ATP");
        address.setState("AP");
        address.setPincode("515631");

        Person person=new Person();
        person.setId(1L);
        person.setName("MS Dhoni");
        person.setEmail("dhoni7@gmail.com");
        person.setPassword("Dhoni");
        person.setAddress(address);
        person.setAuthorities(List.of(authorityRepository.findById(1L).get()));

        return person;
    }


    @GetMapping("/createroles")
    public ResponseEntity<List<Authority>> createRoles()
    {
        Authority userAuthority=new Authority();
        userAuthority.setRole(Role.ROLE_USER);

        Authority adminAuthority=new Authority();
        adminAuthority.setRole(Role.ROLE_ADMIN);

        System.out.println(userAuthority);
        System.out.println(adminAuthority);
        System.out.println(authorityRepository!=null);
        List<Authority> savedAuthorities = new ArrayList<>();
        savedAuthorities.add(authorityRepository.save(userAuthority));
        savedAuthorities.add(authorityRepository.save(adminAuthority));
       // List<Authority> savedAuthorities = authorityRepository.saveAll(Arrays.asList(userAuthority, adminAuthority));
        return new ResponseEntity<>(savedAuthorities, HttpStatus.CREATED);
    }
}
