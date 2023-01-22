package com.example.unicvproject.entity;

import com.example.unicvproject.entity.enums.Degree;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "lectors")
@Getter
@Setter
public class Lector {
    @Id
    private Long id;
    @ManyToMany
    private List<Department> department;
    @Column
    private String name;
    @Column
    private Degree degree;
    @Column
    private int salary;
}
