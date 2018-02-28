/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Profesor
 */
public class ManejadorBd {
    private String driver;
    private String url;
    private String usuario;
    private String password;

    public ManejadorBd(String driver, String url, String usuario, String password) {
        this.driver = driver;
        this.url = url;
        this.usuario = usuario;
        this.password = password;
    }
    
    private Connection conectar(){
        Connection cn;
        try {
            Class.forName(driver);
            cn=DriverManager.getConnection(url, usuario, password);
        } catch (ClassNotFoundException ex) {
            cn=null;
        } catch (SQLException ex) {
            cn=null;
        }
        return cn;    
    }

    public boolean valida (String usuario, String password) 
            throws SQLException{
        Connection cn=conectar();
        if (cn!=null){
            Statement st = cn.createStatement();
            String sql="select * from usuarios where usuario='"+usuario
                    +"' and password='"+password+"'";
            ResultSet rs=st.executeQuery(sql);
            return rs.next();
        }else{
            throw new SQLException();
        }
    }
}
