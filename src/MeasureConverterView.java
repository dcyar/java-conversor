import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;

public class MeasureConverterView extends JFrame implements ActionListener {
    JLabel from, to;
    JComboBox fromCombo, toCombo;
    ArrayList<String> currencyOptions = new ArrayList<>(Arrays.asList("CM", "M", "KM"));
    JButton convert, exit;
    MeasureManager measureManager = new MeasureManager();

    MeasureConverterView() {
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
        this.setTitle("Conversor de medidas");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(300, 200);
        this.setResizable(false);
    }

    private int getMeasure(String from, String to) {
        Boolean valid = false;
        int amount = 0;

        while (!valid) {
            try {
                String input = JOptionPane.showInputDialog(
                        null,
                        "Ingresa la medida a convertir (" + from + "->" + to + ")",
                        "Medida a convertir",
                        JOptionPane.PLAIN_MESSAGE
                );

                if (input == null || input.equals("0")) {
                    return amount;
                }

                amount = Integer.parseInt(input);
                valid = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingresa una medida válida", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        return amount;
    }

    private void printResult(String measure, Double val) {
        JOptionPane.showMessageDialog(null, "Son " + val + " " + measure);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(convert)) {
            if (fromCombo.getSelectedItem() == toCombo.getSelectedItem()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Selecciona otro tipo de medida para convertir",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            int measure = this.getMeasure(
                    (String) fromCombo.getSelectedItem(),
                    (String) toCombo.getSelectedItem()
            );

            if(measure != 0) {
                Double val = this.measureManager.convert(
                        (String) fromCombo.getSelectedItem(),
                        (String) toCombo.getSelectedItem(),
                        measure
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
