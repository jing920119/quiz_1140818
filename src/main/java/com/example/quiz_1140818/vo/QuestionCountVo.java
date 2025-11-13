package com.example.quiz_1140818.vo;

import java.util.List;

public class QuestionCountVo {

	private int questionId;
	
	private String name;
	
	private List<OptionsCount> optionsCountList;
	
	private String type;
	
	private boolean required;

	public QuestionCountVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuestionCountVo(int questionId, String name, String type, boolean required) {
		super();
		this.questionId = questionId;
		this.name = name;
		this.type = type;
		this.required = required;
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

	public List<OptionsCount> getOptionsCountList() {
		return optionsCountList;
	}

	public void setOptionsCountList(List<OptionsCount> optionsCountList) {
		this.optionsCountList = optionsCountList;
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
