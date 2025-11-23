import java.util.*;
public class Main
{
    public static Random ra = new Random();
    public static Scanner sc = new Scanner(System.in);
    public static class Person{
        String name, ideology;
        int age;
        int influence, polpower, military, admin, ideology; //President, Chairman, Chief General, Premiere, and Communications Head respectively
        int personality; // determines relations
        public Person(String name){
            this.name = name;
            this.age = ra.nextInt(20)+30;
            this.personality = ra.nextInt(100);
        }
    }
	public static void main(String[] args) {
		System.out.println("Hello World");
	}
}
