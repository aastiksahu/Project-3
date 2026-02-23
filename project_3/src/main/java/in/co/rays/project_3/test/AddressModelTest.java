package in.co.rays.project_3.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.project_3.dto.AddressDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.model.AddressModelHibImpl;
import in.co.rays.project_3.model.AddressModelInt;

public class AddressModelTest {

	public static AddressModelInt model = new AddressModelHibImpl();

	public static void main(String[] args) throws Exception {
		 addTest();
//		 updateTest();
//		 deleteTest();
//		 findByPKTest();
//		 listTest();
		 searchTest();
	}

	public static void searchTest() throws ApplicationException {
		AddressDTO dto = new AddressDTO();
//		dto.setAddressID(123L);
//		dto.setPersonName("amit");
//		dto.setCity("Indore");
//		dto.setState("M.P.");
		dto.setPinCode(452001);

		ArrayList<AddressDTO> a = (ArrayList<AddressDTO>) model.search(dto, 1, 5);

		for (AddressDTO udto1 : a) {
			System.out.println(udto1.getId() + "\t" + udto1.getPersonName() + "\t" + udto1.getCity() + "\t"
					+ udto1.getState() + "\t" + udto1.getPinCode());
		}
	}

	public static void listTest() throws ApplicationException {
		AddressDTO dto = new AddressDTO();
		List list = new ArrayList();
		list = model.list(1, 5);
		if (list.size() < 0) {
			System.out.println("list fail");
		}
		Iterator it = list.iterator();
		while (it.hasNext()) {
			dto = (AddressDTO) it.next();
			System.out.println(dto.getId());
			System.out.println(dto.getPersonName());
			System.out.println(dto.getCity());
			System.out.println(dto.getState());
			System.out.println(dto.getPinCode());
			System.out.println(dto.getCreatedBy());
			System.out.println(dto.getModifiedBy());
			System.out.println(dto.getCreatedDatetime());
			System.out.println(dto.getModifiedDatetime());
		}
	}

	public static void findByPKTest() throws ApplicationException {
		AddressDTO dto = model.findByPK(1L);
		System.out.println(dto.getId() + "\t" + dto.getPersonName() + "\t" + dto.getCity() + "\t"
				+ dto.getState() + "\t" + dto.getPinCode());
	}

	public static void addTest() throws Exception {
		AddressDTO dto = new AddressDTO();

		dto.setPersonName("Test");
		dto.setCity("Test");
		dto.setState("test");
		dto.setPinCode(452);
		dto.setCreatedBy("admin");
		dto.setModifiedBy("admin");
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		long pk = model.add(dto);
		System.out.println(pk + "data successfully insert");
	}

	public static void deleteTest() throws ApplicationException {
		AddressDTO dto = new AddressDTO();
		dto.setId(8L);
		model.delete(dto);
		System.out.println("delete data successfully");
	}

	public static void updateTest() throws Exception {
		AddressDTO dto = new AddressDTO();
		dto.setId(1L);
		dto.setPersonName("Amit");
		dto.setCity("Indore");
		dto.setState("M.P.");
		dto.setPinCode(452001);
		dto.setCreatedBy("admin");
		dto.setModifiedBy("admin");
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		model.update(dto);
		System.out.println("data update successfully");
	}

}
