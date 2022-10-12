package bd.dbos;
import java.sql.*;

public class DBOCargo implements Cloneable{
    private String nomeCargo;
    private int idCardo;
    private String UF;

    public String getNomeCargo(){
        return  nomeCargo;
    }

    public int getIdCardo() {
        return idCardo;
    }

    public String getUF() {
        return UF;
    }

    public void setNomeCargo(String nomeCargo)  throws Exception
    {
        if(nomeCargo.equals("") || nomeCargo == null )
        {
            throw new Exception ("Nome nao fornecido");
        }
        this.nomeCargo = nomeCargo;
    }

    public void setIdCardo(int idCardo) throws Exception
    {
        if(idCardo <= 0 )
        {
            throw new Exception("Numero do cargo invalido");
        }
        this.idCardo = idCardo;
    }

    public void setUF(String UF) throws Exception
    {
        if(UF.equals("") || UF == null )
        {
            throw new Exception ("UF nao fornecido");
        }
        this.UF = UF;
    }

    public DBOCargo(String nome, int num, String uf){
        try
        {
            this.setNomeCargo(nome);
            this.setIdCardo(num);
            this.setUF(uf);
        }
        catch (Exception erro)
        {
            System.err.println(erro.getMessage());
        }
    }

    public String toString ()
    {
        String ret="";

        ret+="Cargo: "+this.nomeCargo+"\n";
        ret+="Número cargo: "+this.idCardo  +"\n";
        ret+="UF: "+this.UF;

        return ret;
    }

    public boolean equals (Object obj)
    {
        if (this==obj)
            return true;

        if (obj==null)
            return false;

        if (!(obj instanceof DBOCargo))
            return false;

        DBOCargo cg = (DBOCargo)obj;

        if (this.idCardo!=cg.idCardo)
            return false;

        if (this.nomeCargo.equals(cg.nomeCargo))
            return false;

        if (this.UF.equals(cg.UF))
            return false;

        return true;
    }

    public int hashCode ()
    {
        int ret=666;

        ret = 7*ret + Integer.valueOf(this.idCardo).hashCode();
        ret = 7*ret + this.nomeCargo.hashCode();
        ret = 7*ret + this.UF.hashCode();

        return ret;
    }

    public DBOCargo (DBOCargo modelo) throws Exception
    {
        this.nomeCargo  = modelo.nomeCargo;
        this.idCardo   = modelo.idCardo;
        this.UF        = modelo.UF;
    }

    public Object clone ()
    {
        DBOCargo ret=null;

        try
        {
            ret = new DBOCargo (this);
        }
        catch (Exception erro)
        {}

        return ret;
    }
}
