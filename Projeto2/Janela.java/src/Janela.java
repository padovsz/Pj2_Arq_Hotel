import bd.core.MeuResultSet;
import bd.daos.DAOHoteis;
import bd.dbos.DBOHotel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.sql.SQLException;

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

    protected JTextField txtHotel = new JTextField();
    protected JTextField txtCep = new JTextField();
    protected JTextField txtRua = new JTextField();
    protected JTextField txtNum = new JTextField();
    protected JTextField txtComplemento = new JTextField();
    protected JTextField txtCidade = new JTextField();
    protected JTextField txtEstado = new JTextField();
    protected JTextField txtTelefone = new JTextField();
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
    MeuResultSet Hoteis;
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
                                .addComponent(lbNum)
                                .addComponent(lbCEP)
                                .addComponent(lbRua)
                                .addComponent(lbComplemento)
                                .addComponent(lbCidade)
                                .addComponent(lbEstado)
                                .addComponent(lbTelefone)
                                .addComponent(pnlBotoesCentralizados))
                        .addGroup(grpComponentes.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(txtHotel, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNum)
                                .addComponent(txtCep)
                                .addComponent(txtRua)
                                .addComponent(txtComplemento)
                                .addComponent(txtCidade)
                                .addComponent(txtEstado)
                                .addComponent(txtTelefone))
                        .addComponent(pnlBotoesCentralizados)
        );
        grpComponentes.setVerticalGroup(
                grpComponentes.createSequentialGroup()
                        .addGroup(grpComponentes.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lbHotel)
                                .addComponent(txtHotel))
                        .addGroup(grpComponentes.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lbNum)
                                .addComponent(txtNum))
                        .addGroup(grpComponentes.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lbCEP)
                                .addComponent(txtCep))
                        .addGroup(grpComponentes.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lbRua)
                                .addComponent(txtRua))
                        .addGroup(grpComponentes.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lbComplemento)
                                .addComponent(txtComplemento))
                        .addGroup(grpComponentes.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lbCidade)
                                .addComponent(txtCidade))
                        .addGroup(grpComponentes.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lbEstado)
                                .addComponent(txtEstado))
                        .addGroup(grpComponentes.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lbTelefone)
                                .addComponent(txtTelefone))
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
            Hoteis = DAOHoteis.getHoteis();
            Hoteis.first();
        }
        catch (Exception err)
        {
            JOptionPane.showMessageDialog (null,
                    err.getMessage(),
                    "Erro ao exibir candidatos",
                    JOptionPane.WARNING_MESSAGE);
        }

        situacaoAtual = SituacaoAtual.navegando;
        atualizarTela();

        this.setSize(700, 350);
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
    }

    protected void atualizarTela()
    {
        switch(situacaoAtual)
        {
            case navegando:
            {
                    btnProximo.setEnabled(true);
                    btnAnterior.setEnabled(true);
                    try {
                        if (Hoteis.isFirst()) {
                            btnAnterior.setEnabled(false);
                        }
                        if (Hoteis.isLast()) {
                            btnProximo.setEnabled(false);
                        }

                        txtHotel.setText(Hoteis.getString("Nome Hotel"));
                        txtNum.setText(Integer.toString(Hoteis.getInt("numero")));
                        txtCep.setText(Hoteis.getString("cep"));
                        txtComplemento.setText(Hoteis.getString("complemento"));
                        txtCidade.setText(Hoteis.getString("cidade")); //Informaçao do json
                        txtEstado.setText(Hoteis.getString("estado")); //Informaçao do json
                        txtRua.setText(Hoteis.getString("rua"));
                        txtTelefone.setText(Hoteis.getString("numero"));  //Informação json
                    } catch (Exception err)
                    {}
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
                    lbComplemento.setText("Complemento");
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
                txtRua.setEditable(true);
                txtComplemento.setEditable(true);
                txtNum.setEditable(true);
                txtCidade.setEditable(false);
                lbComplemento.setText("Nº Hotel:");
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
                txtNum.setEditable(true);
                txtCidade.setEditable(false);
                lbComplemento.setText("N° Hotel:");
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
                txtRua.setEditable(true);
                txtHotel.setEditable(false);
                txtComplemento.setEditable(false);
                txtNum.setEditable(false);
                txtCidade.setEditable(false);
                txtRua.setText("");
                txtRua.grabFocus();
                lbMensagem.setText("Mensagem: Insira o número do hotel que deseja consultar");
                break;
            }
            case exibindo:
            {
                txtRua.setEditable(false);
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
                    DAOHoteis.excluir(Integer.valueOf(txtNum.getText()), Integer.valueOf(txtCep.getText()));
                    Hoteis = DAOHoteis.getHoteis();
                    Hoteis.first();
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
                    if (txtHotel.getText().equals("") || txtComplemento.getText().equals("") || txtNum.getText().equals("") || txtRua.getText().equals(""))
                        lbMensagem.setText("Mensagem: Preencha todos os dados de candidato!");
                    else {
                        try {
                            DBOHotel cargo = DAOHoteis.getCargo(Integer.valueOf(txtComplemento.getText()));

                        if(JOptionPane.showConfirmDialog(null,
                                "Deseja incluir o candidato:\n" + "Nome: " + txtHotel.getText() + "\n" +
                                                                        "Número: " + txtRua.getText() + "\n" +
                                                                        "Partido: " +  txtNum.getText() + "\n" +
                                                                        "Cargo: " + txtComplemento.getText() + " - " + cargo.getNomeCargo() +  "\n" +
                                                                        "UF: " + cargo.getUF(),
                                "Inclusão de registro",
                                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                        {
                            DBOHotel novoCandidato = new DBOHotel(txtHotel.getText(), Integer.parseInt(txtRua.getText()), txtNum.getText(), Integer.parseInt(txtComplemento.getText()));
                            try {
                                DAOHoteis.incluir(novoCandidato);
                                Hoteis = DAOHoteis.getCandidatos();
                                Hoteis.first();
                            } catch (Exception err) {
                                JOptionPane.showMessageDialog(null,
                                        err.getMessage(),
                                        "Erro de inclusão",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                            situacaoAtual = SituacaoAtual.navegando;
                            atualizarTela();
                        }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null,
                                    "Cargo fornecido não existe",
                                    "Cargo inexistente!",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;
                }
                case alterando: {
                    if (txtHotel.getText().equals("") || txtComplemento.getText().equals("") || txtNum.getText().equals("") || txtRua.getText().equals("") || txtCidade.getText().equals(""))
                        lbMensagem.setText("Mensagem: Novo candidato inválido");
                    else
                    {
                        DBOCargo cargo = null;
                        try {
                            cargo = DAOCargos.getCargo(Integer.valueOf(txtComplemento.getText()));

                        if(JOptionPane.showConfirmDialog(null,
                                "Deseja incluir o candidato:\n" + "Nome: " + txtHotel.getText() + "\n" +
                                        "Número: " + txtRua.getText() + "\n" +
                                        "Partido: " +  txtNum.getText() + "\n" +
                                        "Cargo: " + txtComplemento.getText() + " - " + cargo.getNomeCargo() +  "\n" +
                                        "UF: " + cargo.getUF(),
                                "Atualização de registro",
                                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                            DBOHotel novoCandidato = new DBOHotel(txtHotel.getText(), Integer.parseInt(txtRua.getText()), txtNum.getText(), Integer.parseInt(txtComplemento.getText()));
                            try {
                                DAOHoteis.atualizar(novoCandidato);
                                Hoteis = DAOHoteis.getCandidatos();
                                Hoteis.first();
                            } catch (Exception err) {
                                JOptionPane.showMessageDialog(null,
                                        err.getMessage(),
                                        "Erro ao atualizar candidato!",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                            situacaoAtual = SituacaoAtual.navegando;
                            atualizarTela();
                        }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null,
                                    ex.getMessage(),
                                    "Cargo inexistente!",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;
                }
                case consultando: {
                    if (txtRua.getText() == "")
                        lbMensagem.setText("Mensagem: Digite o número do candidato que deseja consultar!");
                    else
                    {
                        try {
                            DBOHotel candidato = DAOHoteis.getHotel(Integer.valueOf(txtRua.getText()));

                            txtHotel.setText(candidato.getNomeHotel());
                            txtRua.setText(Integer.toString(candidato.getNumero()));
                            txtNum.setText(candidato.getTelefone());
                            DBOCargo cargo = DAOCargos.getCargo(candidato.getCEP());
                            txtComplemento.setText(cargo.getNomeCargo());
                            txtCidade.setText(cargo.getUF());

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
            try {
                Hoteis.next();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            atualizarTela();
        }
    }

    protected class AnteriorRegistro implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Hoteis.previous();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            atualizarTela();
        }
    }

    protected class MaxLength implements KeyListener
    {
        @Override
        public void keyPressed(KeyEvent e) {
            if (txtHotel.getText().length() >= 50)
                txtHotel.setText(txtHotel.getText().substring(0,49));

            if (txtComplemento.getText().length() >= 10)
                txtComplemento.setText(txtComplemento.getText().substring(0,9));

            if (txtNum.getText().length() >= 5)
                txtNum.setText(txtNum.getText().substring(0,4));

            if(txtCep.getText().length() >= 8)
                txtCep.setText(txtCep.getText().substring(0, 7));

            if(txtTelefone.getText().length() >= 20)
                txtTelefone.setText(txtTelefone.getText().substring(0,19));
        }

        @Override
        public void keyReleased(KeyEvent e) {}

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