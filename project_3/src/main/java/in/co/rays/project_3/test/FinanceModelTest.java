package in.co.rays.project_3.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.project_3.dto.FinanceDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.model.FinanceModelHibImpl;
import in.co.rays.project_3.model.FinanceModelInt;

public class FinanceModelTest {

	public static FinanceModelInt model = new FinanceModelHibImpl();

	public static void main(String[] args) throws Exception {
//		 addTest();
//		 updateTest();
		 deleteTest();
//		 findByPKTest();
//		 listTest();
		 searchTest();
	}

	public static void searchTest() throws ApplicationException, Exception {
		FinanceDTO dto = new FinanceDTO();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//		dto.setId(1L);
//		dto.setLoanAmount("150000");
//		dto.setTenure("24 Months");
//		dto.setStatus("PENDING");
//		dto.setAppliedDate(sdf.parse("2026-02-01"));

		ArrayList<FinanceDTO> a = (ArrayList<FinanceDTO>) model.search(dto, 1, 5);

		for (FinanceDTO udto1 : a) {
			System.out.println(udto1.getId() + "\t" + udto1.getLoanAmount() + "\t" + udto1.getTenure() + "\t"
					+ udto1.getStatus() + "\t" + udto1.getAppliedDate());
		}
	}

	public static void listTest() throws ApplicationException {
		FinanceDTO dto = new FinanceDTO();
		List list = new ArrayList();
		list = model.list(1, 10);
		if (list.size() < 0) {
			System.out.println("list fail");
		}
		Iterator it = list.iterator();
		while (it.hasNext()) {
			dto = (FinanceDTO) it.next();
			System.out.println(dto.getId());
			System.out.println(dto.getLoanAmount());
			System.out.println(dto.getTenure());
			System.out.println(dto.getStatus());
			System.out.println(dto.getAppliedDate());
			System.out.println(dto.getCreatedBy());
			System.out.println(dto.getModifiedBy());
			System.out.println(dto.getCreatedDatetime());
			System.out.println(dto.getModifiedDatetime());
		}
	}

	public static void findByPKTest() throws ApplicationException {
		FinanceDTO dto = model.findByPK(1L);
		System.out.println(dto.getId() + "\t" + dto.getLoanAmount() + "\t" + dto.getTenure() + "\t"
				+ dto.getStatus() + "\t" + dto.getAppliedDate());
	}

	public static void addTest() throws Exception {
		FinanceDTO dto = new FinanceDTO();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		dto.setLoanAmount("150000");
		dto.setTenure("24 Mo");
		dto.setStatus("PENDING");
		dto.setAppliedDate(sdf.parse("2026-02-01"));
		dto.setCreatedBy("admin");
		dto.setModifiedBy("admin");
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		long pk = model.add(dto);
		System.out.println(pk + "data successfully insert");
	}

	public static void deleteTest() throws ApplicationException {
		FinanceDTO dto = new FinanceDTO();
		dto.setId(1L);
		model.delete(dto);
		System.out.println("delete data successfully");
	}

	public static void updateTest() throws Exception {
		FinanceDTO dto = new FinanceDTO();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		dto.setId(1L);
		dto.setLoanAmount("150000");
		dto.setTenure("24 Months");
		dto.setStatus("PENDING");
		dto.setAppliedDate(sdf.parse("2026-02-01"));
		dto.setCreatedBy("admin");
		dto.setModifiedBy("admin");
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		model.update(dto);
		System.out.println("data update successfully");
	}

}
