package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import dao.NoteDao;
import entity.Note;

public class AddNewNote extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddNewNote() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/json");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();
		//TODO Ìí¼Ó
		String method = request.getParameter("method");
		NoteDao noteDao = new NoteDao();
		if(method.equals("add")||method.equals("update")){
			Note n = new Note(request.getParameter("content"),
					Integer.parseInt(request.getParameter("height").equals("")?"150":request.getParameter("height")), 
					Integer.parseInt(request.getParameter("width").equals("")?"150":request.getParameter("width")), 
					Integer.parseInt(request.getParameter("posX").equals("")?"150":request.getParameter("posX")), 
					Integer.parseInt(request.getParameter("posY").equals("")?"150":request.getParameter("posY")), 
					request.getParameter("BGColour"), 
					Integer.parseInt(request.getParameter("contentTextSize").equals("")?"30":request.getParameter("contentTextSize")));
			if(method.equals("add")){
				try {
					noteDao.addNewNote(n);
					out.print(n.getId());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(method.equals("update")){
				n.setId(Integer.parseInt(request.getParameter("id")));
				try {
					noteDao.updateNote(n);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else if(method.equals("delete")){
			Note n = new Note();
			n.setId(Integer.parseInt(request.getParameter("id")));
			try {
				noteDao.deleteNote(n);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(method.equals("select")){
			try {
				List<Note> list = noteDao.selectNote();
				JSONArray ja = JSONArray.fromObject(list);
				out.print(ja.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
