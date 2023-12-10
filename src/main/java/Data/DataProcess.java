package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import Model.ArrayList;
import Service.People;

public class DataProcess {
	private static final Connection CONNECTION = getCONNECTION();

	private static Connection getCONNECTION(){
		try{
			String URL = "jdbc:mysql://localhost:3306/studentdb?user=root";
			String USER = "root";
			String PASSWORD = "Tuantu2610.";
			return DriverManager.getConnection(URL , USER , PASSWORD);
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		return null;
	}
//	public static ArrayList<People> findAll(){
//		ArrayList<People> result = new ArrayList<>();
//		try{
//			ResultSet resultSet = CONNECTION.createStatement().executeQuery("Select * FROM student");
//			while (resultSet.next()){
//				Student student = new Student(resultSet.getInt("id"),resultSet.getString("name"), resultSet.getInt("age"));
//				result.add(student);
//			}
//			resultSet.close();
//		} catch (Exception e){
//			System.out.println(e.getMessage());
//		}
//		return result;
//
//	}
}
