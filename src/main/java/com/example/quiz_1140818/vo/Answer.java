package com.example.quiz_1140818.vo;

import java.util.List;

import com.example.quiz_1140818.constants.ConstantsMessage;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

public class Answer {
	@Min(value =1,message =ConstantsMessage.QUESTION_ID_ERROR)
	private int questionId;

	@Valid
	private List<Options> optionsList;
	
	//簡答的選項編號
	private String textAnswer;
	
	//單選的選項編號
	private int radioAnswer;

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public List<Options> getOptionsList() {
		return optionsList;
	}

	public void setOptionsList(List<Options> optionsList) {
		this.optionsList = optionsList;
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

	

	
	
	
 }
