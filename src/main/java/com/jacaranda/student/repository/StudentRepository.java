package com.jacaranda.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jacaranda.student.modell.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
