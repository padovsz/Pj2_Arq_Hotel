import bd.core.MeuResultSet;
import bd.daos.DAOCandidatos;
import bd.dbos.DBOCandidato;
import bd.dbos.DBOCargo;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import javax.swing.text.MaskFormatter;
import java.io.*;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.ScrollPaneConstants;

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
    protected JLabel lbCandidato = new JLabel("Candidato:");
    protected JLabel lbNum = new JLabel("Num:");
    protected JLabel lbPartido = new JLabel("Partido:");
    protected JLabel lbCargo = new JLabel("Cargo:");
    protected JLabel lbUF = new JLabel("UF:");

    protected JTextField txtCandidato = new JTextField();
    protected JTextField txtNum = new JTextField();
    protected JTextField txtPartido = new JTextField();
    protected JTextField txtCargo = new JTextField();
    protected JTextField txtUF = new JTextField();
    

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

    protected JTable tabela = new JTable(data, nomeCampos);
    {
        tabela.setEnabled(false);
    }

    JScrollPane scroll = new JScrollPane(tabela,
                                         ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                                         ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    MeuResultSet candidatos;
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

        GroupLayout grpComponentes = new GroupLayout(pnlComponentes);
        pnlComponentes.setLayout(grpComponentes);
        grpComponentes.setAutoCreateGaps(true);
        grpComponentes.setAutoCreateContainerGaps(true);
        grpComponentes.setHorizontalGroup(
                grpComponentes.createSequentialGroup()
                        .addGroup(grpComponentes.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(lbCandidato)
                                .addComponent(lbNum)
                                .addComponent(lbPartido)
                                .addComponent(lbCargo)
                                .addComponent(lbUF)
                                .addComponent(btnAnterior))
                        .addGroup(grpComponentes.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(txtCandidato, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNum)
                                .addComponent(txtPartido)
                                .addComponent(txtCargo)
                                .addComponent(txtUF)
                                .addComponent(btnProximo))
        );
        grpComponentes.setVerticalGroup(
                grpComponentes.createSequentialGroup()
                        .addGroup(grpComponentes.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lbCandidato)
                                .addComponent(txtCandidato))
                        .addGroup(grpComponentes.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lbNum)
                                .addComponent(txtNum))
                        .addGroup(grpComponentes.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lbPartido)
                                .addComponent(txtPartido))
                        .addGroup(grpComponentes.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lbCargo)
                                .addComponent(txtCargo))
                        .addGroup(grpComponentes.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lbUF)
                                .addComponent(txtUF))
                        .addGroup(grpComponentes.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(btnAnterior)
                                .addComponent(btnProximo))
        );

        JPanel pnlResultado = new JPanel();
        FlowLayout flwResultado = new FlowLayout();
        pnlResultado.setLayout(flwResultado);

        pnlResultado.add(scroll);

        Container cntForm = this.getContentPane();
        cntForm.setLayout(new BorderLayout());
        cntForm.add(pnlBotoes,  BorderLayout.NORTH);
        cntForm.add(pnlComponentes, BorderLayout.WEST);
        cntForm.add(pnlResultado, BorderLayout.EAST);
        cntForm.add(pnlMensagem,  BorderLayout.SOUTH);

        this.addWindowListener(new FechamentoDeJanela());

        try{
            candidatos = DAOCandidatos.getCandidatos();
            candidatos.first();
        }
        catch (Exception err)
        {
            System.out.println(err.getMessage());
        }

        situacaoAtual = SituacaoAtual.navegando;
        atualizarTela();

        this.setSize(800, 300);
        this.setVisible(true);
    }

    protected void limparTela()
    {
        txtCandidato.setText("");
        txtCargo.setText("");
        txtNum.setText("");
        txtPartido.setText("");
        txtUF.setText("");
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
                        if (candidatos.isFirst()) {
                            btnAnterior.setEnabled(false);
                        }
                        if (candidatos.isLast()) {
                            btnProximo.setEnabled(false);
                        }

                        txtCandidato.setText(candidatos.getString("Nome do Candidato"));
                        txtNum.setText(Integer.toString(candidatos.getInt("número")));
                        txtPartido.setText(candidatos.getString("partido"));
                        txtCargo.setText(candidatos.getString("Cargo"));
                        txtUF.setText(candidatos.getString("UF"));
                    } catch (Exception err) {
                    }
                    btnAtualizar.setEnabled(true);
                    btnConsultar.setEnabled(true);
                    btnDeletar.setEnabled(true);
                    btnCancelar.setEnabled(true);
                    btnCriar.setEnabled(true);
                    btnSalvar.setEnabled(true);
                    txtCandidato.setEnabled(false);
                    txtNum.setEnabled(false);
                    txtCargo.setEnabled(false);
                    txtPartido.setEnabled(false);
                    txtUF.setEnabled(false);
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
                txtCandidato.setEnabled(true);
                txtNum.setEnabled(true);
                txtCargo.setEnabled(true);
                txtPartido.setEnabled(true);
                txtUF.setEnabled(true);
                lbMensagem.setText("Mensagem: Insira os dados do novo candidato");
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
                txtNum.setEnabled(false);
                txtCandidato.setEnabled(true);
                txtCargo.setEnabled(true);
                txtPartido.setEnabled(true);
                txtUF.setEnabled(true);
                lbMensagem.setText("Mensagem: Insira os novos dados do candidato");
                break;
            }
        }
    }

    protected enum SituacaoAtual
    {
        navegando, consultando, alterando, criando, excluindo
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
            //codigo pra quando clicar no botão Consultar
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
                    DAOCandidatos.excluir(Integer.valueOf(txtNum.getText()));
                    candidatos = DAOCandidatos.getCandidatos();
                    candidatos.first();
                    situacaoAtual = SituacaoAtual.navegando;
                    atualizarTela();
                }
                catch (Exception err)
                {
                    JOptionPane.showMessageDialog(null,
                            err.getMessage(),
                            "Registro inválido!",
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
                    if (txtCandidato.getText() == "" || txtCargo.getText() == "" || txtPartido.getText() == "" || txtNum.getText() == "" || txtUF.getText() == "")
                        lbMensagem.setText("Mensagem: Preencha os dados de candidato!");
                    else {
                        DBOCandidato novoCandidato = new DBOCandidato(txtCandidato.getText(), Integer.parseInt(txtNum.getText()), txtPartido.getText(), Integer.parseInt(txtCargo.getText()));
                        try {
                            DAOCandidatos.incluir(novoCandidato);
                            candidatos = DAOCandidatos.getCandidatos();
                        } catch (Exception err) {
                            JOptionPane.showMessageDialog(null,
                                    err.getMessage(),
                                    "Erro de inclusão",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                        situacaoAtual = SituacaoAtual.navegando;
                        atualizarTela();
                    }
                    break;
                }
                case alterando: {
                    if (txtCandidato.getText() == "" || txtCargo.getText() == "" || txtPartido.getText() == "" || txtNum.getText() == "" || txtUF.getText() == "")
                        lbMensagem.setText("Mensagem: Novo candidato inválido");
                    else
                    {
                        DBOCandidato novoCandidato = new DBOCandidato(txtCandidato.getText(), Integer.parseInt(txtNum.getText()), txtPartido.getText(), Integer.parseInt(txtCargo.getText()));
                        try {
                            DAOCandidatos.atualizar(novoCandidato);
                            candidatos = DAOCandidatos.getCandidatos();
                        } 
                        catch (Exception err)
                        {
                            JOptionPane.showMessageDialog(null,
                                    err.getMessage(),
                                    "Erro de atualização",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                        situacaoAtual = SituacaoAtual.navegando;
                        atualizarTela();
                    }
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
                candidatos.next();
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
                candidatos.previous();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            atualizarTela();
        }
    }

    protected class FechamentoDeJanela extends WindowAdapter
    {
        public void windowClosing (WindowEvent e)
        {
            System.exit(0);
        }
    }
}