const Hoteis     = require ('./hoteis.js');
const Hotel      = require ('./hotel.js');
const Comunicado = require ('./comunicado.js');


// para a rota de CREATE
async function inclusao (req, res)
{
    if (Object.values(req.body).length!=6 || !req.body.id || !req.body.nomeHotel || !req.body.cep || !req.body.numero || !req.body.complemento || !req.body.telefone)
    {
        const erro = Comunicado.novo('DdI','Dados inesperados','Não foram fornecidos exatamente as 6 informações esperadas de um hotel (id, nome do Hotel, cep, nujmero, complemento e telefone)').object;
        return res.status(422).json(erro);
    }
    
    let hotel;
    try
    {
        hotel = Hotel.novo (req.body.id,req.body.nomeHotel,req.body.cep,req.body.numero,req.body.complemento,req.body.telefone);
    }
    catch (excecao)
    {
        const erro = Comunicado.novo('TDE','Dados de tipos errados','ID deve ser um numero natural positivo, nome do hotel, o cep, o número, o complemento e o telefone devem ser um texto não vazio').object;
        return res.status(422).json(erro);
    }

    const ret = await Hoteis.inclua(hotel);

    if (ret===null)
    {
        const  erro = Comunicado.novo('CBD','Sem conexão com o BD','Não foi possível estabelecer conexão com o banco de dados').object;
        return res.status(500).json(erro);
    }

    if (ret===false)
    {
        const  erro = Comunicado.novo('LJE','hOTEL já existe','Já há HOTEL cadastrado com o cep e numero informado').object;
        return res.status(409).json(erro);
    }

  //if (ret===true)
  //{
        const  sucesso = Comunicado.novo('IBS','Inclusão bem sucedida','O hotel foi incluído com sucesso').object;
        return res.status(201).json(sucesso);
  //}
}

// para a rota de UPDATE
async function atualizacao (req, res)
{
    if (Object.values(req.body).length!=6 || !req.body.id || !req.body.nomeHotel || !req.body.cep || !req.body.numero || !req.body.complemento || !req.body.telefone)
    {
        const erro = Comunicado.novo('DdI','Dados inesperados','Não foram fornecidos exatamente as 6 informações esperadas de um hotel (id, nome do Hotel, cep, nujmero, complemento e telefone)').object;
        return res.status(422).json(erro);
    }
    
    let hotel;
    try
    {
        hotel = Hotel.novo (req.body.id,req.body.nomeHotel,req.body.cep,req.body.numero,req.body.complemento,req.body.telefone);
    }
    catch (excecao)
    {
        const erro = Comunicado.novo('TDE','Dados de tipos errados','Id deve ser um numero natural positivo, nome do hotel, o cep, o numero, o complemento e o telefone deve ser um texto não vazio').object;
        return res.status(422).json(erro);
    }

    const cep = req.params.cep;
    const numero = req.params.numero;
    
    if (cep!=hotel.cep || numero!=hotel.numero)
    {
        const erro = Comunicado.novo('TMC','Mudança de código','Tentativa de mudar o cep e o numero do hotel').object;
        return res.status(400).json(erro);    
    }
    
    let ret = await Hoteis.recupereUm(numero,cep);

    if (ret===null)
    {
        const  erro = Comunicado.novo('CBD','Sem conexão com o BD','Não foi possível estabelecer conexão com o banco de dados').object;
        return res.status(500).json(erro);
    }

    if (ret===false)
    {
        const  erro = Comunicado.novo('FNC','Falha no comando SQL','O comando SQL apresenta algum erro').object;
        return res.status(409).json(erro);
    }

    if (ret.length==0)
    {
        const erro = Comunicado.novo('LNE','Hotel inexistente','Não há hotel cadastrado com o cep e o número informado').object;
        return res.status(404).json(erro);
    }

    ret = await Hoteis.atualize(hotel);

    if (ret===null)
    {
        const  erro = Comunicado.novo('CBD','Sem conexão com o BD','Não foi possível estabelecer conexão com o banco de dados').object;
        return res.status(500).json(erro);
    }

    if (ret===false)
    {
        const  erro = Comunicado.novo('FNC','Falha no comando SQL','O comando SQL apresenta algum erro').object;
        return res.status(409).json(erro);
    }

  //if (ret===true)
  //{
        const sucesso = Comunicado.novo('ABS','Alteração bem sucedida','O hotel foi atualizado com sucesso').object;
        return res.status(201).json(sucesso);
  //}
}

// para a rota de DELETE
async function remocao (req, res)
{
    if (Object.values(req.body).length!=0)
    {
        const erro = Comunicado.novo('DSP','Fornecimento de dados sem propósito','Foram fornecidos dados sem necessidade no corpo da requisição').object;
        return res.status(422).json(erro);
    }
    
    const cep = req.params.cep;
    const numero = req.params.numero;
    let ret = await Hoteis.recupereUm(numero,cep);

    if (ret===null)
    {
        const  erro = Comunicado.novo('CBD','Sem conexão com o BD','Não foi possível estabelecer conexão com o banco de dados').object;
        return res.status(500).json(erro);
    }

    if (ret===false)
    {
        const  erro = Comunicado.novo('FNC','Falha no comando SQL','O comando SQL apresenta algum erro').object;
        return res.status(409).json(erro);
    }

    if (ret.length==0)
    {
        const erro = Comunicado.novo('LNE','Hotel inexistente','Não há hotel cadastrado com o cep e o número informado').object;
        return res.status(404).json(erro);
    }

    ret = await Hoteis.remova(numero,cep);

    if (ret===null)
    {
        const  erro = Comunicado.novo('CBD','Sem conexão com o BD','Não foi possível estabelecer conexão com o banco de dados').object;
        return res.status(500).json(erro);
    }

    if (ret===false)
    {
        const  erro = Comunicado.novo('FNC','Falha no comando SQL','O comando SQL apresenta algum erro').object;
        return res.status(409).json(erro);
    }

  //if (ret===true)
  //{
        const sucesso = Comunicado.novo('RBS','Remoção bem sucedida','O hotel foi removido com sucesso').object;
        return res.status(200).json(sucesso);
  //}    
}

// para a segunda rota de READ (um)
async function recuperacaoDeUm (req, res)
{
    if (Object.values(req.body).length!=0)
    {
        const erro = Comunicado.novo('DSP','Fornecimento de dados sem propósito','Foram fornecidos dados sem necessidade no corpo da requisição').object;
        return res.status(422).json(erro);
    }

    const cep = req.params.cep;
    const numero = req.params.numero;

    const ret = await Hoteis.recupereUm(numero,cep);

    if (ret===null)
    {
        const  erro = Comunicado.novo('CBD','Sem conexão com o BD','Não foi possível estabelecer conexão com o banco de dados').object;
        return res.status(500).json(erro);
    }

    if (ret===false)
    {
        const  erro = Comunicado.novo('FNC','Falha no comando SQL','O comando SQL apresenta algum erro').object;
        return res.status(409).json(erro);
    }

    if (ret.length==0)
    {
        const erro = Comunicado.novo('LNE','Hotel inexistente','Não há hotel cadastrado com o cep e o número informado').object;
        return res.status(404).json(erro);
    }

    return res.status(200).json(ret);
}

// para a primeira rota de READ (todos)
async function recuperacaoDeTodos (req, res)
{
    if (Object.values(req.body).length!=0)
    {
        const erro = Comunicado.novo('DSP','Fornecimento de dados sem propósito','Foram fornecidos dados sem necessidade no corpo da requisição').object;
        return res.status(422).json(erro);
    }

    const ret = await Hoteis.recupereTodos();

    if (ret===null)
    {
        const  erro = Comunicado.novo('CBD','Sem conexão com o BD','Não foi possível estabelecer conexão com o banco de dados').object;
        return res.status(500).json(erro);
    }

    if (ret===false)
    {
        const  erro = Comunicado.novo('FNC','Falha no comando SQL','O comando SQL apresenta algum erro').object;
        return res.status(409).json(erro);
    }

    return res.status(200).json(ret);
}

module.exports = {inclusao, atualizacao, remocao, recuperacaoDeUm, recuperacaoDeTodos}