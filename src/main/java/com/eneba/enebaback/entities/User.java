package com.eneba.enebaback.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "USERS")
// TODO move from public schema and change to USERS
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @OneToMany(mappedBy="user")
    private Set<Tool> tools;

    @OneToMany(mappedBy="user")
    private Set<BorrowLog> borrowLogs;

    @OneToMany(mappedBy="reviewedUser")
    private Set<UserReview> reviewedUsers;

    @OneToMany(mappedBy="reviewedByUser")
    private Set<UserReview> reviewedByUsers;
}
