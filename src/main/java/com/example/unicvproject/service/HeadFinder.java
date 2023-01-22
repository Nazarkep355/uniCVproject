package com.example.unicvproject.service;

import com.example.unicvproject.entity.Department;
import com.example.unicvproject.entity.Lector;
import com.example.unicvproject.repos.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HeadFinder implements Finder {
    @Autowired
    private DepartmentRepository depRepository;

    @Override
    public Lector find(String attribute) {
        Department department = depRepository.findByName(attribute);
        return department.getHead();
    }
}
