package in.co.rays.project_3.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.FinanceDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class FinanceModelHibImpl implements FinanceModelInt {

	@Override
	public long add(FinanceDTO dto) throws ApplicationException, DuplicateRecordException {

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
			throw new ApplicationException("Exception in Add Finance Details " + e.getMessage());
		} finally {
			session.close();
		}
		return dto.getId();
	}

	@Override
	public void delete(FinanceDTO dto) throws ApplicationException {
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
			throw new ApplicationException("Exception in Delete Finance Details" + e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public void update(FinanceDTO dto) throws ApplicationException, DuplicateRecordException {
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
			throw new ApplicationException("Exception in update Finance Details" + e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public FinanceDTO findByPK(long pk) throws ApplicationException {
		Session session = null;
		FinanceDTO dto = null;
		try {
			session = HibDataSource.getSession();
			dto = (FinanceDTO) session.get(FinanceDTO.class, pk);

		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in getting Finance Details by pk");
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
			Criteria criteria = session.createCriteria(FinanceDTO.class);
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);

			}
			list = criteria.list();

		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in  Finance Details");
		} finally {
			session.close();
		}

		return list;
	}

	@Override
	public List search(FinanceDTO dto) throws ApplicationException {
		return search(dto, 0, 0);
	}

	@Override
	public List search(FinanceDTO dto, int pageNo, int pageSize) throws ApplicationException {
		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(FinanceDTO.class);
			if (dto != null) {
				if (dto.getId() != null) {
					criteria.add(Restrictions.like("id", dto.getId()));
				}

				if (dto.getLoanAmount() != null && dto.getLoanAmount().length() > 0) {
					criteria.add(Restrictions.like("loanAmount", dto.getLoanAmount() + "%"));
				}

				if (dto.getAppliedDate() != null && dto.getAppliedDate().getDate() > 0) {
					criteria.add(Restrictions.eq("appliedDate", dto.getAppliedDate()));
				}

				if (dto.getTenure() != null && dto.getTenure().length() > 0) {
					criteria.add(Restrictions.like("tenure", dto.getTenure() + "%"));
				}

				if (dto.getStatus() != null && dto.getStatus().length() > 0) {
					criteria.add(Restrictions.like("status", dto.getStatus() + "%"));
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
			throw new ApplicationException("Exception in Finance Details search");
		} finally {
			session.close();
		}

		return list;
	}

}
