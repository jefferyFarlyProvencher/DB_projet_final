/**
 * Created by farlyprj on 17-04-10.
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {


    private static ArrayList<String> LMD = new ArrayList<String>();
    private static String createTable = "";

    public static void main(String[] args)
    {
        initLMD();
        MainWindow window = new MainWindow(LMD);
    }

    private static void initLMD()
    {
        System.out.println(new File(".").getAbsoluteFile());

        File sqlFile = new File(new File("").getAbsolutePath()+"//DB_projet_final//LMD.sql");
        try {
            Scanner infile = new Scanner(sqlFile);
            infile.useDelimiter(Pattern.compile(";"));
            while (infile.hasNext()) {
                LMD.add(infile.next());
            }
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}