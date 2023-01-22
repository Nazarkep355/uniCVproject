package com.example.unicvproject.service;

import com.example.unicvproject.entity.Lector;
import com.example.unicvproject.repos.LectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GlobalFinder implements Finder{
    @Autowired
    private LectorRepository lectorRepository;
    @Override
    public List<Lector> find(String attribute) {
        return lectorRepository.findLectorsByNameContainingIgnoreCase(attribute);
    }
}
