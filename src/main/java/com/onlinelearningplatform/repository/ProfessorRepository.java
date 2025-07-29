package com.onlinelearningplatform.repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.onlinelearningplatform.models.Professor;



public interface ProfessorRepository extends CrudRepository<Professor, Integer>
{
    public Professor findByEmail(String email);
    
    public List<Professor> findProfessorListByEmail(String email);
	
	public Professor findByProfessorname(String professorname);
	
	public Professor findByEmailAndPassword(String email, String password);
	
	public List<Professor> findProfileByEmail(String email);
	
	@Transactional
	@Modifying
	@Query(value = "update professor set status = 'accept' where email = ?1", nativeQuery = true)
	public void updateStatus(String email);
	
	@Transactional
	@Modifying
	@Query(value = "update professor set status = 'reject' where email = ?1", nativeQuery = true)
	public void rejectStatus(String email);
	
	@Modifying
	@Query("UPDATE Professor p SET p.status = 'accept', p.comment = NULL WHERE p.email = ?1")
	void updateStatusAndClearComment(String email);

	@Modifying
	@Query("UPDATE Professor p SET p.status = 'reject', p.comment = ?2 WHERE p.email = ?1")
	void rejectStatusWithComment(String email, String comment);


	
}