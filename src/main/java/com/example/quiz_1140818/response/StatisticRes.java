package com.example.quiz_1140818.response;

import com.example.quiz_1140818.vo.StatisticVo;

public class StatisticRes extends BasicRes{

	private StatisticVo statisticVo;

	public StatisticRes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StatisticRes(int code, String message) {
		super(code, message);
		// TODO Auto-generated constructor stub
	}

	public StatisticRes(int code, String message, StatisticVo statisticVo) {
		super(code, message);
		this.statisticVo = statisticVo;
	}

	public StatisticVo getStatisticVo() {
		return statisticVo;
	}

	public void setStatisticVo(StatisticVo statisticVo) {
		this.statisticVo = statisticVo;
	}
	
	
}
