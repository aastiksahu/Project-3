package in.co.rays.project_3.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.project_3.dto.AuditDTO;
import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.model.AuditModelInt;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

@WebServlet(name = "AuditListCtl", urlPatterns = { "/ctl/AuditListCtl" })
public class AuditListCtl extends BaseCtl {

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

	protected BaseDTO populateDTO(HttpServletRequest request) {

		AuditDTO dto = new AuditDTO();

		dto.setActionDate(DataUtility.getDate(request.getParameter("actionDate")));

		dto.setActionbBy(DataUtility.getString(request.getParameter("actionbBy")));

		dto.setResult(DataUtility.getString(request.getParameter("result")));

		dto.setActionType(DataUtility.getString(request.getParameter("actionType")));

		populateBean(dto, request);

		return dto;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List list;
		List next;

		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
		System.out.println(pageSize);

		AuditDTO dto = new AuditDTO();

		AuditModelInt model = ModelFactory.getInstance().getAuditModel();
		try {
			list = model.search(dto, pageNo, pageSize);

			next = model.search(dto, pageNo + 1, pageSize);

			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No record found ", request);
			}
			if (next == null || next.size() == 0) {
				request.setAttribute("nextListSize", 0);

			} else {
				request.setAttribute("nextListSize", next.size());
			}
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);
		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List list = null;
		List next = null;

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		AuditDTO dto = (AuditDTO) populateDTO(request);

		String op = DataUtility.getString(request.getParameter("operation"));

		String[] ids = request.getParameterValues("ids");

		AuditModelInt model = ModelFactory.getInstance().getAuditModel();
		try {

			if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op) || "Previous".equalsIgnoreCase(op)) {

				if (OP_SEARCH.equalsIgnoreCase(op)) {
					pageNo = 1;
				} else if (OP_NEXT.equalsIgnoreCase(op)) {
					pageNo++;
				} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
					pageNo--;
				}

			} else if (OP_NEW.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.AUDIT_CTL, request, response);
				return;
			} else if (OP_RESET.equalsIgnoreCase(op)) {

				ServletUtility.redirect(ORSView.AUDIT_LIST_CTL, request, response);
				return;
			} else if (OP_DELETE.equalsIgnoreCase(op)) {
				pageNo = 1;
				if (ids != null && ids.length > 0) {
					AuditDTO deletedto = new AuditDTO();
					for (String id : ids) {
						deletedto.setId(DataUtility.getLong(id));
						model.delete(deletedto);
						ServletUtility.setSuccessMessage("Data Successfully Deleted!", request);
					}
				} else {
					ServletUtility.setErrorMessage("Select atleast one record", request);
				}
			}
			if (OP_BACK.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.AUDIT_LIST_CTL, request, response);
				return;
			}
			list = model.search(dto, pageNo, pageSize);

			next = model.search(dto, pageNo + 1, pageSize);

			if (list == null || list.size() == 0) {
				if (!OP_DELETE.equalsIgnoreCase(op)) {
					ServletUtility.setErrorMessage("No record found ", request);
				}
			}
			if (next == null || next.size() == 0) {
				request.setAttribute("nextListSize", 0);

			} else {
				request.setAttribute("nextListSize", next.size());
			}
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);

		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected String getView() {

		return ORSView.AUDIT_LIST_VIEW;
	}

}
