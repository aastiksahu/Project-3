package in.co.rays.project_3.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.project_3.dto.BannerDTO;
import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.BannerModelInt;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

@WebServlet(urlPatterns = { "/ctl/BannerCtl" })
public class BannerCtl extends BaseCtl {

	protected void preload(HttpServletRequest request) {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("ACTIVE", "ACTIVE");
		map.put("INACTIVE", "INACTIVE");

		request.setAttribute("map", map);

	}

	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("bannerCode"))) {
			request.setAttribute("bannerCode", PropertyReader.getValue("error.require", "Banner Code"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("bannerTitle"))) {
			request.setAttribute("bannerTitle", PropertyReader.getValue("error.require", "Banner Title"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("imagePath"))) {
			request.setAttribute("imagePath", PropertyReader.getValue("error.require", "Image Path"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("bannerStatus"))) {
			request.setAttribute("bannerStatus", PropertyReader.getValue("error.require", "Banner Status"));
			pass = false;
		}
		
		return pass;

	}

	protected BaseDTO populateDTO(HttpServletRequest request) {

		BannerDTO dto = new BannerDTO();

		dto.setId(DataUtility.getLong(request.getParameter("id")));

		dto.setBannerCode(DataUtility.getString(request.getParameter("bannerCode")));

		dto.setBannerTitle(DataUtility.getString(request.getParameter("bannerTitle")));

		dto.setImagePath(DataUtility.getString(request.getParameter("imagePath")));

		dto.setBannerStatus(DataUtility.getString(request.getParameter("bannerStatus")));

		populateBean(dto, request);

		return dto;

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String op = DataUtility.getString(request.getParameter("operation"));

		BannerModelInt model = ModelFactory.getInstance().getBannerModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (id > 0 || op != null) {
			System.out.println("in id > 0  condition");
			BannerDTO dto = null;
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

		BannerModelInt model = ModelFactory.getInstance().getBannerModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			BannerDTO dto = (BannerDTO) populateDTO(request);

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
						ServletUtility.setErrorMessage("Banner already exists", request);
					}

				}
				ServletUtility.setDto(dto, request);

			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setDto(dto, request);
				ServletUtility.setErrorMessage("Banner already exists", request);
			}
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.BANNER_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.BANNER_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {
		return ORSView.BANNER_VIEW;
	}

}
