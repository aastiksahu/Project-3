package in.co.rays.project_3.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.project_3.dto.EventRegistrationDTO;
import in.co.rays.project_3.dto.FeatureDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.model.FeatureModelHibImpl;
import in.co.rays.project_3.model.FeatureModelInt;

public class FeatureModelTest {

	public static FeatureModelInt model = new FeatureModelHibImpl();

	public static void main(String[] args) throws Exception {
//		 addTest();
//		 updateTest();
//		 deleteTest();
//		 listTest();
//		 searchTest();
//		 findByAccessCode();
		 findByPKTest();
	}
	
	public static void findByPKTest() throws ApplicationException {
		FeatureDTO dto = model.findByPK(2L);
		System.out.println(dto.getId() + "\t" + dto.getAccessCode() + "\t" + dto.getFeatureName() + "\t"
				+ dto.getUserName() + "\t" + dto.getStatus());
	}

	private static void findByAccessCode() throws ApplicationException {

		FeatureDTO dto = model.findByAccessCode("AC003");
		System.out.println(dto.getId() + "\t" + dto.getAccessCode() + "\t" + dto.getFeatureName() + "\t"
				+ dto.getUserName() + "\t" + dto.getStatus());

	}

	public static void searchTest() throws ApplicationException, Exception {
		FeatureDTO dto = new FeatureDTO();
//		dto.setId(1L);
//		dto.setAccessCode("AC001");
//		dto.setFeatureName("Dashboard");
//		dto.setUserName("admin");
//		dto.setStatus("ACTIVE");

		ArrayList<FeatureDTO> a = (ArrayList<FeatureDTO>) model.search(dto, 1, 5);

		for (FeatureDTO udto1 : a) {
			System.out.println(udto1.getId() + "\t" + udto1.getAccessCode() + "\t" + udto1.getFeatureName() + "\t"
					+ udto1.getUserName() + "\t" + udto1.getStatus());
		}
	}

	public static void listTest() throws ApplicationException {
		FeatureDTO dto = new FeatureDTO();
		List list = new ArrayList();
		list = model.list(0, 5);
		if (list.size() < 0) {
			System.out.println("list fail");
		}
		Iterator it = list.iterator();
		while (it.hasNext()) {
			dto = (FeatureDTO) it.next();
			System.out.println(dto.getId());
			System.out.println(dto.getAccessCode());
			System.out.println(dto.getFeatureName());
			System.out.println(dto.getUserName());
			System.out.println(dto.getStatus());
			System.out.println(dto.getCreatedBy());
			System.out.println(dto.getModifiedBy());
			System.out.println(dto.getCreatedDatetime());
			System.out.println(dto.getModifiedDatetime());
		}
	}

	public static void addTest() throws Exception {
		FeatureDTO dto = new FeatureDTO();

		dto.setAccessCode("AC001");
		dto.setFeatureName("Dash");
		dto.setUserName("admin");
		dto.setStatus("ACTIVE");
		dto.setCreatedBy("admin");
		dto.setModifiedBy("admin");
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		long pk = model.add(dto);
		System.out.println(pk + "data successfully insert");
	}

	public static void deleteTest() throws ApplicationException {
		FeatureDTO dto = new FeatureDTO();
		dto.setId(1L);
		model.delete(dto);
		System.out.println("delete data successfully");
	}

	public static void updateTest() throws Exception {
		FeatureDTO dto = new FeatureDTO();
		dto.setId(1L);
		dto.setAccessCode("AC001");
		dto.setFeatureName("Dashboard");
		dto.setUserName("admin");
		dto.setStatus("ACTIVE");
		dto.setCreatedBy("admin");
		dto.setModifiedBy("admin");
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		model.update(dto);
		System.out.println("data update successfully");
	}

}
