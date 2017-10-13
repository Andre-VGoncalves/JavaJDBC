package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataSource {

    static PreparedStatement getPreparedStatement(Connection conn, String insert_into_pessoas_nomeidade_VALUES_) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private String hostname;
    private int port;
    private String database;
    private String username;
    private String password;
    private Connection connection;
    
    public DataSource(){
        try{
            hostname = "localhost";
            port     = 3306;
            database = "cloudbd";
            username = "root";
            password = "";
        
            String url = "jdbc:mysql://"+hostname+":"+port+"/"+database;
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connection = (Connection) DriverManager.getConnection(url, username, password);
            System.out.println("Deu tudo certo, Palmas para o JDBC!");
        }
        
        
        catch(SQLException ex){
            System.err.println("Erro na conexão: "+ex.getMessage());
        }
        catch(Exception ex){
            System.err.println("Erro geral: "+ex.getMessage());
        }
        
    }
    public Connection getConnection(){
            return this.connection;
    }
    public void closeConnection(){
        try{
            connection.close();
        }catch(Exception ex){
            System.err.println("Erro ao desconectar: "+ex.getMessage());
        }
    }
}
