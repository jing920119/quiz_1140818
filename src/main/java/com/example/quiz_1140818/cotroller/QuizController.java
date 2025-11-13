package com.example.quiz_1140818.cotroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz_1140818.entity.Quiz;
import com.example.quiz_1140818.response.BasicRes;
import com.example.quiz_1140818.response.QuestionListRes;
import com.example.quiz_1140818.response.QuizListRes;
import com.example.quiz_1140818.resquest.CreateUpdateReq;
import com.example.quiz_1140818.resquest.DeleteReq;
import com.example.quiz_1140818.resquest.SerchReq;
import com.example.quiz_1140818.service.QuizService;
import com.example.quiz_1140818.vo.QuestionVo;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
public class QuizController {

	@Autowired
	private QuizService quizService;
	
	@PostMapping(value = "quiz/create")
	public BasicRes create(@Valid @RequestBody CreateUpdateReq req) throws Exception {
		return quizService.create(req.getQuiz(), req.getQuestionList());
	}
	
	@PostMapping(value = "quiz/update")
	public BasicRes update(@Valid @RequestBody CreateUpdateReq req) throws Exception {
		return quizService.update(req.getQuiz(), req.getQuestionList());
	}
	
	@GetMapping(value = "quiz/list")
	public QuizListRes getQuizList()  throws Exception{
		return quizService.getQuizList(false);
	}
	
	@GetMapping(value = "quiz/Published_list")
	public QuizListRes getPublishedQuizList()  throws Exception{
		return quizService.getQuizList(true);
	}
	
	@PostMapping(value = "quiz/serch")
	public QuizListRes getQuizList(@RequestBody SerchReq req) {
		return quizService.getQuizList(req.getTitle(),req.getStartDate(),req.getEndDate(),req.isGetPublish());
	}
	@GetMapping(value = "quiz/question_list")
	public QuestionListRes getQuestionList(@RequestParam("quizId") int quizId) throws Exception {
		return quizService.getQuestionList(quizId);
	}
	
	@PostMapping(value = "quiz/delete")
	public BasicRes deleteByQuizId(@Valid @RequestBody DeleteReq req) throws Exception{
		return quizService.deleteByQuizId(req.getQuizIdList());
	}
	
	
}
