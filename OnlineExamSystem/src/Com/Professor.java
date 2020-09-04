package Com;

import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.commons.io.IOUtils;

import DAO.DepartmentProfessorDao;
import DAO.LoginDAO;
import DAO.ProfessorDao;
import DAO.SemProfessorDao;
import DAO.SubjectProfessorDao;
import VO.CollegeVo;
import VO.DepartmentProfessorVo;
import VO.DepartmentVo;
import VO.LoginVO;
import VO.ProfessorVo;
import VO.SemProfessorVo;
import VO.SemVo;
import VO.SubjectProfessorVo;
import VO.SubjectVo;

/**
 * Servlet implementation class Professor
 */
@WebServlet("/Professor")
@MultipartConfig
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
			String flag2 = request.getParameter("flag2");
			session.setAttribute("flag2", flag2);
			if (flag2.equalsIgnoreCase("professor")) {
				response.sendRedirect("College_Professor_Reg.jsp");
			} else if (flag2.equalsIgnoreCase("hod")) {
				response.sendRedirect("College_HOD_Reg.jsp");
			}
		}
		if (flag.equalsIgnoreCase("editprofile")) {
			int collegeid = Integer.parseInt(request.getParameter("id"));
			session.setAttribute("collegeid", collegeid);
			editProfessorProfile(request, response);
			response.sendRedirect("College_professor_Edit_Profile.jsp");
		}
		if (flag.equalsIgnoreCase("searchcollegeprofessor")) {
			searchCollegeProfessor(request, response);
			response.sendRedirect("College_Professor_List.jsp");
		}
		if (flag.equalsIgnoreCase("viewsubjectprofessor")) {
			searchSubjectProfessor(request, response);
			response.sendRedirect("CollegeSubjectProfessorList.jsp");
		}
		if (flag.equalsIgnoreCase("viewcollegesemesterprofessor")) {
			searchSemProfessor(request, response);
			response.sendRedirect("CollegeSemProfessorList.jsp");
		}
		if (flag.equalsIgnoreCase("viewdepartmentprofessor")) {
			searchDepartmentProfessor(request, response);
			response.sendRedirect("CollegeDepartmentProfessorList.jsp");
		}
		if (flag.equalsIgnoreCase("hodprofessorlist")) {
			searchDepartmentProfessor(request, response);
			response.sendRedirect("HOD_Professor_List.jsp");
		}
		if (flag.equalsIgnoreCase("viewcollegesemestersubjectprofessor")) {
			searchSubjectProfessor(request, response);
			response.sendRedirect("collegeSemesterSubjectProfessor.jsp");
		}
		if (flag.equalsIgnoreCase("viewCollegeProfessorDepartmentList")) {
			searchProfessorDepartment(request, response);
			response.sendRedirect("CollegeProfessorDepartmentList.jsp");
		}
		
		if (flag.equalsIgnoreCase("viewCollegeProfessorDepartmentSemList")) {
			searchProfessorDepartmentSemester(request, response);
			response.sendRedirect("CollegeDepartmentProfessorSemesterList.jsp");
		}
		if (flag.equalsIgnoreCase("viewCollegeSemesterProfessorDepartmentList")) {
			searchProfessorDepartmentSubject(request, response);
			response.sendRedirect("CollegeSemProfessorDepartmentList.jsp");
		}
		if (flag.equalsIgnoreCase("viewCollegeProfessorDetails")) {
			searchProfessorDepartmentSubject(request, response);
			response.sendRedirect("CollegeProfessorDepartmentSubjectList.jsp");
		}
		if (flag.equalsIgnoreCase("viewCollegeSubjectProfessorDetails")) {
			searchProfessorDepartmentSubject(request, response);
			response.sendRedirect("CollegeSubjectProfessordetail.jsp");
		}
		if (flag.equalsIgnoreCase("viewCollegedepartmentProfessorDetails")) {
			searchProfessorDepartmentSubject(request, response);
			response.sendRedirect("CollegeDepartmentProfessorDepartment.jsp");
		}
		if (flag.equalsIgnoreCase("hodprofessordetails")) {
			searchProfessorDepartmentSubject(request, response);
			response.sendRedirect("HOD_Professor_Details.jsp");
		}
		if (flag.equalsIgnoreCase("deleteProfessorSubject")) {
			deleteProfessorSubject(request, response);
			searchProfessorDepartmentSubject(request, response);
			response.sendRedirect("CollegeProfessorDepartmentSubjectList.jsp");
		}
		if (flag.equalsIgnoreCase("viewCollegeProfessorDepartmentSemesterSubjectList")) {
			int semid = Integer.parseInt(request.getParameter("id"));
			session.setAttribute("subjectid", semid);
			searchProfessorSubject(request, response);
			response.sendRedirect("CollegeDepartmentProfessorSemesterSubjectList.jsp");
		}
		if (flag.equalsIgnoreCase("deleteProfessorSubject")) {
			deleteProfessorSubject(request, response);
			searchProfessorSubject(request, response);
			response.sendRedirect("CollegeDepartmentProfessorSemesterSubjectList.jsp");
		}
		if (flag.equalsIgnoreCase("viewCollegeProfessorSemList")) {
			searchProfessorSemester(request, response);
			response.sendRedirect("CollegeProfessorSemList.jsp");
		}
		if (flag.equalsIgnoreCase("viewCollegeProfessorSemesterSubjectList")) {
			int semid = Integer.parseInt(request.getParameter("id"));
			session.setAttribute("viewCollegeProfessorSemesterSubjectList", semid);
			searchProfessorSemesterSubject(request, response);
			response.sendRedirect("CollegeProfessorSemSubjectList.jsp");
		}
		if (flag.equalsIgnoreCase("deleteCollegeProfessorSemestersubject")) {
			deleteProfessorSubject(request, response);
			searchProfessorSemesterSubject(request, response);
			response.sendRedirect("CollegeProfessorSemSubjectList.jsp");
		}

	}

	private void searchProfessorSemesterSubject(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		int semid = (int) session.getAttribute("viewCollegeProfessorSemesterSubjectList");
		
		SemProfessorVo semProfessorVo = new SemProfessorVo();
		semProfessorVo.setId(semid);
		
		SubjectProfessorVo subjectProfessorVo = new SubjectProfessorVo();
		subjectProfessorVo.setSemprofessorid(semProfessorVo);
		
		SubjectProfessorDao subjectprofessordao = new SubjectProfessorDao();
		ArrayList<SubjectProfessorVo> subjectprofessorlist = subjectprofessordao.searchSemesterSubjectProfessor(subjectProfessorVo);

		session.setAttribute("professordepartmentsemestersubjectlist", subjectprofessorlist);
		
	}

	private void searchProfessorSemester(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		int professorid = Integer.parseInt(request.getParameter("id"));

		ProfessorVo professorvo = new ProfessorVo();
		professorvo.setId(professorid);

		SemProfessorVo semProfessorVo = new SemProfessorVo();
		semProfessorVo.setProfessorid(professorvo);

		SemProfessorDao semProfessorDao = new SemProfessorDao();
		ArrayList<SemProfessorVo> semprofessorlist = semProfessorDao.searchCollegeProfessorsemester(semProfessorVo);
		session.setAttribute("collegeProfessorSemester", semprofessorlist);

	}

	private void searchProfessorDepartmentSubject(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		int professorid = Integer.parseInt(request.getParameter("id"));
		System.out.println(professorid);

		ProfessorVo professorvo = new ProfessorVo();
		professorvo.setId(professorid);
		
		SubjectProfessorVo subjectProfessorVo = new SubjectProfessorVo();
		subjectProfessorVo.setProfessorid(professorvo);
		
		SubjectProfessorDao subjectprofessordao = new SubjectProfessorDao();
		ArrayList<SubjectProfessorVo> subjectprofessorlist = subjectprofessordao.getProfessorDepartment(subjectProfessorVo);
		System.out.println("-------------------------------------------------------------");
		System.out.println(subjectprofessorlist.size());
		session.setAttribute("collegeProfessorDepartmentSubjectList", subjectprofessorlist);
		
	}

	private void deleteProfessorSubject(HttpServletRequest request, HttpServletResponse response) {
		int subjectid = Integer.parseInt(request.getParameter("id"));
		
		SubjectProfessorVo subjectProfessorVo = new SubjectProfessorVo();
		subjectProfessorVo.setId(subjectid);
		
		SubjectProfessorDao subjectprofessordao = new SubjectProfessorDao();
		subjectprofessordao.deleteProfessorSubject(subjectProfessorVo);

	}

	private void searchProfessorSubject(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		int semid = (int) session.getAttribute("subjectid");
		
		SemProfessorVo semProfessorVo = new SemProfessorVo();
		semProfessorVo.setId(semid);
		
		SubjectProfessorVo subjectProfessorVo = new SubjectProfessorVo();
		subjectProfessorVo.setSemprofessorid(semProfessorVo);
		
		SubjectProfessorDao subjectprofessordao = new SubjectProfessorDao();
		ArrayList<SubjectProfessorVo> subjectprofessorlist = subjectprofessordao.searchSemesterSubjectProfessor(subjectProfessorVo);
		System.out.println(subjectprofessorlist.size());
		session.setAttribute("professordepartmentsemestersubjectlist", subjectprofessorlist);
	}

	private void searchProfessorDepartmentSemester(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		int departmentid = Integer.parseInt(request.getParameter("id"));
		
		DepartmentProfessorVo departmentProfessorVo = new DepartmentProfessorVo();
		departmentProfessorVo.setId(departmentid);
		
		SemProfessorVo semProfessorVo = new SemProfessorVo();
		semProfessorVo.setDepartmentprofessorid(departmentProfessorVo);
		
		SemProfessorDao semProfessorDao = new SemProfessorDao();
		ArrayList<SemProfessorVo> semprofessorlist = semProfessorDao.searchCollegeDepartmentProfessorsemester(semProfessorVo);
		System.out.println("----------------------------"+semprofessorlist.size());
		session.setAttribute("professordepartmentsemesterlist", semprofessorlist);
		
	}

	private void searchProfessorDepartment(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		int professorid = Integer.parseInt(request.getParameter("id"));

		ProfessorVo professorvo = new ProfessorVo();
		professorvo.setId(professorid);

		DepartmentProfessorVo departmentProfessorVo = new DepartmentProfessorVo();
		departmentProfessorVo.setProfessorid(professorvo);

		DepartmentProfessorDao departmentProfessorDao = new DepartmentProfessorDao();
		ArrayList<DepartmentProfessorVo> departmetprofessorlist = departmentProfessorDao
				.searchCollegeDepartmentProfessor(departmentProfessorVo);

		session.setAttribute("professordepartmentlist", departmetprofessorlist);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String flag = request.getParameter("flag");
		if (flag.equalsIgnoreCase("insert")) {
			String flag2 = (String) session.getAttribute("flag2");
			if (flag2.equalsIgnoreCase("professor")) {
				professorInsert(request, response);
				response.sendRedirect("College_Professor_Reg.jsp");
			} else if (flag2.equalsIgnoreCase("hod")) {
				professorInsert(request, response);
				response.sendRedirect("College_HOD_Reg.jsp");
			}
		}
	}
	

	private void professorInsert(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		try {
			String email = request.getParameter("email");
			if (EmailValidation.isValid(email)) {

				int collegeid = (int) session.getAttribute("collegeid");

				CollegeVo collegevo = new CollegeVo();
				collegevo.setId(collegeid);

				String firstname = request.getParameter("firstName");
				String lastname = request.getParameter("lastName");
				String con_no = request.getParameter("Con_no");
				String Address = request.getParameter("address");
				String gender = request.getParameter("gender");
				String salary = request.getParameter("salary");
				String pass = request.getParameter("pass");
				String roll = request.getParameter("roll");

				String[] departmentlist = request.getParameterValues("departmentlist[]");
				String[] semlist = request.getParameterValues("semesterlist[]");
				String[] subjectlist = request.getParameterValues("subjectlist[]");

				Part filepart = request.getPart("myfile");
				InputStream inputstream = null;
				if (filepart != null) {
					inputstream = filepart.getInputStream();
				}
				byte[] bytes = IOUtils.toByteArray(inputstream);

				Timestamp t1 = new Timestamp(System.currentTimeMillis());
				SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy  HH.mm.ss a");
				String joiningdate = sdf.format(t1);

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
				professorvo.setImage(bytes);

				LoginVO loginvo = new LoginVO();
				loginvo.setProfessorid(professorvo);
				loginvo.setEmail(email);
				loginvo.setPassword(pass);
				loginvo.setLastlogin(joiningdate);
				loginvo.setRoll("Professor");

				DepartmentProfessorVo departmentprofessorvo = new DepartmentProfessorVo();
				SemProfessorVo semprofessorvo = new SemProfessorVo();
				SubjectProfessorVo subjectprofessorvo = new SubjectProfessorVo();

				LoginDAO logdao = new LoginDAO();
				ArrayList<LoginVO> emailchack = logdao.emailverify(loginvo);
				if (emailchack.isEmpty() == true) {

					ProfessorDao professordao = new ProfessorDao();
					String flag2 = (String) session.getAttribute("flag2");
					if (flag2.equalsIgnoreCase("professor")) {
						professordao.professorInsert(professorvo, loginvo);

					} else if (flag2.equalsIgnoreCase("hod")) {
						DepartmentProfessorDao departmentprofessordao = new DepartmentProfessorDao();
						ArrayList<DepartmentProfessorVo> hodchack = departmentprofessordao
								.chackHodProfessor(departmentprofessorvo);
						if (hodchack.isEmpty() == true) {
							System.out.println("---5-5-5-5-5------");
							for (int i = 0; i < departmentlist.length; i++) {
								System.out.println("-------------------------------------");
								int departmentid = Integer.parseInt(departmentlist[i]);
								DepartmentVo departmentvo = new DepartmentVo();
								departmentvo.setId(departmentid);
								professorvo.setDepartmentid(departmentvo);
								professordao.professorInsert(professorvo, loginvo);
							}
							session.setAttribute("professoradd", "true");
						} else if (hodchack.isEmpty() == false) {
							System.out.println("234161321111111111111111111111111111111111111");
							session.setAttribute("hodexist", "true");
						}
					}
					for (int i = 0; i < departmentlist.length; i++) {
						int departmentid = Integer.parseInt(departmentlist[i]);
						DepartmentVo departmentvo = new DepartmentVo();
						departmentvo.setId(departmentid);

						departmentprofessorvo.setRoll(roll);
						departmentprofessorvo.setCollegeid(collegevo);
						departmentprofessorvo.setDepartmentid(departmentvo);
						departmentprofessorvo.setProfessorid(professorvo);

						DepartmentProfessorDao departmentProfessorDao = new DepartmentProfessorDao();
						departmentProfessorDao.insert(departmentprofessorvo);

						int semid = Integer.parseInt(semlist[i]);

						SemVo semvo = new SemVo();
						semvo.setId(semid);

						semprofessorvo.setSemid(semvo);
						semprofessorvo.setCollegeid(collegevo);
						semprofessorvo.setProfessorid(professorvo);
						semprofessorvo.setDepartmentprofessorid(departmentprofessorvo);

						SemProfessorDao semProfessorDao = new SemProfessorDao();
						semProfessorDao.insert(semprofessorvo);

						int subjectid = Integer.parseInt(subjectlist[i]);

						SubjectVo subjectvo = new SubjectVo();
						subjectvo.setId(subjectid);

						subjectprofessorvo.setSubjectid(subjectvo);
						subjectprofessorvo.setCollegeid(collegevo);
						subjectprofessorvo.setProfessorid(professorvo);
						subjectprofessorvo.setDepartmentprofessorid(departmentprofessorvo);
						subjectprofessorvo.setSemprofessorid(semprofessorvo);

						SubjectProfessorDao subjectProfessorDao = new SubjectProfessorDao();
						subjectProfessorDao.insert(subjectprofessorvo);
						session.setAttribute("professoradd", "true");
					}
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

			session.setAttribute("professorlist", professorlist);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void searchSemProfessor(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			int semid = Integer.parseInt(request.getParameter("id"));

			SemVo semVo = new SemVo();
			semVo.setId(semid);

			SemProfessorVo semProfessorVo = new SemProfessorVo();
			semProfessorVo.setSemid(semVo);

			SemProfessorDao semProfessorDao = new SemProfessorDao();
			ArrayList<SemProfessorVo> professorlist = semProfessorDao.searchSemesterProfessor(semProfessorVo);

			session.setAttribute("semprofessorlist", professorlist);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void searchDepartmentProfessor(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			int departmentid = Integer.parseInt(request.getParameter("id"));

			DepartmentVo departmentvo = new DepartmentVo();
			departmentvo.setId(departmentid);

			DepartmentProfessorVo departmentprofessorvo = new DepartmentProfessorVo();
			departmentprofessorvo.setDepartmentid(departmentvo);

			DepartmentProfessorDao departmentprofessordao = new DepartmentProfessorDao();
			ArrayList<DepartmentProfessorVo> professorlist = departmentprofessordao.searchDepartmentProfessor(departmentprofessorvo);
			System.out.println(professorlist.size());

			
			session.setAttribute("professorlist", professorlist);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void searchSubjectProfessor(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			int subject = Integer.parseInt(request.getParameter("id"));

			SubjectVo subjectVo = new SubjectVo();
			subjectVo.setId(subject);

			SubjectProfessorVo subjectProfessorVo = new SubjectProfessorVo();
			subjectProfessorVo.setSubjectid(subjectVo);

			SubjectProfessorDao subjectProfessorDao = new SubjectProfessorDao();
			ArrayList<SubjectProfessorVo> professorlist = subjectProfessorDao.SubjectProfessor(subjectProfessorVo);

			session.setAttribute("subjectprofessorlist", professorlist);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
