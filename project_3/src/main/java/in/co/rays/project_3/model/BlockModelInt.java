package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.BlockDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface BlockModelInt {

	public long add(BlockDTO dto) throws ApplicationException, DuplicateRecordException;

	public void delete(BlockDTO dto) throws ApplicationException;

	public void update(BlockDTO dto) throws ApplicationException, DuplicateRecordException;
	
	public BlockDTO findByPK(long pk) throws ApplicationException;

	public BlockDTO findByBlockCode(String accessCode) throws ApplicationException;

	public List list() throws ApplicationException;

	public List list(int pageNo, int pageSize) throws ApplicationException;

	public List search(BlockDTO dto, int pageNo, int pageSize) throws ApplicationException;

	public List search(BlockDTO dto) throws ApplicationException;



}
