(async () => {
    const bd     = require("./bd");
    const hoteis = require("./hoteis");

    console.log('CREATE TABLE IF NOT EXISTS hoteis (id TINYINT PRIMARY KEY IDENTITY, ' +
                                                    'nome VARCHAR(50), ' + 
                                                    'CEP VARCHAR(8) NOT NULL, ' +
                                                    'numero VARCHAR(5) not null, ' + 
                                                    'complemento VARCHAR(10), ' +
                                                    'telefone VARCHAR(20))');
    const result0 = await bd.estrutureSe();
    console.log(result0);
    
    console.log('INSERT INTO hoteis VALUES (1,"A República (Platão)", 77.77)');
    const result1 = await hoteis.inclua({codigo: 1, nome: "A República (Platão)", preco: 77.77});
    console.log(result1);

    console.log('INSERT INTO hoteis VALUES (2,"O Príncipe (Nicolau Maquiavel)",55.55)');
    const result2 = await hoteis.inclua({codigo: 2, nome: "O Príncipe (Nicolau Maquiavel)", preco: 55.55});
    console.log(result2);

    console.log('INSERT INTO hoteis VALUES (3,"A Condição Humana (Hannah Arendt)",33.33)');
    const result3 = await hoteis.inclua({codigo: 3, nome: "A Condição Humana (Hannah Arendt)", preco: 33.33});
    console.log(result3);

    console.log('INSERT INTO hoteis VALUES (4,"Ser e tempo (Martin Heidegger)",66.66)');
    const result4 = await hoteis.inclua({codigo: 4, nome: "Ser e tempo (Martin Heidegger)", preco: 66.66});
    console.log(result4);

    console.log('INSERT INTO hoteis VALUES (5,"Crítica da Razão Pura (Immanuel Kant)",22.22)');
    const result5 = await hoteis.inclua({codigo: 5, nome: "Crítica da Razão Pura (Immanuel Kant)", preco: 22.22});
    console.log(result5);

    console.log('INSERT INTO hoteis VALUES (6,"A Política (Aristóteles)",44.44)');
    const result6 = await hoteis.inclua({codigo: 6, nome: "A Política (Aristóteles)", preco: 44.44});
    console.log(result6);

    console.log('SELECT * FROM hoteis WHERE codigo=4');
    const result7 = await hoteis.recupereUm (4);
    console.log(result7);

    console.log('SELECT * FROM hoteis');
    const result8 = await hoteis.recupereTodos ();
    console.log(result8);

    console.log('UPDATE hoteis SET preco=88.88 WHERE codigo=4');
    const result9 = await hoteis.atualize({codigo: 4, nome: "Ser e tempo (Martin Heidegger)", preco: 88.88});
    console.log(result9);

    console.log('SELECT * FROM hoteis WHERE codigo=4');
    const result10 = await hoteis.recupereUm (4);
    console.log(result10);

    console.log('DELETE FROM hoteis WHERE codigo=4');
    const result11 = await hoteis.remova(4);
    console.log(result11);

    console.log('SELECT * FROM hoteis WHERE codigo=4');
    const result12 = await hoteis.recupereUm (4);
    console.log(result12);

    process.exit(1);
})();

