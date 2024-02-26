/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package paur.davide.controlescolar;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author mati
 */
public class ControlEscolar {

    public static void main(String[] args) {

        Connection conexion = getConnection();
        
        menu(conexion);
        

        closeConnection(conexion);
    }
    
    public static Connection getConnection()
    {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("config/db.properties"));
            String username = props.getProperty("username");
            String password = props.getProperty("password");
            String bd_name = props.getProperty("bd");

            String host = "jdbc:mysql://localhost/control_escolar";
             try {
                Class.forName("com.mysql.cj.jdbc.Driver");

                Connection conexion = DriverManager.getConnection(host, username, password);

                return conexion;
            } catch (SQLException | ClassNotFoundException ex) {
                System.out.println("Error en la conexion de la base de datos");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }

    public static void menu (Connection con) {
        Scanner sc = new Scanner(System.in);
        boolean correctInput = false;
        while (!correctInput) {
            System.out.println("Que quieres hacer?\n    0- Salir    \n    1- Gestionar Carreras:");
            switch (sc.nextByte()) {
                case 0:
                    correctInput = true;
                    break;
                case 1:
                    menuCarrera(sc, con);
                    break;
                default:
                    System.out.println("Elige una de las opciones disponibles");
                    break;
            }
        }
        sc.close();
    }

    public static void menuCarrera (Scanner sc, Connection con) {
        System.out.println("1- Añadir carrera\n2- Actualizar carrera\n3- Ver carreras\n4-Borrar carrera");
        switch (sc.nextByte()) {
            case 1:
                sc.nextLine();
                System.out.println("Que carrera quieres añadir");
                String carr = sc.nextLine();
                InsertData("carreras", carr, con);
                break;
            case 2:
                sc.nextLine();
                System.out.println("Que carrera quiere actualizar (Seleccione un numero)");
                getValues(con,"carreras");
                int idCarrera = sc.nextInt();
                sc.nextLine();
                System.out.println("Elige el nuevo nombre de la carrera:");
                String newName = sc.nextLine();
                updateData("carreras",idCarrera,newName,con);
                break;
            case 3:
                sc.nextLine();
                getValues(con, "carreras");
                break;
            case 4:
                sc.nextLine();
                System.out.println("Que carrera quieres borrar");
                getValues(con, "carreras");
                carr = sc.nextLine();
                deleteCarrera("carreras", carr, con);
                break;
            default:
                System.out.println("Selecciona una de las opciones disponibles");
        }
    }
    
    public static void InsertData(String table_name, String name, Connection con)
    {
        try
        {
            String Query = "INSERT INTO " + table_name + " (nombre) VALUES("
                    + "\"" + name +  "\")";
            
            Statement st = con.createStatement();
            System.out.println(Query);
            st.executeUpdate(Query);
        }catch (SQLException ex)
        {
            System.out.println(ex);
        }
    }

    public static void updateData (String table_name, int id,String newName, Connection con) {
        try
        {
            System.out.println("Entra al try");
            String Query = "UPDATE " + table_name +
                    " SET nombre = " +
                    "\'" + newName +  "\' " +
                    "WHERE id = " + id ;

            Statement st = con.createStatement();
            System.out.println(Query);
            st.executeUpdate(Query);
        }catch (SQLException ex)
        {
            System.out.println(ex);
        }
    }


    public static void getValues(Connection conn, String table_name)
    {
        try
        {
            Connection conexion = conn;
            String Query = "SELECT * FROM " + table_name;
            Statement st = conexion.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);
            
            while(resultSet.next())
            {
                System.out.println("ID: " + resultSet.getString("id") + " | " +
                    " Nombre: " + resultSet.getString("nombre"));
            }
        }catch (SQLException ex)
        {
            System.out.println("Error en la adquisición de datos");
        }
    }

    public static void deleteCarrera(String table_name, String nombre, Connection con)
    {
        try
        {
            String Query = "DELETE FROM " + table_name + " WHERE nombre = \"" + nombre + "\"";
            Statement st = con.createStatement();
            st.executeUpdate(Query);
        }catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error borrando el registro especificado");
        }
    }
    
    public static void closeConnection(Connection con)
    {
        try
        {
            con.close();
            JOptionPane.showMessageDialog(null, "Se ha finalizado la conexión con el servidor");
        }catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "No se ha podido finalizar la conexión con el servidor");

        }
    }
}
