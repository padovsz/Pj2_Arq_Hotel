package bd.daos;

import java.sql.*;
import bd.*;
import bd.core.*;
import bd.dbos.*;

public class DAOHoteis
{
    public static boolean cadastrado(String numero, String cep) throws Exception
    {
        boolean retorno = false;

        try {
            String sql;

            sql = "SELECT * " +
                    "FROM Hoteis.Hotel " +
                    "WHERE numero = ? " +
                    "AND cep = ?";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setString(1, numero);
            BDSQLServer.COMANDO.setString(2, cep);

            MeuResultSet resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();

            retorno = resultado.first();
        }
        catch (SQLException erro)
        {
            throw new Exception ("[ERRO]: Problemas ao acessar o Banco de Dados para consultar hoteis!");
        }
        return retorno;
    }

    public static void incluir(DBOHotel hotel) throws Exception
    {
        if (hotel == null)
            throw new Exception("[ERRO]: Hotel não fornecido!");

        try
        {
            String sql;

            sql = "INSERT INTO Hoteis.Hotel " +
                    "(nome,cep,numero,complemento,telefone) " +
                    "VALUES " +
                    "(?,?,?,?,?)";

            BDSQLServer.COMANDO.prepareStatement(sql);

            BDSQLServer.COMANDO.setString(1, hotel.getNomeHotel());
            BDSQLServer.COMANDO.setString(2, ((Integer) hotel.getCEP()).toString());
            BDSQLServer.COMANDO.setString(3, ((Integer) hotel.getNumero()).toString());
            BDSQLServer.COMANDO.setString(4, hotel.getComplemento());
            BDSQLServer.COMANDO.setString(5, hotel.getTelefone());

            BDSQLServer.COMANDO.executeUpdate();
            BDSQLServer.COMANDO.commit();
        }
        catch (SQLException erro)
        {
            BDSQLServer.COMANDO.rollback();
            throw new Exception("[ERRO]: Problemas ao acessar o Banco de Dados para inserir hoteis!");
        }
    }

        public static void excluir(String numero, String cep) throws Exception
        {
            if (!cadastrado (numero,cep))
                throw new Exception ("[ERRO]: Hotel não cadastrado");

            try
            {
                String sql;

                sql = "DELETE FROM Hoteis.Hotel " +
                        "WHERE numero=? " +
                        "AND cep=?";

                BDSQLServer.COMANDO.prepareStatement (sql);

                BDSQLServer.COMANDO.setString (1, numero);
                BDSQLServer.COMANDO.setString (2, cep);

                BDSQLServer.COMANDO.executeUpdate ();
                BDSQLServer.COMANDO.commit        ();
            }
            catch (SQLException erro)
            {
                BDSQLServer.COMANDO.rollback();
                throw new Exception ("[ERRO]: Problemas ao acessar o Banco de Dados para excluir hotel!");
            }
        }

    public static void atualizar (DBOHotel hotel) throws Exception
    {
        if (hotel==null)
            throw new Exception ("[ERRO]: Hotel não fornecido");

        if (!cadastrado (((Integer) hotel.getNumero()).toString(), ((Integer) hotel.getCEP()).toString()))
            throw new Exception ("[ERRO]: Hotel não cadastrado");

        try
        {
            String sql;

            sql = "UPDATE Hoteis.Hotel " +
                    "SET nome =?, " +
                    "telefone=?, " +
                    "complemento=? " +
                    "WHERE numero= ? " +
                    "AND cep=?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString (1, hotel.getNomeHotel().trim());
            BDSQLServer.COMANDO.setString (2, hotel.getTelefone().trim());
            BDSQLServer.COMANDO.setString (3, hotel.getComplemento());
            BDSQLServer.COMANDO.setString (4, ((Integer) hotel.getNumero()).toString());
            BDSQLServer.COMANDO.setString (2, ((Integer) hotel.getCEP()).toString());

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            BDSQLServer.COMANDO.rollback();
            throw new Exception ("[ERRO]: Problemas ao acessar o Banco de Dados para atualizar hoteis!");
        }
    }

    //pegar um hotem especifico
    public static DBOHotel getHotel(String idNumero, String idCep) throws Exception
    {
        DBOHotel hotel = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                    "FROM Hoteis.Hotel " +
                    "WHERE numero  = ? " +
                    "AND cep = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString (1, idNumero);
            BDSQLServer.COMANDO.setString (2, idCep);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

            if (!resultado.first())
                throw new Exception ("[ERRO]: Hotel não cadastrado");

            hotel = new DBOHotel(
                                 resultado.getString ("nome"),
                                 Integer.parseInt(resultado.getString("numero")),
                                 resultado.getString ("telefone"),
                                 Integer.parseInt(resultado.getString("cep")),
                                 resultado.getString("complemento")
                                );
        }
        catch (SQLException erro)
        {
            throw new Exception ("[ERRO]: Problemas ao acessar o Banco de Dados para consultar hotel!");
        }

        return hotel;
    }


    //pegar todos os hoteis
    public static MeuResultSet getHoteis () throws Exception
    {
        MeuResultSet resultado = null;

        try
        {
            String sql;

            sql = "SELECT E.nome as 'Nome Hotel', e.cep as 'cep', e.numero as 'número', e.complemento as 'Complemento', e.telefone as 'Telefone' " +
                    "FROM Hoteis.Hotel E ";

            BDSQLServer.COMANDO.prepareStatement (sql);

            resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("[ERRO]: Problemas ao acessar o Banco de Dados para recuperar hoteis!");
        }

        return resultado;
    }
}
