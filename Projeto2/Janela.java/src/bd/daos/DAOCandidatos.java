package bd.daos;

import java.sql.*;
import bd.*;
import bd.core.*;
import bd.dbos.*;

public class DAOCandidatos
{
    public static boolean cadastrado(int numCandidato) throws Exception
    {
        boolean retorno = false;

        try {
            String sql;

            sql = "SELECT * " +
                    "FROM ELEICOES.CANDIDATO " +
                    "WHERE IDNUMERO = ?";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setInt(1, numCandidato);

            MeuResultSet resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();

            retorno = resultado.first();
        }
        catch (SQLException erro)
        {
            throw new Exception ("[ERRO]: Problemas ao acessar o Banco de Dados para consultar candidatos!");
        }
        return retorno;
    }

    public static void incluir (DBOCandidato candidato) throws Exception
    {
        if (candidato == null)
            throw new Exception("[ERRO]: Candidato não fornecido!");

        try
        {
            String sql;

            sql = "INSERT INTO ELEICOES.CANDIDATO " +
                    "(nome_candidato,idNumero,partido,numCargo) " +
                    "VALUES " +
                    "(?,?,?,?)";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setString(1, candidato.getNomeCandidato());
            BDSQLServer.COMANDO.setInt(2, candidato.getNumCandidato());
            BDSQLServer.COMANDO.setString(3, candidato.getPartido());
            BDSQLServer.COMANDO.setInt(4, candidato.getNumCargo());

            BDSQLServer.COMANDO.executeUpdate();
            BDSQLServer.COMANDO.commit();
        }
        catch (SQLException erro)
        {
            BDSQLServer.COMANDO.rollback();
            throw new Exception("[ERRO]: Problemas ao acessar o Banco de Dados para inserir candidatos!");
        }
    }

        public static void excluir(int numCandidato) throws Exception
        {
            if (!cadastrado (numCandidato))
                throw new Exception ("[ERRO]: Candidato não cadastrado");

            try
            {
                String sql;

                sql = "DELETE FROM ELEICOES.CANDIDATO " +
                        "WHERE IDNUMERO=?";

                BDSQLServer.COMANDO.prepareStatement (sql);

                BDSQLServer.COMANDO.setInt (1, numCandidato);

                BDSQLServer.COMANDO.executeUpdate ();
                BDSQLServer.COMANDO.commit        ();        }
            catch (SQLException erro)
            {
                BDSQLServer.COMANDO.rollback();
                throw new Exception ("[ERRO]: Problemas ao acessar o Banco de Dados para excluir candidatos!");
            }
        }

    public static void atualizar (DBOCandidato cand) throws Exception
    {
        if (cand==null)
            throw new Exception ("[ERRO]: Candidato não fornecido");

        if (!cadastrado (cand.getNumCandidato()))
            throw new Exception ("[ERRO]: Candidato não cadastrado");

        try
        {
            String sql;

            sql = "UPDATE ELEICOES.CANDIDATO " +
                    "SET nome_candidato =?, " +
                    "partido =?, " +
                    "numCargo  =? " +
                    "WHERE idNumero  = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString (1, cand.getNomeCandidato ().trim());
            BDSQLServer.COMANDO.setInt    (4, cand.getNumCandidato ());
            BDSQLServer.COMANDO.setString (2, cand.getPartido ().trim());
            BDSQLServer.COMANDO.setInt    (3, cand.getNumCargo ());

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            BDSQLServer.COMANDO.rollback();
            throw new Exception ("[ERRO]: Problemas ao acessar o Banco de Dados para atualizar candidatos!");
        }
    }

    public static DBOCandidato getCandidato (int idNum) throws Exception
    {
        DBOCandidato candidato = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                    "FROM ELEICOES.CANDIDATO " +
                    "WHERE idNumero  = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt (1, idNum);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

            if (!resultado.first())
                throw new Exception ("[ERRO]: Candidato não cadastrado");

            candidato = new DBOCandidato (resultado.getString("nome_candidato"),
                    resultado.getInt ("idNumero"),
                    resultado.getString ("partido"),
                    resultado.getInt("numCargo"));
        }
        catch (SQLException erro)
        {
            throw new Exception ("[ERRO]: Problemas ao acessar o Banco de Dados para consultar candidatos!");
        }

        return candidato;
    }

    public static MeuResultSet getCandidatos (String nomeCargo, String UF) throws Exception
    {
        MeuResultSet resultado = null;

        try
        {
            String sql;

            sql = "SELECT E.nome_candidato as 'Nome do Candidato', e.partido, e.idNumero as 'número', c.nome_cargo as 'Cargo', c.UF " +
                    "FROM ELEICOES.CANDIDATO E JOIN ELEICOES.CARGO C" +
                    "ON C.IDCARGO = E.NUMCARGO" +
                    "WHERE C.NOME_CARGO = ? AND" +
                    "C.UF = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);
            BDSQLServer.COMANDO.setString (1, nomeCargo.trim());
            BDSQLServer.COMANDO.setString (2, UF.trim());

            resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("[ERRO]: Problemas ao acessar o Banco de Dados para recuperar candidatos!");
        }

        return resultado;
    }

    public static MeuResultSet getCandidatos () throws Exception
    {
        MeuResultSet resultado = null;

        try
        {
            String sql;

            sql = "SELECT E.nome_candidato as 'Nome do Candidato', e.partido, e.idNumero as 'número', c.nome_cargo as 'Cargo', c.UF " +
                    "FROM ELEICOES.CANDIDATO E JOIN ELEICOES.CARGO C " +
                    "ON C.IDCARGO = E.NUMCARGO" ;

            BDSQLServer.COMANDO.prepareStatement (sql);

            resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("[ERRO]: Problemas ao acessar o Banco de Dados para recuperar candidatos!");
        }

        return resultado;
    }

    public static Object[][] getTabelaCandidatos () throws Exception
    {
        Object[][] candidatos = new Object[30][5];

        try
        {
            String sql;

            sql = "SELECT * FROM ELEICOES.CANDIDATO";

            BDSQLServer.COMANDO.prepareStatement (sql);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

            resultado.first();
            int candidatoAtual = 0;
            while(!resultado.isAfterLast()){
                int registroAtual  = 0;
                int numCargo  = resultado.getInt("numCargo");

                String sql2 = "SELECT nome_cargo, UF " +
                        "FROM Eleicoes.Cargo " +
                        "WHERE idCargo = ?";

                BDSQLServer.COMANDO.prepareStatement (sql2);
                BDSQLServer.COMANDO.setInt (1, numCargo);

                MeuResultSet resultadoFinal = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();


                candidatos[candidatoAtual][registroAtual++] = resultado.getString("nome_candidato");
                candidatos[candidatoAtual][registroAtual++] = resultado.getString("partido");
                candidatos[candidatoAtual][registroAtual++] = resultado.getString("idNumero");
                candidatos[candidatoAtual][registroAtual++] = resultadoFinal.getString("nome_cargo");
                candidatos[candidatoAtual][registroAtual]   = resultadoFinal.getString("UF");

                resultado.next();   //para ir ao proximo
            }
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao recuperar candidatos");
        }

        return candidatos;
    }
}
