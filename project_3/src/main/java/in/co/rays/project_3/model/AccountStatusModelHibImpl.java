package in.co.rays.project_3.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.AccountStatusDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class AccountStatusModelHibImpl implements AccountStatusModelInt{

	@Override
	public long add(AccountStatusDTO dto) throws ApplicationException, DuplicateRecordException {
		Session session = null;
		Transaction tx = null;
		AccountStatusDTO duplicateLogin = findByAccountCode(dto.getAccountCode());
		if (duplicateLogin != null) {
			throw new DuplicateRecordException("Account Code already exist");
		}

		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.save(dto);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();

			}
			HibDataSource.handleException(e);
			throw new ApplicationException("Exception in Add Block" + e.getMessage());
		} finally {
			session.close();
		}
		return dto.getId();
	}

	@Override
	public void delete(AccountStatusDTO dto) throws ApplicationException {
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
			HibDataSource.handleException(e);
			throw new ApplicationException("Exception in Delete Block" + e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public void update(AccountStatusDTO dto) throws ApplicationException, DuplicateRecordException {
		Session session = null;
		Transaction tx = null;

		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			System.out.println("before update");

			session.saveOrUpdate(dto);
			System.out.println("after update");
			tx.commit();

		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			HibDataSource.handleException(e);
			throw new ApplicationException("Exception in update Block" + e.getMessage());
		} finally {
			session.close();
		}

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
			Criteria criteria = session.createCriteria(AccountStatusDTO.class);
			if (pageSize > 0) {
				pageNo = ((pageNo - 1) * pageSize) + 1;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = criteria.list();

		} catch (HibernateException e) {
			HibDataSource.handleException(e);
			throw new ApplicationException("Exception : Exception in Account Status list");
		} finally {
			session.close();
		}

		return list;
	}

	@Override
	public List search(AccountStatusDTO dto) throws ApplicationException {
		return search(dto, 0, 0);

	}

	@Override
	public List search(AccountStatusDTO dto, int pageNo, int pageSize) throws ApplicationException {
		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(AccountStatusDTO.class);

			if (dto.getId() != null && dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getAccountCode() != null && dto.getAccountCode().length() > 0) {
				criteria.add(Restrictions.like("accountCode", dto.getAccountCode() + "%"));
			}
			if (dto.getUserName() != null && dto.getUserName().length() > 0) {
				criteria.add(Restrictions.like("userName", dto.getUserName() + "%"));
			}
			if (dto.getAcccountType() != null && dto.getAcccountType().length() > 0) {
				criteria.add(Restrictions.like("acccountType", dto.getAcccountType() + "%"));
			}
			if (dto.getStatus() != null && dto.getStatus().length() > 0) {
				criteria.add(Restrictions.like("status", dto.getStatus() + "%"));
			}

			if (pageSize > 0) {
				criteria.setFirstResult((pageNo - 1) * pageSize);
				criteria.setMaxResults(pageSize);

			}
			list = criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			HibDataSource.handleException(e);
			throw new ApplicationException("Exception in search Block");
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public AccountStatusDTO findByAccountCode(String accountCode) throws ApplicationException {
		Session session = null;
		AccountStatusDTO dto = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(AccountStatusDTO.class);
			criteria.add(Restrictions.eq("accountCode", accountCode));
			List list = criteria.list();
			if (list.size() == 1) {
				dto = (AccountStatusDTO) list.get(0);
			}
		} catch (HibernateException e) {
			HibDataSource.handleException(e);
			throw new ApplicationException("Exception in getting Feature by Account Code " + e.getMessage());

		} finally {
			session.close();
		}
		return dto;
	}

	@Override
	public AccountStatusDTO findByPK(long pk) throws ApplicationException {
		System.out.println("======" + pk + "----------------------------------");
		Session session = null;
		AccountStatusDTO dto = null;
		try {
			session = HibDataSource.getSession();

			dto = (AccountStatusDTO) session.get(AccountStatusDTO.class, pk);
			System.out.println(dto);
		} catch (HibernateException e) {
			HibDataSource.handleException(e);
			throw new ApplicationException("Exception : Exception in getting Account by pk");
		} finally {
			session.close();
		}
		System.out.println("++++" + dto);
		return dto;
	}

}
