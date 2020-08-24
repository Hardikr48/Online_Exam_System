package Com;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation class College
 */
@WebServlet("/College")
public class College extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public College() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String flag = request.getParameter("flag");
		if (flag.equalsIgnoreCase("editprofile")) {
			editCollegeProfile(request, response);
			response.sendRedirect("College_Edit_Profile.jsp");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String flag = request.getParameter("flag");
		if (flag.equalsIgnoreCase("insert")) {
			collegeInsert(request, response);
			response.sendRedirect("College_Reg.jsp");
		}
		if (flag.equalsIgnoreCase("updateprofile")) {
			updateCollegeProfile(request, response);
			response.sendRedirect("College_Edit_Profile.jsp");
		}
	}

	private void collegeInsert(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			String collegeName = request.getParameter("collegename");
			String collegeAdd = request.getParameter("collegeaddress");
			String email = request.getParameter("email");
			String pass = request.getParameter("pass");

			if (EmailValidation.isValid(email)) {
				CollegeVo collegevo = new CollegeVo();
				collegevo.setCollegename(collegeName);
				collegevo.setAddress(collegeAdd);
				collegevo.setEmail(email);
				collegevo.setPassword(pass);

				Timestamp t1 = new Timestamp(System.currentTimeMillis());
				String time = t1.toString();

				LoginVO loginvo = new LoginVO();
				loginvo.setCollegeid(collegevo);
				loginvo.setEmail(email);
				loginvo.setPassword(pass);
				loginvo.setLastlogin(time);
				loginvo.setRoll("Admin");

				LoginDAO logdao = new LoginDAO();
				ArrayList<LoginVO> emailchack = logdao.emailverify(loginvo);
				if (emailchack.isEmpty() == true) {
					CollegeDao collegedao = new CollegeDao();
					collegedao.collegeInsert(collegevo, loginvo);
					session.setAttribute("collegeadd", "true");
				} else {
					session.setAttribute("emailidadd", "true");
				}
			} else {
				session.setAttribute("collegeemailwrong", "true");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void editCollegeProfile(HttpServletRequest request, HttpServletResponse response) {
		int collegeid = (Integer.parseInt(request.getParameter("id")));

		CollegeVo collegevo = new CollegeVo();
		collegevo.setId(collegeid);
		CollegeDao d1 = new CollegeDao();
		List collegelist = d1.editCollegeProfile(collegevo);
		System.out.println(collegelist.size());
		HttpSession h1 = request.getSession();
		h1.setAttribute("collegedata", collegelist);
	}

	private void updateCollegeProfile(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		String collegename = request.getParameter("collegename");
		String collegeadd = request.getParameter("collegeaddress");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		int collegeid = (Integer.parseInt(request.getParameter("id")));
		if (EmailValidation.isValid(email)) {
			CollegeVo collegevo = new CollegeVo();
			collegevo.setCollegename(collegename);
			collegevo.setAddress(collegeadd);
			collegevo.setEmail(email);
			collegevo.setPassword(password);
			collegevo.setId(collegeid);

			LoginVO loginvo = new LoginVO();
			loginvo.setCollegeid(collegevo);
			loginvo.setEmail(email);
			loginvo.setPassword(password);

			CollegeDao collegedao = new CollegeDao();
			LoginDAO logdao = new LoginDAO();
			ArrayList<LoginVO> emailchack = logdao.emailverify(loginvo);
			if (emailchack.isEmpty() == false) {
				logdao.loginupdate(loginvo);
				collegedao.updateCollegeProfile(collegevo);
				List collegelist = collegedao.editCollegeProfile(collegevo);
				session.setAttribute("loginResult", "true");
				session.setAttribute("collegedata", collegelist);
			} else if (emailchack.isEmpty() == true) {
				String chack = logdao.collegeLoginUpdate(loginvo);
				if (chack.equals("add")) {
					collegedao.updateCollegeProfile(collegevo);
					List collegelist = collegedao.editCollegeProfile(collegevo);
					session.setAttribute("loginResult", "true");
					session.setAttribute("collegedata", collegelist);
				} else {
					List collegelist = collegedao.editCollegeProfile(collegevo);
					session.setAttribute("collegedata", collegelist);
					session.setAttribute("not", "true");
				}
			}
		} else {
			session.setAttribute("wrong", "true");
		}
	}
}
