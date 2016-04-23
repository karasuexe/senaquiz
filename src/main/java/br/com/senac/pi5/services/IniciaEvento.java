package br.com.senac.pi5.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/iniciaEvento")
public class IniciaEvento {

    public boolean updateIniciaEvento(int codEvento) throws SQLException {
        Connection conn = null;
        PreparedStatement p = null;
        boolean executaEvento = false; 

        //testa a quantidade de grupos formados no evento antes de fazer o UPDATE
        if (getQtdeGrupos(codEvento) < 2){
            executaEvento = false;
        } else {        
            try {
                conn = Database.get().conn();
                p = conn.prepareStatement(
                    "UPDATE Evento SET codStatus = 'E' WHERE codEvento = ?");

                p.setInt(1, codEvento);

                int atualiza = p.executeUpdate();

                if (atualiza > 0) {
                    executaEvento = true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (p != null) {
                    p.close();
                }
                if (conn != null) {
                    conn.close();
                }
            }
        }
        return executaEvento;
    }

    @GET
    @Path("/{codEvento}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response executaEvento(@PathParam("codEvento") int codEvento) throws SQLException {
        boolean execucao = false;
        
        try {
            execucao = updateIniciaEvento(codEvento);
        } catch (Exception e) {
            return Response.status(500).entity("{\"Erro\":\"Problemas na conexao com o Servidor. Tente novamente!\"}").build();
        }
        
        if (execucao == false) {
            return Response.status(404).entity("{\"Erro\":\"Verifique o Código do Evento e o número de Grupos e tente novamente!\"}").build();
        } else {
            return Response.status(200).entity("{\"Status\":\"Evento Iniciado com Sucesso!\"}").build();
        }
    }

    public int getQtdeGrupos(int codEvento) throws SQLException {
        Connection dbConn = null;
        PreparedStatement p2 = null;
        int numeroGrupos = 0;

        try {
            dbConn = Database.get().conn();
            String query = "SELECT COUNT(*) FROM Grupo WHERE codEvento = ?";
            p2 = dbConn.prepareStatement(query);

            p2.setInt(1, codEvento);

            ResultSet rs = p2.executeQuery();
            
            if (rs.next()) {
              numeroGrupos = rs.getInt(1);
            } else { 
              numeroGrupos = 0; 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (p2 != null) {
                p2.close();
            }
            if (dbConn != null) {
                dbConn.close();
            }
        }
        return numeroGrupos;
    }
}
