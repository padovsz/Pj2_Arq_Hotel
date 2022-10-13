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
            throw new Exception ("Erro ao procurar candidato");
        }
        return retorno;
    }

    public static void incluir (DBOCandidato candidato) throws Exception
    {
        if (candidato == null)
            throw new Exception("Candidato nao fornecido");

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
            throw new Exception("Erro ao inserir candidato");
        }
    }

        public static void excluir(int numCandidato) throws Exception
        {
            if (!cadastrado (numCandidato))
                throw new Exception ("Nao cadastrado");

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
                throw new Exception ("Erro ao excluir candidato");
            }
        }

    public static void atualizar (DBOCandidato cand) throws Exception
    {
        if (cand==null)
            throw new Exception ("Livro nao fornecido");

        if (!cadastrado (cand.getNumCandidato()))
            throw new Exception ("Nao cadastrado");

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
            throw new Exception ("Erro ao atualizar dados do candidato");
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
                throw new Exception ("Nao cadastrado");

            candidato = new DBOCandidato (resultado.getString("nome_candidato"),
                    resultado.getInt ("idNumero"),
                    resultado.getString ("partido"),
                    resultado.getInt("numCargo"));
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar livro");
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
            throw new Exception ("Erro ao recuperar candidato");
        }

        return resultado;
    }

    public static MeuResultSet getCandidatos () throws Exception
    {
        MeuResultSet resultado = null;

        try
        {
            String sql;

            sql = "SELECT nome_candidato as 'Nome do Candidato', partido, idNumero as 'número', nome_cargo as 'Cargo', UF " +
                    "FROM ELEICOES.CANDIDATO JOIN ELEICOES.CARGO " +
                    "ON IDCARGO = NUMCARGO";

            BDSQLServer.COMANDO.prepareStatement (sql);

            resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao recuperar candidatos");
        }

        return resultado;
    }
}
