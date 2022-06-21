package com.data.project.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtmNotes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private int noteType;
	
	private int noteCount;
	

	public int getNoteType() {
		return noteType;
	}

	public void setNoteType(int noteType) {
		this.noteType = noteType;
	}

	public int getNoteCount() {
		return noteCount;
	}

	public void setNoteCount(int noteCount) {
		this.noteCount = noteCount;
	}

	@Override
	public String toString() {
		return "AtmNotes [noteType=" + noteType + ", noteCount=" + noteCount + "]";
	}


}
