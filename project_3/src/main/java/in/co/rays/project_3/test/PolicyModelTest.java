package in.co.rays.project_3.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.project_3.dto.PolicyDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.model.PolicyModelHibImpl;
import in.co.rays.project_3.model.PolicyModelInt;

public class PolicyModelTest {

	public static PolicyModelInt model = new PolicyModelHibImpl();

	public static void main(String[] args) throws Exception {
//		 addTest();
//		 updateTest();
		 deleteTest();
//		 findByPKTest();
//		 listTest();
//		 searchTest();
	}

	public static void searchTest() throws ApplicationException, Exception {
		PolicyDTO dto = new PolicyDTO();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//		dto.setId(1L);
//		dto.setPolicyName("Health Secure Plan");
//		dto.setPremiumAmount(12000.50);
//		dto.setStartDate(sdf.parse("2026-02-01"));

		ArrayList<PolicyDTO> a = (ArrayList<PolicyDTO>) model.search(dto, 1, 5);

		for (PolicyDTO udto1 : a) {
			System.out.println(udto1.getId() + "\t" + udto1.getPolicyName() + "\t" + udto1.getPremiumAmount() + "\t"
					+ udto1.getStartDate());
		}
	}

	public static void listTest() throws ApplicationException {
		PolicyDTO dto = new PolicyDTO();
		List list = new ArrayList();
		list = model.list(1, 10);
		if (list.size() < 0) {
			System.out.println("list fail");
		}
		Iterator it = list.iterator();
		while (it.hasNext()) {
			dto = (PolicyDTO) it.next();
			System.out.println(dto.getId());
			System.out.println(dto.getPolicyName());
			System.out.println(dto.getPremiumAmount());
			System.out.println(dto.getStartDate());
			System.out.println(dto.getCreatedBy());
			System.out.println(dto.getModifiedBy());
			System.out.println(dto.getCreatedDatetime());
			System.out.println(dto.getModifiedDatetime());
		}
	}

	public static void findByPKTest() throws ApplicationException {
		PolicyDTO dto = model.findByPK(1L);
		System.out.println(dto.getId() + "\t" + dto.getPolicyName() + "\t" + dto.getPremiumAmount() + "\t"
				+ dto.getStartDate());
	}

	public static void addTest() throws Exception {
		PolicyDTO dto = new PolicyDTO();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		dto.setPolicyName("Health Secure");
		dto.setPremiumAmount(12000.50);
		dto.setStartDate(sdf.parse("2026-02-01"));
		dto.setCreatedBy("admin");
		dto.setModifiedBy("admin");
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		long pk = model.add(dto);
		System.out.println(pk + "data successfully insert");
	}

	public static void deleteTest() throws ApplicationException {
		PolicyDTO dto = new PolicyDTO();
		dto.setId(1L);
		model.delete(dto);
		System.out.println("delete data successfully");
	}

	public static void updateTest() throws Exception {
		PolicyDTO dto = new PolicyDTO();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		dto.setId(1L);
		dto.setPolicyName("Health Secure Plan");
		dto.setPremiumAmount(12000.50);
		dto.setStartDate(sdf.parse("2026-02-01"));
		dto.setCreatedBy("admin");
		dto.setModifiedBy("admin");
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		model.update(dto);
		System.out.println("data update successfully");
	}



}
