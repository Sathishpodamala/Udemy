package com.alpha.udemy.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Person extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Name can't be empty")
    @Size(min = 3,message = "Name should be more than 3 characters")
    private String name;
    private String email;
    private String password;


    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name = "person_roles",
            joinColumns = {@JoinColumn(name = "person_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "roles_id",referencedColumnName = "id")}
    )
    private List<Authority>authorities;

    @OneToOne(fetch =FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
}
