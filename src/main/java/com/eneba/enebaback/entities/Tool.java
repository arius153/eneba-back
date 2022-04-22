package com.eneba.enebaback.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TOOL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tool {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="TOOL_CATEGORY_ID", nullable=false)
    private ToolCategory toolCategory;

    @ManyToOne
    @JoinColumn(name="OWNER_USER_ID", nullable=false)
    private User user;

    @Lob
    @Column(name="IMAGE")
    private byte[] image;

    @Column(name = "COMMENT")
    private String comment;

    @Column(name = "GEO_CORD_X")
    private String geoCordX;

    @Column(name = "GEO_CORD_Y")
    private String geoCordY;

    @OneToMany(mappedBy="tool")
    private Set<BorrowLog> borrowLogs;
}
