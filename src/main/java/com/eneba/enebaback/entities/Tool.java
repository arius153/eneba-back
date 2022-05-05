package com.eneba.enebaback.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TOOL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tool {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "TOOL_CATEGORY_ID", nullable = false)
    private ToolCategory toolCategory;

    @ManyToOne
    @JoinColumn(name = "OWNER_USER_ID", nullable = false)
    private User user;

    @Column(name = "description")
    private String description;

    @Column(name = "GEO_CORD_X")
    private Double geoCordX;

    @Column(name = "GEO_CORD_Y")
    private Double geoCordY;

    @Column(name = "FORMATED_ADDRESS")
    private String formattedAddress;

    @OneToMany(mappedBy = "tool")
    private Set<BorrowLog> borrowLogs;

    @OneToMany(mappedBy = "tool")
    private Set<Image> images;

    @Column(name = "price")
    private Float price;

    @Column(name = "available")
    private boolean available;

    @Column(name = "PICK_UP_TIME_WEEKEND")
    private String pickUpTimeWeekend;

    @Column(name = "PICK_UP_TIME_WORK_DAY")
    private String pickUpTimeWorkDay;

    @ElementCollection
    private List<Integer> availableDays = new ArrayList<>();
}
