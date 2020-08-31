package Com;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import DAO.DepartmentProfessorDao;
import DAO.LoginDAO;
import DAO.ProfessorDao;
import DAO.SemProfessorDao;
import DAO.StudentDao;
import DAO.SubjectProfessorDao;
import VO.CollegeVo;
import VO.DepartmentProfessorVo;
import VO.DepartmentVo;
import VO.LoginVO;
import VO.ProfessorVo;
import VO.SemProfessorVo;
import VO.SemVo;
import VO.StudentVo;
import VO.SubjectProfessorVo;
import VO.SubjectVo;

/**
 * Servlet implementation class Professor
 */
@WebServlet("/Student")
public class Student extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String flag = request.getParameter("flag");
		HttpSession session = request.getSession();
		if (flag.equalsIgnoreCase("insert")) {
			int collegeid = Integer.parseInt(request.getParameter("id"));
			session.setAttribute("collegeid", collegeid);
			Department department = new Department();
			department.viewcollegeDepartmentList(request, response);
			response.sendRedirect("College_Student_Reg.jsp");
		}
		if (flag.equalsIgnoreCase("editprofile")) {
			int studentid = (Integer.parseInt(request.getParameter("id")));
			session.setAttribute("studentid", studentid);
			editStudentProfile(request, response);
			Department department = new Department();
			department.viewcollegeDepartmentList(request, response);
			response.sendRedirect("College_Student_Edit.jsp");
		}
		if (flag.equalsIgnoreCase("searchcollegestudent")) {
			searchCollegeStudent(request, response);
			response.sendRedirect("College_Student_List.jsp");
		}
		if (flag.equalsIgnoreCase("viewdepartmentstudent")) {
			searchDepartmentStudent(request, response);
			response.sendRedirect("College_Student_List.jsp");
		}
		
		if (flag.equalsIgnoreCase("searchsubjectprofessor")) {
			searchSubjectProfessor(request, response);
			response.sendRedirect("College_professor_Edit_Profile.jsp");
		}
	}

	private void searchDepartmentStudent(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			int departmentid = Integer.parseInt(request.getParameter("id"));

			DepartmentVo departmentVo = new DepartmentVo();
			departmentVo.setId(departmentid);
			
			StudentVo studentVo = new StudentVo();
			studentVo.setDepartmentid(departmentVo);
			
			StudentDao studentdao = new StudentDao();
			ArrayList<StudentVo> Studentlist= studentdao.searchDepartmentStudent(studentVo);
			
			session.setAttribute("collegeStudentlist", Studentlist);

		} catch (Exception e) {
			e.printStackTrace();
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
			studentInsert(request, response);
			response.sendRedirect("College_Student_Reg.jsp");
		}
		if (flag.equalsIgnoreCase("update")) {
			updateStudentProfile(request, response);
			editStudentProfile(request, response);
			response.sendRedirect("College_Student_Edit.jsp");
		}
	}

	private void studentInsert(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		try {
			System.out.println("0.0.00.0.0..00..0........................................");
			String email = request.getParameter("email");
			if (EmailValidation.isValid(email)) {
				
				int collegeid = (int) session.getAttribute("collegeid");
				CollegeVo collegevo = new CollegeVo();
				collegevo.setId(collegeid);

				int departmentid = Integer.parseInt(request.getParameter("departmentid"));
				int semid = Integer.parseInt(request.getParameter("semid"));

				DepartmentVo departmentVo = new DepartmentVo();
				departmentVo.setId(departmentid);
				
				SemVo semVo = new SemVo();
				semVo.setId(semid);
				
				System.out.println(collegeid+" "+semid+" "+departmentid);
				String firstname = request.getParameter("firstName");
				String lastname = request.getParameter("lastName");
				String con_no = request.getParameter("Con_no");
				String Address = request.getParameter("address");
				String gender = request.getParameter("gender");
				String pass = request.getParameter("pass");
				String roll = request.getParameter("roll");
				
				Timestamp t1 = new Timestamp(System.currentTimeMillis());
				String joiningdate = t1.toString();

				StudentVo studentvo = new StudentVo();
				studentvo.setFirstName(firstname);
				studentvo.setLastName(lastname);
				studentvo.setEmail(email);
				studentvo.setPassword(pass);
				studentvo.setCon_no(con_no);
				studentvo.setAddress(Address);
				studentvo.setGender(gender);
				studentvo.setRoll(roll);
				studentvo.setJoiningdate(joiningdate);
				studentvo.setDepartmentid(departmentVo);
				studentvo.setSemesterid(semVo);
				studentvo.setCollegeid(collegevo);
				
				LoginVO loginvo = new LoginVO();
				loginvo.setStudentid(studentvo);
				loginvo.setEmail(email);
				loginvo.setPassword(pass);
				loginvo.setLastlogin(joiningdate);
				loginvo.setRoll("Student");

				LoginDAO logdao = new LoginDAO();
				ArrayList<LoginVO> emailchack = logdao.emailverify(loginvo);
				if (emailchack.isEmpty() == true) {
					System.out.println(".13132323232...........................................");
					StudentDao studentdao = new StudentDao();
					studentdao.studentInsert(studentvo, loginvo);
					session.setAttribute("studentadd", "true");
				} else {
					session.setAttribute("emailidadd", "true");
				}
			} else {
				session.setAttribute("emailwrong", "true");
			}
		} catch (Exception e) {
			session.setAttribute("cahck", "true");
		}
	}

	protected void editStudentProfile(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int studentid = (int) session.getAttribute("studentid");

		StudentVo studentvo = new StudentVo();
		studentvo.setId(studentid);

		StudentDao studentdao = new StudentDao();

		List studentlist = studentdao.editStudentProfile(studentvo);
		System.out.println(studentlist.size());
		HttpSession h1 = request.getSession();
		h1.setAttribute("studentlist", studentlist);
	}

	protected void updateStudentProfile(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			String email = request.getParameter("email");
			if (EmailValidation.isValid(email)) {
				int collegeid = Integer.parseInt(request.getParameter("collegeid"));
				CollegeVo collegevo = new CollegeVo();
				collegevo.setId(collegeid);
				int semid = (Integer.parseInt(request.getParameter("semid")));
				int departmentid = (Integer.parseInt(request.getParameter("departmentid")));
				String firstname = request.getParameter("firstName");
				String lastname = request.getParameter("lastName");
				String con_no = request.getParameter("Con_no");
				String Address = request.getParameter("address");
				String gender = request.getParameter("gender");
				String joiningdate = request.getParameter("joiningdate");
				String pass = request.getParameter("pass");
				String roll = request.getParameter("roll");
				int studentid = Integer.parseInt(request.getParameter("id"));

				SemVo semvo = new SemVo();
				semvo.setId(semid);

				DepartmentVo departmentvo = new DepartmentVo();
				departmentvo.setId(departmentid);

				StudentVo studentvo = new StudentVo();
				studentvo.setFirstName(firstname);
				studentvo.setLastName(lastname);
				studentvo.setEmail(email);
				studentvo.setPassword(pass);
				studentvo.setCon_no(con_no);
				studentvo.setAddress(Address);
				studentvo.setGender(gender);
				studentvo.setRoll(roll);
				studentvo.setJoiningdate(joiningdate);
				studentvo.setDepartmentid(departmentvo);
				studentvo.setSemesterid(semvo);
				studentvo.setId(studentid);

				LoginVO loginvo = new LoginVO();
				loginvo.setStudentid(studentvo);
				loginvo.setEmail(email);
				loginvo.setPassword(pass);

				LoginDAO logdao = new LoginDAO();

				StudentDao studentdao = new StudentDao();

				ArrayList<LoginVO> emailchack = logdao.emailverify(loginvo);
				if (emailchack.isEmpty() == true) {
					String chack = logdao.studentLoginUpdate(loginvo);
					if (chack.equals("add")) {
						studentdao.studentProfileUpdate(studentvo);
						session.setAttribute("loginResult", "true");
					}
				} else if (emailchack.isEmpty() == false) {
					logdao.loginupdate(loginvo);
					studentdao.studentProfileUpdate(studentvo);
					session.setAttribute("loginResult", "true");
				} else {
					session.setAttribute("wrong", "true");
				}
			}
		} catch (Exception e) {
			session.setAttribute("selecterorr", "true");
		}
	}

	private void searchCollegeStudent(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			int collegeid = Integer.parseInt(request.getParameter("id"));

			CollegeVo collegevo = new CollegeVo();
			collegevo.setId(collegeid);

			StudentVo studentVo = new StudentVo();
			studentVo.setCollegeid(collegevo);
			
			StudentDao studentdao = new StudentDao();
			ArrayList<StudentVo> Studentlist= studentdao.searchCollegeStudent(studentVo);
			
			session.setAttribute("collegeStudentlist", Studentlist);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void searchSubjectProfessor(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			int collegeid = Integer.parseInt(request.getParameter("collegeid"));
			int subjectid = Integer.parseInt(request.getParameter("subjectid"));

			CollegeVo collegevo = new CollegeVo();
			collegevo.setId(collegeid);

			SubjectVo subjectvo = new SubjectVo();
			subjectvo.setId(subjectid);

			ProfessorVo professorvo = new ProfessorVo();
			professorvo.setCollegeid(collegevo);

			ProfessorDao professordao = new ProfessorDao();
			ArrayList<ProfessorVo> professorlist = professordao.searchCollegeSubjectProfessor(professorvo);
			System.out.println(professorlist.size());
			session.setAttribute("collegeDepartmentProfessorlist", professorlist);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
