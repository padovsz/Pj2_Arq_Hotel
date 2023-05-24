package json;

public class Hotel {

    private int id;
    public int getId() { return this.id; }
    public void setId(int id) throws Exception
    {
        if( id == 0)
            throw new Exception("json.id ausente");

        this.id = id;
    }
    private String nome;
    public  String getNome()
    {
        return this.nome;
    }
    public void setNome(String nome) throws Exception
    {
        if (nome ==null || nome.length()==0)
            throw new Exception ("json.Logradouro ausente");

        this.nome = nome;
    }

    private String complemento;
    public  String getComplemento ()
    {
        return this.complemento;
    }
    public void setComplemento (String complemento) throws Exception
    {
        this.complemento = complemento;
    }

    private String numero;
    public  String getNumero()
    {
        return this.numero;
    }
    public void setNumero(String numero) throws Exception
    {
        if (numero ==null || numero.length()==0)
            throw new Exception ("Bairro ausente");

        this.numero = numero;
    }

    private String telefone;
    public  String getTelefone()
    {
        return this.telefone;
    }
    public void setTelefone(String telefone) throws Exception
    {
        if (telefone ==null || telefone.length()==0)
            throw new Exception ("Cidade ausente");

        this.telefone = telefone;
    }

    private String cep;
    public  String getCep ()
    {
        return this.cep;
    }
    public void setCep (String cep) throws Exception
    {
        if (cep==null || cep.length()==0)
            throw new Exception ("json.Logradouro ausente");

        this.cep = cep;
    }

    public Hotel      (int id,
                       String complemento,
                       String nome,
                       String numero,
                       String telefone,
                       String cep) throws Exception
    {
        this.setId(id);
        this.setComplemento (complemento);
        this.setNome(nome);
        this.setNumero(numero);
        this.setTelefone(telefone);
        this.setCep         (cep);
    }

    // exigencia do mapeador de JSon
    public Hotel () {}

    public String toString ()
    {
        return "json.Hotel: "+
                this.nome +
                "\nNúmero: "+
                this.numero+
                "\nComplemento: "+
                this.complemento +
                "\nCEP: "+
                this.cep+
                "\nTelefone: "+
                this.telefone;
    }

    public boolean equals (Object obj)
    {
        if (this==obj)
            return true;

        if (obj==null)
            return false;

        //if (!(this.getClass() != obj.getClass())
        //if (!(obj.getClass != json.Logradouro.class))
        if (!(obj instanceof Hotel))
            return false;

        Hotel cep = (Hotel)obj;

        if (!this.nome.equals(cep.nome))
            return false;

        if ((this.complemento==null && cep.complemento!=null) ||
                (this.complemento!=null && cep.complemento==null) ||
                !this.complemento.equals(cep.complemento))
            return false;

        if (!this.telefone.equals(cep.telefone))
            return false;

        if (!this.numero.equals(cep.numero))
            return false;

        if (!this.cep.equals(cep.cep))
            return false;

        return true;
    }

    public int hashCode ()
    {
        int ret=1;

        ret = 2*ret + this.nome.hashCode();

        if (this.complemento!=null)
            ret = 2*ret + this.complemento.hashCode();

        ret = 2*ret + this.telefone.hashCode();
        ret = 2*ret + this.numero.hashCode();
        ret = 2*ret + this.cep        .hashCode();

        return ret;
    }

    public Hotel (Hotel modelo) throws Exception
    {
        if (modelo==null)
            throw new Exception ("Modelo inexistente");

        this.id = modelo.id;
        this.nome = modelo.nome;
        this.complemento = modelo.complemento;
        this.telefone = modelo.telefone;
        this.numero = modelo.numero;
        this.cep         = modelo.cep;
    }

    public Object clone ()
    {
        Hotel ret=null;

        try
        {
            ret = new Hotel (this);
        }
        catch (Exception erro)
        {}

        return ret;
    }
}
