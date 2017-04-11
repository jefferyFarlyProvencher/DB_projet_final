/**
 * Created by farlyprj on 17-04-10.
 */

import oracle.jdbc.driver.OracleDriver;

import java.sql.*;

public class Main {

    public static void main(String[] args)
    {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@oracle.iro.umontreal.ca:1521:orcl","farlyprj", "prjp112F" );
        }catch (SQLException e)
        {
            System.out.println("Connection ERROR");
            e.printStackTrace();
        }

        if(connection !=null)
        {
            System.out.println("Connection successful");

            String statement = "SELECT table_name FROM user_tables";

            try{
                Statement st = connection.createStatement();
                ResultSet resultSet = st.executeQuery(statement);
            }catch (SQLException e)
            {
                e.printStackTrace();
            }



        }
        else
        {
            System.out.println("ANOTHER CONNECTION ERROR");
            System.exit(-1);
        }
    }
}
