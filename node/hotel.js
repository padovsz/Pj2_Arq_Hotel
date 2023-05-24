class Hotel
{
    #id
    #nome
    #cep
    #numero
    #complemento
    #telefone

    constructor (id,nome,cep,numero,complemento,telefone)
    {
        this.id=id;
        this.nome=nome;
        this.cep=cep;
        this.numero=numero;
        this.complemento=complemento;
        this.telefone=telefone;
    }

    get id ()
    {
        return this.#id
    }

    get nome ()
    {
        return this.#nome
    }

    get cep ()
    {
        return this.#cep
    }

    get numero ()
    {
        return this.#numero
    }

    get complemento ()
    {
        return this.#complemento
    }

    get telefone ()
    {
        return this.#telefone
    }

    set id (id)
    {
        if (id===undefined || typeof id !== 'number' || isNaN(id) || id!==parseInt(id) || id<=0)
            throw ('Código inválido');

        this.#id = id;
    }

    set nome (nome)
    {
        if (nome===undefined || typeof nome !== 'string' || nome==="")
            throw ('Nome inválido');

        this.#nome = nome;
    }

    set cep (cep)
    {
        if (cep===undefined || typeof cep !== 'string' || cep==="")
            throw ('Nome inválido');

        this.#cep = cep;
    }

    set numero (numero)
    {
        if (numero===undefined || typeof numero !== 'string' || numero==="")
            throw ('Nome inválido');

        this.#numero = numero;
    }

    set complemento (complemento)
    {
        if (complemento===undefined || typeof complemento !== 'string' || complemento==="")
            throw ('Nome inválido');

        this.#complemento = complemento;
    }

    set telefone (telefone)
    {
        if (telefone===undefined || typeof telefone !== 'string' || telefone==="")
            throw ('Nome inválido');

        this.#telefone = telefone;
    }
}

function novo (id,nome,cep,numero,complemento,telefone)
{
    return new Hotel (id,nome,cep,numero,complemento,telefone);
}

module.exports = {novo}
