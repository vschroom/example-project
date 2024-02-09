package com.chernov.exampleproject.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "users")

@EqualsAndHashCode(exclude = {"emailData", "phoneData"})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<EmailData> emailData;

    @OneToMany(mappedBy = "user")
    private Set<PhoneData> phoneData;
}
