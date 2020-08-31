package Com;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
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
			String flag2 = request.getParameter("flag2");
			session.setAttribute("flag2", flag2);
			searchCollegeProfessor(request, response);
			response.sendRedirect("College_Professor_List.jsp");
		}
		if (flag.equalsIgnoreCase("viewsemesterprofessor")) {
			String flag2 = request.getParameter("flag2");
			session.setAttribute("flag2", flag2);
			searchSemProfessor(request, response);
			response.sendRedirect("College_Professor_List.jsp");
		}
		if (flag.equalsIgnoreCase("viewdepartmentprofessor")) {
			String flag2 = request.getParameter("flag2");
			session.setAttribute("flag2", flag2);
			searchDepartmentProfessor(request, response);
			response.sendRedirect("College_Professor_List.jsp");
		}
		if (flag.equalsIgnoreCase("viewsubjectprofessor")) {
			String flag2 = request.getParameter("flag2");
			session.setAttribute("flag2", flag2);
			searchSubjectProfessor(request, response);
			response.sendRedirect("College_Professor_List.jsp");
		}
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
				System.out.println(".00000000000000000000000000..................................."+departmentlist+" "+semlist+" "+subjectlist);
				
				Part filepart = request.getPart("myfile");
				InputStream inputstream = null;
				if (filepart != null) {
					inputstream = filepart.getInputStream();
				}
				byte[] bytes = IOUtils.toByteArray(inputstream);

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
						ArrayList<DepartmentProfessorVo> hodchack = departmentprofessordao.chackHodProfessor(departmentprofessorvo);
						if (hodchack.isEmpty() == true) {
							professordao.professorInsert(professorvo, loginvo);
							session.setAttribute("professoradd", "true");
						} else if (hodchack.isEmpty() == false) {
							session.setAttribute("hodexist", "true");
						}	
					}
						for (int i = 0; i < departmentlist.length; i++) {
							int departmentid = Integer.parseInt(departmentlist[i]);
							System.out.println("--------------------------------------------"+departmentid);
							DepartmentVo departmentvo = new DepartmentVo();
							departmentvo.setId(departmentid);
							
							departmentprofessorvo.setRoll(roll);
							departmentprofessorvo.setCollegeid(collegevo);
							departmentprofessorvo.setDepartmentid(departmentvo);
							departmentprofessorvo.setProfessorid(professorvo);
							
														
							DepartmentProfessorDao departmentProfessorDao = new DepartmentProfessorDao();
							departmentProfessorDao.insert(departmentprofessorvo);
							
						}
						for (int i = 0; i < semlist.length; i++) {
							
							int semid = Integer.parseInt(semlist[i]);
							
							SemVo semvo = new SemVo();
							semvo.setId(semid);

							semprofessorvo.setSemid(semvo);
							semprofessorvo.setCollegeid(collegevo);
							semprofessorvo.setProfessorid(professorvo);
														
							SemProfessorDao semProfessorDao = new SemProfessorDao();
							semProfessorDao.insert(semprofessorvo);

						}
						for (int i = 0; i < subjectlist.length; i++) {
							int subjectid = Integer.parseInt(subjectlist[i]);
							
							SubjectVo subjectvo = new SubjectVo();
							subjectvo.setId(subjectid);

							subjectprofessorvo.setSubjectid(subjectvo);
							subjectprofessorvo.setCollegeid(collegevo);
							subjectprofessorvo.setProfessorid(professorvo);
							
							SubjectProfessorDao  subjectProfessorDao = new SubjectProfessorDao();
							subjectProfessorDao.insert(subjectprofessorvo);
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

			int professorid = professorlist.get(0).getId();
			professorvo.setId(professorid);
			
			DepartmentProfessorVo departmentProfessorVo = new DepartmentProfessorVo();
			departmentProfessorVo.setProfessorid(professorvo);

			DepartmentProfessorDao departmentProfessorDao = new DepartmentProfessorDao();
			ArrayList<DepartmentProfessorVo> departmetprofessorlist = departmentProfessorDao.searchCollegeDepartmentProfessor(departmentProfessorVo);

			SemProfessorVo semprofessorvo = new SemProfessorVo();
			semprofessorvo.setProfessorid(professorvo);

			SemProfessorDao semprofessordao = new SemProfessorDao();
			ArrayList<SemProfessorVo> semprofessorlist = semprofessordao.searchCollegeDepartmentProfessor(semprofessorvo);

			SubjectProfessorVo subjectprofessorvo = new SubjectProfessorVo();
			subjectprofessorvo.setProfessorid(professorvo);

			SubjectProfessorDao subjectprofessordao = new SubjectProfessorDao();
			ArrayList<SubjectProfessorVo> subjectprofessorlist = subjectprofessordao.searchSubjectProfessor(subjectprofessorvo);
			session.setAttribute("departmentprofessorlist", departmetprofessorlist);
			session.setAttribute("semprofessorlist", semprofessorlist);
			session.setAttribute("colprofessorlist", professorlist);
			session.setAttribute("subjectrofessorlist", subjectprofessorlist);

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
			System.out.println(professorlist.size());

			int professorid = professorlist.get(0).getProfessorid().getId();
			
			ProfessorVo professorvo = new ProfessorVo();
			professorvo.setId(professorid);
			
			SubjectProfessorVo subjectprofessorvo = new SubjectProfessorVo();
			subjectprofessorvo.setProfessorid(professorvo);

			SubjectProfessorDao subjectprofessordao = new SubjectProfessorDao();
			ArrayList<SubjectProfessorVo> subjectprofessorlist = subjectprofessordao.searchSubjectProfessor(subjectprofessorvo);

			session.setAttribute("semprofessorlist", professorlist);
			session.setAttribute("subjectrofessorlist", subjectprofessorlist);

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
			ArrayList<DepartmentProfessorVo> professorlist = departmentprofessordao
					.searchDepartmentProfessor(departmentprofessorvo);
			System.out.println(professorlist.size());

			int professorid = professorlist.get(0).getProfessorid().getId();
			ProfessorVo professorvo = new ProfessorVo();
			professorvo.setId(professorid);

			SemProfessorVo semprofessorvo = new SemProfessorVo();
			semprofessorvo.setProfessorid(professorvo);

			SemProfessorDao semprofessordao = new SemProfessorDao();
			ArrayList<SemProfessorVo> semprofessorlist = semprofessordao.searchCollegeDepartmentProfessor(semprofessorvo);

			SubjectProfessorVo subjectprofessorvo = new SubjectProfessorVo();
			subjectprofessorvo.setProfessorid(professorvo);

			SubjectProfessorDao subjectprofessordao = new SubjectProfessorDao();
			ArrayList<SubjectProfessorVo> subjectprofessorlist = subjectprofessordao.searchSubjectProfessor(subjectprofessorvo);

			session.setAttribute("semprofessorlist", semprofessorlist);
			session.setAttribute("depprofessorlist", professorlist);
			session.setAttribute("subjectrofessorlist", subjectprofessorlist);

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
			
			SubjectProfessorDao subjectProfessorDao= new SubjectProfessorDao();
			ArrayList<SubjectProfessorVo> professorlist = subjectProfessorDao.SubjectProfessor(subjectProfessorVo);
			System.out.println(professorlist.size());

			session.setAttribute("subprofessorlist", professorlist);			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
