package in.co.rays.project_3.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.dto.FinanceDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.FinanceModelInt;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

@WebServlet(urlPatterns = { "/ctl/FinanceCtl" })
public class FinanceCtl extends BaseCtl{

	protected void preload(HttpServletRequest request) {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("PENDING", "PENDING");
		map.put("APPROVED", "APPROVED");
		map.put("REJECTED", "REJECTED");

		request.setAttribute("map", map);

	}

	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("loanAmount"))) {
			request.setAttribute("loanAmount", PropertyReader.getValue("error.require", "Loan Amount"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("tenure"))) {
			request.setAttribute("tenure", PropertyReader.getValue("error.require", "Tenure"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("status"))) {
			request.setAttribute("status", PropertyReader.getValue("error.require", "Status"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("appliedDate"))) {
			request.setAttribute("appliedDate", PropertyReader.getValue("error.require", "Applied Date"));
			pass = false;
		} else if (!DataValidator.isDate(request.getParameter("appliedDate"))) {
			request.setAttribute("appliedDate", PropertyReader.getValue("error.date", "Applied Date"));
			pass = false;
		}
		return pass;

	}

	protected BaseDTO populateDTO(HttpServletRequest request) {

		FinanceDTO dto = new FinanceDTO();

		dto.setId(DataUtility.getLong(request.getParameter("id")));

		dto.setAppliedDate(DataUtility.getDate(request.getParameter("appliedDate")));

		dto.setLoanAmount(DataUtility.getString(request.getParameter("loanAmount")));

		dto.setTenure(DataUtility.getString(request.getParameter("tenure")));

		dto.setStatus(DataUtility.getString(request.getParameter("status")));

		populateBean(dto, request);

		return dto;

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String op = DataUtility.getString(request.getParameter("operation"));

		FinanceModelInt model = ModelFactory.getInstance().getFinanceModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (id > 0 || op != null) {
			System.out.println("in id > 0  condition");
			FinanceDTO dto = null;
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

		FinanceModelInt model = ModelFactory.getInstance().getFinanceModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			FinanceDTO dto = (FinanceDTO) populateDTO(request);

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
						ServletUtility.setErrorMessage("Finance Details already exists", request);
					}

				}
				ServletUtility.setDto(dto, request);

			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setDto(dto, request);
				ServletUtility.setErrorMessage("Finance Details already exists", request);
			}
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.FINANCE_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.FINANCE_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {
		return ORSView.FINANCE_VIEW;
	}

}
