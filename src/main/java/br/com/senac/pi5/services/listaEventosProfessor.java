package br.com.senac.pi5.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/listaEventosProfessor")
public class listaEventosProfessor {

    public List<Evento> selectEventoProfessor(String codProfessor) throws Exception {
        Connection conn = null;
        PreparedStatement p = null;
        Evento evt = null;
        List<Evento> resultadoLista = new ArrayList<Evento>();
        
        try {
            conn = Database.get().conn();
                p = conn.prepareStatement(
                    "SELECT [codEvento]" +
                    ",[descricao]" +
                    ",[data]" +
                    ",[codTipoEvento]" +
                    ",[codStatus]" +
                    ",[codProfessor]" +
                    ",[identificador]" +
                    "FROM [senaquiz].[dbo].[Evento]" +
                    "WHERE [codProfessor] = ?");

            p.setString(1, codProfessor);

            ResultSet rs = p.executeQuery();

            while (rs.next()) {
                evt = new Evento();
                
                evt.setCodEvento(rs.getInt("codEvento"));
                evt.setCodProfessor(rs.getInt("codProfessor"));
                evt.setCodStatus(rs.getString("codStatus"));
                evt.setCodTipoEvento(rs.getInt("codTipoEvento"));
                evt.setData(rs.getDate("Data"));
                evt.setDescricao(rs.getString("Descricao"));
                evt.setIdentificador(rs.getString("Identificador"));
                
                resultadoLista.add(evt);
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
        return resultadoLista;
    }

    @GET
    @Path("/{codProfessor}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBuscaProduto(@PathParam("codProfessor") String codProfessor) {
        List<Evento> evt2 = new ArrayList<Evento>();

        try {
            evt2 = selectEventoProfessor(codProfessor);
        } catch (Exception e) {
            return Response.status(500).entity("{\"Erro\":\"Problemas na conexao com o Servidor. Tente novamente!\"}").build();
        }
        if (evt2.size() <= 0) {
            return Response.status(404).entity("{\"Erro\":\"Evento nao encontrado!\"}").build();
        }
        return Response.status(200).entity(evt2).build();
    }
}