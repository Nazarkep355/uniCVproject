package com.example.unicvproject.repos;

import com.example.unicvproject.entity.Department;
import com.example.unicvproject.entity.Lector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {

    @Query("select d from Department d where d.name = ?1")
    Department findByName(String name);

}
