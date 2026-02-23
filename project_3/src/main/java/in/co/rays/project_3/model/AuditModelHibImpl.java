package in.co.rays.project_3.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.AuditDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class AuditModelHibImpl implements AuditModelInt {

	@Override
	public long add(AuditDTO dto) throws ApplicationException, DuplicateRecordException {

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
			throw new ApplicationException("Exception in Add Audit Details " + e.getMessage());
		} finally {
			session.close();
		}
		return dto.getId();
	}

	@Override
	public void delete(AuditDTO dto) throws ApplicationException {
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
			throw new ApplicationException("Exception in Delete Audit Details" + e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public void update(AuditDTO dto) throws ApplicationException, DuplicateRecordException {
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
			throw new ApplicationException("Exception in update Audit Details" + e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public AuditDTO findByPK(long pk) throws ApplicationException {
		Session session = null;
		AuditDTO dto = null;
		try {
			session = HibDataSource.getSession();
			dto = (AuditDTO) session.get(AuditDTO.class, pk);

		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in getting Audit Details by pk");
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
			Criteria criteria = session.createCriteria(AuditDTO.class);
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);

			}
			list = criteria.list();

		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in  Audit Details");
		} finally {
			session.close();
		}

		return list;
	}

	@Override
	public List search(AuditDTO dto) throws ApplicationException {
		return search(dto, 0, 0);
	}

	@Override
	public List search(AuditDTO dto, int pageNo, int pageSize) throws ApplicationException {
		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(AuditDTO.class);
			if (dto != null) {
				if (dto.getId() != null) {
					criteria.add(Restrictions.like("id", dto.getId()));
				}

				if (dto.getActionType() != null && dto.getActionType().length() > 0) {
					criteria.add(Restrictions.like("actionType", dto.getActionType() + "%"));
				}

				if (dto.getActionDate() != null && dto.getActionDate().getDate() > 0) {
					criteria.add(Restrictions.eq("actionDate", dto.getActionDate()));
				}

				if (dto.getActionbBy() != null && dto.getActionbBy().length() > 0) {
					criteria.add(Restrictions.like("actionbBy", dto.getActionbBy() + "%"));
				}

				if (dto.getResult() != null && dto.getResult().length() > 0) {
					criteria.add(Restrictions.like("result", dto.getResult() + "%"));
				}
			}
			// if pageSize is greater than 0
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = criteria.list();

		} catch (HibernateException e) {
			throw new ApplicationException("Exception in Audit Details search");
		} finally {
			session.close();
		}

		return list;
	}

}
