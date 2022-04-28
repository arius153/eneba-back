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
@Table(name = "TOOL_CATEGORY")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ToolCategory {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "CATEGORY_NAME")
    private String categoryName;

    @OneToMany(mappedBy = "toolCategory")
    private Set<Tool> tools;
}
