package in.co.rays.project_3.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.project_3.dto.StaffMemberDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.model.StaffMemberModelHibImpl;
import in.co.rays.project_3.model.StaffMemberModelInt;

public class StaffMemberModelTest {

	public static StaffMemberModelInt model = new StaffMemberModelHibImpl();

	public static void main(String[] args) throws Exception {
//		 addTest();
//		 updateTest();
//		 deleteTest();
//		 findByPKTest();
//		 listTest();
		 searchTest();
	}

	public static void searchTest() throws ApplicationException {
		// TODO Auto-generated method stub
		StaffMemberDTO dto = new StaffMemberDTO();
		// dto.setId(1L);
		// dto.setFirstName("Mayankshi");
		// dto.setLastName("agrawal");
		// dto.setLogin("login");
		// dto.setPassword("123");
		// dto.setMobileNO("989");
		// dto.setRoleId(1);
		// dto.setUnSuccessfullLogin(1);

		// dto.setGender("male");

		ArrayList<StaffMemberDTO> a = (ArrayList<StaffMemberDTO>) model.search(dto, 2, 5);

		for (StaffMemberDTO udto1 : a) {
			System.out.println(udto1.getId() + "\t" + udto1.getFullName() + "\t" + udto1.getJoiningDate() + "\t"
					+ udto1.getDivision() + "\t" + udto1.getPreviousEmployer());
		}
	}

	public static void listTest() throws ApplicationException {
		StaffMemberDTO dto = new StaffMemberDTO();
		List list = new ArrayList();
		list = model.list(1, 10);
		if (list.size() < 0) {
			System.out.println("list fail");
		}
		Iterator it = list.iterator();
		while (it.hasNext()) {
			dto = (StaffMemberDTO) it.next();
			System.out.println(dto.getId());
			System.out.println(dto.getFullName());
			System.out.println(dto.getJoiningDate());
			System.out.println(dto.getDivision());
			System.out.println(dto.getPreviousEmployer());
			System.out.println(dto.getCreatedBy());
			System.out.println(dto.getModifiedBy());
			System.out.println(dto.getCreatedDatetime());
			System.out.println(dto.getModifiedDatetime());
		}
	}

	public static void findByPKTest() throws ApplicationException {
		StaffMemberDTO dto = model.findByPK(1L);
		System.out.println(dto.getId() + "\t" + dto.getFullName() + "\t" + dto.getJoiningDate() + "\t"
				+ dto.getDivision() + "\t" + dto.getPreviousEmployer());
	}

	public static void addTest() throws Exception {
		StaffMemberDTO dto = new StaffMemberDTO();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		dto.setFullName("Yash Kanoongo");
		dto.setJoiningDate(sdf.parse("13-05-1999"));
		dto.setDivision("Accounts");
		dto.setPreviousEmployer("27 Year");
		dto.setCreatedBy("admin");
		dto.setModifiedBy("admin");
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		long pk = model.add(dto);
		System.out.println(pk + "data successfully insert");
	}

	public static void deleteTest() throws ApplicationException {
		StaffMemberDTO dto = new StaffMemberDTO();
		dto.setId(1L);
		model.delete(dto);
		System.out.println("delete data successfully");
	}

	public static void updateTest() throws Exception {
		StaffMemberDTO dto = new StaffMemberDTO();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		dto.setId(1L);
		dto.setFullName("rahul Kanoongo");
		dto.setJoiningDate(sdf.parse("13-05-1999"));
		dto.setDivision("Accounts");
		dto.setPreviousEmployer("27 Year");
		dto.setCreatedBy("admin");
		dto.setModifiedBy("admin");
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		model.update(dto);
		System.out.println("data update successfully");
	}

}
