package Com;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.LoginDAO;
import VO.CollegeVo;
import VO.LoginVO;
import VO.ProfessorVo;
import VO.StudentVo;

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
		else if (flag.equals("forgetpassword")) {
			forgotepassword(request, response);
		} else if (flag.equals("updatepassword")) {
			updatepassword(request, response);
		}
	}

	private void verifyEmailAndPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			HttpSession session = request.getSession(false);
			String email = (String) session.getAttribute("email");
			String pass = (String) session.getAttribute("pass");
			if (EmailValidation.isValid(email)) {
				
				Timestamp t1 = new Timestamp(System.currentTimeMillis());
				String lastlogin = t1.toString();
				
				LoginVO loginvo = new LoginVO();
				loginvo.setEmail(email);
				loginvo.setPassword(pass);

				LoginDAO logindao = new LoginDAO();
				
				ArrayList<LoginVO> l1 = logindao.verify(loginvo);
				
				int loginid = l1.get(0).getId();
				String lastlogintime = l1.get(0).getLastlogin();
				session.setAttribute("time", lastlogintime);

				System.out.println(l1.get(0).getRoll());
				String roll = l1.get(0).getRoll();
				if (roll.equals("Admin")) {
					CollegeVo collegevo = new CollegeVo();
					
					collegevo.setEmail(email);
					collegevo.setPassword(pass);
					
					
					ArrayList<CollegeVo> admin = logindao.collegeVerify(collegevo);
					byte[] s= admin.get(0).getImage();
					session.setAttribute("collegename", admin.get(0).getCollegename());
					session.setAttribute("collegedata", admin);
					session.setAttribute("collegedata1", s);
					
					loginvo.setLastlogin(lastlogin);
					loginvo.setId(loginid);
					
					logindao.logintime(loginvo);

					response.sendRedirect("College_Login.jsp");

				} else if (roll.equalsIgnoreCase("Professor")) {
					ProfessorVo professorvo = new ProfessorVo();
					professorvo.setEmail(email);
					professorvo.setPassword(pass);
					
					ArrayList<ProfessorVo> professorlist = logindao.professorVerify(professorvo);
					String professorroll = professorlist.get(0).getRoll();
					session.setAttribute("professorname", professorlist.get(0).getFirstName());
					
					if (professorroll.equals("hod")) {
						

						loginvo.setLastlogin(lastlogin);
						loginvo.setId(loginid);
						
						logindao.logintime(loginvo);
						
						session.setAttribute("professordata", professorlist);
						
						response.sendRedirect("Professor_HOD_Login.jsp");
					} else if (professorroll.equalsIgnoreCase("professor")) {

						loginvo.setLastlogin(lastlogin);
						loginvo.setId(loginid);
						
						logindao.logintime(loginvo);
						
						session.setAttribute("professordata", professorlist);
						
						response.sendRedirect("Professor_Login.jsp");
					} 
				}
				else if (roll.equalsIgnoreCase("Student")) {
					StudentVo studentvo = new StudentVo();
					studentvo.setEmail(email);
					studentvo.setPassword(pass);
					ArrayList<StudentVo> studentlist = logindao.studentVerify(studentvo);
					session.setAttribute("studentname", studentlist.get(0).getFirstName());
					
					loginvo.setLastlogin(lastlogin);
					loginvo.setId(loginid);
					
					logindao.logintime(loginvo);
					
					session.setAttribute("studentdata", studentlist);
					response.sendRedirect("Student_Login.jsp");
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
	
	private void updatepassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			HttpSession session = request.getSession(false);
			String email = (String) session.getAttribute("email");
			String userroll = (String) session.getAttribute("roll");
			String password = request.getParameter("password");
			String conformpassword = request.getParameter("cpassword");
			if (EmailValidation.isValid(email)) {
				if (password.equals(conformpassword)) {
					if (userroll.equals("Admin")) {
						CollegeVo collegevo = new CollegeVo();
						collegevo.setEmail(email);
						collegevo.setPassword(conformpassword);

						LoginVO login = new LoginVO();
						login.setEmail(email);
						login.setPassword(conformpassword);

						LoginDAO logindao = new LoginDAO();
						logindao.forgetPasswordCollege(collegevo, login);
					} else if (userroll.equals("professor")) {
						ProfessorVo professorvo = new ProfessorVo();
						professorvo.setEmail(email);
						professorvo.setPassword(conformpassword);

						LoginVO login = new LoginVO();
						login.setEmail(email);
						login.setPassword(conformpassword);

						LoginDAO logindao = new LoginDAO();
						logindao.forgetPasswordProfessor(professorvo, login);
					}
					else if (userroll.equals("student")) {
						StudentVo studentvo = new StudentVo();
						studentvo.setEmail(email);
						studentvo.setPassword(conformpassword);

						LoginVO login = new LoginVO();
						login.setEmail(email);
						login.setPassword(conformpassword);

						LoginDAO logindao = new LoginDAO();
						logindao.forgetPasswordStudent(studentvo, login);
					}
					response.sendRedirect("Com_Login.jsp");
				} else {
					session.setAttribute("error", "true");
					response.sendRedirect("forgetPasswordupdate.jsp");
				}

			} else {
				session.setAttribute("wrong", "true");
				response.sendRedirect("forgetPasswordupdate.jsp");
			}
		} catch (Exception e) {
			HttpSession session = request.getSession();
			session.setAttribute("error", "true");
			response.sendRedirect("forgetPasswordupdate.jsp");
		}

	}

	private void forgotepassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			String email = request.getParameter("email");
			HttpSession session = request.getSession();
			if (EmailValidation.isValid(email)) {
				LoginVO login = new LoginVO();
				login.setEmail(email);

				LoginDAO logindao = new LoginDAO();
				ArrayList<LoginVO> forgetpass = logindao.emailverify(login);
				String roll = forgetpass.get(0).getRoll();
				String forgetemail = forgetpass.get(0).getEmail();

				session.setAttribute("email", forgetemail);
				session.setAttribute("roll", roll);
				response.sendRedirect("forgetPasswordupdate.jsp");

			} else {
				session.setAttribute("wrong", "true");
				response.sendRedirect("forgetPasswordupdate.jsp");
			}
		} catch (Exception e) {
			HttpSession session = request.getSession();
			session.setAttribute("error", "true");
			response.sendRedirect("forgetpassword.jsp");
		}

	}
		
}