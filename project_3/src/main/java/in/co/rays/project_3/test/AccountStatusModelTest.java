package in.co.rays.project_3.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.project_3.dto.AccountStatusDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.model.AccountStatusModelHibImpl;
import in.co.rays.project_3.model.AccountStatusModelInt;

public class AccountStatusModelTest {

	public static AccountStatusModelInt model = new AccountStatusModelHibImpl();

	public static void main(String[] args) throws Exception {
//		 addTest();
//		 updateTest();
//		 deleteTest();
//		 listTest();
//		 searchTest();
//		 findByBlockCode();
//		 findByPKTest();
	}
	
	public static void findByPKTest() throws ApplicationException {
		AccountStatusDTO dto = model.findByPK(2L);
		System.out.println(dto.getId() + "\t" + dto.getAccountCode() + "\t" + dto.getUserName() + "\t"
				+ dto.getAcccountType() + "\t" + dto.getStatus());
	}

	private static void findByBlockCode() throws ApplicationException {

		AccountStatusDTO dto = model.findByAccountCode("ACC003");
		System.out.println(dto.getId() + "\t" + dto.getAccountCode() + "\t" + dto.getUserName() + "\t"
				+ dto.getAcccountType() + "\t" + dto.getStatus());

	}

	public static void searchTest() throws ApplicationException, Exception {
		AccountStatusDTO dto = new AccountStatusDTO();
//		dto.setId(1L);
//		dto.setAccountCode("ACC001");
//		dto.setUserName("Neha Patel");
//		dto.setAcccountType("Salary");
//		dto.setStatus("Inactive");

		ArrayList<AccountStatusDTO> a = (ArrayList<AccountStatusDTO>) model.search(dto, 1, 5);

		for (AccountStatusDTO udto1 : a) {
			System.out.println(udto1.getId() + "\t" + udto1.getAccountCode() + "\t" + udto1.getUserName() + "\t"
					+ udto1.getAcccountType() + "\t" + udto1.getStatus());
		}
	}

	public static void listTest() throws ApplicationException {
		AccountStatusDTO dto = new AccountStatusDTO();
		List list = new ArrayList();
		list = model.list(0, 5);
		if (list.size() < 0) {
			System.out.println("list fail");
		}
		Iterator it = list.iterator();
		while (it.hasNext()) {
			dto = (AccountStatusDTO) it.next();
			System.out.println(dto.getId());
			System.out.println(dto.getAccountCode());
			System.out.println(dto.getUserName());
			System.out.println(dto.getAcccountType());
			System.out.println(dto.getStatus());
			System.out.println(dto.getCreatedBy());
			System.out.println(dto.getModifiedBy());
			System.out.println(dto.getCreatedDatetime());
			System.out.println(dto.getModifiedDatetime());
		}
	}

	public static void addTest() throws Exception {
		AccountStatusDTO dto = new AccountStatusDTO();

		dto.setAccountCode("ACC001");
		dto.setUserName("Rahul");
		dto.setAcccountType("Savings");
		dto.setStatus("Active");
		dto.setCreatedBy("admin");
		dto.setModifiedBy("admin");
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		long pk = model.add(dto);
		System.out.println(pk + "data successfully insert");
	}

	public static void deleteTest() throws ApplicationException {
		AccountStatusDTO dto = new AccountStatusDTO();
		dto.setId(1L);
		model.delete(dto);
		System.out.println("delete data successfully");
	}

	public static void updateTest() throws Exception {
		AccountStatusDTO dto = new AccountStatusDTO();
		dto.setId(1L);
		dto.setAccountCode("ACC001");
		dto.setUserName("Rahul Sharma");
		dto.setAcccountType("Savings");
		dto.setStatus("ACTIVE");
		dto.setCreatedBy("admin");
		dto.setModifiedBy("admin");
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		model.update(dto);
		System.out.println("data update successfully");
	}





}
