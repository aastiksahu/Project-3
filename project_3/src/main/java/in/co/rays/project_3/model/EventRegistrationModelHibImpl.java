package in.co.rays.project_3.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.EventRegistrationDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class EventRegistrationModelHibImpl implements EventRegistrationModelInt {

	@Override
	public long add(EventRegistrationDTO dto) throws ApplicationException, DuplicateRecordException {
		Session session = null;
		Transaction tx = null;
		EventRegistrationDTO duplicateLogin = findByLogin(dto.getEmail());
		if (duplicateLogin != null) {
			throw new DuplicateRecordException("Login already exist");
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
			throw new ApplicationException("Exception in Event Registration Add " + e.getMessage());
		} finally {
			session.close();
		}
		return dto.getId();
	}

	@Override
	public void delete(EventRegistrationDTO dto) throws ApplicationException {
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
			throw new ApplicationException("Exception in Event Registration Delete" + e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public void update(EventRegistrationDTO dto) throws ApplicationException, DuplicateRecordException {
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
			throw new ApplicationException("Exception in Event Registration update" + e.getMessage());
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
			Criteria criteria = session.createCriteria(EventRegistrationDTO.class);
			if (pageSize > 0) {
				pageNo = ((pageNo - 1) * pageSize) + 1;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = criteria.list();

		} catch (HibernateException e) {
			HibDataSource.handleException(e);
			throw new ApplicationException("Exception : Exception in  Event Registration list");
		} finally {
			session.close();
		}

		return list;
	}

	@Override
	public List search(EventRegistrationDTO dto) throws ApplicationException {
		return search(dto, 0, 0);

	}

	@Override
	public List search(EventRegistrationDTO dto, int pageNo, int pageSize) throws ApplicationException {
		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(EventRegistrationDTO.class);

			if (dto.getId() != null && dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getParticipantName() != null && dto.getParticipantName().length() > 0) {
				criteria.add(Restrictions.like("participantName", dto.getParticipantName() + "%"));
			}
			if (dto.getEventName() != null && dto.getEventName().length() > 0) {
				criteria.add(Restrictions.like("eventName", dto.getEventName() + "%"));
			}
			if (dto.getEmail() != null && dto.getEmail().length() > 0) {
				criteria.add(Restrictions.like("email", dto.getEmail() + "%"));
			}
			if (dto.getRegistrationDate() != null && dto.getRegistrationDate().getDate() > 0) {
				criteria.add(Restrictions.eq("registrationDate", dto.getRegistrationDate()));
			}
			if (pageSize > 0) {
				criteria.setFirstResult((pageNo - 1) * pageSize);
				criteria.setMaxResults(pageSize);

			}
			list = criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			HibDataSource.handleException(e);
			throw new ApplicationException("Exception in Event Registration search");
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public EventRegistrationDTO findByPK(long pk) throws ApplicationException {
		System.out.println("======" + pk + "----------------------------------");
		Session session = null;
		EventRegistrationDTO dto = null;
		try {
			session = HibDataSource.getSession();

			dto = (EventRegistrationDTO) session.get(EventRegistrationDTO.class, pk);
			System.out.println(dto);
		} catch (HibernateException e) {
			HibDataSource.handleException(e);
			throw new ApplicationException("Exception : Exception in getting Event Registration by pk");
		} finally {
			session.close();
		}
		System.out.println("++++" + dto);
		return dto;
	}

	@Override
	public EventRegistrationDTO findByLogin(String email) throws ApplicationException {
		Session session = null;
		EventRegistrationDTO dto = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(EventRegistrationDTO.class);
			criteria.add(Restrictions.eq("email", email));
			List list = criteria.list();
			if (list.size() == 1) {
				dto = (EventRegistrationDTO) list.get(0);
			}
		} catch (HibernateException e) {
			HibDataSource.handleException(e);
			throw new ApplicationException("Exception in getting Event Registration by Login " + e.getMessage());

		} finally {
			session.close();
		}
		return dto;
	}

}
