package com.example.unicvproject.repos;

import com.example.unicvproject.entity.Department;
import com.example.unicvproject.entity.Lector;
import com.example.unicvproject.entity.enums.Degree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LectorRepository extends JpaRepository<Lector, Long> {

    @Query("select l from Lector l where ?1 member l.department")
    List<Lector> findByDepartment(Department department);

    @Query("select avg(l.salary) from Lector l where ?1 member l.department")
    Double findAvgSalaryByDepartment(Department department);

    @Query("select l from Lector l where upper(l.name) like upper(concat('%', ?1, '%'))")
    List<Lector> findLectorsByNameContainingIgnoreCase(String name);
}
