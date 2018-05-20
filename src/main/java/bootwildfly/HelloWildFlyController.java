package bootwildfly;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.sql.*;

@RestController
public class HelloWildFlyController {


    @RequestMapping("hello")
    public String sayHello(){
	StringBuffer returnStr = new StringBuffer("Hello, SpringBoot on Wildfly - ");
	returnStr.append("<br></br>");
		try {
			// step1 load the driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// step2 create the connection object
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.43.235:1521:xe", "system", "admin");

			// step3 create the statement object
			Statement stmt = con.createStatement();

			// step4 execute query
			ResultSet rs = stmt.executeQuery("select distinct * from employee order by id");
			while (rs.next()){
				returnStr.append(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getInt(4)
						+ "  " + rs.getInt(5) + "  " + rs.getString(6));
				returnStr.append("<br></br>");
			}
			// step5 close the connection object
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	
        return returnStr.toString();
    }
}