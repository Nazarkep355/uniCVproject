package com.example.unicvproject.service;

import com.example.unicvproject.entity.Department;
import com.example.unicvproject.entity.Lector;
import com.example.unicvproject.entity.enums.Degree;
import com.example.unicvproject.repos.DepartmentRepository;
import com.example.unicvproject.repos.LectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticsFinder implements Finder {
    @Autowired
    private DepartmentRepository depRepository;
    @Autowired
    private LectorRepository lectorRepository;

    @Override
    public Map<Degree, Integer> find(String attribute) {

        Department department = depRepository.findByName(attribute);
        List<Lector> lectors = lectorRepository.findByDepartment(department);
        Map<Degree, Integer> map = sort(lectors);
        return map;
    }
    private Map<Degree, Integer> sort(List<Lector> lectors){
        Map<Degree,Integer> map = new HashMap();
        map.put(Degree.ASSISTANT,0);
        map.put(Degree.PROFESSOR,0);
        map.put(Degree.ASSOCIATE_PROFESSOR,0);
        for(Lector lector: lectors){
            if(lector.getDegree()==Degree.ASSISTANT){
                map.put(Degree.ASSISTANT,map.get(Degree.ASSISTANT)+1);
            }
            if(lector.getDegree()==Degree.PROFESSOR){
                map.put(Degree.PROFESSOR,map.get(Degree.PROFESSOR)+1);
            }
            else {
                map.put(Degree.ASSOCIATE_PROFESSOR,map.get(Degree.ASSOCIATE_PROFESSOR)+1);
            }
        }
        return map;
    }
}
