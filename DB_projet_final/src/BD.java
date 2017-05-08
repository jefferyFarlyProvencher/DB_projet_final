import java.sql.*;

import javax.swing.*;
import java.sql.*;

/**
 * Created by Mihai on 2017-04-10.
 */
public class BD {

    /**
     * Executes request and returns a string containing formated String of the answers
     * @param request :
     * @return what to show ;
     */
    public static String execute(String request){

        //Set
        Connection connection = null;
        ResultSet resultSet = null;
        String response = "";

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

            Statement st = null;

            try{
                st = connection.createStatement();
                resultSet = st.executeQuery(request);
                response = formatResultSetIntoString(resultSet);
            }

            catch (SQLException e){
                System.out.print("PHOQUE!");
                e.printStackTrace();
            }

            finally {

                if(st != null)
                {
                    try {

                        System.out.print(response);
                        st.close();
                    } catch (SQLException e)
                    {
                        e.printStackTrace();
                    }
                }

            }



        }
        else
        {
            System.out.println("ANOTHER CONNECTION ERROR");
            System.exit(-1);
        }


        return response;
    }

    private static String formatResultSetIntoString(ResultSet resultSet)
    {
        String formatedString = "";

        if(resultSet == null)
        {
            System.out.println("NULL RESULT SET IS NULL");
        }

        else {



            try {
                while (resultSet.next()) {
                    formatedString += resultSet.getString(1) + "\n";
                    //System.out.println(formatedString);
                }
            } catch (SQLException e) {
                System.out.println("ResultSet Error in format");
                e.printStackTrace();
            }
        }

        return formatedString;

    }

}