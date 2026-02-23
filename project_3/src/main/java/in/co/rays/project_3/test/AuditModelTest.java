package in.co.rays.project_3.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.project_3.dto.AuditDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.model.AuditModelHibImpl;
import in.co.rays.project_3.model.AuditModelInt;

public class AuditModelTest {

	public static AuditModelInt model = new AuditModelHibImpl();

	public static void main(String[] args) throws Exception {
//		 addTest();
//		 updateTest();
//		 deleteTest();
//		 findByPKTest();
//		 listTest();
		 searchTest();
	}

	public static void searchTest() throws ApplicationException, Exception {
		AuditDTO dto = new AuditDTO();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//		dto.setId(1L);
//		dto.setActionType("TRANSACTION_INITIATED");
//		dto.setActionbBy("Swati Gupta");
//		dto.setActionDate(sdf.parse("2026-02-01"));
//		dto.setResult("Transaction of ₹10,000 initiated to Account ID 45892");

		ArrayList<AuditDTO> a = (ArrayList<AuditDTO>) model.search(dto, 1, 5);

		for (AuditDTO udto1 : a) {
			System.out.println(udto1.getId() + "\t" + udto1.getActionType() + "\t" + udto1.getActionbBy() + "\t"
					+ udto1.getActionDate() + "\t" + udto1.getResult());
		}
	}

	public static void listTest() throws ApplicationException {
		AuditDTO dto = new AuditDTO();
		List list = new ArrayList();
		list = model.list(1, 10);
		if (list.size() < 0) {
			System.out.println("list fail");
		}
		Iterator it = list.iterator();
		while (it.hasNext()) {
			dto = (AuditDTO) it.next();
			System.out.println(dto.getId());
			System.out.println(dto.getActionType());
			System.out.println(dto.getActionbBy());
			System.out.println(dto.getActionDate());
			System.out.println(dto.getResult());
			System.out.println(dto.getCreatedBy());
			System.out.println(dto.getModifiedBy());
			System.out.println(dto.getCreatedDatetime());
			System.out.println(dto.getModifiedDatetime());
		}
	}

	public static void findByPKTest() throws ApplicationException {
		AuditDTO dto = model.findByPK(1L);
		System.out.println(dto.getId() + "\t" + dto.getActionType() + "\t" + dto.getActionbBy() + "\t"
				+ dto.getActionDate() + "\t" + dto.getResult());
	}

	public static void addTest() throws Exception {
		AuditDTO dto = new AuditDTO();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		dto.setActionType("TRANSACTION_INITI");
		dto.setActionbBy("Swati Gupta");
		dto.setActionDate(sdf.parse("2026-02-01"));
		dto.setResult("Transaction of ₹10,000 initiated to Account ID 45892");
		dto.setCreatedBy("admin");
		dto.setModifiedBy("admin");
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		long pk = model.add(dto);
		System.out.println(pk + "data successfully insert");
	}

	public static void deleteTest() throws ApplicationException {
		AuditDTO dto = new AuditDTO();
		dto.setId(1L);
		model.delete(dto);
		System.out.println("delete data successfully");
	}

	public static void updateTest() throws Exception {
		AuditDTO dto = new AuditDTO();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		dto.setId(1L);
		dto.setActionType("TRANSACTION_INITIATED");
		dto.setActionbBy("Swati Gupta");
		dto.setActionDate(sdf.parse("2026-02-01"));
		dto.setResult("Transaction of ₹10,000 initiated to Account ID 45892");
		dto.setCreatedBy("admin");
		dto.setModifiedBy("admin");
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		model.update(dto);
		System.out.println("data update successfully");
	}

}
