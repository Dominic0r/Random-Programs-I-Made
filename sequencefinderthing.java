import java.util.*;
public class Main
{
	public static void main(String[] args) {
		String sequence = "It is never too late to have a happy childhood";
		String toFind ="happy";
		
		char[] sequenceArray = sequence.toCharArray();
		int index = 0;
		boolean hasBeenFound = false;
		do{
		    String currentseq ="";
		    for(int i=0; i<toFind.length();i++){
		        currentseq+=sequenceArray[index+i];
		    }
		    System.out.println("\nIndex "+ index);
		    if(index>0){
		    for(int i=0; i<index;i++) System.out.print(" ");
		    }
		    
		    System.out.println(toFind);
		    System.out.println(sequence);
		    index++;
		    hasBeenFound = toFind.equals(currentseq);
		}while(!hasBeenFound);
		
		System.out.println("Found at Index "+ index);
	}
}
