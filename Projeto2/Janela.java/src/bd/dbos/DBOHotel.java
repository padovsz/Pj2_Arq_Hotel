package bd.dbos;

public class DBOHotel implements Cloneable{
    private String nomeHotel;
    private int numero;
    private String telefone;
    private int cep;
    private String complemento;

    public String getNomeHotel(){
        return nomeHotel;
    }

    public int getNumero() {
        return numero;
    }

    public String getTelefone() {
        return telefone;
    }

    public int getCEP() {
        return cep;
    }
    public String getComplemento() { return complemento; }


    public void setNomeHotel(String nomeHotel) throws Exception
    {
        if(nomeHotel.equals("") || nomeHotel == null )
        {
            throw new Exception ("[ERRO]: Nome do Hotel não fornecido");
        }
        if(nomeHotel.length() > 50)
            nomeHotel = nomeHotel.substring(0, 50);

        this.nomeHotel = nomeHotel;
    }

    public void setNumero(int numero) throws Exception
    {
        if(numero <= 0 || numero > 99999)
        {
            throw new Exception ("[ERRO]: Número do Hotel inválido");
        }

        this.numero = numero;
    }

    public void setTelefone(String telefone) throws  Exception
    {
        if(telefone == null )
        {
            throw new Exception ("[ERRO]: Telefone não fornecido");
        }
        if(telefone.length() > 15)
            telefone = telefone.substring(0, 15);

        this.telefone = telefone;
    }

    public void setCEP(int cep) throws Exception
    {
        if(cep <= 0 || cep > 99999999)
        {
            throw new Exception ("[ERRO]: CEP inválido");
        }

        this.cep = cep;
    }

    public void setComplemento(String complemento) throws Exception
    {
        if(complemento == null ) {
            throw new Exception("[ERRO]: Complemento não fornecido");
        }
        if(complemento.length() > 20)
            complemento = complemento.substring(0, 20);

        this.complemento = complemento;
    }

    public DBOHotel(String nomeHotel, int numero, String telefone, int cep, String complemento)
    {
        try
        {
            this.setNomeHotel(nomeHotel);
            this.setNumero(numero);
            this.setTelefone(telefone);
            this.setCEP(cep);
            this.setComplemento(complemento);
        }
        catch (Exception erro)
        {
            System.err.println(erro.getMessage());
        }

    }

    public String toString ()
    {
        String ret="";

        ret+="Nome do Hotel: "+this.nomeHotel+"\n";
        ret+="Número: "+this.numero +"\n";
        ret+="Telefone: "+this.telefone +"\n";
        ret+="CEP: "+this.cep +"\n";
        ret+="Complemento: "+this.complemento +"\n";

        return ret;
    }

    public boolean equals (Object obj)
    {
        if (this==obj)
            return true;

        if (obj==null)
            return false;

        if (!(obj instanceof DBOHotel))
            return false;

        DBOHotel hotel = (DBOHotel)obj;

        if (this.nomeHotel.equals(hotel.nomeHotel))
            return false;

        if (this.numero!=hotel.numero)
            return false;

        if (this.telefone.equals(hotel.telefone))
            return false;

        if (this.cep!=hotel.cep)
            return false;

        if (this.complemento.equals(hotel.complemento))
            return false;

        return true;
    }

    public int hashCode ()
    {
        int ret=666;

        ret = 7*ret + this.nomeHotel.hashCode();
        ret = 7*ret + Integer.valueOf(this.numero).hashCode();
        ret = 7*ret + this.telefone.hashCode();
        ret = 7*ret + Integer.valueOf(this.cep).hashCode();
        ret = 7*ret + this.complemento.hashCode();

        return ret;
    }

    public DBOHotel(DBOHotel modelo) throws Exception
    {
        this.nomeHotel   = modelo.nomeHotel;
        this.numero      = modelo.numero;
        this.telefone    = modelo.telefone;
        this.cep         = modelo.cep;
        this.complemento = modelo.complemento;
    }

    public Object clone ()
    {
        DBOHotel ret=null;

        try
        {
            ret = new DBOHotel(this);
        }
        catch (Exception erro)
        {}

        return ret;
    }
}
