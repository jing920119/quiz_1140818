package com.example.quiz_1140818.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;


@Entity
@IdClass(value = QuestionId.class)
@Table(name="question")
public class Question {

	@Id
	@Column(name="quiz_id")
	private int quizId;
	
	
	@Column(name="question_id")
	private int questionId;
	
	
	@Column(name="name")
	private String name;
	
	
	@Column(name="options_str")
	//物件 Option轉成字串
	private String optionsStr;
	
	
	@Column(name="type")
	private String type;
	
	//必填
	@Column(name="is_required")
	private boolean required;
	
	


	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Question(int quizId, int questionId, String name, String optionsStr, String type, boolean required) {
		super();
		this.quizId = quizId;
		this.questionId = questionId;
		this.name = name;
		this.optionsStr = optionsStr;
		this.type = type;
		this.required = required;
	}



	public int getQuizId() {
		return quizId;
	}



	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}



	public int getQuestionId() {
		return questionId;
	}



	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getOptionsStr() {
		return optionsStr;
	}



	public void setOptionsStr(String optionsStr) {
		this.optionsStr = optionsStr;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public boolean isRequired() {
		return required;
	}



	public void setRequired(boolean required) {
		this.required = required;
	}





	
	
	
}
