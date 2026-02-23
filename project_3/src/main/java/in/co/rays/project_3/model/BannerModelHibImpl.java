package in.co.rays.project_3.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.BannerDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class BannerModelHibImpl implements BannerModelInt {

	@Override
	public long add(BannerDTO dto) throws ApplicationException, DuplicateRecordException {

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
			throw new ApplicationException("Exception in Add Banner " + e.getMessage());
		} finally {
			session.close();
		}
		return dto.getId();
	}

	@Override
	public void delete(BannerDTO dto) throws ApplicationException {
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
			throw new ApplicationException("Exception in Delete Banner " + e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public void update(BannerDTO dto) throws ApplicationException, DuplicateRecordException {
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
			throw new ApplicationException("Exception in update Banner " + e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public BannerDTO findByPK(long pk) throws ApplicationException {
		Session session = null;
		BannerDTO dto = null;
		try {
			session = HibDataSource.getSession();
			dto = (BannerDTO) session.get(BannerDTO.class, pk);

		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in getting Banner by pk");
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
			Criteria criteria = session.createCriteria(BannerDTO.class);
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);

			}
			list = criteria.list();

		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in  Banner");
		} finally {
			session.close();
		}

		return list;
	}

	@Override
	public List search(BannerDTO dto) throws ApplicationException {
		return search(dto, 0, 0);
	}

	@Override
	public List search(BannerDTO dto, int pageNo, int pageSize) throws ApplicationException {
		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(BannerDTO.class);
			if (dto != null) {
				if (dto.getId() != null) {
					criteria.add(Restrictions.like("id", dto.getId()));
				}

				if (dto.getBannerCode() != null && dto.getBannerCode().length() > 0) {
					criteria.add(Restrictions.like("bannerCode", dto.getBannerCode() + "%"));
				}

				if (dto.getBannerTitle() != null && dto.getBannerTitle().length() > 0) {
					criteria.add(Restrictions.like("bannerTitle", dto.getBannerTitle() + "%"));
				}

				if (dto.getImagePath() != null && dto.getImagePath().length() > 0) {
					criteria.add(Restrictions.like("imagePath", dto.getImagePath() + "%"));
				}

				if (dto.getBannerStatus() != null && dto.getBannerStatus().length() > 0) {
					criteria.add(Restrictions.like("bannerStatus", dto.getBannerStatus() + "%"));
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
			throw new ApplicationException("Exception in Banner search");
		} finally {
			session.close();
		}

		return list;
	}

}
