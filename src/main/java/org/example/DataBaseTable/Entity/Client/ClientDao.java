package org.example.DataBaseTable.Entity.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientDao {

    private static final String CREATE_CLIENT = "INSERT INTO client (NAME) values(?)";
    private static final String UPDATE_NAME_CLIENT = "UPDATE client SET name = ? WHERE id = ?";
    private static final String DELETE_CLIENT = "DELETE FROM client WHERE id = ?";
    private static final String SELECT_СLIENT = "SELECT id, name FROM client WHERE id = ?";
    private static final String SELECT_ID_CLIENT = "SELECT id FROM client WHERE name = ?";
    private static final String SELECT_ALL_CLIENT = "SELECT * FROM client";

    PreparedStatement insertClientStatement;
    PreparedStatement updateNameClient;
    PreparedStatement deleteClient;
    PreparedStatement selectClient;
    PreparedStatement selectIDClient;
    PreparedStatement selectAllClient;



    public ClientDao(Connection connection) {
        try {
            this.insertClientStatement = connection.prepareStatement(CREATE_CLIENT, PreparedStatement.RETURN_GENERATED_KEYS);
            this.updateNameClient = connection.prepareStatement(UPDATE_NAME_CLIENT);
            this.deleteClient = connection.prepareStatement(DELETE_CLIENT);

            this.selectClient = connection.prepareStatement(SELECT_СLIENT);
            this.selectIDClient = connection.prepareStatement(SELECT_ID_CLIENT);
            this.selectAllClient = connection.prepareStatement(SELECT_ALL_CLIENT);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

   public long create(String name){
            try {
                this.insertClientStatement.setString(1, name);
                this.insertClientStatement.executeUpdate();

                try(ResultSet resultSet = this.insertClientStatement.getGeneratedKeys()) {
                    if(resultSet.next()){
                        return resultSet.getInt("id");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return -1;
    }

    public Optional<Client> getById(long id){
        try {
            this.selectClient.setLong(1, id);

            try(ResultSet resultSet = selectClient.executeQuery()){
                if(resultSet.next()) {
                    Client client = new Client(resultSet.getLong("id"), resultSet.getString("name"));
                    return Optional.of(client);
                }
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    public void setName(long id, String name){
        try {
            this.updateNameClient.setLong(2, id);
            this.updateNameClient.setString(1, name);
            this.updateNameClient.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(long id){
      try{
          this.deleteClient.setLong(1, id);
          this.deleteClient.executeUpdate();
      }catch (Exception e){
          throw new RuntimeException();
      }
    }

   public List<Client> listAll(){
        try(ResultSet resultSet = this.selectAllClient.executeQuery()) {
             List<Client> clients = new ArrayList<>();
             while (resultSet.next()){
                 Client client = new Client(resultSet.getLong("id"), resultSet.getString("name"));
                 clients.add(client);
             }
             return clients;
        }catch (Exception e){
            throw new RuntimeException();
        }
   }
}

