package com.javaGG.daodto;

import java.sql.*;
import java.util.*;

public class MemberDAO {	
	   
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String uid = "scott";
	private String upw = "tiger";
	private String query = "select * from members"; 
	public MemberDAO() {
		super();
		// TODO Auto-generated constructor stub
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<MemberDTO> memberSelect() {
		ArrayList<MemberDTO> dtos = new ArrayList<MemberDTO>();
		
		Connection con = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    
	    try {
	    	con = DriverManager.getConnection(url, uid, upw);
		    stmt = con.createStatement();
		    rs = stmt.executeQuery(query);		    
		    while(rs.next()){
		    	String name = rs.getString("name");
	            String id = rs.getString("id");
	            String pw = rs.getString("pw");
	            String ph1 = rs.getString("ph1");
	            String ph2 = rs.getString("ph2");
	            String ph3 = rs.getString("ph3");
	            String gender = rs.getString("gender");
	            
	            MemberDTO dto = new MemberDTO(name, id, pw, ph1, ph2, ph3, gender);
	            dtos.add(dto);
	         }
	    }catch(SQLException e) {
	    	e.printStackTrace();
	    }finally {
	    	try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(con != null) con.close();
	    	}catch(SQLException e) {
	    		e.printStackTrace();
	    	}
		}
	    return dtos;
	}
	
	
}
