package com.example.quiz_1140818.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.quiz_1140818.entity.Quiz;
import com.example.quiz_1140818.response.BasicRes;

import jakarta.transaction.Transactional;
@Repository
public interface QuizDao extends JpaRepository<Quiz,Integer>{
	
	@Transactional
	@Modifying
	@Query(value = "insert into quiz(title, description, start_date,end_date,"
			+ "is_publish) values (?1,?2,?3,?4,?5)",nativeQuery = true)
	public void create(String title,String description,LocalDate startDate,LocalDate endDate,boolean publish);
	
	@Query(value = "select max(id) from quiz",nativeQuery = true)
	public int selectMaxIId();
	
	//select count(id)是去搜尋欄位id 出現的次數，因為id是PK ，所以結果只會是0或1
	@Query(value = "select count(id) from quiz where id = ?1",nativeQuery = true)
	public int selectCountId(int id);
	
	@Transactional
	@Modifying
	@Query(value = "update quiz set title = ?2,description = ?3 ,start_date =?4,"
			+ "end_date = ?5 ,is_publish = ?6 where id = ?1",nativeQuery = true)
	public int update(int id,String title,String description,LocalDate startDate,LocalDate endDate,boolean publish);

	@Query(value = "select * from quiz",nativeQuery = true)
	public List<Quiz> getAll();
	
	@Query(value = "select * from quiz where is_publish = true",nativeQuery = true)
	public List<Quiz> getPublishedAll();
	
	@Query(value = "select * from quiz where title like %?1% and start_date >= ?2 and "
			+ " end_date <= ?3",nativeQuery = true)
	public List<Quiz> getSearch(String title,LocalDate startDate,LocalDate endDate);
	
	@Query(value = "select * from quiz where title like %?1% and start_date >= ?2 and "
			+ " end_date <= ?3 and is_publish = true",nativeQuery = true)
	public List<Quiz> getPublishedSearch(String title,LocalDate startDate,LocalDate endDate);
	
	@Transactional
	@Modifying
	@Query(value = "delete from quiz where id in (?1)",nativeQuery = true)
	public void deleteByQuizIdIn(List<Integer> quizIdList);
	
	
	//指
	@Transactional
	@Modifying
	@Query(value = "delete from quiz where is_publish is true and now > end_date  and id in (?1)",nativeQuery = true)
	public void deleteByQuizIdInWihCandition(List<Integer> quizIdList);
	
	@Query(value = "select * from quiz where id = ?1",nativeQuery = true)
	public Quiz getById(int id);
}
