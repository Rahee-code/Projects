package Test;

import java.util.Scanner;

import DaoImpl.UserDaoImpl;
import Pojo.UserPojo;

public class User {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserDaoImpl userDaoImpl=new UserDaoImpl();
		Scanner sc=new Scanner(System.in);
		System.out.println("1.Generate pin  2.Login");
		int choice=sc.nextInt();
		if(choice==1)
		{
			int pin=0;
			System.out.println("Enter your name: ");
			String name=sc.next();
			System.out.println("Enter Account Number: ");
			int accNo=sc.nextInt();
			System.out.println("Set new Pin: ");
			int old_pin=sc.nextInt();
			System.out.println("Confirm pin: ");
			int new_pin=sc.nextInt();
			if(old_pin==new_pin)
			{
				pin=new_pin;
			}
			else {
				System.out.println("Pin doesnot match");
			}
			UserPojo userPojo=new UserPojo(accNo, name, pin);
			if(userDaoImpl.register(userPojo))
			{
				System.out.println("register");
			}
			else {
				System.out.println("something went wrong");
			}
		}
		else
		{
			System.out.println("Enter your name: ");
			String name=sc.next();
			System.out.println("Enter Pin: ");
			int pin=sc.nextInt();
			if(userDaoImpl.login(name, pin))
			{
				System.out.println("1.current account  2.Saving account");
				int ch=sc.nextInt();
				if(ch==1)
				{
					System.out.println("current");
				}
				else
				{
					System.out.println("Saving");
				}
			}
			else
			{
				System.out.println("Something went wrong");
			}
		}
		
		

	}

}
