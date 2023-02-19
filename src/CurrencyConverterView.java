import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;

public class CurrencyConverterView extends JFrame implements ActionListener {
    JLabel from, to;
    JComboBox fromCombo, toCombo;
    ArrayList<String> currencyOptions = new ArrayList<>(Arrays.asList("PEN", "USD", "EUR", "MXN", "COP"));
    JButton convert, exit;
    ExchangeRateManager exchangeRateManager = new ExchangeRateManager();

    CurrencyConverterView() {
        this.setupViewComponents();
        this.setupFrame();
        this.addComponentsToFrame();

        this.setVisible(true);
    }

    private void addComponentsToFrame() {
        this.add(from);
        this.add(to);
        this.add(fromCombo);
        this.add(toCombo);
        this.add(convert);
        this.add(exit);
    }

    private void setupViewComponents() {
        from = new JLabel("De:");
        from.setBounds(35, 20, 50, 15);
        fromCombo = new JComboBox<>(currencyOptions.toArray());
        fromCombo.setBounds(35, 40, 100, 30);

        to = new JLabel("Convertir a:");
        to.setBounds(165, 20, 100, 15);
        toCombo = new JComboBox<>(currencyOptions.toArray());
        toCombo.setBounds(165, 40, 100, 30);

        convert = new JButton();
        convert.setText("Convertir");
        convert.setBounds(35, 100, 100, 30);
        convert.addActionListener(this);

        exit = new JButton();
        exit.setText("Atrás");
        exit.setBounds(165, 100, 100, 30);
        exit.addActionListener(this);
    }

    private void setupFrame() {
        this.setTitle("Conversor de monedas");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(300, 200);
        this.setResizable(false);
    }

    private int getAmount(String from, String to) {
        Boolean valid = false;
        int amount = 0;

        while (!valid) {
            try {
                String input = JOptionPane.showInputDialog(
                        null,
                        "Ingresa el monto a convertir (" + from + "->" + to + ")",
                        "Monto a convertir",
                        JOptionPane.PLAIN_MESSAGE
                );

                if (input == null || input.equals("0")) {
                    return amount;
                }

                amount = Integer.parseInt(input);
                valid = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingresa un número válido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        return amount;
    }

    private void printResult(String exchange, Double val) {
        Formatter f = new Formatter();
        String message = String.valueOf(f.format("Tienes %s %.2f", exchange, val));
        JOptionPane.showMessageDialog(null, message);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source.equals(convert)) {
            if (fromCombo.getSelectedItem() == toCombo.getSelectedItem()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Selecciona otro tipo de modena para convertir",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            int amount = this.getAmount(
                    (String) fromCombo.getSelectedItem(),
                    (String) toCombo.getSelectedItem()
            );

            if(amount != 0) {
                Double val = this.exchangeRateManager.convert(
                        (String) fromCombo.getSelectedItem(),
                        (String) toCombo.getSelectedItem(),
                        amount
                );

                this.printResult((String) toCombo.getSelectedItem(), val);
            }
        }

        if (source.equals(exit)) {
            this.dispose();
            new ConversionSelectorView();
        }
    }
}
