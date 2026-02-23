package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.BannerDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface BannerModelInt {
	
	public long add(BannerDTO dto) throws ApplicationException, DuplicateRecordException;

	public void delete(BannerDTO dto) throws ApplicationException;

	public void update(BannerDTO dto) throws ApplicationException, DuplicateRecordException;

	public BannerDTO findByPK(long pk) throws ApplicationException;

	public List list() throws ApplicationException;

	public List list(int pageNo, int pageSize) throws ApplicationException;

	public List search(BannerDTO dto, int pageNo, int pageSize) throws ApplicationException;

	public List search(BannerDTO dto) throws ApplicationException;
}
