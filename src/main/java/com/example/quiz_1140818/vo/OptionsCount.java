package com.example.quiz_1140818.vo;

public class OptionsCount {

	private int code;
	private String optionsName;
	private int count = 1; //給定預設值
	
	

	public OptionsCount(int code, String optionsName, int count) {
		super();
		this.code = code;
		this.optionsName = optionsName;
		this.count = count;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getOptionsName() {
		return optionsName;
	}
	public void setOptionsName(String optionsName) {
		this.optionsName = optionsName;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
