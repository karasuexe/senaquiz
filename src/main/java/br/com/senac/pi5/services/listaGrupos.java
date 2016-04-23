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

@Path("/listaGrupos")
public class listaGrupos {

    public List<GrupoPart> selectGrupos(String codEvento) throws Exception {
        Connection conn = null;
        PreparedStatement p = null;
        GrupoPart gpart = null;
        List<GrupoPart> resultadoLista = new ArrayList<GrupoPart>();
        
        try {
            conn = Database.get().conn();
                p = conn.prepareStatement(
                    "SELECT G.[codGrupo]" +
                    ",[nmGrupo]" +
                    ",[codAssunto]" +
                    ",[codLider]" +
                    ",[finalizado]" +
                    ",P.[codParticipante]" +
                    ",[nmParticipante]" +
                    ",[codCurso]" +
                    ",[email]" +
                    " FROM [senaquiz].[dbo].[Grupo] G" +
                    " INNER JOIN ParticipanteGrupo PG ON PG.codGrupo = G.codGrupo" +
                    " INNER JOIN Participante P ON P.[codParticipante] = PG.codParticipante" +
                    " WHERE codEvento = ? AND P.ativo = 1");

            p.setString(1, codEvento);

            ResultSet rs = p.executeQuery();

            while (rs.next()) {
                gpart = new GrupoPart();
                
                gpart.setCodGrupo(rs.getInt("codGrupo"));
                gpart.setNmGrupo(rs.getString("nmGrupo"));
                gpart.setCodAssunto(rs.getInt("codAssunto"));
                gpart.setCodLider(rs.getInt("codLider"));
                gpart.setFinalizado(rs.getBoolean("finalizado"));
                gpart.setCodParticipante(rs.getInt("codParticipante"));
                gpart.setNmParticipante(rs.getString("nmParticipante"));
                gpart.setCodCurso(rs.getInt("codCurso"));
                gpart.setEmail(rs.getString("email"));
                
                resultadoLista.add(gpart);
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
    @Path("/{codEvento}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBuscaProduto(@PathParam("codEvento") String codEvento) {
        List<GrupoPart> gpart2 = new ArrayList<GrupoPart>();

        try {
            gpart2 = selectGrupos(codEvento);
        } catch (Exception e) {
            return Response.status(500).entity("{\"Erro\":\"Problemas na conexao com o Servidor. Tente novamente!\"}").build();
        }
        if (gpart2.size() <= 0) {
            return Response.status(404).entity("{\"Erro\":\"Grupos nao encontrados para o Evento selecionado!!\"}").build();
        }
        return Response.status(200).entity(gpart2).build();
    }
}