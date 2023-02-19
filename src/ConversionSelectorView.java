import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class ConversionSelectorView extends JFrame implements ActionListener {
    JLabel label;
    JComboBox comboBox;
    ArrayList<String> conversorOptions = new ArrayList<>(Arrays.asList("Conversor de moneda", "Conversor de medidas"));
    JButton ok, exit;

    ConversionSelectorView() {
        this.setupViewComponents();
        this.setupFrame();
        this.addComponentsToFrame();

        this.setVisible(true);
    }

    private void addComponentsToFrame() {
        this.add(label);
        this.add(comboBox);
        this.add(ok);
        this.add(exit);
    }

    private void setupFrame() {
        this.setTitle("Conversor");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(300, 200);
        this.setResizable(false);
    }

    private void setupViewComponents() {
        label = new JLabel("Seleccione el tipo de conversión");
        label.setBounds(20, 30, 300, 15);

        comboBox = new JComboBox<>(conversorOptions.toArray());
        comboBox.setBounds(20, 60, 260, 30);
        comboBox.setAlignmentX(JComboBox.CENTER_ALIGNMENT);

        ok = new JButton();
        ok.setText("Ok");
        ok.setBounds(65, 110, 80, 30);
        ok.addActionListener(this);

        exit = new JButton();
        exit.setText("Salir");
        exit.setBounds(165, 110, 80, 30);
        exit.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispose();
        if(e.getSource() == exit) {
            System.exit(0);
        }

        switch (comboBox.getSelectedIndex()) {
            case 0:
                new CurrencyConverterView();
                break;
            case 1:
                new MeasureConverterView();
                break;
        }
    }
}
