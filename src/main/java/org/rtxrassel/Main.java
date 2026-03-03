package org.rtxrassel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Stack;


public class Main {


    public static void main(String[] args) throws ClassNotFoundException, SQLException {

       String  url ="jdbc:mysql://localhost:3306/Hotel_DB";
         String  userName ="root";
        String  pass  ="Rassel1122#";

        try {
            Connection con = DriverManager.getConnection(url,userName,pass);
            Statement stmt = con.createStatement();
            System.out.println("connection succesfull");
            while (true){
                System.out.println("");
                System.out.println("Hotem Management System:");
                Scanner scanner = new Scanner(System.in);
                System.out.println("1. Reserve a room");
                System.out.println("2. View Reservations");
                System.out.println("3. Get Room Number");
                System.out.println("4. Update Reservations");
                System.out.println("5. Delete Reservations");
                System.out.println("0. exit");
                System.out.println("Choose Or Option");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public static void reserveRoom(Connection connection,Scanner scanner){
        System.out.print("Enter Guest Name: ");
        String guestName = scanner.next();
        scanner.nextLine();
        System.out.print("Enter room Number: ");
        int roomNumber = scanner.nextInt();
        System.out.print("Enter Your Contact Numebr: ");
        String contactNumber = scanner.next();


    }
}
