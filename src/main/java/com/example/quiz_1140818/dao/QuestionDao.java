package com.example.quiz_1140818.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.quiz_1140818.entity.Question;
import com.example.quiz_1140818.entity.QuestionId;
import com.example.quiz_1140818.response.QuestionListRes;
import com.example.quiz_1140818.vo.QuestionVo;

import jakarta.transaction.Transactional;

public interface QuestionDao extends JpaRepository<Question,QuestionId>{

	@Transactional
	@Modifying
	@Query(value = "insert into question(quiz_id, question_id, name,options_str,type,"
			+ "is_required) values (?1,?2,?3,?4,?5,?6)",nativeQuery = true)
	public void create(int id,int questionId,String name,String optionsStr,String type,boolean required);
	
	@Transactional
	@Modifying
	@Query(value = "delete from question where quiz_id = ?1",nativeQuery = true)
	public void deleteByQuizId(int quizId);
	
	@Transactional
	@Modifying
	@Query(value = "delete from question where quiz_id in (?1)",nativeQuery = true)
	public void deleteByQuizIdIn(List<Integer> quizIdList);
		
	@Query(value = "select * from question where quiz_id = ?1", nativeQuery = true)
	public List<Question> getByQuizId(int quizId);
}
