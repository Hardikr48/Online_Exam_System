package Com;

import java.io.File;
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
import DAO.DepartmentDao;
import DAO.LoginDAO;
import DAO.ProfessorDao;
import DAO.SubjectDao;
import VO.CollegeVo;
import VO.DepartmentVo;
import VO.LoginVO;
import VO.ProfessorVo;
import VO.SemVo;
import VO.SubjectVo;

/**
 * Servlet implementation class Professor
 */
@WebServlet("/Professor")
public class Professor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Professor() {
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
			department.serchSem(request, response);
			Subject subject = new Subject();
			subject.searchCollegeSubject(request, response);
			response.sendRedirect("College_Professor_Reg.jsp");
		}
		if (flag.equalsIgnoreCase("editprofile")) {
			int collegeid = Integer.parseInt(request.getParameter("id"));
			session.setAttribute("collegeid", collegeid);
			editProfessorProfile(request, response);
			response.sendRedirect("College_professor_Edit_Profile.jsp");
		}
		if (flag.equalsIgnoreCase("searchcollegeprofessor")) {
			searchCollegeProfessor(request, response);
			response.sendRedirect("College_professor_Edit_Profile.jsp");
		}
		if (flag.equalsIgnoreCase("searchsemprofessor")) {
			searchSemProfessor(request, response);
			response.sendRedirect("College_professor_Edit_Profile.jsp");
		}
		if (flag.equalsIgnoreCase("searchdepartmentprofessor")) {
			searchDepartmentProfessor(request, response);
			response.sendRedirect("College_professor_Edit_Profile.jsp");
		}
		if (flag.equalsIgnoreCase("searchsubjectprofessor")) {
			searchSubjectProfessor(request, response);
			response.sendRedirect("College_professor_Edit_Profile.jsp");
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
			professorInsert(request, response);
			response.sendRedirect("College_Professor_Reg.jsp");
		}
	}

	private void professorInsert(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			String email = request.getParameter("email");
			if (EmailValidation.isValid(email)) {
				
				int collegeid = (int) session.getAttribute("collegeid");
				int semid = (Integer.parseInt(request.getParameter("semid")));
				int departmentid = (Integer.parseInt(request.getParameter("departmentid")));
				int subjectid = (Integer.parseInt(request.getParameter("subjectid")));
				String id[]= new String[10];
					for(int i=0;i<10;i++){
				id[i]=request.getParameter("check"+i);

				}
				
				String firstname = request.getParameter("firstName");
				String lastname = request.getParameter("lastName");
				String con_no = request.getParameter("Con_no");
				String Address = request.getParameter("address");
				String gender = request.getParameter("gender");
				String salary = request.getParameter("salary");
				String pass = request.getParameter("pass");
				String roll = request.getParameter("roll");

				CollegeVo collegevo = new CollegeVo();
				collegevo.setId(collegeid);

				SemVo semvo = new SemVo();
				semvo.setId(semid);

				DepartmentVo departmentvo = new DepartmentVo();
				departmentvo.setId(departmentid);

				SubjectVo subjectvo = new SubjectVo();
				subjectvo.setId(subjectid);

				Timestamp t1 = new Timestamp(System.currentTimeMillis());
				String joiningdate = t1.toString();

				ProfessorVo professorvo = new ProfessorVo();
				professorvo.setFirstName(firstname);
				professorvo.setLastName(lastname);
				professorvo.setEmail(email);
				professorvo.setPassword(pass);
				professorvo.setCon_no(con_no);
				professorvo.setAddress(Address);
				professorvo.setGender(gender);
				professorvo.setSalary(salary);
				professorvo.setRoll(roll);
				professorvo.setJoiningdate(joiningdate);
				professorvo.setCollegeid(collegevo);
				professorvo.setSemid(semvo);
				professorvo.setDepartmentid(departmentvo);
				professorvo.setSubjectid(subjectvo);

				LoginVO loginvo = new LoginVO();
				loginvo.setProfessorid(professorvo);
				loginvo.setEmail(email);
				loginvo.setPassword(pass);
				loginvo.setLastlogin(joiningdate);
				loginvo.setRoll("Professor");

				LoginDAO logdao = new LoginDAO();
				ArrayList<LoginVO> emailchack = logdao.emailverify(loginvo);
				if (emailchack.isEmpty() == true) {
					ProfessorDao professordao = new ProfessorDao();
					professordao.professorInsert(professorvo, loginvo);
					session.setAttribute("professoradd", "true");
				} else {
					session.setAttribute("emailidadd", "true");
				}
			} else {
				session.setAttribute("emailwrong", "true");
			}
		} catch (Exception e) {
			session.setAttribute("selecterorr", "true");
		}
	}

	protected void editProfessorProfile(HttpServletRequest request, HttpServletResponse response) {
		int professorid = (Integer.parseInt(request.getParameter("id")));

		ProfessorVo professorvo = new ProfessorVo();
		professorvo.setId(professorid);

		ProfessorDao d1 = new ProfessorDao();

		List professorlist = d1.editProfessorProfile(professorvo);
		System.out.println(professorlist.size());
		HttpSession h1 = request.getSession();
		h1.setAttribute("professorlist", professorlist);
	}

	protected void updateProfessorProfile(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			String email = request.getParameter("email");
			if (EmailValidation.isValid(email)) {
				int collegeid = (int) session.getAttribute("collegeid");
				int semid = (Integer.parseInt(request.getParameter("semid")));
				int departmentid = (Integer.parseInt(request.getParameter("departmentid")));
				int subjectid = (Integer.parseInt(request.getParameter("subjectid")));
				int professorid = (Integer.parseInt(request.getParameter("professorid")));
				String firstname = request.getParameter("firstName");
				String lastname = request.getParameter("lastName");
				String con_no = request.getParameter("Con_no");
				String Address = request.getParameter("address");
				String gender = request.getParameter("gender");
				String salary = request.getParameter("salary");
				String pass = request.getParameter("pass");
				String roll = request.getParameter("roll");

				CollegeVo collegevo = new CollegeVo();
				collegevo.setId(collegeid);

				SemVo semvo = new SemVo();
				semvo.setId(semid);

				DepartmentVo departmentvo = new DepartmentVo();
				departmentvo.setId(departmentid);

				SubjectVo subjectvo = new SubjectVo();
				subjectvo.setId(subjectid);

				Timestamp t1 = new Timestamp(System.currentTimeMillis());
				String joiningdate = t1.toString();

				ProfessorVo professorvo = new ProfessorVo();
				professorvo.setId(professorid);
				professorvo.setFirstName(firstname);
				professorvo.setLastName(lastname);
				professorvo.setEmail(email);
				professorvo.setPassword(pass);
				professorvo.setCon_no(con_no);
				professorvo.setAddress(Address);
				professorvo.setGender(gender);
				professorvo.setSalary(salary);
				professorvo.setRoll(roll);
				professorvo.setJoiningdate(joiningdate);
				professorvo.setCollegeid(collegevo);
				professorvo.setSemid(semvo);
				professorvo.setDepartmentid(departmentvo);
				professorvo.setSubjectid(subjectvo);

				LoginVO loginvo = new LoginVO();
				loginvo.setProfessorid(professorvo);
				loginvo.setEmail(email);
				loginvo.setPassword(pass);

				LoginDAO logdao = new LoginDAO();

				ProfessorDao professordao = new ProfessorDao();

				ArrayList<LoginVO> emailchack = logdao.emailverify(loginvo);
				if (emailchack.isEmpty() == true) {
					String chack = logdao.profrssorLoginUpdate(loginvo);
					if (chack.equals("add")) {
						if (roll.equalsIgnoreCase("HOD")) {
							ArrayList<ProfessorVo> search = professordao.professorHodSearch(professorvo);
							if (search.isEmpty() == false) {
								professordao.professorProfileUpdate(professorvo);
							} else if (search.isEmpty() == true) {
								professordao.professorProfileUpdate(professorvo);
								session.setAttribute("loginResult", "true");
							} else {
								session.setAttribute("department", "true");
							}
						} else if (roll.equals("Professor")) {
							professordao.professorProfileUpdate(professorvo);
							session.setAttribute("loginResult", "true");
						}
					} else if (chack.equals("add") == false) {
						session.setAttribute("emailrecord", "true");
					}
				} else if (emailchack.isEmpty() == false) {
					logdao.loginupdate(loginvo);
					if (roll.equals("HOD")) {
						ArrayList<ProfessorVo> search = professordao.professorHodSearch(professorvo);
						if (search.isEmpty() == false) {
							professordao.professorProfileUpdate(professorvo);
							session.setAttribute("loginResult", "true");
						} else if (search.isEmpty() == true) {
							professordao.professorProfileUpdate(professorvo);
							session.setAttribute("loginResult", "true");
						} else {
							session.setAttribute("department", "true");
						}
					} else if (roll.equals("Professor")) {
						professordao.professorProfileUpdate(professorvo);
						session.setAttribute("loginResult", "true");
					}
				} else {
					session.setAttribute("wrong", "true");
				}
			}
		} catch (Exception e) {
			session.setAttribute("selecterorr", "true");
		}
	}

	private void searchCollegeProfessor(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			int collegeid = Integer.parseInt(request.getParameter("id"));

			CollegeVo collegevo = new CollegeVo();
			collegevo.setId(collegeid);

			ProfessorVo professorvo = new ProfessorVo();
			professorvo.setCollegeid(collegevo);

			ProfessorDao professordao = new ProfessorDao();
			ArrayList<ProfessorVo> professorlist = professordao.searchCollegeProfessor(professorvo);
			System.out.println(professorlist.size());
			session.setAttribute("collegeProfessorlist", professorlist);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void searchSemProfessor(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			int collegeid = Integer.parseInt(request.getParameter("collegeid"));
			int semid = Integer.parseInt(request.getParameter("semid"));

			CollegeVo collegevo = new CollegeVo();
			collegevo.setId(collegeid);

			SemVo semvo = new SemVo();
			semvo.setId(semid);

			ProfessorVo professorvo = new ProfessorVo();
			professorvo.setCollegeid(collegevo);
			professorvo.setSemid(semvo);

			ProfessorDao professordao = new ProfessorDao();
			ArrayList<ProfessorVo> professorlist = professordao.searchCollegeSemProfessor(professorvo);
			System.out.println(professorlist.size());
			session.setAttribute("collegeSemProfessorlist", professorlist);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void searchDepartmentProfessor(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			int collegeid = Integer.parseInt(request.getParameter("collegeid"));
			int departmentid = Integer.parseInt(request.getParameter("departmentid"));

			CollegeVo collegevo = new CollegeVo();
			collegevo.setId(collegeid);

			DepartmentVo departmentvo = new DepartmentVo();
			departmentvo.setId(departmentid);

			ProfessorVo professorvo = new ProfessorVo();
			professorvo.setCollegeid(collegevo);
			professorvo.setDepartmentid(departmentvo);

			ProfessorDao professordao = new ProfessorDao();
			ArrayList<ProfessorVo> professorlist = professordao.searchCollegeDepartmentProfessor(professorvo);
			System.out.println(professorlist.size());
			session.setAttribute("collegeDepartmentProfessorlist", professorlist);

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
			professorvo.setSubjectid(subjectvo);

			ProfessorDao professordao = new ProfessorDao();
			ArrayList<ProfessorVo> professorlist = professordao.searchCollegeSubjectProfessor(professorvo);
			System.out.println(professorlist.size());
			session.setAttribute("collegeDepartmentProfessorlist", professorlist);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
