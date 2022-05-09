package com.eneba.enebaback.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "USER_REVIEW")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserReview {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "REVIEWED_USER_ID", nullable = false)
    private User reviewedUser;

    @ManyToOne
    @JoinColumn(name = "REVIEWED_BY_USER_ID", nullable = false)
    private User reviewedByUser;

    @Column(name = "RATING")
    private int rating;

    @Column(name = "COMMENTS")
    private String comments;

    @Column(name = "REVIEWED_AT")
    private LocalDateTime reviewedAt;

    @OneToMany(mappedBy = "userReview")
    private List<UserReviewAnswer> answers;
}
