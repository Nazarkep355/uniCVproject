package com.example.unicvproject.service;

import com.example.unicvproject.entity.Department;
import com.example.unicvproject.repos.DepartmentRepository;
import com.example.unicvproject.repos.LectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeCountFinder implements Finder {
    @Autowired
    private DepartmentRepository depRepository;
    @Autowired
    private LectorRepository lectorRepository;


    @Override
    public Integer find(String attribute) {
        Department department = depRepository.findByName(attribute);
        Integer size = lectorRepository.findByDepartment(department).size();
        return size;
    }
}
