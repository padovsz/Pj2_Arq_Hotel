async function getConexao() 
{
    if (global.conexao && global.conexao.state !== 'disconnected')
        return global.conexao;

    const mysql    = require('mysql2/promise');
    const bdConfig = require('./bdconfig.js');

    const conexao = await mysql.createConnection (bdConfig);
    global.conexao = conexao;
    return conexao;
}

async function estrutureSe()
{
    const conexao = await getConexao();

    const sql = 'CREATE TABLE IF NOT EXISTS hoteis (id TINYINT PRIMARY KEY IDENTITY, ' +
                                                    'nome VARCHAR(50), ' + 
                                                    'CEP VARCHAR(8) NOT NULL, ' +
                                                    'numero VARCHAR(5) not null, ' + 
                                                    'complemento VARCHAR(10), ' +
                                                    'telefone VARCHAR(20))'

    return await conexao.query (sql);
}

module.exports = {getConexao, estrutureSe}