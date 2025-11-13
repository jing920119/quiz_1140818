package com.example.quiz_1140818.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.example.quiz_1140818.constants.QuestionType;
import com.example.quiz_1140818.constants.ResCodeMessage;
import com.example.quiz_1140818.dao.QuestionDao;
import com.example.quiz_1140818.dao.QuizDao;
import com.example.quiz_1140818.entity.Question;
import com.example.quiz_1140818.entity.Quiz;
import com.example.quiz_1140818.response.BasicRes;
import com.example.quiz_1140818.response.QuestionListRes;
import com.example.quiz_1140818.response.QuizListRes;
import com.example.quiz_1140818.vo.Options;
import com.example.quiz_1140818.vo.QuestionVo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;




@Service
public class QuizService {

	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private QuizDao quizDao;

	@Autowired
	private QuestionDao questionDao;

	/**
	 * @Transactional:
	 * 1. 使用的於修改資料時(insert/update/delete)且有以下2種情形<br>
	 * 1.1 同一個方法中有使用多個 Dao 時，例如下面方法中有同時使用 quizDao 和 questionDao<br>
	 * 1.2 同一個 Dao 有修改多筆的資料，例如下面方法中有使用 questionDao 新增多筆的問題<br>
	 * rollbackFor = Exception.class <br>
	 * 2. 其預設的有效作用範圍是當程式發生 RuntimeException(以及其子類別) 時才會讓資料回朔，所以為了
	 *    在發生其他 Exception 時也可以讓資料回朔，就要把作用範圍提升到所有例外的父類別: Exception<br>
	 * 3. 要讓 @Transactional 有效的另一個條件必須要把發生的 Exception 給它 throw 出去
	 */

	@Transactional(rollbackFor = Exception.class)
	public BasicRes create(Quiz quiz, List<QuestionVo> questionVoList) throws Exception {
		try {
			// 檢查question
			BasicRes checkRes = checkQuestion(questionVoList);
			if (checkRes != null) {
				return checkRes;
			}
			//檢查時間：開始時間不能比結束時間晚 || 結束時間不能比開始時間 早
			checkRes = checkDate(quiz.getStartDate(),quiz.getEndDate());
			if (checkRes != null) {
				return checkRes;
			}
			// 新增quiz
			quizDao.create(quiz.getTitle(), quiz.getDescription(), quiz.getStartDate(), quiz.getEndDate(),
					quiz.isPublish());
			int quizId = quizDao.selectMaxIId();
			// 將 QuestionVo中的List<Options>轉成字串
			for (QuestionVo vo : questionVoList) {
				List<Options> optionsList = vo.getOptionsList();
				if (optionsList == null) {
					optionsList = new ArrayList<>();
				}
				// 把vo中的資料以及optionsStr 一一寫入到Question這個entity中
				String optionsStr = mapper.writeValueAsString(vo.getOptionsList());
				//新增question				
				questionDao.create(quizId,vo.getQuestionId(), vo.getName(), optionsStr, vo.getType(),
						vo.isRequired());

			}
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			// 若是 id(PK)已存在，新增資料就會失敗
			return new BasicRes(ResCodeMessage.UPDATE_PASSWORD_FAILD.getCode(),
					ResCodeMessage.UPDATE_PASSWORD_FAILD.getMessage());

		}
		return new BasicRes(ResCodeMessage.SUCCESS.getCode(), ResCodeMessage.SUCCESS.getMessage());

	}

	private BasicRes checkQuestion(List<QuestionVo> questionVoList) {
		for (QuestionVo item : questionVoList) {
			if (!QuestionType.checkAllType(item.getType())) {
				return new BasicRes(ResCodeMessage.QUESTION_TYPE_ERROR.getCode(),
						ResCodeMessage.QUESTION_TYPE_ERROR.getMessage());
			}
			// 檢查選項
			// 正確的應該是:type是1.簡答題時不會有選項 2.選擇題(單、多)時要有選項
			// 以下是排除:type是簡答題且時都有選項
			if (!QuestionType.checkChoiceType(item.getType())) {
				if (!item.getOptionsList().isEmpty()) {
					return new BasicRes(ResCodeMessage.QUESTION_TYPE_OPTIONS_MISMACH.getCode(),
							ResCodeMessage.QUESTION_TYPE_OPTIONS_MISMACH.getMessage());
				}

			}
			// 以下是排除:type是單或多選時卻沒有選項
			if (QuestionType.checkChoiceType(item.getType())) {
				if (item.getOptionsList().isEmpty()) {

					return new BasicRes(ResCodeMessage.QUESTION_TYPE_OPTIONS_MISMACH.getCode(),
							ResCodeMessage.QUESTION_TYPE_OPTIONS_MISMACH.getMessage());
				}

			}
		}
		return null;
	}
	
	/**
	 * 檢查問卷日期：
	 * 1.開始日期不能比結束日期晚 || 結束日期不能比開始日期 早<br>
	 * 2.條件1.成立下，開始日期不能比當前日期早<br>
	 * 3. startDate.isAfter(endDate): <br>
	 * 3.1 startDate 早於 endDate  --> false <br>
	 * 3.2 startDate 等於 endDate  --> false <br>
	 * 3.3 startDate 晚於 endDate  --> true

	 */
	private BasicRes checkDate(LocalDate startDate,LocalDate endDate) {
		// 正常來說，問卷的開始日期一定是等於或晚於結束日期，若 startDate.isAfter(endDate)
		// 的結果是 true，則表示問卷的兩個時間是錯的
		if(startDate.isAfter(endDate)) {
			return new BasicRes(ResCodeMessage.QUIZ_DATE_ERROR.getCode(),
					ResCodeMessage.QUIZ_DATE_ERROR.getMessage());
		}
		//isBefore 也是不包含兩個日期相等
		if(LocalDate.now().isBefore(startDate)) {
			return new BasicRes(ResCodeMessage.QUIZ_DATE_ERROR.getCode(),
					ResCodeMessage.QUIZ_DATE_ERROR.getMessage());
		}
		return null;
		
	}
	
	@Transactional(rollbackFor = Exception.class)
	public BasicRes update(Quiz quiz, List<QuestionVo> questionVoList) throws Exception {
		try {
			//檢查quizId是否存在於DB
			int quizId= quiz.getId();
			
			//搜尋欄位id 出現的次數，因為id是PK ，所以結果只會是0或1
//			if(quizDao.selectCountId(quizId) == 0) {
//				return new BasicRes(ResCodeMessage.NOT_FOUND.getCode(),
//						ResCodeMessage.NOT_FOUND.getMessage());
//			}
			//檢查時間：開始時間不能比結束時間晚 || 結束時間不能比開始時間 早
			BasicRes checkRes = checkDate(quiz.getStartDate(),quiz.getEndDate());
			if (checkRes != null) {
				return checkRes;
			}
			
			//更新quiz
			int res = quizDao.update(quizId, quiz.getTitle(), quiz.getDescription(),quiz.getStartDate(), quiz.getEndDate(), quiz.isPublish());
			if(res == 0) {
				return new BasicRes(ResCodeMessage.NOT_FOUND.getCode(),
						ResCodeMessage.NOT_FOUND.getMessage());
			}
			//更新question
			//1.刪除相同 quizId的所有問卷
			questionDao.deleteByQuizId(quizId);
			//2.新增問題:確認這些問題都是在同一個quizId
			
			// 把vo中的資料以及optionsStr 一一寫入到Question這個entity中
			for (QuestionVo vo : questionVoList) {
				List<Options> optionsList = vo.getOptionsList();
				if (optionsList == null) {
					optionsList = new ArrayList<>();
				}
				// 把vo中的資料以及optionsStr 一一寫入到Question這個entity中
				String optionsStr = mapper.writeValueAsString(vo.getOptionsList());
				//新增question	
				//確保這些問題都是同一個quizId -->使用quiz中取得的id
				questionDao.create(quizId,vo.getQuestionId(), vo.getName(), optionsStr, vo.getType(),
						vo.isRequired());

			}
			return new BasicRes(ResCodeMessage.SUCCESS.getCode(), 
					ResCodeMessage.SUCCESS.getMessage());
		}catch (Exception e) {
			throw e;
		}
		
	}
	public QuizListRes getQuizList(boolean getPublish) throws Exception{
		 try {
		        if (getPublish) {
		            return new QuizListRes(ResCodeMessage.SUCCESS.getCode(),
		                    ResCodeMessage.SUCCESS.getMessage(), quizDao.getPublishedAll());
		        }
		        return new QuizListRes(ResCodeMessage.SUCCESS.getCode(),
		                ResCodeMessage.SUCCESS.getMessage(), quizDao.getAll());
		    } catch (Exception e) {
		        e.printStackTrace(); // ✅ 讓 console 一定印出錯誤
		        return new QuizListRes(500, "伺服器錯誤：" + e.getMessage(), null);
		    }
		}
	
	public QuizListRes getQuizList() {
		return new QuizListRes(ResCodeMessage.SUCCESS.getCode(), 
				ResCodeMessage.SUCCESS.getMessage(),quizDao.getAll());
	}
	
	
	public QuizListRes getQuizList(String title,LocalDate startDate,LocalDate endDate,boolean getPublish) {
		//假設startDate 和 endDate --?檢查 endDate > startDate
		if(startDate != null && endDate != null && startDate.isAfter(endDate)) {
			return new QuizListRes(ResCodeMessage.QUIZ_DATE_ERROR.getCode(), //
					ResCodeMessage.QUIZ_DATE_ERROR.getMessage(), quizDao.getAll());
		}
		//若title沒帶值(預設是null)或空字串-->一律轉成空字串
		//到時SQL中搭配like %空字串的title %就把所有title的資料撈出來
		if(!StringUtils.hasText(title)) {
			title="";
		}
		//轉換沒有帶值的開始日期:將開始日期改成很早之前的日期
		if(startDate==null) {
			startDate=LocalDate.of(1970, 1, 1);
		}
		//轉換沒有帶值的結束日期:將結束日期改成很久之後的日期
		if(endDate==null) {
			endDate=LocalDate.of(2999, 12, 31);
		}
		if(getPublish) {//getPublish 等同於 is_publish = true
			return new QuizListRes(ResCodeMessage.SUCCESS.getCode(), 
					ResCodeMessage.SUCCESS.getMessage(),quizDao.getPublishedSearch(title,startDate,endDate));
		}
		
		return new QuizListRes(ResCodeMessage.SUCCESS.getCode(), 
				ResCodeMessage.SUCCESS.getMessage(),quizDao.getSearch(title,startDate,endDate));
		
	}
	
	
	public QuestionListRes getQuestionList(int quizId) throws Exception {
		//檢查參數
		if(quizId <=0) {
			return new QuestionListRes(ResCodeMessage.QUIZ_ID_ERROR.getCode(), 
					ResCodeMessage.QUIZ_ID_ERROR.getMessage());
		}
		//檢查quizId是否存在於DB
		//搜尋欄位id 出現的次數，因為id是PK ，所以結果只會是0或1
		if(quizDao.selectCountId(quizId) == 0) {
			return new QuestionListRes(ResCodeMessage.NOT_FOUND.getCode(),
					ResCodeMessage.NOT_FOUND.getMessage());
		}
		//用quizId從question索取資料
		List<Question> questionList =questionDao.getByQuizId(quizId);
		//建立List<QuestionVo>用來放下面for迴圈中所建立的每個QuestionVo
		List<QuestionVo> voList =new ArrayList<>();
		
		for(Question item:questionList) {
			//將每個optionsStr 轉成List<Question>
			try {
				List<Options> optionsList = mapper.readValue(item.getOptionsStr(),
						new TypeReference<>() {					
				});
						
				//把每個Question得屬性值塞到QuestionVo中
				QuestionVo vo =new QuestionVo
						(item.getQuizId(),//
						item.getQuestionId(),
						item.getName(),//
						optionsList,//
						item.getType(),//
						item.isRequired());
			
				//將vo(QuestionVo)新增到voList裡
				voList.add(vo);
			}catch  (Exception e) {
				throw e;
			}				
		}
		return new QuestionListRes(ResCodeMessage.SUCCESS.getCode(), 
				ResCodeMessage.SUCCESS.getMessage(),voList);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public BasicRes deleteByQuizId(List<Integer> quizIdList) throws Exception{
		try {
			//刪除quiz
			quizDao.deleteByQuizIdIn(quizIdList);
			//刪除question
			questionDao.deleteByQuizIdIn(quizIdList);
		}catch(Exception e) {
			throw e;
		}		
		return new BasicRes(ResCodeMessage.SUCCESS.getCode(), 
				ResCodeMessage.SUCCESS.getMessage());
	}
	
	
}
