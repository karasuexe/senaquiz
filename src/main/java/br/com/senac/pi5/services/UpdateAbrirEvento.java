package br.com.senac.pi5.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/abrirEvento")
public class UpdateAbrirEvento {

    public boolean updateStatusEvento(int codEvento) throws SQLException {
        Connection conn = null;
        PreparedStatement p = null;
        boolean EventoAberto = false;
        
        try {
            conn = Database.get().conn();
            p = conn.prepareStatement(
                "UPDATE Evento SET codStatus = 'A' WHERE codStatus = 'C' AND codEvento = ?");

            p.setInt(1, codEvento);

            int atualiza = p.executeUpdate();

            if (atualiza > 0) {
                EventoAberto = true;
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
        return EventoAberto;
    }

    @GET
    @Path("/{codEvento}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizaEvento(@PathParam("codEvento") int codEvento) throws SQLException {

        try {
            if(updateStatusEvento(codEvento)){
                //TRUE: Evento Atualizado com Sucesso
                return Response.status(200).entity("{\"Status\":\"Evento marcado como Aberto!\"}").build();
            } else {
                //FALSE: Erro atualizando Evento
                return Response.status(404).entity("{\"Status\":\"Erro! Verifique se o Código do Evento está correto e se o mesmo já não encontra-se aberto.\"}").build();
            }
        } catch (Exception e) {
            return Response.status(500).entity("{\"Erro\":\"Problemas na conexao com o Servidor. Tente novamente!\"}").build();
        }
    }
}