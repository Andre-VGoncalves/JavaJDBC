package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Cliente;

public class ClienteDAO {
    private DataSource dataSource;
    
    public ClienteDAO(DataSource dataSource){
        this.dataSource = dataSource;
    }
    
    public ArrayList<Cliente>readAll(){
        try{
            String SQL = "SELECT * from Cliente";
            PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            
            ArrayList<Cliente> lista = new ArrayList<Cliente>();
            while(rs.next()){
                Cliente cli = new Cliente();
                cli.setId(rs.getInt("id"));
                cli.setNome(rs.getString("nome"));
                cli.setEmail(rs.getString("email"));
                cli.setTelefone(rs.getString("Telefone"));
                lista.add(cli);
                
            }
            ps.close();
            return lista;
        }
        catch(SQLException ex){
            System.err.println("Erro ao recuperar "+ex.getMessage());
        }
        return null;
    }
    
	public void insert(Cliente entidade)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		Connection conn = null;
		try {
                        conn= dataSource.getConnection();
                      
			
			PreparedStatement statement = DataSource.getPreparedStatement(conn,
					"INSERT INTO pessoas (nome) VALUES (?)");
			statement.setString(1, entidade.getNome());
			
			statement.execute();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
        
        public void delete(Cliente entidade)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement statement = dataSource.getPreparedStatement(conn, "DELETE FROM pessoas WHERE id = ?");
			statement.setInt(1, entidade.getId());
			statement.execute();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

	}

}
