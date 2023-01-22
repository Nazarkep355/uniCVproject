package com.example.unicvproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name = "departments")
@Getter
@Setter
public class Department {
    @Id
    private Long id;
    @Column
    private String name;
    @OneToOne
//    @NonNull
    @JoinColumn(name = "head")
    private Lector head;
}
