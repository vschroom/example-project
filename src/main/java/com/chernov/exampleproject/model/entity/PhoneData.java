package com.chernov.exampleproject.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "phone_data")

@EqualsAndHashCode(exclude = "user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneData {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "phone_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "phone")
    private String phone;
}
