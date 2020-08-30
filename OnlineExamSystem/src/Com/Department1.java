package Com;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

import DAO.DepartmentDao;
import DAO.SemDao;
import DAO.SubjectDao;
import VO.CollegeVo;
import VO.Common;
import VO.DepartmentVo;
import VO.SemVo;
import VO.SubjectVo;

/**
 * Servlet implementation class Department1
 */
@WebServlet("/Department1")
public class Department1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Department1() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int departmentid = Integer.parseInt(request.getParameter("q"));

		DepartmentVo departmentvo = new DepartmentVo();
		departmentvo.setId(departmentid);

		SemVo semvo = new SemVo();
		semvo.setDepartmentid(departmentvo);

		SemDao semdao = new SemDao();
		ArrayList<SemVo> semlist = semdao.searchDepartment(semvo);
		System.out.println(semlist.size());
		List<Common> list = new ArrayList<Common>();

		for (SemVo sem : semlist) {
			int id = sem.getId();
			int semester = sem.getSemname();
			Common common = new Common();
			common.setId(id);
			common.setSemester(semester);
			list.add(common);
		}

		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		out.print(gson.toJson(list));
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("....................................");
		String flag = request.getParameter("flag");
		System.out.println(flag);
		if (flag.equalsIgnoreCase("semester")) {
			System.out.println("0320.32......................");
			int departmentid = Integer.parseInt(request.getParameter("department"));
			DepartmentVo departmentVo = new DepartmentVo();
			departmentVo.setId(departmentid);

			SemVo semVo = new SemVo();
			semVo.setDepartmentid(departmentVo);

			SemDao semDao = new SemDao();
			ArrayList<SemVo> semlist = semDao.searchDepartment(semVo);
			System.out.println(semlist.size());
			List<Common> list = new ArrayList<Common>();
			for (SemVo sem : semlist) {
				int id = sem.getId();
				int semester = sem.getSemname();
				Common common = new Common();
				common.setId(id);
				common.setSemester(semester);
				list.add(common);
			}

			Gson gson = new Gson();
			PrintWriter out = response.getWriter();
			out.print(gson.toJson(list));
			out.flush();
			out.close();

		} else if(flag.equalsIgnoreCase("deaprtment")){
			
			int departmentid = 1;

			CollegeVo collegeVo = new CollegeVo();
			collegeVo.setId(departmentid);

			DepartmentVo departmentVo = new DepartmentVo();
			departmentVo.setDepartmentcollegeid(collegeVo);

			DepartmentDao departmentDao = new DepartmentDao();
			ArrayList<DepartmentVo> semlist = departmentDao.searchCollegeDepartment(departmentVo);
			System.out.println(semlist.size());
			List<Common> list = new ArrayList<Common>();

			for (DepartmentVo sem : semlist) {
				int id = sem.getId();
				String semester = sem.getDepartment();
				Common common = new Common();
				common.setId(id);
				common.setDepartment(semester);
				list.add(common);
			}

			Gson gson = new Gson();
			PrintWriter out = response.getWriter();
			out.print(gson.toJson(list));
			out.flush();
			out.close();
			
		}else if(flag.equalsIgnoreCase("subject")){
			int semsterid = Integer.parseInt(request.getParameter("semesterid"));

			SemVo semVo = new SemVo();
			semVo.setId(semsterid);

			SubjectVo subjectVo = new SubjectVo();
			subjectVo.setSemid(semVo);
			
			SubjectDao subjectDao = new SubjectDao();
			ArrayList<SubjectVo> semlist = subjectDao.searchSem(subjectVo);
			System.out.println(semlist.size());
			List<Common> list = new ArrayList<Common>();

			for (SubjectVo subjectlist : semlist) {
				int id = subjectlist.getId();
				String subject = subjectlist.getSubject();
				Common common = new Common();
				common.setId(id);
				common.setSubject(subject);
				list.add(common);
			}

			Gson gson = new Gson();
			PrintWriter out = response.getWriter();
			out.print(gson.toJson(list));
			out.flush();
			out.close();
		}
	}

}
