package com.example.demo1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo1.entity.Student;

@Repository
public interface StudentRepository  extends JpaRepository<Student, Long> {
	
	@Query(value = "SELECT ks FROM Student ks WHERE ks.id = :id ")
	public  Student findByStudentId(long id);

}
