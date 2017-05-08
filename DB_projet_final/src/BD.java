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

        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@oracle.iro.umontreal.ca:1521:orcl","deladurf", "urfp099D" );
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
            }

            catch (SQLException e){
                System.out.print("FUCK!");
                e.printStackTrace();
            }

            finally {

                if(st != null)
                {
                    try {
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


        return formatResultSetIntoString(resultSet);
    }

    private static String formatResultSetIntoString(ResultSet resultSet)
    {
        String formatedString = "";
        try{
            while(resultSet.next())
            {
                formatedString += "- " + resultSet.getString(1) + "\n";
            }
        }catch (SQLException e)
        {
            System.out.println("ResultSet Error in format");
            e.printStackTrace();
        }

        return formatedString;

    }

}
