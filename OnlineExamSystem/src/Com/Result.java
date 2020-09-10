package Com;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ResultDao;
import VO.ResultVo;
import VO.StudentVo;
import VO.SubjectVo;

/**
 * Servlet implementation class Result
 */
@WebServlet("/Result")
public class Result extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Result() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String flag = request.getParameter("flag");
		
		if(flag.equalsIgnoreCase("studentid")) {
			int studentid = Integer.parseInt(request.getParameter("id"));
			
			StudentVo studentVo = new  StudentVo();
			studentVo.setId(studentid);
			
			ResultVo resultVo = new ResultVo();
			resultVo.setStudentid(studentVo);
			
			ResultDao resultDao = new ResultDao();
			ArrayList<ResultVo> reportlist = resultDao.searchResult(resultVo);
			
			session.setAttribute("studentresult", reportlist);
			response.sendRedirect("Report.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		int studentid = (int) session.getAttribute("studentid");
		Timestamp t1 = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy  hh:mm:ss aa");
		String date = sdf.format(t1);
		
		String result = request.getParameter("result1");
		int time = Integer.parseInt(request.getParameter("time"));
		float markobtained = Float.parseFloat(request.getParameter("result"));
		int correct = Integer.parseInt(request.getParameter("correct"));
		int wrong = Integer.parseInt(request.getParameter("wrong"));
		int markreview = Integer.parseInt(request.getParameter("mark"));
		int notattended = Integer.parseInt(request.getParameter("notattended"));
		int totalmcq = Integer.parseInt(request.getParameter("totalmcq"));
		String topic = request.getParameter("topic");
		int subject = Integer.parseInt(request.getParameter("subject"));
		int totalmark = Integer.parseInt(request.getParameter("totalmark"));
		
		StudentVo studentVo = new  StudentVo();
		studentVo.setId(studentid);
		
		SubjectVo subjectVo = new SubjectVo();
		subjectVo.setId(subject);
		
		ResultVo resultVo = new ResultVo();
		resultVo.setStudentid(studentVo);
		resultVo.setResult(result);
		resultVo.setMarkobtained(markobtained);
		resultVo.setTime(time);
		resultVo.setCorrect(correct);
		resultVo.setWrong(wrong);
		resultVo.setMarkreview(markreview);
		resultVo.setNotattended(notattended);
		resultVo.setTotalmcq(totalmcq);
		resultVo.setDate(date);
		resultVo.setTopic(topic);
		resultVo.setSubjectid(subjectVo);
		resultVo.setTotalmark(totalmark);
		
		ResultDao resultDao = new ResultDao();
		resultDao.insert(resultVo);
		
		response.sendRedirect("Student_Login.jsp");
	}

}
