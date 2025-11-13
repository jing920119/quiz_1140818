package com.example.quiz_1140818.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.quiz_1140818.constants.ResCodeMessage;
import com.example.quiz_1140818.dao.AccountDao;
import com.example.quiz_1140818.entity.Account;
import com.example.quiz_1140818.response.BasicRes;

@Service
public class AccountService {
	@Autowired
	private AccountDao dao;
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	public BasicRes addInfo(String account,String password) {
		try {
			//若文件有說明在新增資訊之前要先檢查帳號是否已存在
			int count=dao.selectCountByAccount(account);
			//因為是透過PK欄位account來查詢是否有存在值，所以count只會是1或0
			if(count == 1) {
				return new BasicRes(ResCodeMessage.ACCOUNT_EXIST.getCode(),
						ResCodeMessage.ACCOUNT_EXIST.getMessage());
			}
			//存進DB中的密碼記得要記得加密
			dao.addInfo(account, encoder.encode(password));
			
			return new BasicRes(ResCodeMessage.SUCCESS.getCode(),
					ResCodeMessage.SUCCESS.getMessage());
		}catch (Exception e) {
//			return new BasicRes(ResCodeMessage.ADD_INFO_FAILD.getCode(), ResCodeMessage.ADD_INFO_FAILD.getMessage());
			// 發生 Exception 時，可以有以下2種處理方式:
						// 1. 固定的回覆訊息，但真正錯誤原因無法顯示

			// 2. 將 catch 到的例外(Exception)拋出(throw)，再由自定義的類別
						//    GlobalExceptionHandler 寫入(回覆)真正的錯誤訊息

			throw e;
		}
	}
	public BasicRes login(String account,String password) {
		//使用account 取得對應資料
		Account data = dao.selectByAccount(account);
		if(data==null) {//data==null表示沒資料
			return new BasicRes(ResCodeMessage.NOT_FOUND.getCode(),
					ResCodeMessage.NOT_FOUND.getMessage());
		}
		//比對密碼
		if(!encoder.matches(password, data.getPassword())) {
			return new BasicRes(ResCodeMessage.NOT_FOUND.getCode(),
					ResCodeMessage.NOT_FOUND.getMessage());
		}
		return new BasicRes(ResCodeMessage.SUCCESS.getCode(),
				ResCodeMessage.SUCCESS.getMessage());
	}
}
