package in.co.rays.project_3.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.project_3.dto.SettingsDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.model.SettingsModelHibImpl;
import in.co.rays.project_3.model.SettingsModelInt;

public class SettingsModelTest {

	public static SettingsModelInt model = new SettingsModelHibImpl();

	public static void main(String[] args) throws Exception {
//		 addTest();
//		 updateTest();
		 deleteTest();
//		 findByPKTest();
//		 listTest();
//		 searchTest();
	}

	public static void searchTest() throws ApplicationException, Exception {
		SettingsDTO dto = new SettingsDTO();
//		dto.setId(1L);
//		dto.setSettingName("EMAIL_HOST");
//		dto.setValue("smtp.gmail.com");
//		dto.setType("STRING");
//		dto.setStatus("ACTIVE");

		ArrayList<SettingsDTO> a = (ArrayList<SettingsDTO>) model.search(dto, 1, 5);

		for (SettingsDTO udto1 : a) {
			System.out.println(udto1.getId() + "\t" + udto1.getSettingName() + "\t" + udto1.getValue() + "\t"
					+ udto1.getType() + "\t" + udto1.getStatus());
		}
	}

	public static void listTest() throws ApplicationException {
		SettingsDTO dto = new SettingsDTO();
		List list = new ArrayList();
		list = model.list(1, 10);
		if (list.size() < 0) {
			System.out.println("list fail");
		}
		Iterator it = list.iterator();
		while (it.hasNext()) {
			dto = (SettingsDTO) it.next();
			System.out.println(dto.getId());
			System.out.println(dto.getSettingName());
			System.out.println(dto.getValue());
			System.out.println(dto.getType());
			System.out.println(dto.getStatus());
			System.out.println(dto.getCreatedBy());
			System.out.println(dto.getModifiedBy());
			System.out.println(dto.getCreatedDatetime());
			System.out.println(dto.getModifiedDatetime());
		}
	}

	public static void findByPKTest() throws ApplicationException {
		SettingsDTO dto = model.findByPK(1L);
		System.out.println(dto.getId() + "\t" + dto.getSettingName() + "\t" + dto.getValue() + "\t"
				+ dto.getType() + "\t" + dto.getStatus());
	}

	public static void addTest() throws Exception {
		SettingsDTO dto = new SettingsDTO();

		dto.setSettingName("EMAIL_HOST");
		dto.setValue("smtp.gmail.com");
		dto.setType("STR");
		dto.setStatus("ACTIVE");
		dto.setCreatedBy("admin");
		dto.setModifiedBy("admin");
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		long pk = model.add(dto);
		System.out.println(pk + "data successfully insert");
	}

	public static void deleteTest() throws ApplicationException {
		SettingsDTO dto = new SettingsDTO();
		dto.setId(1L);
		model.delete(dto);
		System.out.println("delete data successfully");
	}

	public static void updateTest() throws Exception {
		SettingsDTO dto = new SettingsDTO();
		dto.setId(1L);
		dto.setSettingName("EMAIL_HOST");
		dto.setValue("smtp.gmail.com");
		dto.setType("STRING");
		dto.setStatus("ACTIVE");
		dto.setCreatedBy("admin");
		dto.setModifiedBy("admin");
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		model.update(dto);
		System.out.println("data update successfully");
	}



}
