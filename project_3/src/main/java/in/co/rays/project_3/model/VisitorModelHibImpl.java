package in.co.rays.project_3.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.VisitorDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class VisitorModelHibImpl implements VisitorModelInt{

	@Override
	public long add(VisitorDTO dto) throws ApplicationException, DuplicateRecordException {
		Session session = null;
		Transaction tx = null;
		VisitorDTO duplicateVisitorPassCode = fingByVisitorPassCode(dto.getVisitorPassCode());
		if (duplicateVisitorPassCode != null) {
			throw new DuplicateRecordException("Visitor Pass Code already exist");
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
			throw new ApplicationException("Exception in Visitor Add " + e.getMessage());
		} finally {
			session.close();
		}
		return dto.getId();
	}

	@Override
	public void delete(VisitorDTO dto) throws ApplicationException {
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
			throw new ApplicationException("Exception in Visitor Delete" + e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public void update(VisitorDTO dto) throws ApplicationException, DuplicateRecordException {
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
			throw new ApplicationException("Exception in Visitor update" + e.getMessage());
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
			Criteria criteria = session.createCriteria(VisitorDTO.class);
			if (pageSize > 0) {
				pageNo = ((pageNo - 1) * pageSize) + 1;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = criteria.list();

		} catch (HibernateException e) {

			throw new ApplicationException("Exception : Exception in  Visitor list");
		} finally {
			session.close();
		}

		return list;
	}

	@Override
	public List search(VisitorDTO dto) throws ApplicationException {
		return search(dto, 0, 0);

	}

	@Override
	public List search(VisitorDTO dto, int pageNo, int pageSize) throws ApplicationException {
		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(VisitorDTO.class);
			
			if (dto.getId() != null && dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getVisitorPassCode() != null && dto.getVisitorPassCode().length() > 0) {
				criteria.add(Restrictions.like("visitorPassCode", dto.getVisitorPassCode() + "%"));
			}
			if (dto.getVisitorName()!= null && dto.getVisitorName().length() > 0) {
				criteria.add(Restrictions.like("visitorName", dto.getVisitorName() + "%"));
			}
			if (dto.getPurpose() != null && dto.getPurpose().length() > 0) {
				criteria.add(Restrictions.like("purpose", dto.getPurpose() + "%"));
			}
			if (dto.getVisitDate() != null && dto.getVisitDate().getDate() > 0) {
				criteria.add(Restrictions.eq("visitDate", dto.getVisitDate()));
			}
			if (dto.getVisitStatus() != null && dto.getVisitStatus().length() > 0) {
				criteria.add(Restrictions.like("visitStatus", dto.getVisitStatus() + "%"));
			}
			if (pageSize > 0) {
				criteria.setFirstResult((pageNo - 1) * pageSize);
				criteria.setMaxResults(pageSize);

			}
			list = criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in Visitor search");
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public VisitorDTO findByPK(long pk) throws ApplicationException {
		System.out.println("======" + pk + "----------------------------------");
		Session session = null;
		VisitorDTO dto = null;
		try {
			session = HibDataSource.getSession();

			dto = (VisitorDTO) session.get(VisitorDTO.class, pk);
			System.out.println(dto);
		} catch (HibernateException e) {

			throw new ApplicationException("Exception : Exception in getting Visitor by pk");
		} finally {
			session.close();
		}
		System.out.println("++++" + dto);
		return dto;
	}

	@Override
	public VisitorDTO fingByVisitorPassCode(String visitorPassCode) throws ApplicationException {
		Session session = null;
		VisitorDTO dto = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(VisitorDTO.class);
			criteria.add(Restrictions.eq("visitorPassCode", visitorPassCode));
			List list = criteria.list();
			if (list.size() == 1) {
				dto = (VisitorDTO) list.get(0);
			}
		} catch (HibernateException e) {

			throw new ApplicationException("Exception in getting Visitor by Login " + e.getMessage());

		} finally {
			session.close();
		}
		return dto;
	}


}
