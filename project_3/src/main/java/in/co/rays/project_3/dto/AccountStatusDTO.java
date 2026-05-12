package in.co.rays.project_3.dto;

public class AccountStatusDTO extends BaseDTO {

	private String accountCode;
	private String userName;
	private String acccountType;
	private String status;

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAcccountType() {
		return acccountType;
	}

	public void setAcccountType(String acccountType) {
		this.acccountType = acccountType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String getKey() {
		return id + "";
	}

	@Override
	public String getValue() {
		return userName;
	}

}
