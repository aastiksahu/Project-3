package in.co.rays.project_3.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.project_3.dto.BannerDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.model.BannerModelHibImpl;
import in.co.rays.project_3.model.BannerModelInt;

public class BannerModelTest {
	
	public static BannerModelInt model = new BannerModelHibImpl();

	public static void main(String[] args) throws Exception {
		
//		 addTest();
//		 updateTest();
		 deleteTest();
//		 findByPKTest();
//		 listTest();
//		 searchTest();
	}

	public static void searchTest() throws ApplicationException, Exception {
		BannerDTO dto = new BannerDTO();
//		dto.setId(1L);		
//		dto.setBannerCode("BNR001");
//		dto.setBannerTitle("New Year Sale");
//		dto.setImagePath("/images/banners/new_year_sale.jpg");
		dto.setBannerStatus("ACTIVE");

		ArrayList<BannerDTO> a = (ArrayList<BannerDTO>) model.search(dto, 1, 5);

		for (BannerDTO udto1 : a) {
			System.out.println(udto1.getId() + "\t" + udto1.getBannerCode() + "\t" + udto1.getBannerTitle() + "\t"
					+ udto1.getImagePath() + "\t" + udto1.getBannerStatus());
		}
	}

	public static void listTest() throws ApplicationException {
		BannerDTO dto = new BannerDTO();
		List list = new ArrayList();
		list = model.list(1, 10);
		if (list.size() < 0) {
			System.out.println("list fail");
		}
		Iterator it = list.iterator();
		while (it.hasNext()) {
			dto = (BannerDTO) it.next();
			System.out.println(dto.getId());
			System.out.println(dto.getBannerCode());
			System.out.println(dto.getBannerTitle());
			System.out.println(dto.getImagePath());
			System.out.println(dto.getBannerStatus());
			System.out.println(dto.getCreatedBy());
			System.out.println(dto.getModifiedBy());
			System.out.println(dto.getCreatedDatetime());
			System.out.println(dto.getModifiedDatetime());
		}
	}

	public static void findByPKTest() throws ApplicationException {
		BannerDTO dto = model.findByPK(1L);
		System.out.println(dto.getId() + "\t" + dto.getBannerCode() + "\t" + dto.getBannerTitle() + "\t"
				+ dto.getImagePath() + "\t" + dto.getBannerStatus());
	}

	public static void addTest() throws Exception {
		BannerDTO dto = new BannerDTO();

		dto.setBannerCode("BN");
		dto.setBannerTitle("New Year Sale");
		dto.setImagePath("/images/banners/new_year_sale.jpg");
		dto.setBannerStatus("ACTIVE");
		dto.setCreatedBy("admin");
		dto.setModifiedBy("admin");
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		long pk = model.add(dto);
		System.out.println(pk + "data successfully insert");
	}

	public static void deleteTest() throws ApplicationException {
		BannerDTO dto = new BannerDTO();
		dto.setId(1L);
		model.delete(dto);
		System.out.println("delete data successfully");
	}

	public static void updateTest() throws Exception {
		BannerDTO dto = new BannerDTO();
		
		dto.setId(1L);
		dto.setBannerCode("BNR001");
		dto.setBannerTitle("New Year Sale");
		dto.setImagePath("/images/banners/new_year_sale.jpg");
		dto.setBannerStatus("ACTIVE");
		dto.setCreatedBy("admin");
		dto.setModifiedBy("admin");
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		model.update(dto);
		System.out.println("data update successfully");
	}

}
