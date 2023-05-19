let ativacaoDoServidor = (async () => {
    const bd     = require("./bd");
    const hoteis = require("./hoteis");
    const rotas = require("./rotas");
    const express = require("express");

    const ret = await bd.estrutureSe();

    if(ret === null)
    {
        console.log("Não foi possível estabelecer conexão com o BD!");
        process.exit(1);
    }

    if(ret === false)
    {
        console.log("Não foi possível estruturar o BD");
        process.exit(1);
    }

    const app = express();

    app.use(express.json());
    
    app.post('/hoteis', rotas.inclusao);
    app.put('/hoteis/:cep/:numero', rotas.atualizacao);
    app.delete("/hoteis/:cep/:numero", rotas.remocao);
    app.get('/hoteis/:cep/:numero', rotas.recuperacaoDeUm);
    app.get('/hoteis', rotas.recuperacaoDeTodos);

    console.log("Servidor ativo na porta 3000...");
    app.listen(3000);
});
ativacaoDoServidor();