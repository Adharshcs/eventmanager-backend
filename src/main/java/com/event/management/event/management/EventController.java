package com.event.management.event.management;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/page1")
public class EventController {
	
	

	@GetMapping
	public String printString() {
		return "Script Kids";
	}
	@GetMapping
	@RequestMapping("/page2")
	public String printStrings() {
		return "JP";
	}
	
	@GetMapping
	@RequestMapping("/page3")
	public sample returnDetails() {
		sample s1= new sample();
		s1.setName("Jayaprakash");
		return s1;
	
	}
	@GetMapping
	@RequestMapping("/page4")
	public extra returnData() {
		extra e= new extra();
		e.setId("32");
		e.setName("Jayaprakash");
		e.setPhone("8754813384");
		e.setAddress("chennai");
		
		return e;
	
	}
	
	@GetMapping
	@RequestMapping("/list")
	public List<extra> return1List(){
		List<extra> l1=new ArrayList<>();
		extra e= new extra();		
		e.setId("31");
		e.setName("Jayaprakash");
		e.setPhone("8754813384");
		e.setAddress("chennai");
		l1.add(e);
		
		extra e1= new extra();		
		e1.setId("30");
		e1.setName("Adhi");
		e1.setPhone("42290708");
		e1.setAddress("chennai");
		l1.add(e1);
		return l1;
		
	}
	
	@GetMapping
	@RequestMapping("/page5")
	public List<extra> return2List(){
		List<extra> e=new ArrayList<>();	
		Connection con =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:1002/etab","root","1002");
			Statement statement = con.createStatement();
			ResultSet r = statement.executeQuery("SELECT * FROM mytab;");
			while(r.next()) {
				extra d=new extra();
				d.setId(r.getString("id"));
				d.setName(r.getString("name"));
				d.setPhone(r.getString("phone"));
				d.setAddress(r.getString("address"));
				e.add(d);
			}			
		}
		catch(Exception e1) {
			e1.printStackTrace();
		}
		finally {
			try {
				con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		return e;
		
	

}
	@PostMapping
	@RequestMapping("/page6")
	public ArrayList<sample> addString(@RequestBody sample event) throws SQLException{
		
		
		ArrayList<sample> e=new ArrayList<>();
		c(event.getId(),event.getName(),event.getPhone(),event.getAddress(),e);
		
		
		return e;
		
	}


	public void c(String b,String c,String d,String e,ArrayList<sample> l) throws SQLException {
		/*
		Event s=new Event();
		s.setName(b);
		s.setType(c);
		s.setDate(d);
		s.setVenue(e);
		*/
		Connection con= null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:1002/etab","root","1002");
		    PreparedStatement s = con.prepareStatement("insert into data(Id,Name,Phone,Address)values(?,?,?,?)");
			s.setString(1,b);
			s.setString(2,c);
			s.setString(3,d);
			s.setString(4,e);
			s.executeUpdate();
			
		}catch(Exception i) {
			i.printStackTrace();
		}finally {
			con.close();
		}
		System.out.println("done");
	}
}
