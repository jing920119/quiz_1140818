package com.example.quiz_1140818.response;

import java.util.List;

import com.example.quiz_1140818.resquest.FillinReq;
import com.example.quiz_1140818.vo.FeedbackVo;


public class FeedbackRes extends BasicRes{

	private  List<FeedbackVo>  FeedbackVoList;

	public FeedbackRes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FeedbackRes(int code, String message) {
		super(code, message);
		// TODO Auto-generated constructor stub
	}

	public FeedbackRes(int code, String message, List<FeedbackVo> feedbackVoList) {
		super(code, message);
		FeedbackVoList = feedbackVoList;
	}

	public List<FeedbackVo> getFeedbackVoList() {
		return FeedbackVoList;
	}

	public void setFeedbackVoList(List<FeedbackVo> feedbackVoList) {
		FeedbackVoList = feedbackVoList;
	}

	
	
	
}
