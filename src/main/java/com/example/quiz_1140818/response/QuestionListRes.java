package com.example.quiz_1140818.response;

import java.util.List;

import com.example.quiz_1140818.vo.QuestionVo;
import com.fasterxml.jackson.annotation.JsonAlias;

public class QuestionListRes extends BasicRes{
	@JsonAlias({"questionList","QuestionList"})
	private List<QuestionVo> questionVoList;

	public QuestionListRes() {
		super();
		
	}

	public QuestionListRes(int code, String message) {
		super(code, message);
	}

	
	public QuestionListRes(List<QuestionVo> questionVoList) {
		super();
		this.questionVoList = questionVoList;
	}

	public QuestionListRes(int code, String message, List<QuestionVo> questionVoList) {
		super(code, message);
		this.questionVoList = questionVoList;
	}

	public List<QuestionVo> getQuestionVoList() {
		return questionVoList;
	}

	public void setQuestionVoList(List<QuestionVo> questionVoList) {
		this.questionVoList = questionVoList;
	}
	

	
}
