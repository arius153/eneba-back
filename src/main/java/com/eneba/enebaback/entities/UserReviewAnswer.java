package com.eneba.enebaback.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "USER_REVIEW_ANSWER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserReviewAnswer {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ANSWERED_BY_USER_ID", nullable = false)
    private User answeredBy;

    @Column(name = "ANSWER")
    private String answer;

    @Column(name = "ANSWERED_AT")
    private LocalDateTime answeredAt;

    @ManyToOne
    @JoinColumn(name = "USER_REVIEW_ID", nullable = false)
    private UserReview userReview;

}
