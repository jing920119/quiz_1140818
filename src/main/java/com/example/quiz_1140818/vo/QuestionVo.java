package com.example.quiz_1140818.vo;

import java.util.List;

import com.example.quiz_1140818.constants.ConstantsMessage;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class QuestionVo {
	
	private int quizId;
	
	@Min(value = 1, message =ConstantsMessage.QUESTION_ID_ERROR)
	private int questionId;
	
	@NotBlank(message =ConstantsMessage.QUESTION_NAME_ERROR)
	private String name;
	
	//物件 Option轉成字串
	private List<Options> optionsList;
	
	@NotBlank(message =ConstantsMessage.QUESTION_TYPE_ERROR)
	private String type;

	private boolean required;
	
	public QuestionVo() {
		super();
	}
	

	public QuestionVo(int quizId, int questionId, String name, List<Options> optionsList,
			String type, boolean required) {
		super();
		this.quizId = quizId;
		this.questionId = questionId;
		this.name = name;
		this.optionsList = optionsList;
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

	//簡答題時不會有選項，所以不能加上限制]
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
	
	
}
