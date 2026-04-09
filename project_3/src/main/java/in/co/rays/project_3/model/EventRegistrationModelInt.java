package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.EventRegistrationDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface EventRegistrationModelInt {
	
	public long add(EventRegistrationDTO dto) throws ApplicationException, DuplicateRecordException;

	public void delete(EventRegistrationDTO dto) throws ApplicationException;

	public void update(EventRegistrationDTO dto) throws ApplicationException, DuplicateRecordException;

	public EventRegistrationDTO findByPK(long pk) throws ApplicationException;
	
	public EventRegistrationDTO findByLogin(String email)throws ApplicationException;

	public List list() throws ApplicationException;

	public List list(int pageNo, int pageSize) throws ApplicationException;

	public List search(EventRegistrationDTO dto, int pageNo, int pageSize) throws ApplicationException;

	public List search(EventRegistrationDTO dto) throws ApplicationException;


}
