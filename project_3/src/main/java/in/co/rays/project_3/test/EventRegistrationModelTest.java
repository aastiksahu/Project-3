package in.co.rays.project_3.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.project_3.dto.EventRegistrationDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.model.EventRegistrationModelHibImpl;
import in.co.rays.project_3.model.EventRegistrationModelInt;

public class EventRegistrationModelTest {

	public static EventRegistrationModelInt model = new EventRegistrationModelHibImpl();

	public static void main(String[] args) throws Exception {
//		 addTest();
//		 updateTest();
//		 deleteTest();
//		 findByPKTest();
//		 listTest();
//		 searchTest();
//		 fingByLoginTest();
	}

	private static void fingByLoginTest() throws ApplicationException {
		
		EventRegistrationDTO dto = model.findByLogin("karan.mehta@gmail.com");
		System.out.println(dto.getId() + "\t" + dto.getParticipantName() + "\t" + dto.getEventName() + "\t"
				+ dto.getEmail() + "\t" + dto.getRegistrationDate());
		
	}

	public static void searchTest() throws ApplicationException, Exception {
		EventRegistrationDTO dto = new EventRegistrationDTO();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//		dto.setId(2L);
//		dto.setParticipantName("Priya Patel");
//		dto.setEventName("Cyber Security Seminar");
//		dto.setEmail("neha.verma@gmail.com");
//		dto.setRegistrationDate(sdf.parse("05-02-2026"));

		ArrayList<EventRegistrationDTO> a = (ArrayList<EventRegistrationDTO>) model.search(dto, 1, 5);

		for (EventRegistrationDTO udto1 : a) {
			System.out.println(udto1.getId() + "\t" + udto1.getParticipantName() + "\t" + udto1.getEventName() + "\t"
					+ udto1.getEmail() + "\t" + udto1.getRegistrationDate());
		}
	}

	public static void listTest() throws ApplicationException {
		EventRegistrationDTO dto = new EventRegistrationDTO();
		List list = new ArrayList();
		list = model.list(0, 5);
		if (list.size() < 0) {
			System.out.println("list fail");
		}
		Iterator it = list.iterator();
		while (it.hasNext()) {
			dto = (EventRegistrationDTO) it.next();
			System.out.println(dto.getId());
			System.out.println(dto.getParticipantName());
			System.out.println(dto.getEventName());
			System.out.println(dto.getEmail());
			System.out.println(dto.getRegistrationDate());
			System.out.println(dto.getCreatedBy());
			System.out.println(dto.getModifiedBy());
			System.out.println(dto.getCreatedDatetime());
			System.out.println(dto.getModifiedDatetime());
		}
	}

	public static void findByPKTest() throws ApplicationException {
		EventRegistrationDTO dto = model.findByPK(2L);
		System.out.println(dto.getId() + "\t" + dto.getParticipantName() + "\t" + dto.getEventName() + "\t"
				+ dto.getEmail() + "\t" + dto.getRegistrationDate());
	}

	public static void addTest() throws Exception {
		EventRegistrationDTO dto = new EventRegistrationDTO();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		dto.setParticipantName("Neha V");
		dto.setEventName("AI Workshop");
		dto.setEmail("neha.verma@gmail.com");
		dto.setRegistrationDate(sdf.parse("15-01-2026"));
		dto.setCreatedBy("admin");
		dto.setModifiedBy("admin");
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		long pk = model.add(dto);
		System.out.println(pk + "data successfully insert");
	}

	public static void deleteTest() throws ApplicationException {
		EventRegistrationDTO dto = new EventRegistrationDTO();
		dto.setId(1L);
		model.delete(dto);
		System.out.println("delete data successfully");
	}

	public static void updateTest() throws Exception {
		EventRegistrationDTO dto = new EventRegistrationDTO();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		dto.setId(1L);
		dto.setParticipantName("Neha Verma");
		dto.setEventName("AI Workshop");
		dto.setEmail("neha.verma@gmail.com");
		dto.setRegistrationDate(sdf.parse("15-01-2026"));
		dto.setCreatedBy("admin");
		dto.setModifiedBy("admin");
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		model.update(dto);
		System.out.println("data update successfully");
	}



}
