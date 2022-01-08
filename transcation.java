package teja;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class test {
public static void balance(int balance)
	{System.out.println("Balance = "+balance);}
public static int transfer1(int Accnt_No ,int balance,int x,int t){
		{System.out.println("transfering amount = "+ x + " from account " + Accnt_No + " to account " + t);
				balance(balance);
				System.out.println("Transfer successfull!");
				}
		return 0;
		}
	public static void main(String[] args) {
		String url ="jdbc:mysql://localhost:3306/atmdb";
		String username = "root";
		String password ="Teja@447";
		    try {
		    	Connection connection =DriverManager.getConnection(url, username, password);
		    	Scanner s = new Scanner(System.in);
		    	System.out.println("enter account number:");
		    	int a = s.nextInt();
		    	//int p = 0 ;
				String sql ="select Name,Accnt_No,Balance from atm_data where Accnt_No="+a;
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(sql);
				//String sql2 = "select Balance from atm_data";
				//Statement statement1 = connection.createStatement();
				//ResultSet rs = statement1.executeQuery(sql2);
				//rs.next();
				//test p = new test();
				//p.transfer1();
				//ResultSet result1 = statement.executeQuery("select Balance from atm_data where"+a);
				//int p = result1.getInt("balance");
				//p=result.getInt("Balance");
				//ResultSet result = statement.executeQuery(sql);
				//ResultSet result = statement.executeQuery(sql);
				//ResultSet result1= statement.executeUpdate(sql1);
				//int count=0;
				int x = 0;
				//int a = s.nextInt();
				while(result.next()) {
					String name = result.getString("name");
					int balance = result.getInt("balance");
					int Accnt_No = result.getInt("Accnt_No");
					//Scanner s = new Scanner(System.in);
					//count++;
					//System.out.println("enter account number:");
					//int a = s.nextInt();
					//s.nextInt();
					System.out.println("Accnt No " + Accnt_No + " : " +name);
					balance(balance);
					System.out.println("enter amount to transfer:");
					Scanner s2 = new Scanner(System.in);
					x = s2.nextInt();
					//balance=transfer1(Accnt_No,balance,x,p);
					//statement.executeUpdate(sql);
					//System.out.println("Choose accounts to transfer amount to:");
					//AccntNo = s.nextInt();
					PreparedStatement updatebalance = connection.prepareStatement(
			                "UPDATE atm_data SET Balance = (Balance - ?) WHERE Accnt_No=?");
			        updatebalance.setInt(1,x);
			        updatebalance.setInt(2,a);
			        updatebalance.executeUpdate();
				}
				Scanner s1 = new Scanner(System.in);
		    	System.out.println("enter account number to transfer amount " + x + " to: ");
		    	int t = s1.nextInt();
		    	//int q = 0;
				ResultSet result1 = statement.executeQuery(sql);
				while(result1.next()) {
					//String name = result1.getString("name");
					int balance = result1.getInt("balance");
					int Accnt_No = result1.getInt("Accnt_No");
				//System.out.println("Accnt No " + Accnt_No +":"+name);
				//balance(balance);
				balance=transfer1(Accnt_No,balance,x,t);
				PreparedStatement updatebalance1 = connection.prepareStatement(
		                "UPDATE atm_data SET Balance = (Balance + ?) WHERE Accnt_No=?");
		        updatebalance1.setInt(1,x);
		        updatebalance1.setInt(2,t);
		        updatebalance1.executeUpdate();
				}
				connection.close();
				} 
			 catch (SQLException e) {
				System.out.println("oops, error!");
				e.printStackTrace();
			}
	}
}