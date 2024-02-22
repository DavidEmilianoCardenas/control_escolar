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
            
            System.out.println("Que quieres hacer?\n    0- Salir    \n1- Gestionar Carreras:");
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
        System.out.println("1- Añadir carrera\n2- Actualizar carrera\n3- Ver carreras\n 4-Borrar carrera");
        switch (sc.nextByte()) {
            case 1:
                System.out.println("Que carrera quieres añadir");
                String carr = sc.nextLine();
                InsertData("carreras", carr, con);
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                System.out.println("Selecciona una de las opciones disponibles");
        }
    }
    
    public static void InsertData(String table_name, String name, Connection con)
    {
        try
        {
            String Query = "INSERT INTO " + table_name + " (name) VALUES("
                    + "\"" + name +  "\")";
            
            Statement st = con.createStatement();
            st.executeUpdate(Query);
            JOptionPane.showMessageDialog(null, "Datos almacenados de forma exitosa");
        }catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Datos no han podido ser almacenados");
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
