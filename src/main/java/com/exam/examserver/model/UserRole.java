package com.exam.examserver.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
public class UserRole {
    @Id
    @GeneratedValue(strategy =GenerationType.AUTO )
    private Long userRoleId;

    @ManyToOne(fetch =FetchType.EAGER )
    private User user;

    @ManyToOne
    private Role role;
}
