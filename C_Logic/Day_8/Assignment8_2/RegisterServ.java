

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import mypack.HibernateUtil;
import mypack.Register;

/**
 * Servlet implementation class RegisterServ
 */
public class RegisterServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Session session;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			response.setContentType("text/html");
			
			PrintWriter pw = response.getWriter();
			
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String email = request.getParameter("email");
			String login = request.getParameter("login");
			String password = request.getParameter("password");
			
			Register r = new Register();
			r.setAddress(address);
			r.setEmail(email);
			r.setLogin(login);
			r.setPassword(password);
			r.setName(name);
			
			transaction = session.beginTransaction();
			session.save(r);
			
			transaction.commit();
			pw.println("response added");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
