package in.co.rays.project_3.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.dto.EventRegistrationDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.EventRegistrationModelInt;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

@WebServlet(urlPatterns = { "/ctl/EventRegistrationCtl" })
public class EventRegistrationCtl extends BaseCtl {

	protected void preload(HttpServletRequest request) {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Tech Conference 2026", "Tech Conference 2026");
		map.put("AI Workshop", "AI Workshop");
		map.put("Startup Meetup", "Startup Meetup");
		map.put("Hackathon 2026", "Hackathon 2026");
		map.put("Digital Marketing Workshop", "Digital Marketing Workshop");

		request.setAttribute("map", map);

	}

	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("participantName"))) {
			request.setAttribute("participantName", PropertyReader.getValue("error.require", "Participant Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("eventName"))) {
			request.setAttribute("eventName", PropertyReader.getValue("error.require", "Event Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("email"))) {
			request.setAttribute("email", PropertyReader.getValue("error.require", "Email"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("registrationDate"))) {
			request.setAttribute("registrationDate", PropertyReader.getValue("error.require", "Registration Date"));
			pass = false;
		} else if (!DataValidator.isDate(request.getParameter("registrationDate"))) {
			request.setAttribute("registrationDate", PropertyReader.getValue("error.date", "Registration Date"));
			pass = false;
		}
		
		return pass;

	}

	protected BaseDTO populateDTO(HttpServletRequest request) {

		EventRegistrationDTO dto = new EventRegistrationDTO();

		dto.setId(DataUtility.getLong(request.getParameter("id")));

		dto.setParticipantName(DataUtility.getString(request.getParameter("participantName")));
		
		dto.setEventName(DataUtility.getString(request.getParameter("eventName")));

		dto.setEmail(DataUtility.getString(request.getParameter("email")));
		
		dto.setRegistrationDate(DataUtility.getDate(request.getParameter("registrationDate")));

		populateBean(dto, request);

		return dto;

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String op = DataUtility.getString(request.getParameter("operation"));

		EventRegistrationModelInt model = ModelFactory.getInstance().getEventRegistrationModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (id > 0 || op != null) {
			System.out.println("in id > 0  condition");
			EventRegistrationDTO dto = null;
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

		EventRegistrationModelInt model = ModelFactory.getInstance().getEventRegistrationModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			EventRegistrationDTO dto = (EventRegistrationDTO) populateDTO(request);

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
						ServletUtility.setErrorMessage("Event Registration Details already exists", request);
					}

				}
				ServletUtility.setDto(dto, request);

			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setDto(dto, request);
				ServletUtility.setErrorMessage("Event Registration Details already exists", request);
			}
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.EVENT_REGISTRATION_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.EVENT_REGISTRATION_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {
		return ORSView.EVENT_REGISTRATION_VIEW;
	}





}
