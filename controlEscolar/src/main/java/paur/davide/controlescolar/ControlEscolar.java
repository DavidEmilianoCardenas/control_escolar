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
import javax.swing.JOptionPane;

/**
 *
 * @author mati
 */
public class ControlEscolar {

    public static void main(String[] args) {
        
            
            try {
                Properties props = new Properties();
                props.load(new FileInputStream("config/db.properties"));
                String username = props.getProperty("user");
                String password = props.getProperty("password");
                String bd_name = props.getProperty("bd");
                
                String host = "jdbc:mysql://localhost/bd1";
                 try {
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    Connection conexion = DriverManager.getConnection(host, username, password);

                    closeConnection(conexion);
                } catch (SQLException | ClassNotFoundException ex) {
                    System.out.println("Error en la conexion de la base de datos");
                }
            } catch (Exception e) {
                e.printStackTrace();
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
