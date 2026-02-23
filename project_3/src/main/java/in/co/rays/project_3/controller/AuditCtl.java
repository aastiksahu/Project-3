package in.co.rays.project_3.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.project_3.dto.AuditDTO;
import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.AuditModelInt;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

@WebServlet(urlPatterns = { "/ctl/AuditCtl" })
public class AuditCtl extends BaseCtl {

	protected void preload(HttpServletRequest request) {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("TRANSACTION_INITIATED", "TRANSACTION_INITIATED");
		map.put("TRANSACTION_APPROVED", "TRANSACTION_APPROVED");
		map.put("TRANSACTION_FAILED", "TRANSACTION_FAILED");
		map.put("REFUND_PROCESSED", "REFUND_PROCESSED");
		map.put("TRANSACTION_CANCELLED", "TRANSACTION_CANCELLED");
		map.put("LIMIT_EXCEEDED", "LIMIT_EXCEEDED");

		request.setAttribute("map", map);

	}

	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("actionType"))) {
			request.setAttribute("actionType", PropertyReader.getValue("error.require", "Action Type"));
			pass = false;
		} 

		if (DataValidator.isNull(request.getParameter("actionbBy"))) {
			request.setAttribute("actionbBy", PropertyReader.getValue("error.require", "Actionb By"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("result"))) {
			request.setAttribute("result", PropertyReader.getValue("error.require", "Result"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("actionDate"))) {
			request.setAttribute("actionDate", PropertyReader.getValue("error.require", "Action Date"));
			pass = false;
		} else if (!DataValidator.isDate(request.getParameter("actionDate"))) {
			request.setAttribute("actionDate", PropertyReader.getValue("error.date", "Action Date"));
			pass = false;
		}
		return pass;

	}

	protected BaseDTO populateDTO(HttpServletRequest request) {

		AuditDTO dto = new AuditDTO();

		dto.setId(DataUtility.getLong(request.getParameter("id")));

		dto.setActionDate(DataUtility.getDate(request.getParameter("actionDate")));

		dto.setActionbBy(DataUtility.getString(request.getParameter("actionbBy")));

		dto.setResult(DataUtility.getString(request.getParameter("result")));

		dto.setActionType(DataUtility.getString(request.getParameter("actionType")));

		populateBean(dto, request);

		return dto;

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String op = DataUtility.getString(request.getParameter("operation"));

		AuditModelInt model = ModelFactory.getInstance().getAuditModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (id > 0 || op != null) {
			System.out.println("in id > 0  condition");
			AuditDTO dto = null;
			try {
				dto = model.findByPK(id);
				ServletUtility.setDto(dto, request);
			} catch (Exception e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		System.out.println("in do post");

		String op = DataUtility.getString(request.getParameter("operation"));

		AuditModelInt model = ModelFactory.getInstance().getAuditModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			AuditDTO dto = (AuditDTO) populateDTO(request);

			try {
				if (id > 0) {
					model.update(dto);
					ServletUtility.setSuccessMessage("Data is successfully Updated", request);
				} else {

					try {
						model.add(dto);
						ServletUtility.setSuccessMessage("Data is successfully saved", request);
					} catch (ApplicationException e) {
						ServletUtility.handleException(e, request, response);
						return;
					} catch (DuplicateRecordException e) {
						ServletUtility.setDto(dto, request);
						ServletUtility.setErrorMessage("Audit already exists", request);
					}

				}
				ServletUtility.setDto(dto, request);

			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setDto(dto, request);
				ServletUtility.setErrorMessage("Audit already exists", request);
			}
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.AUDIT_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.AUDIT_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {
		return ORSView.AUDIT_VIEW;
	}

}
