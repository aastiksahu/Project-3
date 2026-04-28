package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.FeatureDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface FeatureModelInt {

	public long add(FeatureDTO dto) throws ApplicationException, DuplicateRecordException;

	public void delete(FeatureDTO dto) throws ApplicationException;

	public void update(FeatureDTO dto) throws ApplicationException, DuplicateRecordException;
	
	public FeatureDTO findByPK(long pk) throws ApplicationException;

	public FeatureDTO findByAccessCode(String accessCode) throws ApplicationException;

	public List list() throws ApplicationException;

	public List list(int pageNo, int pageSize) throws ApplicationException;

	public List search(FeatureDTO dto, int pageNo, int pageSize) throws ApplicationException;

	public List search(FeatureDTO dto) throws ApplicationException;

}
