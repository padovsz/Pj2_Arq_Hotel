async function getConexao() 
{
    if (global.conexao && global.conexao.state !== 'disconnected')
        return global.conexao;

    const mysql    = require('mysql2/promise');
    const DATABASE_URL='mysql://6qg9rezmutbrtnr8xrty:pscale_pw_J2gkT0y6pGl2MJBcN7NJDNW0JE8ze5kcSnXWllP2EkS@aws.connect.psdb.cloud/aosmali?ssl={"rejectUnauthorized":true}'




    const conexao = await mysql.createConnection (DATABASE_URL);
    global.conexao = conexao;
    return conexao;
}

async function estrutureSe()
{
    const conexao = await getConexao();

    const sql = 'CREATE TABLE IF NOT EXISTS hoteis (id TINYINT PRIMARY KEY AUTO_INCREMENT, ' +
                                                    'nome VARCHAR(50), ' + 
                                                    'cep VARCHAR(8) NOT NULL, ' +
                                                    'numero VARCHAR(5) not null, ' + 
                                                    'complemento VARCHAR(10), ' +
                                                    'telefone VARCHAR(20))'

    return await conexao.query (sql);
}

module.exports = {getConexao, estrutureSe}