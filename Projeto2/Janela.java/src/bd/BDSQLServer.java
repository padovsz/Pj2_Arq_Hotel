package bd;

import bd.daos.*;
import bd.core.*;

public class BDSQLServer
{
    public static final MeuPreparedStatement COMANDO;

    static
    {
        MeuPreparedStatement comando = null;

        try
        {
            comando =
                    new MeuPreparedStatement(
                            "com.microsoft.sqlserver.jdbc.SQLServerDriver",
                            "jdbc:sqlserver://regulus.cotuca.unicamp.br:1433;databasename=BD22136",
                            "BD22136", ""
                    );
        }
        catch (Exception erro)
        {
            System.err.println("Problemas de conexão com o banco de dados");
            System.exit(0); //fecha o programa
        }
        COMANDO = comando;
    }
}
