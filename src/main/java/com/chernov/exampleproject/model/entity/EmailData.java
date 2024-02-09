package com.chernov.exampleproject.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "email_data")

@EqualsAndHashCode(exclude = "user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailData {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "email_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "email")
    private String email;
}
