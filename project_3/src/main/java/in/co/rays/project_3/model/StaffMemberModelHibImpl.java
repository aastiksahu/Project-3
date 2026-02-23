package in.co.rays.project_3.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.StaffMemberDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class StaffMemberModelHibImpl implements StaffMemberModelInt {

	@Override
	public long add(StaffMemberDTO dto) throws ApplicationException, DuplicateRecordException {

		Session session = HibDataSource.getSession();
		Transaction tx = null;
		try {
			int pk = 0;
			tx = session.beginTransaction();
			session.save(dto);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();

			}
			throw new ApplicationException("Exception in Staff Member Add " + e.getMessage());
		} finally {
			session.close();
		}
		return dto.getId();
	}

	@Override
	public void delete(StaffMemberDTO dto) throws ApplicationException {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.delete(dto);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in Staff Member Delete" + e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public void update(StaffMemberDTO dto) throws ApplicationException, DuplicateRecordException {
		Session session = null;
		Transaction tx = null;

		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.update(dto);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in Staff Member update" + e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public StaffMemberDTO findByPK(long pk) throws ApplicationException {
		Session session = null;
		StaffMemberDTO dto = null;
		try {
			session = HibDataSource.getSession();
			dto = (StaffMemberDTO) session.get(StaffMemberDTO.class, pk);

		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in getting Staff Member by pk");
		} finally {
			session.close();
		}

		return dto;
	}

	@Override
	public List list() throws ApplicationException {
		return list(0, 0);
	}

	@Override
	public List list(int pageNo, int pageSize) throws ApplicationException {
		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(StaffMemberDTO.class);
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);

			}
			list = criteria.list();

		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in  Staff Member");
		} finally {
			session.close();
		}

		return list;
	}

	@Override
	public List search(StaffMemberDTO dto) throws ApplicationException {
		return search(dto, 0, 0);
	}

	@Override
	public List search(StaffMemberDTO dto, int pageNo, int pageSize) throws ApplicationException {
		System.out.println("model page no" + pageNo);
		System.out.println("model page size" + pageSize);
		System.out.println("model dto" + dto);
		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(StaffMemberDTO.class);
			if (dto != null) {
				if (dto.getId() != null) {
					criteria.add(Restrictions.like("id", dto.getId()));
				}
				
				if (dto.getFullName() != null && dto.getFullName().length() > 0) {
					criteria.add(Restrictions.like("fullName", dto.getFullName() + "%"));
				}
				
				if (dto.getJoiningDate() != null && dto.getJoiningDate().getDate() > 0) {
					criteria.add(Restrictions.eq("joiningDate", dto.getJoiningDate()));
				}

				if (dto.getDivision() != null && dto.getDivision().length() > 0) {
					criteria.add(Restrictions.like("division", dto.getDivision() + "%"));
				}
				
				if (dto.getPreviousEmployer()!= null && dto.getPreviousEmployer().length() > 0) {
					criteria.add(Restrictions.like("previousEmployer", dto.getPreviousEmployer() + "%"));
				}
			}
			// if pageSize is greater than 0
			if (pageSize > 0) {
				System.out.println("mode" +pageNo);
				System.out.println("mode" +pageSize);
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
				System.out.println("iiiii" +pageNo);
				System.out.println("lllll" +pageSize);
			}
			list = criteria.list();
			System.out.println("model list size" + list.size());
			
		} catch (HibernateException e) {
			throw new ApplicationException("Exception in Staff Member search");
		} finally {
			session.close();
		}

		return list;
	}

}
