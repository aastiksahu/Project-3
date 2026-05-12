package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.AccountStatusDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface AccountStatusModelInt {

	public long add(AccountStatusDTO dto) throws ApplicationException, DuplicateRecordException;

	public void delete(AccountStatusDTO dto) throws ApplicationException;

	public void update(AccountStatusDTO dto) throws ApplicationException, DuplicateRecordException;

	public AccountStatusDTO findByPK(long pk) throws ApplicationException;

	public AccountStatusDTO findByAccountCode(String accessCode) throws ApplicationException;

	public List list() throws ApplicationException;

	public List list(int pageNo, int pageSize) throws ApplicationException;

	public List search(AccountStatusDTO dto, int pageNo, int pageSize) throws ApplicationException;

	public List search(AccountStatusDTO dto) throws ApplicationException;

}
