package com.eneba.enebaback.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "BORROW_LOG")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BorrowLog {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "TOOL_ID", nullable = false)
    private Tool tool;

    @ManyToOne
    @JoinColumn(name = "BORROWED_BY_USER_ID", nullable = false)
    private User user;

    @Column(name = "BORROWED_AT")
    private LocalDateTime borrowedAt;

    @Column(name = "RETURNED_AT")
    private LocalDateTime returnedAt;
}
