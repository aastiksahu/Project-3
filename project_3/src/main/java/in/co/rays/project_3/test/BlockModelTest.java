package in.co.rays.project_3.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.project_3.dto.BlockDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.model.BlockModelHibImpl;
import in.co.rays.project_3.model.BlockModelInt;

public class BlockModelTest {

	public static BlockModelInt model = new BlockModelHibImpl();

	public static void main(String[] args) throws Exception {
//		 addTest();
//		 updateTest();
		 deleteTest();
//		 listTest();
//		 searchTest();
//		 findByBlockCode();
//		 findByPKTest();
	}
	
	public static void findByPKTest() throws ApplicationException {
		BlockDTO dto = model.findByPK(2L);
		System.out.println(dto.getId() + "\t" + dto.getBlockCode() + "\t" + dto.getUserName() + "\t"
				+ dto.getReason() + "\t" + dto.getStatus());
	}

	private static void findByBlockCode() throws ApplicationException {

		BlockDTO dto = model.findByBlockCode("BLK003");
		System.out.println(dto.getId() + "\t" + dto.getBlockCode() + "\t" + dto.getUserName() + "\t"
				+ dto.getReason() + "\t" + dto.getStatus());

	}

	public static void searchTest() throws ApplicationException, Exception {
		BlockDTO dto = new BlockDTO();
//		dto.setId(1L);
//		dto.setBlockCode("BLK001");
//		dto.setUserName("rahul123");
//		dto.setReason("Multiple login attempts");
//		dto.setStatus("Inactive");

		ArrayList<BlockDTO> a = (ArrayList<BlockDTO>) model.search(dto, 1, 5);

		for (BlockDTO udto1 : a) {
			System.out.println(udto1.getId() + "\t" + udto1.getBlockCode() + "\t" + udto1.getUserName() + "\t"
					+ udto1.getReason() + "\t" + udto1.getStatus());
		}
	}

	public static void listTest() throws ApplicationException {
		BlockDTO dto = new BlockDTO();
		List list = new ArrayList();
		list = model.list(0, 5);
		if (list.size() < 0) {
			System.out.println("list fail");
		}
		Iterator it = list.iterator();
		while (it.hasNext()) {
			dto = (BlockDTO) it.next();
			System.out.println(dto.getId());
			System.out.println(dto.getBlockCode());
			System.out.println(dto.getUserName());
			System.out.println(dto.getReason());
			System.out.println(dto.getStatus());
			System.out.println(dto.getCreatedBy());
			System.out.println(dto.getModifiedBy());
			System.out.println(dto.getCreatedDatetime());
			System.out.println(dto.getModifiedDatetime());
		}
	}

	public static void addTest() throws Exception {
		BlockDTO dto = new BlockDTO();

		dto.setBlockCode("BLK001");
		dto.setUserName("rahul");
		dto.setReason("Multiple login attempts");
		dto.setStatus("Active");
		dto.setCreatedBy("admin");
		dto.setModifiedBy("admin");
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		long pk = model.add(dto);
		System.out.println(pk + "data successfully insert");
	}

	public static void deleteTest() throws ApplicationException {
		BlockDTO dto = new BlockDTO();
		dto.setId(10L);
		model.delete(dto);
		System.out.println("delete data successfully");
	}

	public static void updateTest() throws Exception {
		BlockDTO dto = new BlockDTO();
		dto.setId(1L);
		dto.setBlockCode("BLK001");
		dto.setUserName("rahul123");
		dto.setReason("Multiple login attempts");
		dto.setStatus("ACTIVE");
		dto.setCreatedBy("admin");
		dto.setModifiedBy("admin");
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		model.update(dto);
		System.out.println("data update successfully");
	}



}
