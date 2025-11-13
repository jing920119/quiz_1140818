package com.example.quiz_1140818.response;

import java.util.List;

import com.example.quiz_1140818.entity.Quiz;


public class QuizListRes extends BasicRes{
	
	private List<Quiz> quizList;

	public QuizListRes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuizListRes(int code, String message) {
		super(code, message);
		// TODO Auto-generated constructor stub
	}

	
	public QuizListRes(int code, String message, List<Quiz> quizList) {
		super(code, message);
		this.quizList = quizList;
	}

	public QuizListRes(List<Quiz> quizList) {
		super();
		this.quizList = quizList;
	}

	public List<Quiz> getQuizList() {
		return quizList;
	}

	public void setQuizList(List<Quiz> quizList) {
		this.quizList = quizList;
	}

	
}
