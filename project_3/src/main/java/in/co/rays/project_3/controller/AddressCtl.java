package in.co.rays.project_3.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.project_3.dto.AddressDTO;
import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.model.AddressModelInt;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

@WebServlet(urlPatterns = { "/ctl/AddressCtl" })
public class AddressCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(AddressCtl.class);

	protected void preload(HttpServletRequest request) {

	}

	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("personName"))) {
			request.setAttribute("personName", PropertyReader.getValue("error.require", "Person Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("personName"))) {
			request.setAttribute("personName", "please enter Person Name");
			pass = false;

		}
		if (DataValidator.isNull(request.getParameter("city"))) {
			request.setAttribute("city", PropertyReader.getValue("error.require", "City"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("state"))) {
			request.setAttribute("state", PropertyReader.getValue("error.require", "State"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("pinCode"))) {
			request.setAttribute("pinCode", PropertyReader.getValue("error.require", "Pin Code"));
			pass = false;
		}
		return pass;

	}

	protected BaseDTO populateDTO(HttpServletRequest request) {

		AddressDTO dto = new AddressDTO();

		dto.setId(DataUtility.getLong(request.getParameter("id")));

		dto.setPersonName(DataUtility.getString(request.getParameter("personName")));

		dto.setCity(DataUtility.getString(request.getParameter("city")));

		dto.setState(DataUtility.getString(request.getParameter("state")));

		dto.setPinCode(DataUtility.getInt(request.getParameter("pinCode")));

		populateBean(dto, request);

		log.debug("Staff Member Ctl Method populatedto Ended");

		return dto;

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		log.debug("Address Ctl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		AddressModelInt model = ModelFactory.getInstance().getAddressModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (id > 0 || op != null) {
			AddressDTO dto = null;
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

		AddressModelInt model = ModelFactory.getInstance().getAddressModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			AddressDTO dto = (AddressDTO) populateDTO(request);

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
					} 

				}
				ServletUtility.setDto(dto, request);

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} 
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.ADDRESS_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.ADDRESS_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

		log.debug("AddressCtl Method doPostEnded");
	}

	@Override
	protected String getView() {
		return ORSView.ADDRESS_VIEW;
	}

}
