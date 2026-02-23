package in.co.rays.project_3.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.AddressDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.util.HibDataSource;

public class AddressModelHibImpl implements AddressModelInt{

	@Override
	public long add(AddressDTO dto) throws ApplicationException {

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
			throw new ApplicationException("Exception in Add Address" + e.getMessage());
		} finally {
			session.close();
		}
		return dto.getId();
	}

	@Override
	public void delete(AddressDTO dto) throws ApplicationException {
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
			throw new ApplicationException("Exception in Delete Address" + e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public void update(AddressDTO dto) throws ApplicationException {
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
			throw new ApplicationException("Exception in update Address" + e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public AddressDTO findByPK(long pk) throws ApplicationException {
		Session session = null;
		AddressDTO dto = null;
		try {
			session = HibDataSource.getSession();
			dto = (AddressDTO) session.get(AddressDTO.class, pk);

		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in getting Address by pk");
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
			Criteria criteria = session.createCriteria(AddressDTO.class);
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);

			}
			list = criteria.list();

		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in  Address");
		} finally {
			session.close();
		}

		return list;
	}

	@Override
	public List search(AddressDTO dto) throws ApplicationException {
		return search(dto, 0, 0);
	}

	@Override
	public List search(AddressDTO dto, int pageNo, int pageSize) throws ApplicationException {
		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(AddressDTO.class);
			
			if (dto != null) {
				if (dto.getId() != null) {
					criteria.add(Restrictions.like("id", dto.getId()));
				}

				if (dto.getPersonName() != null && dto.getPersonName().length() > 0) {
					criteria.add(Restrictions.like("personName", dto.getPersonName() + "%"));
				}
				
				if (dto.getCity() != null && dto.getCity().length() > 0) {
					criteria.add(Restrictions.like("city", dto.getCity() + "%"));
				}
				
				if (dto.getState() != null && dto.getState().length() > 0) {
					criteria.add(Restrictions.like("state", dto.getState() + "%"));
				}
				
				if (dto.getPinCode() != null) {
					criteria.add(Restrictions.eq("pinCode", dto.getPinCode()));
				}
			}
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = criteria.list();

		} catch (HibernateException e) {
			throw new ApplicationException("Exception in Address search");
		} finally {
			session.close();
		}

		return list;
	}

}
