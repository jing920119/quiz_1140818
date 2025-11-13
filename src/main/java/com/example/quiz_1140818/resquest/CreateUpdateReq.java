package com.example.quiz_1140818.resquest;

import java.util.List;

import com.example.quiz_1140818.constants.ConstantsMessage;
import com.example.quiz_1140818.entity.Quiz;
import com.example.quiz_1140818.vo.QuestionVo;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public class CreateUpdateReq {
	// 嵌套驗證: 屬性是個類別(String 除外)，且也有對該類別中的屬性加上限制
	// 因為有對類別 Quiz 中的屬性加上限制，為了要那些限制生效，就要在 Quiz 上面加上 @valid
	@Valid
	@NotNull(message = ConstantsMessage.QUIZ_ERROR)
	private Quiz quiz;
	
	@Valid
	@NotEmpty(message = ConstantsMessage.QUESTION_OPTION_ERROR)
	private List<QuestionVo> questionList;

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public List<QuestionVo> getQuestionList() {
		return questionList;
	}

	public void setQuestionLList(List<QuestionVo> questionList) {
		this.questionList = questionList;
	}
	

	
	
}
