import java.util.*;
public class Main
{
	public static void main(String[] args) {
	    //Colors:
	    String RED = "\u001B[38;5;196m";
	    String GREEN = "\u001B[32m";
	    String RESET = "\u001B[0m";
	    String PINK = "\u001B[38;5;212m";
	    
		String[] Rose={
		    GREEN+"               __",
		    GREEN+"          _   / /|",
		    GREEN+"         |\\\\  \\/_/",
		    GREEN+"         \\_\\| / __",
		    GREEN+"            \\/_/__\\           "+RED+".-=='/~\\",
		    GREEN+"     ____,__/__,_____,______)"+RED+"/   /{~}}}",
		    GREEN+"     =,======,====,=====,===,"+RED+"\\'-' {{~}}",
		      RED+"                              '-==.\\}/",
		     PINK+"============================================================",
		     PINK+" | "+RESET+"Happy "+PINK+"Valentine's Day"+RESET+", Baby Kooo~~ <3                 "+PINK+" |",
		     PINK+" | "+RESET+"Thank "+PINK+"you"+RESET+" so much talaga po for this past year. "+PINK+"You    |",
		     PINK+" | "+RESET+"Have no idea how much joy "+PINK+" you've"+RESET+" brought me po. The  "+PINK+" |",
		     PINK+" | "+RESET+"companionship, the feeling that I'm not alone anym-   "+PINK+" |",
		     PINK+" | "+RESET+"ore, that "+PINK+"you're"+RESET+ " with me. "+PINK+"You"+RESET+" motivated me to look    "+PINK+" |",
		     PINK+" | "+RESET+"into myself and change for the better because I want  "+PINK+" |",
		     PINK+" | "+RESET+"to be my best self for"+PINK+" you"+RESET+". I want "+PINK+"you"+RESET+" to know na     "+PINK+" |",
		     PINK+" | "+RESET+"kahit super busy na "+PINK+"tayo"+RESET+" pareho na I still love "+PINK+"you    |",
		     PINK+" | "+RESET+"with all my heart and I wouldn't trade "+PINK+"you"+RESET+" for the     "+PINK+"|",
		     PINK+" | "+RESET+"world. I'm sorry I'm not physically there, That I     "+PINK+" |",
		     PINK+" | "+RESET+"couldn't take "+PINK+"you"+RESET+" out to buy some ice cream or        "+PINK+" |",
		     PINK+" | "+RESET+"french fries. That I can't hold "+PINK+"your"+RESET+" cheeks in        "+PINK+" |",
		     PINK+" | "+RESET+"my palm and lean into "+PINK+"you"+RESET+" to kiss "+PINK+"you"+RESET+". Pero know po   "+PINK+" |",
		     PINK+" | "+RESET+"na I wouldn't want to spend my "+PINK+"Valentine's Day"+RESET+" with   "+PINK+" |",
		     PINK+" | "+RESET+"anyone except "+PINK+"you.                                     |",
		          " | Happy Valentine's Day po, Fiona, my one and only love  |",
		          " |                                 -Alfred <3             |",
		          "============================================================"
		};
		
		for(int i=0; i<Rose.length; i++){
		    System.out.println(Rose[i]);
		}
	}
}
