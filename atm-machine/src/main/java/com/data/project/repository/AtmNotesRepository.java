package com.data.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.data.project.entity.AtmNotes;

@Repository
public interface AtmNotesRepository extends JpaRepository<AtmNotes, Integer>{


	List<AtmNotes> findAllByOrderByNoteTypeDesc();
	

}
