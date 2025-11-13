package com.example.quiz_1140818.vo;

import java.time.LocalDate;
import java.util.List;

import com.example.quiz_1140818.entity.Quiz;
import com.example.quiz_1140818.entity.User;


public class FeedbackVo{

	private User user;
	
	private Quiz quiz;
	
	private List<QuestionAnswerVo> questionVoList;
	
	private LocalDate fillinDate;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public List<QuestionAnswerVo> getQuestionVoList() {
		return questionVoList;
	}

	public void setQuestionVoList(List<QuestionAnswerVo> questionVoList) {
		this.questionVoList = questionVoList;
	}

	public LocalDate getFillinDate() {
		return fillinDate;
	}

	public void setFillinDate(LocalDate fillinDate) {
		this.fillinDate = fillinDate;
	}

	
	
	
}
