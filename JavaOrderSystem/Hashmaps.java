
package diana_alfred_bscs111_12062024;
import java.util.HashMap;

public class Hashmaps {
    public static HashMap<Integer, String[]> acctList = new HashMap<>();
    public static HashMap<Integer, String[]> prodList = new HashMap<>();
    public static HashMap<Integer, String[]> orderList = new HashMap<>();
    public static int userID = 1001;
    public static int productID = 1003;
    public static void adminAcc(){
        int admID = 1000;
        String admFname = "Alfred Diana", admCNum = "09982077334", admAddr= "308 Negra Arroyo Lane, T.S. Cruz, Almanza Dos, Las Pinas City", admGender = "Male", admPW = "RLFD43", utype = "Admin";
        String admPut[] = {admFname, admPW, admCNum, admAddr,admGender, utype};
        acctList.put(admID,admPut);
    }
    
    public static void foodMenAdd(){
        String[] adobo ={ "Adobo", "50", "5"};
        String[] sinigang = {"Sinigang", "45","10"};
        String[] friedC = {"Fried Chicken", "60", "8"};
        
        prodList.put(1000, adobo);
        prodList.put(1001, sinigang);
        prodList.put(1002, friedC);
        
    }
}
