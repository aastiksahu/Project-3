package in.co.rays.project_3.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.dto.VisitorDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.model.VisitorModelInt;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

@WebServlet(urlPatterns = { "/ctl/VisitorCtl" })
public class VisitorCtl extends BaseCtl {

	protected void preload(HttpServletRequest request) {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("PENDING", "PENDING");
		map.put("APPROVED", "APPROVED");
		map.put("REJECTED", "REJECTED");

		request.setAttribute("map", map);

	}

	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("visitorPassCode"))) {
			request.setAttribute("visitorPassCode", PropertyReader.getValue("error.require", "Visitor Pass Code"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("visitorName"))) {
			request.setAttribute("visitorName", PropertyReader.getValue("error.require", "Visitor Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("purpose"))) {
			request.setAttribute("purpose", PropertyReader.getValue("error.require", "Purpose"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("visitDate"))) {
			request.setAttribute("visitDate", PropertyReader.getValue("error.require", "Visit Date"));
			pass = false;
		} else if (!DataValidator.isDate(request.getParameter("visitDate"))) {
			request.setAttribute("visitDate", PropertyReader.getValue("error.date", "Visit Date"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("visitStatus"))) {
			request.setAttribute("visitStatus", PropertyReader.getValue("error.require", "Visit Status"));
			pass = false;
		}
		return pass;

	}

	protected BaseDTO populateDTO(HttpServletRequest request) {

		VisitorDTO dto = new VisitorDTO();

		dto.setId(DataUtility.getLong(request.getParameter("id")));

		dto.setVisitorPassCode(DataUtility.getString(request.getParameter("visitorPassCode")));
		
		dto.setVisitorName(DataUtility.getString(request.getParameter("visitorName")));

		dto.setPurpose(DataUtility.getString(request.getParameter("purpose")));
		
		dto.setVisitDate(DataUtility.getDate(request.getParameter("visitDate")));

		dto.setVisitStatus(DataUtility.getString(request.getParameter("visitStatus")));

		populateBean(dto, request);

		return dto;

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String op = DataUtility.getString(request.getParameter("operation"));

		VisitorModelInt model = ModelFactory.getInstance().getVisitorModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (id > 0 || op != null) {
			System.out.println("in id > 0  condition");
			VisitorDTO dto = null;
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

		VisitorModelInt model = ModelFactory.getInstance().getVisitorModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			VisitorDTO dto = (VisitorDTO) populateDTO(request);

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
						ServletUtility.setErrorMessage("Visitor Details already exists", request);
					}

				}
				ServletUtility.setDto(dto, request);

			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setDto(dto, request);
				ServletUtility.setErrorMessage("Visitor Details already exists", request);
			}
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.VISITOR_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.VISITOR_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {
		return ORSView.VISITOR_VIEW;
	}



}
