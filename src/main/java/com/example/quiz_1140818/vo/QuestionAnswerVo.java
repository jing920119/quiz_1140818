package com.example.quiz_1140818.vo;

import java.util.List;

public class QuestionAnswerVo {

	private int quizId;
	
	private int questionId;
	
	private String name;
	// 簡答題時不會有選項，所以不能加上限制
	private List<Options> optionsList;
	
	private String type;
	
	private boolean required;
	
	// 簡答的答案
	private String textAnswer;
	
	// 單選的選項編號
	private int radioAnswer;

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

	public List<Options> getOptionsList() {
		return optionsList;
	}

	public void setOptionsList(List<Options> optionsList) {
		this.optionsList = optionsList;
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

	public String getTextAnswer() {
		return textAnswer;
	}

	public void setTextAnswer(String textAnswer) {
		this.textAnswer = textAnswer;
	}

	public int getRadioAnswer() {
		return radioAnswer;
	}

	public void setRadioAnswer(int radioAnswer) {
		this.radioAnswer = radioAnswer;
	}

	public QuestionAnswerVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuestionAnswerVo(int quizId, int questionId, String name , String type,
			boolean required) {
		super();
		this.quizId = quizId;
		this.questionId = questionId;
		this.name = name;
		
		this.type = type;
		this.required = required;

	}

	
}
