package com.example.quiz_1140818.vo;

import java.util.List;

import com.example.quiz_1140818.entity.Quiz;

public class StatisticVo {
	
	private Quiz quiz;
	
	private List<QuestionCountVo> questionCountVoList;



	public StatisticVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StatisticVo(Quiz quiz, List<QuestionCountVo> questionCountVoList) {
		super();
		this.quiz = quiz;
		this.questionCountVoList = questionCountVoList;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public List<QuestionCountVo> getQuestionCountVoList() {
		return questionCountVoList;
	}

	public void setQuestionCountVoList(List<QuestionCountVo> questionCountVoList) {
		this.questionCountVoList = questionCountVoList;
	}


	
}
