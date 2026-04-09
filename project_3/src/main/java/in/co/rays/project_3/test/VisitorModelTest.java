package in.co.rays.project_3.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.project_3.dto.VisitorDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.model.VisitorModelHibImpl;
import in.co.rays.project_3.model.VisitorModelInt;

public class VisitorModelTest {

	public static VisitorModelInt model = new VisitorModelHibImpl();

	public static void main(String[] args) throws Exception {
//		 addTest();
//		 updateTest();
//		 deleteTest();
//		 findByPKTest();
//		 listTest();
		 searchTest();
//		 fingByVisitorPassCodeTest();
	}

	private static void fingByVisitorPassCodeTest() throws ApplicationException {
		
		VisitorDTO dto = model.fingByVisitorPassCode("VP1001");
		System.out.println(dto.getId() + "\t" + dto.getVisitorPassCode() + "\t" + dto.getVisitorName() + "\t"
				+ dto.getPurpose() + "\t" + dto.getVisitDate() + "\t" + dto.getVisitStatus());
		
	}

	public static void searchTest() throws ApplicationException, Exception {
		VisitorDTO dto = new VisitorDTO();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//		dto.setId(1L);
//		dto.setVisitorPassCode("VP1001");
//		dto.setVisitorName("Rahul Sharma");
//		dto.setPurpose("Client Meeting");
//		dto.setVisitDate(sdf.parse("2026-02-01"));
		dto.setVisitStatus("APPROVED");

		ArrayList<VisitorDTO> a = (ArrayList<VisitorDTO>) model.search(dto, 1, 5);

		for (VisitorDTO udto1 : a) {
			System.out.println(udto1.getId() + "\t" + udto1.getVisitorPassCode() + "\t" + udto1.getVisitorName() + "\t"
					+ udto1.getPurpose() + "\t" + udto1.getVisitDate() + "\t" + udto1.getVisitStatus());
		}
	}

	public static void listTest() throws ApplicationException {
		VisitorDTO dto = new VisitorDTO();
		List list = new ArrayList();
		list = model.list(0, 5);
		if (list.size() < 0) {
			System.out.println("list fail");
		}
		Iterator it = list.iterator();
		while (it.hasNext()) {
			dto = (VisitorDTO) it.next();
			System.out.println(dto.getId());
			System.out.println(dto.getVisitorPassCode());
			System.out.println(dto.getVisitorName());
			System.out.println(dto.getPurpose());
			System.out.println(dto.getVisitDate());
			System.out.println(dto.getVisitStatus());
			System.out.println(dto.getCreatedBy());
			System.out.println(dto.getModifiedBy());
			System.out.println(dto.getCreatedDatetime());
			System.out.println(dto.getModifiedDatetime());
		}
	}

	public static void findByPKTest() throws ApplicationException {
		VisitorDTO dto = model.findByPK(1L);
		System.out.println(dto.getId() + "\t" + dto.getVisitorPassCode() + "\t" + dto.getVisitorName() + "\t"
				+ dto.getPurpose() + "\t" + dto.getVisitDate() + "\t" + dto.getVisitStatus());
	}

	public static void addTest() throws Exception {
		VisitorDTO dto = new VisitorDTO();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		dto.setVisitorPassCode("VP1001");
		dto.setVisitorName("Rah");
		dto.setPurpose("Client Meeting");
		dto.setVisitDate(sdf.parse("2026-02-01"));
		dto.setVisitStatus("APPROVED");
		dto.setCreatedBy("admin");
		dto.setModifiedBy("admin");
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		long pk = model.add(dto);
		System.out.println(pk + "data successfully insert");
	}

	public static void deleteTest() throws ApplicationException {
		VisitorDTO dto = new VisitorDTO();
		dto.setId(1L);
		model.delete(dto);
		System.out.println("delete data successfully");
	}

	public static void updateTest() throws Exception {
		VisitorDTO dto = new VisitorDTO();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		dto.setId(1L);
		dto.setVisitorPassCode("VP1001");
		dto.setVisitorName("Rahul Sharma");
		dto.setPurpose("Client Meeting");
		dto.setVisitDate(sdf.parse("2026-02-01"));
		dto.setVisitStatus("APPROVED");
		dto.setCreatedBy("admin");
		dto.setModifiedBy("admin");
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		model.update(dto);
		System.out.println("data update successfully");
	}

}
