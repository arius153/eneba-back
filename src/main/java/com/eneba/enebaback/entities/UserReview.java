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
@Table(name = "USER_REVIEW")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserReview {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="REVIEWED_USER_ID", nullable=false)
    private User reviewedUser;

    @ManyToOne
    @JoinColumn(name="REVIEWED_BY_USER_ID", nullable=false)
    private User reviewedByUser;

    @Column(name="RATING")
    private int rating;

    @Column(name="COMMENTS")
    private String comments;

    @Column(name="REVIEWED_AT")
    private LocalDateTime reviewedAt;
}
