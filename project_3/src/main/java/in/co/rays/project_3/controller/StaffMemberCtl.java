package in.co.rays.project_3.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.dto.StaffMemberDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.model.StaffMemberModelInt;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

@WebServlet(urlPatterns = { "/ctl/StaffMemberCtl" })
public class StaffMemberCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(StaffMemberCtl.class);

	protected void preload(HttpServletRequest request) {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Operations", "Operations");
		map.put("Sales", "Sales");
		map.put("Marketing", "Marketing");
		map.put("Business Development", "Business Development");

		request.setAttribute("map", map);

	}

	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("fullName"))) {
			request.setAttribute("fullName", PropertyReader.getValue("error.require", "Full Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("fullName"))) {
			request.setAttribute("fullName", "please enter correct Name");
			pass = false;

		}
		if (DataValidator.isNull(request.getParameter("division"))) {
			request.setAttribute("division", PropertyReader.getValue("error.require", "division"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("previousEmployer"))) {
			request.setAttribute("previousEmployer", PropertyReader.getValue("error.require", "previousEmployer"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("joiningDate"))) {
			request.setAttribute("joiningDate", PropertyReader.getValue("error.require", "joiningDate"));
			pass = false;
		} else if (!DataValidator.isDate(request.getParameter("joiningDate"))) {
			request.setAttribute("joiningDate", PropertyReader.getValue("error.date", "joiningDate"));
			pass = false;
		}
		System.out.println(request.getParameter("dob"));
		System.out.println("validate end " + pass + "................" + request.getParameter("id"));
		return pass;

	}

	protected BaseDTO populateDTO(HttpServletRequest request) {

		StaffMemberDTO dto = new StaffMemberDTO();

		System.out.println(request.getParameter("dob"));
		System.out.println("Populate end " + "................" + request.getParameter("id"));

		dto.setId(DataUtility.getLong(request.getParameter("id")));

		dto.setJoiningDate(DataUtility.getDate(request.getParameter("joiningDate")));

		dto.setFullName(DataUtility.getString(request.getParameter("fullName")));

		dto.setDivision(DataUtility.getString(request.getParameter("division")));

		dto.setPreviousEmployer(DataUtility.getString(request.getParameter("previousEmployer")));

		populateBean(dto, request);

		System.out.println(request.getParameter("dob") + "......." + dto.getJoiningDate());
		log.debug("Staff Member Ctl Method populatedto Ended");

		return dto;

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		log.debug("Staff Member Ctl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		StaffMemberModelInt model = ModelFactory.getInstance().getStaffMemberModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (id > 0 || op != null) {
			System.out.println("in id > 0  condition");
			StaffMemberDTO dto = null;
			try {
				dto = model.findByPK(id);
				ServletUtility.setDto(dto, request);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String op = DataUtility.getString(request.getParameter("operation"));

		StaffMemberModelInt model = ModelFactory.getInstance().getStaffMemberModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			StaffMemberDTO dto = (StaffMemberDTO) populateDTO(request);

			System.out.println(" in do post method jkjjkjk++++++++" + dto.getId());

			try {
				if (id > 0) {
					model.update(dto);
					ServletUtility.setSuccessMessage("Data is successfully Updated", request);
				} else {

					try {
						model.add(dto);
						ServletUtility.setSuccessMessage("Data is successfully saved", request);
					} catch (ApplicationException e) {
						log.error(e);
						ServletUtility.handleException(e, request, response);
						return;
					} catch (DuplicateRecordException e) {
						ServletUtility.setDto(dto, request);
						ServletUtility.setErrorMessage("Login id already exists", request);
					}

				}
				ServletUtility.setDto(dto, request);

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setDto(dto, request);
				ServletUtility.setErrorMessage("Login id already exists", request);
			}
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.STAFF_MEMBER_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.STAFF_MEMBER_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

		log.debug("UserCtl Method doPostEnded");
	}

	@Override
	protected String getView() {
		return ORSView.STAFF_MEMBER_VIEW;
	}

}
