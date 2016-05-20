import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.time.format.*;
import java.time.LocalDateTime;
import com.microsoft.sqlserver.jdbc.*;

public class DBaccess {
	static{
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static long insertReport(ReportObj obj)
	{
		Connection connection = null;
		try{
			connection = connectToDB();
            
			
            String insertSql = "INSERT INTO reports "+
            "(category, subcategory, location_x, location_y, report_time, report_text, phone) "+
            "VALUES (?,?,?,?,?,?,?);";
            PreparedStatement prepsInsertProduct = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            prepsInsertProduct.setString(1, obj.Category);
            prepsInsertProduct.setString(2, obj.Subcategory);
            prepsInsertProduct.setString(3, String.valueOf(obj.Location_x));
            prepsInsertProduct.setString(4, String.valueOf(obj.Location_y));
            prepsInsertProduct.setString(5, obj.Report_time.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
            prepsInsertProduct.setString(6, obj.Report_text);
            prepsInsertProduct.setString(7, obj.Phone);
            
            int affectedRows = prepsInsertProduct.executeUpdate();
            
            if (affectedRows == 0){
            	throw new SQLException("Addind new report failed");
            }
            try (ResultSet generatedKeys = prepsInsertProduct.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
		}
		catch(Exception e){
			e.printStackTrace();
		}
        finally{
        	closeConnectionToDB(connection);
        }
		
		return -1;
	}
	
	public static ArrayList<ReportObj> getAllReports(){
		Connection connection = null;
		ArrayList<ReportObj> reportsArray = new ArrayList<ReportObj>();
		try{
			connection = connectToDB();
			String selectSql = "SELECT * FROM reports;";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(selectSql);
			ReportObj obj;
            while (resultSet.next()) 
            {
            	obj = new ReportObj();
            	obj.Id = resultSet.getInt(1);
            	obj.Category = resultSet.getString(2);
        		obj.Subcategory = resultSet.getString(3);
        		obj.Location_x = Integer.parseInt(resultSet.getString(4));
        		obj.Location_y = Integer.parseInt(resultSet.getString(5));
        		obj.Report_time = LocalDateTime.parse(resultSet.getString(6),DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        		obj.Report_text = resultSet.getString(7);
        		obj.Phone = resultSet.getString(8);
        		obj.Counter = resultSet.getInt(9);
        		reportsArray.add(obj);
            }
		}
		catch(Exception e){
			e.printStackTrace();
		}
        finally{
        	closeConnectionToDB(connection);
        }
		return reportsArray;
		
	}
	
	public static ArrayList<ReportObj> getMyReports(String phone){
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		ArrayList<ReportObj> reportsArray = new ArrayList<ReportObj>();
		try{
			connection = connectToDB();
			String selectSql = "SELECT * FROM reports WHERE Phone='"+phone+"';";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(selectSql);
			ReportObj obj;
            while (resultSet.next()) 
            {
            	obj = new ReportObj();
            	obj.Id = resultSet.getInt(1);
            	obj.Category = resultSet.getString(2);
        		obj.Subcategory = resultSet.getString(3);
        		obj.Location_x = Integer.parseInt(resultSet.getString(4));
        		obj.Location_y = Integer.parseInt(resultSet.getString(5));
        		obj.Report_time = LocalDateTime.parse(resultSet.getString(6),DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        		obj.Report_text = resultSet.getString(7);
        		obj.Phone = resultSet.getString(8);
        		obj.Counter = resultSet.getInt(9);
        		reportsArray.add(obj);
            }
		}
		catch(Exception e){
			e.printStackTrace();
		}
        finally{
        	if (resultSet != null) try { resultSet.close(); } catch(Exception e) {}
            if (statement != null) try { statement.close(); } catch(Exception e) {}
        	closeConnectionToDB(connection);
        }
		return reportsArray;
	}
	
//	public static void updateReport(int id){
//		Connection connection = null;
//		Statement statement = null;
//		ResultSet resultSet = null;
//		try{
//			String sqlQuery = "SELECT * FROM reports WHERE Id='"+id+"';";
//			statement = connection.createStatement();
//			resultSet = statement.executeQuery(sqlQuery);
//			while (resultSet.next()) {
//	    		int count = resultSet.getInt(9);
//	    		count++;
//	    		sqlQuery = "UPDATE reports SET Counter="+count+" WHERE Id="+id+";";
//				statement = connection.createStatement();
//				ResultSet resultSet2 = statement.executeQuery(sqlQuery);
//				if (resultSet2 != null) try { resultSet2.close(); } catch(Exception e) {}
//			}
//			
//
//		}
//		catch(Exception e){
//			e.printStackTrace();
//		}
//        finally{
//        	if (resultSet != null) try { resultSet.close(); } catch(Exception e) {}
//            if (statement != null) try { statement.close(); } catch(Exception e) {}
//        	closeConnectionToDB(connection);
//        }
//	}
	
	private static Connection connectToDB(){
		String connectionString =
				"jdbc:sqlserver://hujihack.database.windows.net:1433;"
				+ "database=HujiHack;"
				+ "user=HujiHack@hujihack;"
				+ "password=Password1!;"
				+ "encrypt=true;"
				+ "trustServerCertificate=false;"
				+ "hostNameInCertificate=*.database.windows.net;"
				+ "loginTimeout=30;";
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(connectionString);

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	private static void closeConnectionToDB(Connection connection)
	{
		try{ 
			connection.close();
			} 
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
