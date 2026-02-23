package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.FinanceDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface FinanceModelInt {

	public long add(FinanceDTO dto) throws ApplicationException, DuplicateRecordException;

	public void delete(FinanceDTO dto) throws ApplicationException;

	public void update(FinanceDTO dto) throws ApplicationException, DuplicateRecordException;

	public FinanceDTO findByPK(long pk) throws ApplicationException;

	public List list() throws ApplicationException;

	public List list(int pageNo, int pageSize) throws ApplicationException;

	public List search(FinanceDTO dto, int pageNo, int pageSize) throws ApplicationException;

	public List search(FinanceDTO dto) throws ApplicationException;

}
