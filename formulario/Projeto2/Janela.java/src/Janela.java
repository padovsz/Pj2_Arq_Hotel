import bd.core.MeuResultSet;
import bd.daos.DAOHoteis;
import bd.dbos.DBOHotel;
import json.ClienteWS;
import json.Hotel;
import json.Logradouro;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Janela extends JFrame
{
    protected static final long serialVersionUID = 1L;

    protected JButton btnCriar     = new JButton("Criar");
    protected JButton btnAtualizar = new JButton("Atualizar");
    protected JButton btnDeletar   = new JButton("Deletar");
    protected JButton btnConsultar = new JButton("Consultar");
    protected JButton btnSalvar    = new JButton("Salvar");
    protected JButton btnCancelar  = new JButton("Cancelar");
    protected JButton btnProximo   = new JButton(">");
    protected JButton btnAnterior  = new JButton("<");

    protected JLabel lbMensagem = new JLabel("Mensagem:");
    protected JLabel lbHotel = new JLabel("Nome:");
    protected JLabel lbCEP = new JLabel("CEP:");
    protected JLabel lbRua = new JLabel("Rua:");
    protected JLabel lbNum = new JLabel("Num:");
    protected JLabel lbComplemento = new JLabel("Complemento:");
    protected JLabel lbCidade = new JLabel("Cidade:");
    protected JLabel lbEstado = new JLabel("Estado:");
    protected JLabel lbTelefone = new JLabel("Telefone:");
    protected JLabel lbBairro = new JLabel("Bairro:");


    protected JTextField txtHotel = new JTextField();
    protected JTextField txtCep = new JTextField();
    protected JTextField txtRua = new JTextField();
    protected JTextField txtNum = new JTextField();
    protected JTextField txtComplemento = new JTextField();
    protected JTextField txtCidade = new JTextField();
    protected JTextField txtEstado = new JTextField();
    protected JTextField txtTelefone = new JTextField();
    protected JTextField txtBairro = new JTextField();
/*
    String[] cargos = {"1 - Presidente", "2 - Governador", "3 - Senador", "4 - Deputado Federal", "5 - Deputado Estadual"};

    protected JComboBox cargoList = new JComboBox(cargos);

    String[] UFs = {"BR", "SP" ,"MG","ES" , "BA" , "SE" , "AL" , "PB" , "PE" , "RN" , "CE" , "MA" , "PI" , "AM" , "PA" , "AC" , "RR" ,
            "RO" , "TO" , "MS" , "MT" , "GO" , "DF" , "PR" , "SC" , "RS"};

    protected JComboBox UFlist = new JComboBox(UFs);*/
/*
    protected String[] nomeCampos = {
        "Candidato",
        "Codigo",
        "Partido",
        "Data de Nascimento",
        "Cargo",
        "UF"
    };

    protected Object[][] data = {
            {"Lula", String.valueOf(13),"PT","27/08/2006","Presidente","BR"},
            {"Bolsonaro", String.valueOf(22), "PL", "19/02/1990", "Presidente", "BR"},
            {"Haddad", String.valueOf(13), "PT", "01/08/1996", "Governador(a)", "SP"},
            {"Damaris", String.valueOf(200), "PL", "01/01/2000", "Senador(a)", "DF"}
    };

    // DAOCandidatos daoCandidatos = new DAOCandidatos();
    // Object[][] data =  daoCandidatos.getCandidatos();

    {
        try {
            Object[][] data = DAOCandidatos.getTabelaCandidatos();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected JTable tabela = new JTable(data, nomeCampos);
    {
        tabela.setEnabled(false);
    }

    JScrollPane scroll = new JScrollPane(tabela,
                                         ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                                         ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
*/
    ArrayList<LinkedHashMap> Hoteis;
    public Janela()
    {
        super("Consulta");

        try
        {
            Image btnCriarImg = ImageIO.read(getClass().getResource("resources/criar.bmp"));
            btnCriar.setIcon(new ImageIcon(btnCriarImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo criar.bmp não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnConsultarImg = ImageIO.read(getClass().getResource("resources/ler.bmp"));
            btnConsultar.setIcon(new ImageIcon(btnConsultarImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo ler.bmp não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnAtualizarImg = ImageIO.read(getClass().getResource("resources/atualizar.bmp"));
            btnAtualizar.setIcon(new ImageIcon(btnAtualizarImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo atualizar.bmp não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnDeletarImg = ImageIO.read(getClass().getResource("resources/excluir.bmp"));
            btnDeletar.setIcon(new ImageIcon(btnDeletarImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo excluir.bmp não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnSalvarImg = ImageIO.read(getClass().getResource("resources/salvar.bmp"));
            btnSalvar.setIcon(new ImageIcon(btnSalvarImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                    "Arquivo salvar.bmp não foi encontrado",
                    "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }

        btnCriar.addActionListener(new CriarRegistro());
        btnAtualizar.addActionListener(new AtualizarRegistro());
        btnConsultar.addActionListener(new ConsultarRegistro());
        btnDeletar.addActionListener(new DeletarRegistro());
        btnSalvar.addActionListener(new SalvarRegistro());
        btnCancelar.addActionListener(new CancelarRegistro());
        btnProximo.addActionListener(new ProximoRegistro());
        btnAnterior.addActionListener(new AnteriorRegistro());

        txtHotel.addKeyListener(new MaxLength());
        txtRua.addKeyListener(new MaxLength());
        txtComplemento.addKeyListener(new MaxLength());
        txtNum.addKeyListener(new MaxLength());
        txtCidade.addKeyListener(new MaxLength());
        txtEstado.addKeyListener(new MaxLength());
        txtTelefone.addKeyListener(new MaxLength());
        txtCep.addKeyListener(new MaxLength());


        JPanel pnlBotoes = new JPanel();
        FlowLayout flwBotoes = new FlowLayout();
        pnlBotoes.setLayout(flwBotoes);

        pnlBotoes.add(btnCriar);
        pnlBotoes.add(btnConsultar);
        pnlBotoes.add(btnAtualizar);
        pnlBotoes.add(btnDeletar);
        pnlBotoes.add(btnSalvar);
        pnlBotoes.add(btnCancelar);

        JPanel pnlMensagem = new JPanel();
        GridLayout grdMensagem = new GridLayout(1,1);
        pnlMensagem.setLayout(grdMensagem);

        pnlMensagem.add(lbMensagem);

        JPanel pnlComponentes = new JPanel();
        /*GridLayout grdComponentes = new GridLayout(6, 2, 5, 20);
        pnlComponentes.setLayout(grdComponentes);

        pnlComponentes.add(lbCandidato);
        pnlComponentes.add(txtCandidato);
        pnlComponentes.add(lbNum);
        pnlComponentes.add(txtNum);
        pnlComponentes.add(lbPartido);
        pnlComponentes.add(txtPartido);
        pnlComponentes.add(lbNascimento);
        pnlComponentes.add(txtNascimento);
        pnlComponentes.add(lbCargo);
        pnlComponentes.add(txtCargo);
        pnlComponentes.add(lbUF);
        pnlComponentes.add(txtUF);*/

        JPanel pnlBotoesCentralizados = new JPanel();
        FlowLayout flwBtnCent = new FlowLayout();
        pnlBotoesCentralizados.setLayout(flwBtnCent);
        pnlBotoesCentralizados.add(btnAnterior);
        pnlBotoesCentralizados.add(btnProximo);

        GroupLayout grpComponentes = new GroupLayout(pnlComponentes);
        pnlComponentes.setLayout(grpComponentes);
        grpComponentes.setAutoCreateGaps(true);
        grpComponentes.setAutoCreateContainerGaps(true);
        grpComponentes.setHorizontalGroup(
                grpComponentes.createSequentialGroup()
                        .addGroup(grpComponentes.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(lbHotel)
                                .addComponent(lbTelefone)
                                .addComponent(lbNum)
                                .addComponent(lbComplemento)
                                .addComponent(lbCEP)
                                .addComponent(lbRua)
                                .addComponent(lbBairro)
                                .addComponent(lbCidade)
                                .addComponent(lbEstado)
                                .addComponent(pnlBotoesCentralizados))
                        .addGroup(grpComponentes.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(txtHotel, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTelefone)
                                .addComponent(txtNum)
                                .addComponent(txtComplemento)
                                .addComponent(txtCep)
                                .addComponent(txtRua)
                                .addComponent(txtBairro)
                                .addComponent(txtCidade)
                                .addComponent(txtEstado)
                                )
                        .addComponent(pnlBotoesCentralizados)
        );
        grpComponentes.setVerticalGroup(
                grpComponentes.createSequentialGroup()
                        .addGroup(grpComponentes.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lbHotel)
                                .addComponent(txtHotel))
                        .addGroup(grpComponentes.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lbTelefone)
                                .addComponent(txtTelefone))
                        .addGroup(grpComponentes.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lbNum)
                                .addComponent(txtNum))
                        .addGroup(grpComponentes.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lbComplemento)
                                .addComponent(txtComplemento))
                        .addGroup(grpComponentes.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lbCEP)
                                .addComponent(txtCep))
                        .addGroup(grpComponentes.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lbRua)
                                .addComponent(txtRua))
                        .addGroup(grpComponentes.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lbBairro)
                                .addComponent(txtBairro))
                        .addGroup(grpComponentes.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lbCidade)
                                .addComponent(txtCidade))
                        .addGroup(grpComponentes.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lbEstado)
                                .addComponent(txtEstado))
                        .addComponent(pnlBotoesCentralizados)
        );
/*
        JPanel pnlResultado = new JPanel();
        FlowLayout flwResultado = new FlowLayout();
        pnlResultado.setLayout(flwResultado);

        pnlResultado.add(scroll);*/

        JPanel pnlCentralizando = new JPanel();
        FlowLayout flwCentralizando = new FlowLayout();
        pnlCentralizando.setLayout(flwCentralizando);
        pnlCentralizando.add(pnlComponentes);

        Container cntForm = this.getContentPane();
        cntForm.setLayout(new BorderLayout());
        cntForm.add(pnlBotoes,  BorderLayout.NORTH);
        cntForm.add(pnlCentralizando, BorderLayout.CENTER);
        //cntForm.add(pnlResultado, BorderLayout.EAST);
        cntForm.add(pnlMensagem,  BorderLayout.SOUTH);

        this.addWindowListener(new FechamentoDeJanela());

        try{
            Hoteis = (ArrayList<LinkedHashMap>) ClienteWS.getObjeto(ArrayList.class, "https://localhost:3000/hoteis", "13033205");
            System.out.println (Hoteis);
        }
        catch (Exception err)
        {
            JOptionPane.showMessageDialog (null,
                    err.getMessage(),
                    "Erro ao exibir Hoteis",
                    JOptionPane.WARNING_MESSAGE);
        }

        situacaoAtual = SituacaoAtual.navegando;
        atualizarTela();

        this.setSize(700, 400);
        this.setVisible(true);
    }

    protected void limparTela()
    {
        txtHotel.setText("");
        txtComplemento.setText("");
        txtRua.setText("");
        txtNum.setText("");
        txtCidade.setText("");
        txtEstado.setText("");
        txtTelefone.setText("");
        txtCep.setText("");
        txtBairro.setText("");
    }

    protected void atualizarTela()
    {
        switch(situacaoAtual)
        {
            case navegando:
            {
                    btnProximo.setEnabled(true);
                    btnAnterior.setEnabled(true);
                    /*try {
                        if (Hoteis.isFirst()) {
                            btnAnterior.setEnabled(false);
                        }
                        if (Hoteis.isLast()) {
                            btnProximo.setEnabled(false);
                        }

                        txtHotel.setText(Hoteis.);
                        txtNum.setText(Hoteis.getString("número"));
                        txtCep.setText(Hoteis.getString("cep"));
                        txtComplemento.setText(Hoteis.getString("Complemento"));
                        txtTelefone.setText(Hoteis.getString("Telefone"));
                        Logradouro hotel = (Logradouro) ClienteWS.getObjeto(Logradouro.class, "https://api.postmon.com.br/v1/cep", txtCep.getText());
                        txtCidade.setText(hotel.getCidade()); //Informaçao do json
                        txtEstado.setText(hotel.getEstado()); //Informaçao do json
                        txtRua.setText(hotel.getLogradouro()); //json
                        txtBairro.setText(hotel.getBairro()); //json
                    } catch (Exception err)
                    {}*/
                    btnAtualizar.setEnabled(true);
                    btnConsultar.setEnabled(true);
                    btnDeletar.setEnabled(true);
                    btnCancelar.setEnabled(true);
                    btnCriar.setEnabled(true);
                    btnSalvar.setEnabled(true);
                    txtHotel.setEditable(false);
                    txtRua.setEditable(false);
                    txtComplemento.setEditable(false);
                    txtNum.setEditable(false);
                    txtCidade.setEditable(false);
                    txtTelefone.setEditable(false);
                    txtEstado.setEditable(false);
                    txtCep.setEditable(false);
                    txtBairro.setEditable(false);
                    lbMensagem.setText("Mensagem: navegando");
                break;
            }
            case criando:
            {
                limparTela();
                btnCriar.setEnabled(false);
                btnAtualizar.setEnabled(false);
                btnConsultar.setEnabled(false);
                btnDeletar.setEnabled(false);
                btnAnterior.setEnabled(false);
                btnProximo.setEnabled(false);
                txtHotel.setEditable(true);
                txtRua.setEditable(false);
                txtComplemento.setEditable(true);
                txtCep.setEditable(true);
                txtNum.setEditable(true);
                txtCidade.setEditable(false);
                txtEstado.setEditable(false);
                txtTelefone.setEditable(true);
                txtBairro.setEditable(false);
                lbMensagem.setText("Mensagem: Insira os dados do novo hotel");
                break;
            }
            case alterando:
            {
                btnCriar.setEnabled(false);
                btnAtualizar.setEnabled(false);
                btnConsultar.setEnabled(false);
                btnDeletar.setEnabled(false);
                btnAnterior.setEnabled(false);
                btnProximo.setEnabled(false);
                txtRua.setEditable(false);
                txtHotel.setEditable(true);
                txtComplemento.setEditable(true);
                txtNum.setEditable(false);
                txtCidade.setEditable(false);
                txtTelefone.setEditable(true);
                txtCep.setEditable(false);
                txtEstado.setEditable(false);
                txtBairro.setEditable(false);
                lbMensagem.setText("Mensagem: Insira os novos dados do hotel");
                break;
            }
            case consultando:
            {
                btnCriar.setEnabled(false);
                btnAtualizar.setEnabled(false);
                btnConsultar.setEnabled(false);
                btnDeletar.setEnabled(false);
                btnAnterior.setEnabled(false);
                btnProximo.setEnabled(false);
                txtCep.setEditable(true);
                txtHotel.setEditable(false);
                txtComplemento.setEditable(false);
                txtNum.setEditable(true);
                txtCidade.setEditable(false);
                txtEstado.setEditable(false);
                txtRua.setEditable(false);
                txtTelefone.setEditable(false);
                txtBairro.setEditable(false);
                limparTela();
                txtCep.grabFocus();
                lbMensagem.setText("Mensagem: Insira o número do hotel e o cep que deseja consultar");
                break;
            }
            case exibindo:
            {
                txtCep.setEditable(false);
                txtNum.setEditable(false);
                btnConsultar.setEnabled(true);
                lbMensagem.setText("Pressione [Cancelar] para sair");
                break;
            }
        }
    }

    protected enum SituacaoAtual
    {
        navegando, consultando, alterando, criando, exibindo
    }

    SituacaoAtual situacaoAtual;
    
    protected class CriarRegistro implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            situacaoAtual = SituacaoAtual.criando;
            atualizarTela();
        }
    }

    protected class AtualizarRegistro implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            situacaoAtual = SituacaoAtual.alterando;
            atualizarTela();
        }
    }

    protected class ConsultarRegistro implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            situacaoAtual = SituacaoAtual.consultando;
            atualizarTela();
        }

    }

    protected class DeletarRegistro implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(JOptionPane.showConfirmDialog(null,
                    "Deseja excluir o registro atual permanentemente?",
                    "Exclusão de registro",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            {
                try {
                    DAOHoteis.excluir(txtNum.getText(), txtCep.getText());
                    /*Hoteis = DAOHoteis.getHoteis();
                    Hoteis.first();*/
                    situacaoAtual = SituacaoAtual.navegando;
                    atualizarTela();

                }
                catch (Exception err)
                {
                    JOptionPane.showMessageDialog(null,
                            err.getMessage(),
                            "Registro inexistente!",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    protected class SalvarRegistro implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            switch (situacaoAtual) {
                case criando: {
                    if (txtHotel.getText().equals("") || txtCep.getText().equals("") || txtCep.getText().length() < 8 || txtNum.getText().equals("")) {
                        lbMensagem.setText("Mensagem: Preencha todos os dados necessários do hotel corretamente!");
                        JOptionPane.showMessageDialog(null,
                                "Preencha todos os dados necessários do hotel corretamente!",
                                "Dados incorretos",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        try {
                            Logradouro hotel = (Logradouro) ClienteWS.getObjeto(Logradouro.class, "https://api.postmon.com.br/v1/cep", txtCep.getText());

                            if (JOptionPane.showConfirmDialog(null,
                                    "Deseja incluir o hotel:\n" + "Nome: " + txtHotel.getText() + "\n" +
                                            "Telefone: " + txtTelefone.getText() + "\n" +
                                            "Número: " + txtNum.getText() + "\n" +
                                            "Complemento: " + txtComplemento.getText() + "\n" +
                                            "Cep: " + txtCep.getText() + "\n" +
                                            "Rua: " + hotel.getLogradouro() + "\n" +
                                            "Bairro: " + hotel.getBairro() + "\n" +
                                            "Cidade: " + hotel.getCidade() + "\n" +
                                            "Estado: " + hotel.getEstado() + "\n",
                                    "Inclusão de registro",
                                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                                try {
                                    DBOHotel novoHotel = new DBOHotel(txtHotel.getText(), Integer.parseInt(txtNum.getText()), txtTelefone.getText(), Integer.parseInt(txtCep.getText()), txtComplemento.getText());
                                    try {
                                        DAOHoteis.incluir(novoHotel);
                                        /*Hoteis = DAOHoteis.getHoteis();
                                        Hoteis.first();*/
                                    } catch (Exception err) {
                                        JOptionPane.showMessageDialog(null,
                                                err.getMessage(),
                                                "Erro de inclusão",
                                                JOptionPane.ERROR_MESSAGE);
                                    }
                                    situacaoAtual = SituacaoAtual.navegando;
                                    atualizarTela();
                                } catch (Exception errodeTraducao)
                                {
                                    JOptionPane.showMessageDialog(null,
                                            "No campo de número e de CEP só é permitido a entrada de números!",
                                            "Erro ao inserir hotel!",
                                            JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                        catch (Exception erro)
                        {
                            JOptionPane.showMessageDialog(null,
                                    "Não foi possível localizar o CEP informado",
                                    "Erro ao inserir hotel!",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;
                }
                case alterando: {
                    if (txtCep.getText().equals("") || txtNum.getText().equals("")) {
                        lbMensagem.setText("Mensagem: Novo Hotel inválido");
                        JOptionPane.showMessageDialog(null,
                                "Digite o CEP e o número do hotel corretamente!",
                                "Novo Hotel inválido",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    else {

                        Logradouro hotel = (Logradouro) ClienteWS.getObjeto(Logradouro.class, "https://api.postmon.com.br/v1/cep", txtCep.getText());

                        if (JOptionPane.showConfirmDialog(null,
                                "Deseja incluir o hotel:\n" + "Nome: " + txtHotel.getText() + "\n" +
                                        "Telefone: " + txtTelefone.getText() + "\n" +
                                        "Número: " + txtNum.getText() + "\n" +
                                        "Complemento: " + txtComplemento.getText() + "\n" +
                                        "Cep: " + txtCep.getText() + "\n" +
                                        "Rua: " + hotel.getLogradouro() + "\n" +
                                        "Bairro: " + hotel.getBairro() + "\n" +
                                        "Cidade: " + hotel.getCidade() + "\n" +
                                        "Estado: " + hotel.getEstado() + "\n",
                                "Atualização de registro",
                                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                                DBOHotel novoHotel = new DBOHotel(txtHotel.getText(), Integer.parseInt(txtNum.getText()), txtTelefone.getText(), Integer.parseInt(txtCep.getText()), txtComplemento.getText());
                                try {
                                    DAOHoteis.atualizar(novoHotel);
                                    /*Hoteis = DAOHoteis.getHoteis();
                                    Hoteis.first();*/
                                } catch (Exception err) {
                                    JOptionPane.showMessageDialog(null,
                                            err.getMessage(),
                                            "Erro ao atualizar hotel!",
                                            JOptionPane.ERROR_MESSAGE);
                                }
                                situacaoAtual = SituacaoAtual.navegando;
                                atualizarTela();
                        }
                    }
                    }
                    break;

                case consultando: {
                    if (txtNum.getText().equals("") || txtCep.getText().equals("")) {
                        lbMensagem.setText("Mensagem: Digite o número e o cep do hotel que deseja consultar!");
                        JOptionPane.showMessageDialog(null,
                                "Digite o CEP e o número do hotel que deseja consultar!!",
                                "Dados Inexistentes",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        try {
                            DBOHotel hotel = DAOHoteis.getHotel(txtNum.getText(), txtCep.getText());
                            Logradouro logradouroHotel = (Logradouro) ClienteWS.getObjeto(Logradouro.class, "https://api.postmon.com.br/v1/cep", txtCep.getText());

                            txtHotel.setText(hotel.getNomeHotel());
                            txtNum.setText(Integer.toString(hotel.getNumero()));
                            txtCep.setText(Integer.toString(hotel.getCEP()));
                            txtComplemento.setText(hotel.getComplemento());
                            txtTelefone.setText(hotel.getTelefone());
                            txtRua.setText(logradouroHotel.getLogradouro());
                            txtCidade.setText(logradouroHotel.getCidade());
                            txtEstado.setText(logradouroHotel.getEstado());
                            txtBairro.setText(logradouroHotel.getBairro());

                            situacaoAtual = SituacaoAtual.exibindo;
                            atualizarTela();
                        }
                        catch (Exception err)
                        {
                            JOptionPane.showMessageDialog(null,
                                    err.getMessage(),
                                    "Erro ao consultar",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;
                }
            }
        }
    }

    protected class CancelarRegistro implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            situacaoAtual = SituacaoAtual.navegando;
            atualizarTela();
        }
    }

    protected class ProximoRegistro implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            /*try {
                Hoteis.next();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }*/
            atualizarTela();
        }
    }

    protected class AnteriorRegistro implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            /*try {
                //Hoteis.previous();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }*/
            atualizarTela();
        }
    }

    protected class MaxLength implements KeyListener
    {
        @Override
        public void keyReleased(KeyEvent e) {
            if (txtHotel.getText().length() >= 50)
                txtHotel.setText(txtHotel.getText().substring(0,50));

            if (txtComplemento.getText().length() >= 20)
                txtComplemento.setText(txtComplemento.getText().substring(0,20));

            if (txtNum.getText().length() >= 5)
                txtNum.setText(txtNum.getText().substring(0,5));

            if (txtCep.getText().length() >= 8)
                txtCep.setText(txtCep.getText().substring(0, 8));

            if (txtTelefone.getText().length() >= 15)
                txtTelefone.setText(txtTelefone.getText().substring(0,15));

            txtCep.setText(txtCep.getText().replaceAll("[^0-9]", ""));
            txtNum.setText(txtNum.getText().replaceAll("[^0-9]", ""));
            txtTelefone.setText(txtTelefone.getText().replaceAll("[^0-9]", ""));
        }

        @Override
        public void keyPressed(KeyEvent e)
        {}

        @Override
        public void keyTyped(KeyEvent e) {}
    }

    protected class FechamentoDeJanela extends WindowAdapter
    {
        public void windowClosing (WindowEvent e)
        {
            System.exit(0);
        }
    }
}