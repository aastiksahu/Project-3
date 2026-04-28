package in.co.rays.project_3.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.impl.SessionImpl;

import in.co.rays.project_3.dto.UserDTO;
import in.co.rays.project_3.util.HibDataSource;
import in.co.rays.project_3.util.JDBCDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 * Jasper functionality Controller. Performs operation for Print pdf of
 * MarksheetMeriteList
 *
 * @author Aastik Sahu
 */
@WebServlet(name = "JasperCtl", urlPatterns = { "/ctl/JasperCtl" })
public class JasperCtl extends BaseCtl {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			ResourceBundle rb = ResourceBundle.getBundle("in.co.rays.project_3.bundle.system");

			/*
			 * This is used when u create a "Report" folder in "src/main/webapp" and then
			 * put your jasper report in that folder
			 */
			// String path =
			// request.getServletContext().getRealPath("/Report/proj_03.jrxml");
			// JasperReport jasperReport = JasperCompileManager.compileReport(path);

			/*
			 * This is used when u create a "Report" folder in
			 * "Java Resources - src/main/resources" and then put your jasper report in that
			 * folder
			 */
			// InputStream jrxmlStream =
			// getClass().getClassLoader().getResourceAsStream("Report/proj_03.jrxml");
			// JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlStream);

			/*
			 * This is used when u put the path of your jasper report in "system.properties"
			 * file.
			 */
			/* Compilation of jrxml file */
			// JasperReport jasperReport =
			// JasperCompileManager.compileReport(rb.getString("jasperctl"));

			/* here you can mount the jasper folder to docker using yml file */
			String reportPath = System.getenv("JASPER_FILE");

			// fallback to properties file if ENV not set
			if (reportPath == null || reportPath.isEmpty()) {
				reportPath = rb.getString("jasperctl");
			}

			File file = new File(reportPath);

			System.out.println("Jasper Path = " + file.getAbsolutePath());

			if (!file.exists()) {
			    throw new RuntimeException("Report NOT FOUND at: " + file.getAbsolutePath());
			}

			JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

			HttpSession session = request.getSession(true);

			UserDTO dto = (UserDTO) session.getAttribute("user");

			dto.getFirstName();
			dto.getLastName();

			Map<String, Object> map = new HashMap<String, Object>();

			map.put("ID", 1l);
			java.sql.Connection conn = null;

			String Database = rb.getString("DATABASE");

			if ("Hibernate".equalsIgnoreCase(Database)) {
				conn = ((SessionImpl) HibDataSource.getSession()).connection();
			}

			if ("JDBC".equalsIgnoreCase(Database)) {
				conn = JDBCDataSource.getConnection();
			}

			/* Filling data into the report */
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, conn);

			/* Export Jasper report */
			byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);

			response.setContentType("application/pdf");
			response.getOutputStream().write(pdf);
			response.getOutputStream().flush();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	@Override
	protected String getView() {
		return null;
	}

}
