package in.co.rays.project_3.dto;

import java.util.Date;

public class AuditDTO extends BaseDTO {

	private String actionType;
	private String actionbBy;
	private Date actionDate;
	private String result;

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getActionbBy() {
		return actionbBy;
	}

	public void setActionbBy(String actionbBy) {
		this.actionbBy = actionbBy;
	}

	public Date getActionDate() {
		return actionDate;
	}

	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String getKey() {
		return id + "";
	}

	@Override
	public String getValue() {
		return actionType;
	}
}
