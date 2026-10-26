import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class projetoAvaliativo extends JFrame {
    JTextField cliente, placa;
    JComboBox<String> servico;
    JCheckBox cera, interior;
    JLabel valor;
    JTable tabela;

    double total = 0;

    public projetoAvaliativo() {

        setTitle("Lava-Rápido");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        painel.setBackground(new Color(235, 242, 250));

        // titulo

        JLabel titulo = new JLabel("Lava Rapido");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground((new Color(20, 70, 130)));

        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        painel.add(titulo, BorderLayout.NORTH);

        // formulario


        JPanel formulario = new JPanel(new GridLayout(6, 2, 8, 8));
        formulario.setBackground(Color.WHITE);
        formulario.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        formulario.add(new JLabel("Nome: "));
        cliente = new JTextField();
        formulario.add(cliente);

        formulario.add(new JLabel("Placa: "));
        placa = new JTextField();
        formulario.add(placa);

        formulario.add(new JLabel("Serviço: "));

        servico = new JComboBox<>(new String[]{
                "Lavagem Simples - R$ 30",
                "Lavagem Completa - R$ 50",
                "Lavagem Premium - R$ 70"
        });

        formulario.add(servico);

        formulario.add(new JLabel("Adicionais: "));

        JPanel adicionais = new JPanel();
        adicionais.setBackground(Color.WHITE);

        cera = new JCheckBox("Cera+ R$ 20");
        interior = new JCheckBox("Limpeza interna = R$ 30");

        adicionais.add(cera);
        adicionais.add(interior);

        formulario.add(adicionais);

        formulario.add(new JLabel("Valor: "));

        valor = new JLabel("R$ 30");
        valor.setFont(new Font("Arial", Font.BOLD, 18));
        valor.setForeground(new Color(0, 130, 60));

        formulario.add(valor);


        JButton adicionar = new JButton("Adicionar");
        JButton limpar = new JButton("Limpar");


        formulario.add(limpar);
        formulario.add(adicionar);

        painel.add(formulario, BorderLayout.WEST);


        // tabela

        tabela = new JTable(new DefaultTableModel(new String[]{
    "Cliente", "Placa", "Serviço", "Valor"}, 0

        ));

        painel.add(new JScrollPane(tabela), BorderLayout.CENTER);

        // eventos

        servico.addActionListener(e -> calcular());
        cera.addActionListener(e -> calcular());
        interior.addActionListener(e -> calcular());

        adicionar.addActionListener(e -> {

            if (cliente.getText().isEmpty() ||
                    placa.getText().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Preencha o nome e a placa!"
                );

                return;
            }

            double preco = calcular();

            DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();

            modelo.addRow(new Object[]{
                    cliente.getText(),
                    placa.getText().toUpperCase(),
                    servico.getSelectedItem(),
                    String.format("R$ %.2f", preco)
            });
            total += preco;

            limparCampos();

        });

        limpar.addActionListener(e -> limparCampos());

        add(painel);


    }

     // calculo preço

    double calcular() {
        double preco = 30;

        if (servico.getSelectedIndex() == 1)
            preco = 50;

        if (servico.getSelectedIndex() == 2)
            preco = 70;

        if (cera.isSelected())
            preco += 20;

        if (interior.isSelected())
            preco += 30;

        valor.setText(String.format("R$ %.2f", preco));

        return preco;

    }

    //limpar campo

    void limparCampos(){
        cliente.setText("");
        placa.setText("");

        servico.setSelectedIndex(0);

        cera.setSelected(false);
        interior.setSelected(false);

        calcular();

        cliente.requestFocus();





    }


    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new projetoAvaliativo().setVisible(true);
        });
    }

}
