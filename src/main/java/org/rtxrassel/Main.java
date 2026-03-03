package org.rtxrassel;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.Scanner;



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
                System.out.print("Choose Or Option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch(choice){
                    case 1:
                        reserveRoom(con,scanner);
                        break;
                    case 2:
                        viewReservations(con);
                        break;
                    case 3:
                        getRoomNumber(con,scanner);
                        break;
                    case 4:
                        UpdateReservation(con,scanner);
                        break;
                    case 5:
                        deleteResevation(con,scanner);
                        break;
                    case 0:
                        exiting();
                        scanner.close();
                        con.close();
                        return;
                    default:
                        System.out.println("invalid option");
                }
            }
        }catch (SQLException | InterruptedException e){
            System.out.println(e.getMessage());
        }

    }

    public static void reserveRoom(Connection connection,Scanner scanner){
        System.out.print("Enter Guest Name: ");
        String guestName = scanner.nextLine();
        System.out.print("Enter room Number: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Your Contact Numebr: ");
        String contactNumber = scanner.nextLine();

        String insertData = "INSERT INTO reservations (guest_name, room_number, contact_number) " +
                "VALUES ('" + guestName + "', " + roomNumber + ", '" + contactNumber + "')";
        try {
            Statement stmt = connection.createStatement();
            int rowAffected = stmt.executeUpdate(insertData);
            if (rowAffected>0) System.out.println("Data insert Successfully");
            else System.out.println("Data insert failed?");
//            stmt.close();
//            connection.close();
        }catch (SQLException e){
            System.out.println("Rassel Hassan data insert hoi nai"+e.getMessage());
        }

    }

    public static void viewReservations(Connection connection) throws SQLException{

        String fatchData = "SELECT reservation_id, guest_name, room_number, contact_number, reservation_date FROM reservations";

        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(fatchData);


            System.out.println("+----------------+----------------------+--------------+-----------------+-----------------------+");
            System.out.println("| Reservation ID | Guest Name           | Room Number  | Contact Number  | Date                  |");
            System.out.println("+----------------+----------------------+--------------+-----------------+-----------------------+");


            while (resultSet.next()){
                int id = resultSet.getInt("reservation_id");
                String name = resultSet.getString("guest_name");
                int room = resultSet.getInt("room_number");
                String contact = resultSet.getString("contact_number");
                String time = resultSet.getTimestamp("reservation_date").toString();

                System.out.printf("| %-14d | %-20s | %-12d | %-15s | %-21s |\n",
                        id, name, room, contact, time);
            }

            System.out.println("+----------------+----------------------+--------------+-----------------+-----------------------+");
//            stmt.close();
//            connection.close();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }


    public static void getRoomNumber(Connection connection, Scanner scanner){
        System.out.print("Enter Your ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Your Name: ");
        String name = scanner.nextLine();

        String query = "SELECT room_number FROM reservations " +
                "WHERE reservation_id = " + id + " AND guest_name = '" + name + "'";

        try {
            Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery(query);

            if (result.next()){
                int romm = result.getInt("room_number");
                System.out.println("Room Number for Reservation ID: "+id +" And Guest Name: "+name+ " is: "+romm +" Number");
            }

//            stmt.close();
//            connection.close();
        }catch (SQLException e){
            System.out.println("Failed??"+e.getMessage());
        }

    }


    public static void UpdateReservation(Connection connection, Scanner scanner){
        System.out.print("Enter Your Reservation ID To Update:");
        int id = scanner.nextInt();
        scanner.nextLine();

//        if (!reservationExits(connection, id)) {
//            System.out.println("Reservation not found for the given ID.");
//            return;
//        }

        System.out.print("Enter Your Reservation Name to Update: ");
        String name = scanner.nextLine();
        System.out.print("Enter Your Reservation Room to Update: ");
        int room = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Your Reservation Contact to Update: ");
        String contact = scanner.nextLine();

        String query = "UPDATE reservations SET guest_name = '" + name + "', room_number = "+ room +", contact_number = '" + contact + "' WHERE reservation_id = " +id;

        try {
            Statement stmt = connection.createStatement();
            int result = stmt.executeUpdate(query);
            if (result>0) System.out.println("Data Update Successfully");
            else System.out.println("Update failed");

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }


    }
 public static void deleteResevation(Connection connection, Scanner scanner){

     System.out.print("Delete Reservation ID: ");
     int id = scanner.nextInt();

//     if (!reservationExits(connection,id)){
//         System.out.println("Reservation id not found ");
//         return;
//     }

     String query = "DELETE FROM reservations WHERE reservation_id = "+id;
     try {
         Statement stmt = connection.createStatement();
         int delete = stmt.executeUpdate(query);

         if (delete>0) System.out.println("Delete Successfully");
         else System.out.println("delete failed");

     }catch (SQLException e){
         System.out.println(e.getMessage());
     }
 }

 public static boolean reservationExits(Connection connection, int id){
     String query = "DELETE FROM reservations WHERE reservation_id = "+id;
     try(Statement stmt = connection.createStatement()) {
         ResultSet resultSet = stmt.executeQuery(query);
        return  resultSet.next();
     }catch (SQLException e){
         System.out.println(e.getMessage());
         return false;
     }
 }

    public static void exiting() throws InterruptedException{
        System.out.print("Exitting System");
        int i =5;
        while (i!=0){
            System.out.print(".");
            try {
                Thread.sleep(350);
                i--;
            }catch (InterruptedException e){
                System.out.println(e.getMessage());
            }


        }
        System.out.println();
        System.out.println("Thanks: ");
    }

}
