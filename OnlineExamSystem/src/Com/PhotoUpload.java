package Com;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import javax.servlet.http.Part;
import org.apache.commons.io.IOUtils;

import DAO.PhotoUploadDao;
import VO.PhotoUploadVo;
import VO.ProfessorVo;

/**
 * Servlet implementation class PhotoUpload
 */
@WebServlet("/PhotoUpload")
@MultipartConfig
public class PhotoUpload extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String flag = request.getParameter("flag");
		HttpSession session = request.getSession();
		if (flag.equalsIgnoreCase("insert")) {
			int professorid = Integer.parseInt(request.getParameter("id"));
			session.setAttribute("professorid", professorid);
			response.sendRedirect("PhotoUpload.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		int professorid = (int) session.getAttribute("professorid");
		
		Part filepart = request.getPart("file");
		InputStream inputstream = null;
		if (filepart != null) {
			inputstream = filepart.getInputStream();
		}
		byte[] bytes = IOUtils.toByteArray(inputstream);
		
		ProfessorVo professorvo = new ProfessorVo();
		professorvo.setId(professorid);
		
		PhotoUploadVo photouploadvo = new PhotoUploadVo();
		photouploadvo.setImage(bytes);
		photouploadvo.setProfessorid(professorvo);

		PhotoUploadDao dao = new PhotoUploadDao();
		dao.insert(photouploadvo);
	}
}
