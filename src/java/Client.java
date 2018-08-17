

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
private Socket socket;
private Scanner input;
private Scanner output;
private PrintStream reader;


public static void main(String[] args) throws IOException  {
Client cl = new Client();
cl.validate();
}
	
	
private void validate() throws IOException,UnknownHostException{

socket = new Socket("127.0.0.1",1347);
input = new Scanner(System.in);
reader = new PrintStream(socket.getOutputStream());

while(true){
		
System.out.println("\t\t\t Login Section");
System.out.println("Username:->");
String username = input.nextLine();
System.out.println("password:->");
String password = input.nextLine();
reader.println(username);
reader.println(password);
reader.flush();
output = new Scanner(socket.getInputStream());
String response = output.nextLine();
String response2 = output.nextLine();
String response3 = output.nextLine();
String response4 = output.nextLine();
String response5 = output.nextLine();
String response6 = output.nextLine();
String response7 = output.nextLine();


if(response.equals("success")){
System.out.println("\t\t\t----------------------------------------------------");
System.out.println("welcome "+" "+ username);
System.out.println(response2);
System.out.println("Amount"+"->"+response3);
System.out.println("transaction type"+"->"+response4);
System.out.println("customerNo."+"->"+response5);
System.out.println("Service provider"+"->"+response6);
System.out.println("From"+"->"+response7);

transact();
}
else{
System.out.println("wrong usernaame or password");
validate();
			
}
		
}
}
		
		
	
	
public  void transact() throws IOException{

System.out.println("Note that the system has a maximum withdraw amount. thank you");
System.out.println("syntax is [commmit]- [deposit]-[numberfrom]-[amount]-[agent number]-[provider]-[to_agent]");

String s1 = input.next();
String s2 = input.next();
String s3 = input.next();
String s4 = input.next();
String s5 = input.next();
String s6 = input.next();
String s7 = input.next();


reader.println(s1);
reader.println(s2);
reader.println(s3);
reader.println(s4);
reader.println(s5);
reader.println(s6);
reader.println(s7);
reader.flush();

review();

}

    private void review() throws IOException {
        transact(); //To change body of generated methods, choose Tools | Templates.
    }

   

    

}