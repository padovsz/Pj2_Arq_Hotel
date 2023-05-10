const bd = require ('./bd');

async function inclua (hotel)
{
    const conexao = await bd.getConexao ();
    const sql     = 'INSERT INTO hoteis (id,nomeHotel,cep,numero,complemento,telefone) VALUES (?,?,?,?,?,?)';
    const dados   = [hotel.id,hotel.nomeHotel,hotel.cep,hotel.numero,hotel.complemento,hotel.telefone];

    return await conexao.query (sql, dados);
}

async function atualize (hotel)
{
    const conexao = await bd.getConexao ();
    
    const sql   = 'UPDATE hoteis SET nomeHotel=?, telefone=?, complemento=? WHERE numero= ? AND cep=?"';
    const dados = [hotel.nomeHotel,hotel.telefone,hotel.complemento,hotel.numero,hotel.cep];

    return await conexao.query (sql,dados);
}
    
async function remova (numero,cep)
{
    const conexao = await bd.getConexao ();
    
    const sql   = 'DELETE FROM hoteis WHERE numero=? AND cep=?';
    const dados = [numero,cep];

    return await conexao.query (sql,dados);
}

async function recupereUm (numero,cep)
{
    const conexao = await bd.getConexao();
    
    const  sql     = 'SELECT * FROM hoteis WHERE numero=? AND cep=?';
    const  dados   = [numero,cep];
    const [linhas] = await conexao.query(sql,dados);
    
    return linhas;
}

async function recupereTodos ()
{
    const conexao = await bd.getConexao();

    const  sql     = 'SELECT * FROM hoteis';
    const [linhas] = await conexao.query(sql);

    return linhas;
}

module.exports = {inclua, atualize, remova, recupereUm, recupereTodos}
