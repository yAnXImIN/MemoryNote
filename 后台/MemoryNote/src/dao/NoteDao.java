package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Note;

public class NoteDao {
	private Connection getConnection() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		String url="jdbc:mysql://localhost:3306/test?user=root&password=root";
		Connection con = DriverManager.getConnection(url);
		return con;
	}
	public int addNewNote(Note note) throws Exception{
		Connection con = getConnection();
		String sql = "insert into note(content,height,width,posX,posY,BGColour,contentTextSize) values(?,?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, note.getContent());
		ps.setInt(2, note.getHeight());
		ps.setInt(3, note.getWidth());
		ps.setInt(4, note.getPosX());
		ps.setInt(5, note.getPosY());
		ps.setString(6, note.getBGColour());
		ps.setInt(7, note.getContentTextSize());
		ps.execute();
		sql = "select id from note order by id DESC";
		ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			note.setId(rs.getInt("id"));
		}
		rs.close();
		ps.close();
		con.close();
		return 1;
	}
	public int deleteNote(Note note) throws Exception{
		Connection con = getConnection();
		String sql = "delete from note where id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, note.getId());
		ps.execute();
		ps.close();
		con.close();
		return 1;
	}
	public List<Note> selectNote() throws Exception{
		ArrayList<Note> list = new ArrayList<Note>();
		Connection con = getConnection();
		String sql = "select * from note";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Note note = new Note(rs.getString("content"),
					rs.getInt("height"), 
					rs.getInt("width"),
					rs.getInt("posX"), 
					rs.getInt("posY"), 
					rs.getString("bGColour"),
					rs.getInt("contentTextSize"));
			note.setId(rs.getInt("id"));
			list.add(note);
		}
		rs.close();
		ps.close();
		con.close();
		return list;
	}
	public int updateNote(Note note) throws Exception{
		Connection con = getConnection();
		String sql = "update note set content=?,height=?,width=?,posX=?,posY=?,bGColour=?,contentTextSize=? where id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, note.getContent());
		ps.setInt(2, note.getHeight());
		ps.setInt(3, note.getWidth());
		ps.setInt(4, note.getPosX());
		ps.setInt(5, note.getPosY());
		ps.setString(6, note.getBGColour());
		ps.setInt(7, note.getContentTextSize());
		ps.setInt(8, note.getId());
		ps.execute();
		ps.close();
		con.close();
		return 1;
	}
	public static void main(String[] args) throws Exception {
		NoteDao nd = new NoteDao();
		List<Note> l = nd.selectNote();
		return;
	}
}
