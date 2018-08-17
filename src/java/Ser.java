

import java.io.IOException;


import java.io.PrintStream;

import java.net.ServerSocket;
import java.net.Socket;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;
import java.io.DataOutputStream;

    public class Ser {
    private ServerSocket ket;
    private  PrintStream wix;
    private Scanner get;
    private Socket sk;
    private String mtn_line;
    private String airtel_line;
    private double mtn_float;
    private double airtel_float;
    private int initial_cash;
    private double total;
    private String moneys;
    private String number_from;
    private String name;
    private String provider;
    private String kiosk;
    private String choose;
    private String sender;
    private Double commission;
    private int amount_x;
    private Double initial_commission;
    private Double commissionmtn;
    private Double commissionairtel;
    private double total2;
    private String style;
    private Timestamp date;
    private String agent_number;
    private DataOutputStream send;
    
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
    Ser ss = new Ser();
    ss.start();
   }
	

		
private void start() throws ClassNotFoundException, IOException{
ket = new ServerSocket(1347,4);
date = new Timestamp(new java.util.Date().getTime());			
while (true){
sk = ket.accept();
wix = new PrintStream(sk.getOutputStream());
get = new Scanner(sk.getInputStream());
name = get.nextLine();
String pass = get.nextLine();
 			
try {
					
Class.forName("com.mysql.jdbc.Driver");
java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mawanda?autoReconnect=true&useSSL=false","root","sesnaco");
java.sql.PreparedStatement smtp = con.prepareStatement("SELECT * FROM attedantsregister WHERE username = ? ");
smtp.setString(1,name);
ResultSet rs = smtp.executeQuery();
while(rs.next()){

if(rs.getString("username").equals(name) && rs.getString("password").equals(pass)){
wix.println("success");
initial_cash = Integer.parseInt(rs.getString("initialCash"));
kiosk = rs.getString("KioskNo");
mtn_line = rs.getString("MTNLine");
airtel_line = rs.getString("AirtelLine");
mtn_float = Double.parseDouble(rs.getString("MtnFloat"));
airtel_float=Double.parseDouble(rs.getString("AirtelFloat"));
initial_commission = Double.parseDouble(rs.getString("commission"));
commissionmtn = Double.parseDouble(rs.getString("commission_mtn"));
commissionairtel = Double.parseDouble(rs.getString("commission_airtel"));
business();

}

else{
wix.println("unsuccessful");

}
wix.flush();
}
}
catch(SQLException e) {
e.printStackTrace();
}
}		
}
		
private void business() throws IOException, ClassNotFoundException {
try {
				
Class.forName("com.mysql.jdbc.Driver");
java.sql.Connection co = DriverManager.getConnection("jdbc:mysql://localhost/mawanda?autoReconnect=true&useSSL=false","root","sesnaco");
java.sql.PreparedStatement smtp = co.prepareStatement("SELECT * FROM service where to_agent = ? AND status ='pending'");
smtp.setString(1, name);
ResultSet rt = smtp.executeQuery();
boolean success = rt.next();


if(success==true){
while(success){
String money = rt.getString("amount");
String typ = rt.getString("transaction");
String custom = rt.getString("customerNo");
String prov = rt.getString("provider");
String from = rt.getString("from_agent");
 wix.println("you have a pending transaction");
 wix.println(money);
 wix.println(typ);
 wix.println(custom);
 wix.println(prov);
 wix.println(from);
 step2();
}
}
else{
    
 wix.println("you dont have any transaction pending on your account");
 wix.println("0");
 wix.println("0");
 wix.println("0");
 wix.println("0");
 wix.println("0");
 step2();   
}
wix.flush();
}

   
 catch(SQLException e) {
 e.printStackTrace();
			 				   
}
  
}
private void step2() throws IOException, ClassNotFoundException{
  get =  new Scanner(sk.getInputStream());
  style =  get.next();
  choose =   get.next();
  number_from = get.next();
  moneys = get.next();
  agent_number = get.next();
  provider = get.next();
  sender=get.next();
 
if(choose.equals("withdraw") && provider.equals("mtn")&& style.equals("request")){
		service();
}

else if(choose.equals("deposit") && provider.equals("airtel") && style.equals("commit")){
		deposit();
}
else if(choose.equals("deposit") && provider.equals("mtn")&& style.equals("request")){
		service();
}
else if(choose.equals("deposit") && provider.equals("airtel")&& style.equals("request")){
		service();
}
else if(choose.equals("withdraw") && provider.equals("airtel")&& style.equals("request")){
		service();
}
else if(choose.equals("deposit") && provider.equals("mtn") && style.equals("commit")){
		deposit2();
}
else if(choose.equals("withdraw") && provider.equals("airtel")&& style.equals("commit")){
		withdraw();
}
else if(choose.equals("withdraw") && provider.equals("mtn")&& style.equals("commit")){
		withdraw2();
}
else if(choose.equals("withdraw") && provider.equals("mtn")&& style.equals("service")){
		act();
}

else{
System.out.println(" wrong credentials ");
		
}
}
private void service() throws ClassNotFoundException{
Class.forName("com.mysql.jdbc.Driver");
java.sql.Connection co;
try {
 co = DriverManager.getConnection("jdbc:mysql://localhost/mawanda?autoReconnect=true&useSSL=false","root","sesnaco");
 java.sql.PreparedStatement ins = co.prepareStatement("INSERT INTO service (id,transaction,customerNo,amount,attendant_number,provider,to_agent,from_agent,status) VALUES(null,?,?,?,?,?,?,?,?)");
 ins.setString(1,choose);
 ins.setString(2,number_from);
 ins.setString(3,moneys);
 ins.setString(4,agent_number);
 ins.setString(5,provider);
 ins.setString(6, sender);
 ins.setString(7, name);
 ins.setString(8, "pending");
 int okay = ins.executeUpdate();
 if(okay ==1){
 System.out.println("successfully sent your request");
 }
 else{
 System.out.println("failed");
		 }
} catch (SQLException e) {
e.printStackTrace();
	}
}	

private void withdraw() throws ClassNotFoundException {
	
	 
		 amount_x = Integer.parseInt(moneys);
		 int cash_initial =initial_cash;
		double float_airtel = airtel_float;
		 commission =0d;
		 if(amount_x > cash_initial){
			 System.out.println("sorry we dont have enough cash");
		 }
		 
		 else if(amount_x >= 500 && amount_x <= 2500){
				 commission +=100;
				 float_airtel+=amount_x;
				 cash_initial-=amount_x;
			 }
		 else if(amount_x >= 2501 && amount_x <= 5000){
				 
				 commission +=125;
				 float_airtel+=amount_x;
				 cash_initial-=amount_x;
			 }
		else if(amount_x >= 5001 && amount_x <= 15000){
				 commission +=450;
				 float_airtel+=amount_x;
				 cash_initial-=amount_x;
			 }
			 
		 else if(amount_x >= 15001 && amount_x <= 30000){
				 commission +=500;
				 float_airtel+=amount_x;
				 cash_initial-=amount_x;
			 }
		else if(amount_x >= 30001 && amount_x <= 45000){
				 commission +=525;
				 float_airtel+=amount_x;
				 cash_initial-=amount_x;
			 }
		else if(amount_x >= 45001 && amount_x <= 60000){
				 commission +=575;
				 float_airtel+=amount_x;
				 cash_initial-=amount_x;
			 }
		else if(amount_x >= 60001 && amount_x <= 125000){
				 commission += 700;
				 float_airtel+=amount_x;
				 cash_initial-=amount_x;
			 }
		else if(amount_x >= 125001 && amount_x <= 250000){
				 commission +=1300;
				 float_airtel+=amount_x;
				 cash_initial-=amount_x;
			 }
		else if(amount_x >= 250001 && amount_x <= 500000){
				 commission +=2600;
				 float_airtel+=amount_x;
				 cash_initial-=amount_x;
			 }
		else if(amount_x >= 500001 && amount_x <= 1000000){
				 commission +=5000;
				 float_airtel+=amount_x;
				 cash_initial-=amount_x;
			 }
		else if(amount_x >= 1000001 && amount_x <= 2000000){
				 commission +=7500;
				 float_airtel+=amount_x;
				 cash_initial-=amount_x;
			 }
		else if(amount_x >= 2000001 && amount_x <= 3000000){
				 commission +=12500;
				 float_airtel+=amount_x;
				 cash_initial-=amount_x;
			 }
		else if(amount_x >= 3000001 && amount_x <= 4000000){
				 commission +=12500;
				 float_airtel+=amount_x;
				 cash_initial-=amount_x;
			 }
		else if(amount_x >= 4000001 && amount_x <= 5000000){ 
				 commission +=15000;
				 float_airtel+=amount_x;
				 cash_initial-=amount_x;
			 }
		 else{
			System.out.println("the amount must not exceed 500000");
		 }
		 
          total = commission+initial_commission;
          total2 = commission+commissionairtel;
          
		 try {
				
			 Class.forName("com.mysql.jdbc.Driver");
			 java.sql.Connection co = DriverManager.getConnection("jdbc:mysql://localhost/mawanda?autoReconnect=true&useSSL=false","root","sesnaco");
			 
			 java.sql.PreparedStatement smtp = co.prepareStatement("UPDATE  attedantsregister  SET initialCash = ? , AirtelFloat = ?,commission = ?,commission_airtel=? WHERE username = ? ");
  		     smtp.setInt(1, cash_initial);
  		     smtp.setDouble(2, float_airtel);
  		     smtp.setDouble(3, total);
  		     smtp.setDouble(4, total2);
			 smtp.setString(5, name);
			 
			 int yeth = smtp.executeUpdate();
			 if(yeth ==1){
			 System.out.println("successfully updated your airtel withdraw transactions");
			 insert_airtel();
			 }
			 else{
			 						
			 System.out.println("system failure");
			 }

			 }
			 					  
			 catch(SQLException e) {
			 e.printStackTrace();
			 				   
			}
	
	
		
	}
	
	
private void withdraw2() throws ClassNotFoundException{
	
	
	  amount_x = Integer.parseInt(moneys);
	 int cash_initial =initial_cash;
	 double float_mtn = mtn_float;
	  commission =0d;
	 
	 if(amount_x > cash_initial){
		 System.out.println("sorry we dont have enough cash");
	 }
	 else if(amount_x >= 500 && amount_x <= 5500){
			 commission +=100;
			 float_mtn+=amount_x;
			 cash_initial-=amount_x;
		 }
	 else if(amount_x >= 2501 && amount_x <= 5000){
			 
			 commission +=125;
			 float_mtn+=amount_x;
			 cash_initial-=amount_x;
		 }
	else if(amount_x >= 5001 && amount_x <= 15000){
			 commission +=450;
			 float_mtn+=amount_x;
			 cash_initial-=amount_x;
		 }
		 
	 else if(amount_x >= 15001 && amount_x <= 30000){
			 commission +=500;
			 float_mtn+=amount_x;
			 cash_initial-=amount_x;
		 }
	else if(amount_x >= 30001 && amount_x <= 45000){
			 commission +=525;
			 float_mtn+=amount_x;
			 cash_initial-=amount_x;
		 }
	else if(amount_x >= 45001 && amount_x <= 60000){
			 commission +=575;
			 float_mtn+=amount_x;
			 cash_initial-=amount_x;
		 }
	else if(amount_x >= 60001 && amount_x <= 125000){
			 commission += 700;
			 float_mtn+=amount_x;
			 cash_initial-=amount_x;
		 }
	else if(amount_x >= 125001 && amount_x <= 250000){
			 commission +=1300;
			 float_mtn+=amount_x;
			 cash_initial-=amount_x;
		 }
	else if(amount_x >= 250001 && amount_x <= 500000){
			 commission +=2600;
			 float_mtn+=amount_x;
			 cash_initial-=amount_x;
		 }
	else if(amount_x >= 500001 && amount_x <= 1000000){
			 commission +=5000;
			 float_mtn+=amount_x;
			 cash_initial-=amount_x;
		 }
	else if(amount_x >= 1000001 && amount_x <= 2000000){
			 commission +=7500;
			 float_mtn+=amount_x;
			 cash_initial-=amount_x;
		 }
	else if(amount_x >= 2000001 && amount_x <= 4000000){
			 commission +=12500;
			 float_mtn+=amount_x;
			 cash_initial-=amount_x;
		 }
	
	
	 else{
		  
		System.out.println("the amount must not exceed 4000000M");
	 }
	 total = commission+initial_commission;
     total2 = commission+commissionmtn;
	 try {
			
		 Class.forName("com.mysql.jdbc.Driver");
		 java.sql.Connection co = DriverManager.getConnection("jdbc:mysql://localhost/mawanda?autoReconnect=true&useSSL=false","root","sesnaco");
		 
		 java.sql.PreparedStatement smtp = co.prepareStatement("UPDATE  attedantsregister  SET initialCash = ? , MtnFloat = ?, commission =?, commission_mtn =?  WHERE username = ? ");
		 smtp.setInt(1, cash_initial);
		 smtp.setDouble(2, float_mtn);
		 smtp.setDouble(3, total);
		 smtp.setDouble(4, total2);
		 smtp.setString(5, name);
		 
		 int yeth = smtp.executeUpdate();
		 if(yeth==1){
			 System.out.println("successfully updated your mtn withdraw transaction");
			 insert_mtn();
		 }
		 else{
			 System.out.println("unresolved error");
		 }
		 
		 }
		 					  
		 catch(SQLException e) {
		 e.printStackTrace();
		 				   
		}
	
}

private void deposit2() throws ClassNotFoundException {
    
	  amount_x = Integer.parseInt(moneys);
	 int cash_initial =initial_cash;
	 double float_mtn = mtn_float;
	  commission =0d;
	 if(amount_x > float_mtn){
		 System.out.println("sorry we dont have enough float");
	 }
	 
	 else if(amount_x >= 500 && amount_x <= 5500){
			 commission +=0;
			 float_mtn-=amount_x;
			 cash_initial+=amount_x;
		 }
	 else if(amount_x >= 2501 && amount_x <= 5000){
			 
			 commission +=0;
			 float_mtn-=amount_x;
			 cash_initial+=amount_x;
		 }
	else if(amount_x >= 5001 && amount_x <= 15000){
			 commission +=285;
			 float_mtn-=amount_x;
			 cash_initial+=amount_x;
		 }
		 
	 else if(amount_x >= 15001 && amount_x <= 30000){
			 commission +=285;
			 float_mtn-=amount_x;
			 cash_initial+=amount_x;
		 }
	else if(amount_x >= 30001 && amount_x <= 45000){
			 commission +=285;
			 float_mtn-=amount_x;
			 cash_initial+=amount_x;
		 }
	else if(amount_x >= 45001 && amount_x <= 60000){
			 commission +=285;
			 float_mtn-=amount_x;
			 cash_initial+=amount_x;
		 }
	else if(amount_x >= 60001 && amount_x <= 125000){
			 commission += 440;
			 float_mtn-=amount_x;
			 cash_initial+=amount_x;
		 }
	else if(amount_x >= 125001 && amount_x <= 250000){
			 commission +=520;
			 float_mtn-=amount_x;
			 cash_initial+=amount_x;
		 }
	else if(amount_x >= 250001 && amount_x <= 500000){
			 commission +=850;
			 float_mtn-=amount_x;
			 cash_initial+=amount_x;
		 }
	else if(amount_x >= 500001 && amount_x <= 1000000){
			 commission +=2500;
			 float_mtn-=amount_x;
			 cash_initial+=amount_x;
		 }
	else if(amount_x >= 1000001 && amount_x <= 2000000){
			 commission +=4500;
			 float_mtn-=amount_x;
			 cash_initial+=amount_x;
		 }
	else if(amount_x >= 2000001 && amount_x <= 3000000){
			 commission +=8000;
			 float_mtn-=amount_x;
			 cash_initial+=amount_x;
		 }
	else if(amount_x >= 3000001 && amount_x <= 4000000){
			 commission +=8000;
			 float_mtn-=amount_x;
			 cash_initial+=amount_x;
		 }
	
	 else{
		System.out.println("the amount must not exceed 4000000M");
	 }
	 
	 total = commission+initial_commission;
	 total2 = commission+commissionmtn;
	 try {
			
		 Class.forName("com.mysql.jdbc.Driver");
		 java.sql.Connection co = DriverManager.getConnection("jdbc:mysql://localhost/mawanda?autoReconnect=true&useSSL=false","root","sesnaco");
		 
		 java.sql.PreparedStatement smtp = co.prepareStatement("UPDATE  attedantsregister  SET initialCash = ? , MtnFloat = ?, commission = ?, commission_mtn= ? WHERE username = ? ");
 		 smtp.setInt(1, cash_initial);
 		 smtp.setDouble(2, float_mtn);
 		 smtp.setDouble(3, total);
 		 smtp.setDouble(4, total2);
		 smtp.setString(5, name);
		 
		 int yeth = smtp.executeUpdate();
		 if(yeth==1){
			 System.out.println("successfully updated your mtn deposit transaction");
			 insert_mtn();
		 }
		 else{
			 System.out.println("unresolved error");
		 }
		 
		 }
		 					  
		 catch(SQLException e) {
		 e.printStackTrace();
		 				   
		}
	 }


private void insert_mtn() throws ClassNotFoundException {
	Class.forName("com.mysql.jdbc.Driver");
	java.sql.Connection co;
	try {
		co = DriverManager.getConnection("jdbc:mysql://localhost/mawanda?autoReconnect=true&useSSL=false","root","sesnaco");
		java.sql.PreparedStatement ins = co.prepareStatement("INSERT INTO tranfers (ID,KioskNo,Amount,line,username,transaction,date,commission,customerNo) VALUES(null,?,?,?,?,?,?,?,?)");
		 ins.setString(1,kiosk);
		 ins.setInt(2,amount_x);
		 ins.setString(3,mtn_line);
		 ins.setString(4,name);
		 ins.setString(5,choose);
		 ins.setTimestamp(6, date);
		 ins.setDouble(7,commission);
		 ins.setString(8,number_from);
		 int okay = ins.executeUpdate();
		 if(okay ==1){
		 System.out.println("successfully inserted");
		 }
		 else{
		 System.out.println("failed");
		 }
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	

	
	
}


	private void deposit() throws ClassNotFoundException {
		 
		 amount_x = Integer.parseInt(moneys);
		 int cash_initial =initial_cash;
		double float_airtel = airtel_float;
		 commission =0d;
		 if(amount_x > airtel_float){
			 System.out.println("sorry we dont have enough float");
		 }
		 else if(amount_x >= 500 && amount_x <= 2500){
				 commission +=150;
				 float_airtel-=amount_x;
				 cash_initial+=amount_x;
			 }
		 else if(amount_x >= 2501 && amount_x <= 5000){
				 
				 commission +=150;
				 float_airtel-=amount_x;
				 cash_initial+=amount_x;
			 }
		else if(amount_x >= 5001 && amount_x <= 15000){
				 commission +=285;
				 float_airtel-=amount_x;
				 cash_initial+=amount_x;
			 }
			 
		 else if(amount_x >= 15001 && amount_x <= 30000){
				 commission +=285;
				 float_airtel-=amount_x;
				 cash_initial+=amount_x;
			 }
		else if(amount_x >= 30001 && amount_x <= 45000){
				 commission +=285;
				 float_airtel-=amount_x;
				 cash_initial+=amount_x;
			 }
		else if(amount_x >= 45001 && amount_x <= 60000){
				 commission +=285;
				 float_airtel-=amount_x;
				 cash_initial+=amount_x;
			 }
		else if(amount_x >= 60001 && amount_x <= 125000){
				 commission += 440;
				 float_airtel-=amount_x;
				 cash_initial+=amount_x;
			 }
		else if(amount_x >= 125001 && amount_x <= 250000){
				 commission +=520;
				 float_airtel-=amount_x;
				 cash_initial+=amount_x;
			 }
		else if(amount_x >= 250001 && amount_x <= 500000){
				 commission +=850;
				 float_airtel-=amount_x;
				 cash_initial+=amount_x;
			 }
		else if(amount_x >= 500001 && amount_x <= 1000000){
				 commission +=2500;
				 float_airtel-=amount_x;
				 cash_initial+=amount_x;
			 }
		else if(amount_x >= 1000001 && amount_x <= 2000000){
				 commission +=4500;
				 float_airtel-=amount_x;
				 cash_initial+=amount_x;
			 }
		else if(amount_x >= 2000001 && amount_x <= 3000000){
				 commission +=8000;
				 float_airtel-=amount_x;
				 cash_initial+=amount_x;
			 }
		else if(amount_x >= 3000001 && amount_x <= 4000000){
				 commission +=8000;
				 float_airtel-=amount_x;
				 cash_initial+=amount_x;
			 }
		else if(amount_x >= 4000001 && amount_x <= 5000000){ 
				 commission +=9000;
			 float_airtel-=amount_x;
			 cash_initial+=amount_x;
			 }
		 else{
			System.out.println("the amount must not exceed 50000");
		 }
		 
		 total = commission+initial_commission;
		 total2 = commission+commissionairtel;
		 try {
				
			 Class.forName("com.mysql.jdbc.Driver");
			 java.sql.Connection co = DriverManager.getConnection("jdbc:mysql://localhost/mawanda?autoReconnect=true&useSSL=false","root","sesnaco");
			 
			 java.sql.PreparedStatement smtp = co.prepareStatement("UPDATE  attedantsregister  SET initialCash = ? , AirtelFloat = ?, commission = ?,commission_airtel=? WHERE username = ? ");
     		 smtp.setInt(1, cash_initial);
     		 smtp.setDouble(2, float_airtel);
     		 smtp.setDouble(3, total);
     		 smtp.setDouble(4, total2);
			 smtp.setString(5, name);
			 
			 int yeth = smtp.executeUpdate();
			 if(yeth ==1){
			 System.out.println("successfully updated your airtel deposit transactions");
			 insert_airtel();
			 }
			 else{
			 						
			 System.out.println("system failure");
			 }

			 }
			 					  
			 catch(SQLException e) {
			 e.printStackTrace();
			 }			   
			}
		 
	private void insert_airtel() throws ClassNotFoundException{
		
		Class.forName("com.mysql.jdbc.Driver");
		java.sql.Connection co;
		try {
			co = DriverManager.getConnection("jdbc:mysql://localhost/mawanda?autoReconnect=true&useSSL=false","root","sesnaco");
			java.sql.PreparedStatement ins2 = co.prepareStatement("INSERT INTO tranfers (ID,KioskNo,Amount,line,username,transaction,date,commission,customerNo) VALUES(null,?,?,?,?,?,?,?,?)");
			 ins2.setString(1,kiosk);
			 ins2.setInt(2,amount_x);
			 ins2.setString(3,airtel_line);
			 ins2.setString(4,name);
			 ins2.setString(5,choose);
			 ins2.setTimestamp(6,date);
			 ins2.setDouble(7,commission);
			 ins2.setString(8,number_from);
			 int okay = ins2.executeUpdate();
			 if(okay ==1){
			 System.out.println("successfully inserted");
			 }
			 else{
			 System.out.println("failed");
			 }
		} 
                catch (SQLException e) {
			
			e.printStackTrace();
		}
		

		
		
		
		
	}

    private void act() throws ClassNotFoundException {
      amount_x = Integer.parseInt(moneys);
	 int cash_initial =initial_cash;
	 double float_mtn = mtn_float;
	  commission =0d;
	 
	 if(amount_x > cash_initial){
		 System.out.println("sorry we dont have enough cash");
	 }
	 else if(amount_x >= 500 && amount_x <= 5500){
			 commission +=100;
			 float_mtn+=amount_x;
			 cash_initial-=amount_x;
		 }
	 else if(amount_x >= 2501 && amount_x <= 5000){
			 
			 commission +=125;
			 float_mtn+=amount_x;
			 cash_initial-=amount_x;
		 }
	else if(amount_x >= 5001 && amount_x <= 15000){
			 commission +=450;
			 float_mtn+=amount_x;
			 cash_initial-=amount_x;
		 }
		 
	 else if(amount_x >= 15001 && amount_x <= 30000){
			 commission +=500;
			 float_mtn+=amount_x;
			 cash_initial-=amount_x;
		 }
	else if(amount_x >= 30001 && amount_x <= 45000){
			 commission +=525;
			 float_mtn+=amount_x;
			 cash_initial-=amount_x;
		 }
	else if(amount_x >= 45001 && amount_x <= 60000){
			 commission +=575;
			 float_mtn+=amount_x;
			 cash_initial-=amount_x;
		 }
	else if(amount_x >= 60001 && amount_x <= 125000){
			 commission += 700;
			 float_mtn+=amount_x;
			 cash_initial-=amount_x;
		 }
	else if(amount_x >= 125001 && amount_x <= 250000){
			 commission +=1300;
			 float_mtn+=amount_x;
			 cash_initial-=amount_x;
		 }
	else if(amount_x >= 250001 && amount_x <= 500000){
			 commission +=2600;
			 float_mtn+=amount_x;
			 cash_initial-=amount_x;
		 }
	else if(amount_x >= 500001 && amount_x <= 1000000){
			 commission +=5000;
			 float_mtn+=amount_x;
			 cash_initial-=amount_x;
		 }
	else if(amount_x >= 1000001 && amount_x <= 2000000){
			 commission +=7500;
			 float_mtn+=amount_x;
			 cash_initial-=amount_x;
		 }
	else if(amount_x >= 2000001 && amount_x <= 4000000){
			 commission +=12500;
			 float_mtn+=amount_x;
			 cash_initial-=amount_x;
		 }
	
	
	 else{
		  
		System.out.println("the amount must not exceed 4000000M");
	 }
	 total = commission+initial_commission;
     total2 = commission+commissionmtn;
	 try {
			
		 Class.forName("com.mysql.jdbc.Driver");
		 java.sql.Connection co = DriverManager.getConnection("jdbc:mysql://localhost/mawanda?autoReconnect=true&useSSL=false","root","sesnaco");
		 
		 java.sql.PreparedStatement smtp = co.prepareStatement("UPDATE  attedantsregister  SET initialCash = ? , MtnFloat = ?, commission =?, commission_mtn =?  WHERE username = ? ");
		 smtp.setInt(1, cash_initial);
		 smtp.setDouble(2, float_mtn);
		 smtp.setDouble(3, total);
		 smtp.setDouble(4, total2);
		 smtp.setString(5, name);
		 
		 int yeth = smtp.executeUpdate();
		 if(yeth==1){
			 System.out.println("successfully updated your mtn withdraw transaction");
			 insert_mtn_service();
		 }
		 else{
			 System.out.println("unresolved error");
		 }
		 
		 }
		 					  
		 catch(SQLException e) {
		 e.printStackTrace();
		 				   
		} 
    }

    private void insert_mtn_service() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
	java.sql.Connection co;
	try {
		co = DriverManager.getConnection("jdbc:mysql://localhost/mawanda?autoReconnect=true&useSSL=false","root","sesnaco");
		java.sql.PreparedStatement ins = co.prepareStatement("INSERT INTO tranfers (ID,KioskNo,Amount,line,username,transaction,date,commission,customerNo) VALUES(null,?,?,?,?,?,?,?,?)");
		 ins.setString(1,kiosk);
		 ins.setInt(2,amount_x);
		 ins.setString(3,mtn_line);
		 ins.setString(4,name);
		 ins.setString(5,choose);
		 ins.setTimestamp(6, date);
		 ins.setDouble(7,commission);
		 ins.setString(8,number_from);
		 int okay = ins.executeUpdate();
		 if(okay ==1){
		 System.out.println("successfully inserted");
                 call_update();
		 }
		 else{
		 System.out.println("failed");
		 }
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	

	
    }

    private void call_update() throws ClassNotFoundException {
         try {
			
		 Class.forName("com.mysql.jdbc.Driver");
		 java.sql.Connection co = DriverManager.getConnection("jdbc:mysql://localhost/mawanda?autoReconnect=true&useSSL=false","root","sesnaco");
		 
		 java.sql.PreparedStatement smtp = co.prepareStatement("UPDATE  service  SET status = ?   WHERE to_agent = ? ");
		 smtp.setString(1, "serviced");
		 smtp.setString(2, name);
		 
		 
		 int yeth = smtp.executeUpdate();
		 if(yeth==1){
			 System.out.println("successfully updated your mtn service withdraw transaction");
			 
		 }
		 else{
			 System.out.println("");
		 }
		 
		 }
		 					  
		 catch(SQLException e) {
		 e.printStackTrace();
		 				   
		} 
    }

    	 
	}

	
			
		
		
	
	
	
	

