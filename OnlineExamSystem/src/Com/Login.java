package Com;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.CollegeDao;
import DAO.LoginDAO;
import VO.CollegeVo;
import VO.LoginVO;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String flag = request.getParameter("flag");
		if (flag.equals("verify")) {
			HttpSession session = request.getSession();
			String email = request.getParameter("email");
			String pass = request.getParameter("pwd");
			session.setAttribute("email", email);
			session.setAttribute("pass", pass);
			verifyEmailAndPassword(request, response);
		}
	}

	private void verifyEmailAndPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			HttpSession session = request.getSession(false);
			String email = (String) session.getAttribute("email");
			String pass = (String) session.getAttribute("pass");
			if (EmailValidation.isValid(email)) {
				LoginVO v1 = new LoginVO();
				v1.setEmail(email);
				v1.setPassword(pass);

				LoginDAO d1 = new LoginDAO();
				ArrayList<LoginVO> l1 = d1.verify(v1);
				int logintime = l1.get(0).getId();
				String lastlogintime = l1.get(0).getLastlogin();
				session.setAttribute("time", lastlogintime);

				System.out.println(l1.get(0).getRoll());
				String roll = l1.get(0).getRoll();
				if (roll.equals("Admin")) {
					CollegeVo collegevo = new CollegeVo();
					
					collegevo.setEmail(email);
					collegevo.setPassword(pass);
					LoginDAO dsearch = new LoginDAO();
					ArrayList<CollegeVo> admin = dsearch.collegeVerify(collegevo);
					HttpSession h1 = request.getSession();
					h1.setAttribute("collegename", admin.get(0).getCollegename());
					h1.setAttribute("collegedata", admin);

					Timestamp t1 = new Timestamp(System.currentTimeMillis());
					String lastlogin = t1.toString();
					LoginVO login = new LoginVO();
					login.setLastlogin(lastlogin);
					login.setId(logintime);
					
					LoginDAO logindao = new LoginDAO();
					logindao.logintime(login);

					response.sendRedirect("College_Login.jsp");

//				} else if (roll.equals("Emp")) {
//					System.out.println("");
//					EmployeeVo v3 = new EmployeeVo();
//					v3.setEmail(email);
//					v3.setPassword(pass);
//
//					LoginDAO dsearch = new LoginDAO();
//					ArrayList<EmployeeVo> devloper = dsearch.everify(v3);
//					String roll = devloper.get(0).getRoll();
//					String department = devloper.get(0).getDep_id().getDepartment();
//					if (roll.equals("Emp")) {
//						Timestamp t1 = new Timestamp(System.currentTimeMillis());
//						String s111 = t1.toString();
//						LoginVO login = new LoginVO();
//						login.setLogin(s111);
//						login.setId(logintime);
//
//						LoginDAO logindao = new LoginDAO();
//						logindao.logintime(login);
//						HttpSession h1 = request.getSession();
//						h1.setAttribute("data", devloper);
//						response.sendRedirect("Emp_Login.jsp");
//					} else if (roll.equals("Head") && department.equals("HR") == false) {
//
//						Timestamp t1 = new Timestamp(System.currentTimeMillis());
//						String s111 = t1.toString();
//						LoginVO login = new LoginVO();
//						login.setLogin(s111);
//						login.setId(logintime);
//
//						LoginDAO logindao = new LoginDAO();
//						logindao.logintime(login);
//						HttpSession h1 = request.getSession();
//						h1.setAttribute("data", devloper);
//						response.sendRedirect("Emp_Admin_Login.jsp");
//					} else if (roll.equals("Head") && department.equals("HR")) {
//
//						Timestamp t1 = new Timestamp(System.currentTimeMillis());
//						String s111 = t1.toString();
//						LoginVO login = new LoginVO();
//						login.setLogin(s111);
//						login.setId(logintime);
//
//						LoginDAO logindao = new LoginDAO();
//						logindao.logintime(login);
//						HttpSession h1 = request.getSession();
//						h1.setAttribute("data", devloper);
//						response.sendRedirect("Emp_HR_Login.jsp");
//					}
//				}
				}
			} else {
				session.setAttribute("wrong", "true");
				response.sendRedirect("Com_Login.jsp");
			}

		} catch (Exception e) {
			HttpSession session = request.getSession();
			session.setAttribute("loginResult", "true");
			response.sendRedirect("Com_Login.jsp");
		}

	}
		
}