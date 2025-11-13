package com.example.quiz_1140818.resquest;

import com.example.quiz_1140818.constants.ConstantsMessage;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class AddInfoReq {

	/**
	 * @NotBlank(message = "Acounnt format error!!") <br>
	 * @NotBlank:表示限制字串Acounnt 不能事null 或空字串或 全空白字串<br>
	 * message 後面的字串表示當違反 @NotBlank 的限制時會得到的提示訊息
	 * @Pattern：可以使用正規表達式的限制
	 */
	
	@NotBlank(message = ConstantsMessage.PARM_ACCOUNT_ERROR)
	@Pattern(regexp="\\w{3,8}",message=ConstantsMessage.PARM_ACCOUNT_ERROR)
	private String account;
	@NotBlank(message = ConstantsMessage.PARAM_PASSWORD_ERROR)
	@Pattern(regexp="\\w{8,16}",message=ConstantsMessage.PARAM_PASSWORD_ERROR)
	private String password;
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
