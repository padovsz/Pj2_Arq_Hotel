package bd.daos;

import java.sql.*;
import bd.*;
import bd.core.*;
import bd.dbos.*;

public class DAOCargos {

    public static boolean cadastrado(int idCargo) throws Exception
    {
        boolean retorno = false;

        try {
            String sql;

            sql = "SELECT * " +
                    "FROM ELEICOES.CARGO " +
                    "WHERE IDCARGO = ?";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setInt(1, idCargo);

            MeuResultSet resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();

            retorno = resultado.first();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar candidato");
        }
        return retorno;
    }

    public static void incluir (DBOCargo cargo) throws Exception
    {
        if (cargo == null)
            throw new Exception("Cargo nao fornecido");

        try
        {
            String sql;

            sql = "INSERT INTO ELEICOES.CARGO " +
                    "(idCargo,nome_cargo,UF) " +
                    "VALUES " +
                    "(?,?,?)";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setInt(1, cargo.getIdCardo());
            BDSQLServer.COMANDO.setString(2, cargo.getNomeCargo());
            BDSQLServer.COMANDO.setString(3, cargo.getUF());

            BDSQLServer.COMANDO.executeUpdate();
            BDSQLServer.COMANDO.commit();
        }
        catch (SQLException erro)
        {
            BDSQLServer.COMANDO.rollback();
            throw new Exception("Erro ao inserir cargo");
        }
    }

    public static void excluir(int idCargo) throws Exception
    {
        if (!cadastrado (idCargo))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "DELETE FROM ELEICOES.CARGO " +
                    "WHERE IDCARGO=?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt (1, idCargo);

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();        }
        catch (SQLException erro)
        {
            BDSQLServer.COMANDO.rollback();
            throw new Exception ("Erro ao excluir cargo");
        }
    }

    public static void atualizar (DBOCargo cargo) throws Exception
    {
        if (cargo==null)
            throw new Exception ("Cargo nao fornecido");

        if (!cadastrado (cargo.getIdCardo()))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "UPDATE ELEICOES.CARGO " +
                    "SET nome_cargo =?, " +
                    "UF =?, " +
                    "WHERE idCargo = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString (1, cargo.getNomeCargo().trim());
            BDSQLServer.COMANDO.setString (2, cargo.getUF().trim());
            BDSQLServer.COMANDO.setInt    (3, cargo.getIdCardo());

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            BDSQLServer.COMANDO.rollback();
            throw new Exception ("Erro ao atualizar dados do cargo");
        }
    }

    public static DBOCargo getCargo(int idCargo) throws Exception
    {
        DBOCargo retorno = null;

        try {
            String sql;

            sql = "SELECT * " +
                    "FROM ELEICOES.CARGO " +
                    "WHERE IDCARGO = ?";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setInt(1, idCargo);

            MeuResultSet resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();

            if(!resultado.first())
                throw new Exception("Não cadastrado!");

            retorno = new DBOCargo(resultado.getString("nome_cargo"),
                    resultado.getInt("idCargo"),
                    resultado.getString("UF"));
        }
        catch (SQLException err)
        {
            throw new Exception("Erro ao procurar cargo");
        }

        return retorno;
    }

    public static MeuResultSet getCargos() throws Exception
    {
        MeuResultSet retorno = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                    "FROM ELEICOES.CARGO ";

            BDSQLServer.COMANDO.prepareStatement(sql);

            retorno = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
        }
        catch (SQLException err)
        {
            throw new Exception("Erro ao recuperar cargos");
        }

        return retorno;
    }
}
