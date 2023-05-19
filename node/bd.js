async function getConexao() 
{
    if (global.conexao && global.conexao.state !== 'disconnected')
        return global.conexao;

    const mysql    = require('mysql2/promise');
    const DATABASE_URL = 'mysql://4ycnpk4n6zq3hwjo11vd:pscale_pw_INuHTPrx7MWOCPKnfjKmvMujMNS0FFXIqoJ6N7ElNyU@aws.connect.psdb.cloud/aosmali?ssl={"rejectUnauthorized":true}'


    const conexao = await mysql.createConnection (DATABASE_URL);
    global.conexao = conexao;
    return conexao;
}

async function estrutureSe()
{
    const conexao = await getConexao();

    const sql = 'CREATE TABLE IF NOT EXISTS hoteis (id TINYINT PRIMARY KEY AUTO_INCREMENT, ' +
                                                    'nome VARCHAR(50), ' + 
                                                    'CEP VARCHAR(8) NOT NULL, ' +
                                                    'numero VARCHAR(5) not null, ' + 
                                                    'complemento VARCHAR(10), ' +
                                                    'telefone VARCHAR(20))'

    return await conexao.query (sql);
}

module.exports = {getConexao, estrutureSe}