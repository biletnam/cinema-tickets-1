package bg.uni_sofia.fmi.cinema;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import bg.uni_sofia.fmi.cinema.dao.EmployeeDAO;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/employees")
public class MyResource {

	@GET
	@Produces("application/json")
	public List<Employee> getEmployee() {
		EmployeeDAO dao = new EmployeeDAO();
		List employees = dao.getEmployees();
		return employees;
	}

	@POST
	@Path("/create")
	@Consumes("application/json")
	public Response addEmployee(EmployeeBean emp) {
		emp.setName(emp.getName());
		emp.setAge(emp.getAge());

		EmployeeDAO dao = new EmployeeDAO();
		dao.addEmployee(emp);

		return Response.ok().build();
	}

	@DELETE
	@Path("/delete/{id}")
	@Consumes("application/json")
	public Response deleteEmployee(@PathParam("id") int id) {
		EmployeeDAO dao = new EmployeeDAO();
		int count = dao.deleteEmployee(id);
		if (count == 0) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok().build();
	}
}
