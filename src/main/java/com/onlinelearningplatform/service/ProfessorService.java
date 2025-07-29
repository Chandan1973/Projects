//package com.onlinelearningplatform.service;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.onlinelearningplatform.models.Professor;
//import com.onlinelearningplatform.repository.ProfessorRepository;
//
//@Service
//public class ProfessorService 
//{
//	@Autowired
//	private ProfessorRepository professorRepo;
//	
//	public Professor saveProfessor(Professor professor)
//	{
//		return professorRepo.save(professor);
//	}
//	
//	public Professor addNewProfessor(Professor professor)
//	{
//		return professorRepo.save(professor);
//	}
//	
//	public Professor updateProfessorProfile(Professor professor)
//	{
//		return professorRepo.save(professor);
//	}
//	
//	public List<Professor> getAllProfessors()
//	{
//		return (List<Professor>)professorRepo.findAll();
//	}
//	
//	public List<Professor> getProfessorListByEmail(String email) 
//	{
//		return (List<Professor>)professorRepo.findProfessorListByEmail(email);
//	}
//	
//	public Professor fetchProfessorByEmail(String email)
//	{
//		return professorRepo.findByEmail(email);
//	}
//	
//	public Professor fetchProfessorByProfessorname(String professorname)
//	{
//		return professorRepo.findByProfessorname(professorname);
//	}
//	
//	public Professor fetchProfessorByEmailAndPassword(String email, String password)
//	{
//		return professorRepo.findByEmailAndPassword(email, password);
//	}
//	
//	public List<Professor> fetchProfileByEmail(String email)
//	{
//		return (List<Professor>)professorRepo.findProfileByEmail(email);
//	}
//
//	public void updateStatus(String email) 
//	{
//		professorRepo.updateStatus(email);
//	}
//
//	public void rejectStatus(String email) 
//	{
//		professorRepo.rejectStatus(email);
//	}
//
//	public List<Professor> getProfessorsByEmail(String email)
//	{
//		return professorRepo.findProfessorListByEmail(email);
//	}
//	
//public void rejectStatusWithComment(String email, String comment) {
//    professorRepo.rejectStatusWithComment(email, comment);
//}
//
//}


package com.onlinelearningplatform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinelearningplatform.models.Professor;
import com.onlinelearningplatform.repository.ProfessorRepository;

import jakarta.transaction.Transactional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepo;

    public Professor saveProfessor(Professor professor) {
        return professorRepo.save(professor);
    }

    public Professor addNewProfessor(Professor professor) {
        return professorRepo.save(professor);
    }

    public Professor updateProfessorProfile(Professor professor) {
        return professorRepo.save(professor);
    }

//    public List<Professor> getAllProfessors() {
//        return professorRepo.findAll();
//    }

    public List<Professor> getAllProfessors() {
        return (List<Professor>) professorRepo.findAll();
    }

    
    public List<Professor> getProfessorListByEmail(String email) {
        return professorRepo.findProfessorListByEmail(email);
    }

    public Professor fetchProfessorByEmail(String email) {
        return professorRepo.findByEmail(email);
    }

    public Professor fetchProfessorByProfessorname(String professorname) {
        return professorRepo.findByProfessorname(professorname);
    }

    public Professor fetchProfessorByEmailAndPassword(String email, String password) {
        return professorRepo.findByEmailAndPassword(email, password);
    }

    public List<Professor> fetchProfileByEmail(String email) {
        return professorRepo.findProfileByEmail(email);
    }

    // ✅ Accept professor: status = accept, clear comment
    @Transactional
    public void updateStatus(String email) {
        professorRepo.updateStatusAndClearComment(email);
    }

    // ✅ Reject professor with comment
    @Transactional
    public void rejectStatusWithComment(String email, String comment) {
        professorRepo.rejectStatusWithComment(email, comment);
    }

    public List<Professor> getProfessorsByEmail(String email) {
        return professorRepo.findProfessorListByEmail(email);
    }
}
