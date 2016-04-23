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

@Path("/loginProfessor")
public class ProfessorServices {

    public Professor selectProfessor(String email, String senha) throws Exception {
        Connection conn = null;
        PreparedStatement p = null;
        Professor pro = null;

        try {
            conn = Database.get().conn();
            p = conn.prepareStatement(
                    "SELECT TOP 1 [codProfessor], [nome], [email], [senha], [idSenac], [tipo] "
                    + "FROM [dbo].[Professor] "
                    + "WHERE [email] = ? AND [senha] = HASHBYTES('SHA1','" + senha + "')");

            p.setString(1, email);
            //p.setString(2, senha);

            ResultSet rs = p.executeQuery();

            while (rs.next()) {
                pro = new Professor();

                pro.setCodProfessor(rs.getInt("codProfessor"));
                pro.setNome(rs.getString("nome"));
                pro.setEmail(rs.getString("email"));
                pro.setSenha(rs.getString("senha"));
                pro.setIdSenac(rs.getString("idSenac"));
                pro.setTipo(rs.getString("tipo"));
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
        return pro;
    }

    @GET
    @Path("/{loginProfessor}/{senhaProfessor}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProfessor(@PathParam("loginProfessor") String loginProfessor, @PathParam("senhaProfessor") String senhaProfessor) {
        Professor prof = null;

        try {
            prof = selectProfessor(loginProfessor, senhaProfessor);
        } catch (Exception e) {
            return Response.status(500).entity("{\"Erro\":\"Problemas na conexao com o Servidor. Tente novamente!\"}").build();
        }
        if (prof == null) {
            return Response.status(404).entity("{\"Erro\":\"Login ou Senha Incorretos!!\"}").build();
        }
        return Response.status(200).entity(prof).build();
    }
}
