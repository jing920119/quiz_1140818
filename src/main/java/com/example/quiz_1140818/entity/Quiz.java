package com.example.quiz_1140818.entity;

import java.time.LocalDate;

import com.example.quiz_1140818.constants.ConstantsMessage;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
@Entity
@Table(name="quiz")
public class Quiz {

	@Id
	@Column(name="id")
	private int id;
	
	@NotBlank(message =ConstantsMessage.QUIZ_TITLE_ERROR)
	@Column(name="title")
	private String title;
	
	@NotBlank(message =ConstantsMessage.QUIZ_DESCRIPTION_ERROR)
	@Column(name="description")
	private String description;
	
	@NotNull(message =ConstantsMessage.QUIZ_START_DATE_ERROR)
	@Column(name="start_date")
	private LocalDate startDate;
	
	@NotNull(message =ConstantsMessage.QUIZ_END_DATE_ERROR)
	@Column(name="end_date")
	private LocalDate endDate;
	
	@Column(name="is_publish")
	private boolean publish;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public boolean isPublish() {
		return publish;
	}

	public void setPublish(boolean publish) {
		this.publish = publish;
	}
	
	
}
