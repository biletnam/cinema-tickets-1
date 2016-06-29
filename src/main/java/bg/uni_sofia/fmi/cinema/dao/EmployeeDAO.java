package bg.uni_sofia.fmi.cinema.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import bg.uni_sofia.fmi.cinema.Employee;
import bg.uni_sofia.fmi.cinema.EmployeeBean;

public class EmployeeDAO {

	public void addEmployee(EmployeeBean bean) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		addEmployee(session, bean);
		tx.commit();
		session.close();

	}

	private void addEmployee(Session session, EmployeeBean bean) {
		Employee employee = new Employee();

		employee.setName(bean.getName());
		employee.setAge(bean.getAge());

		session.save(employee);
	}

	public List getEmployees() {
		Session session = SessionUtil.getSession();
		Query query = session.createQuery("from Employee");
		List employees = query.list();
		return employees;
	}

	public int deleteEmployee(int id) {
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		String hql = "delete from Employee where id = :id";
		Query query = session.createQuery(hql);
		query.setInteger("id", id);
		int rowCount = query.executeUpdate();
		System.out.println("Rows affected: " + rowCount);
		tx.commit();
		session.close();
		return rowCount;
	}

}