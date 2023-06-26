package com.integra.test.SpringBootDemo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.integra.test.SpringBootDemo.entity.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {

	/*
	 * @Transactional
	 * 
	 * @Query(value = "select * from student where id=:id", nativeQuery = true)
	 * Student findById(@Param(value = "id") int id);
	 */

	@Transactional
	@Modifying
	@Query(value = "delete from student where id=:id", nativeQuery = true)
	void deleteById(@Param(value =  "id") int id);

	
}
