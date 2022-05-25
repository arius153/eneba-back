package com.eneba.enebaback.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;

@Entity
@Table(name = "TOOL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
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

    @OneToMany(mappedBy = "tool", fetch = FetchType.LAZY)
    private Set<Image> images;

    @Column(name = "price")
    private Float price;

    @Formula(value = "(SELECT CASE WHEN (COUNT(b.id) > 0) THEN false ELSE true END FROM borrow_log AS b WHERE b.tool_id = id AND b.borrowed_at <= CURRENT_DATE AND b.returned_at >= CURRENT_DATE)")
    private boolean available;

    @Column(name = "ASSISTED_TRANSPORTATION")
    private String assistedTransportation;

    @Column(name = "PICK_UP_TIME_WEEKEND")
    private String pickUpTimeWeekend;

    @Column(name = "PICK_UP_TIME_WORK_DAY")
    private String pickUpTimeWorkDay;

    @ElementCollection
    private List<Integer> availableDays = new ArrayList<>();

    @Version
    private Long version;
}
