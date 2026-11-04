import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import javax.swing.*;

public class DataSetGUI extends JFrame implements ActionListener
{
    private JLabel lblTitle;
    private JLabel lblCategory;
    private JLabel lblValue1;
    private JLabel lblValue2;
    private JLabel lblValue3;

    private JTextField txtValue1;
    private JTextField txtValue2;
    private JTextField txtValue3;

    private JComboBox<String> cboCategory;

    private JButton btnCalculate;
    private JButton btnClear;
    private JButton btnExit;

    private JTextArea txtResult;

    public DataSetGUI()
    {
        setTitle("DataSet Calculator");
        setSize(550, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Container pane = getContentPane();
        pane.setLayout(new BorderLayout());

        // TITLE 
        lblTitle = new JLabel("DATASET CALCULATOR");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));

        JPanel titlePanel = new JPanel();
        titlePanel.add(lblTitle);

        pane.add(titlePanel, BorderLayout.NORTH);

        //CENTER PANEL 
        JPanel centerPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        lblCategory = new JLabel("Category");
        lblValue1 = new JLabel("Value 1");
        lblValue2 = new JLabel("Value 2");
        lblValue3 = new JLabel("Value 3");

        cboCategory = new JComboBox<>();
        cboCategory.addItem("Bank");
        cboCategory.addItem("Coin");
        cboCategory.addItem("Student");

        txtValue1 = new JTextField();
        txtValue2 = new JTextField();
        txtValue3 = new JTextField();

        centerPanel.add(lblCategory);
        centerPanel.add(cboCategory);

        centerPanel.add(lblValue1);
        centerPanel.add(txtValue1);

        centerPanel.add(lblValue2);
        centerPanel.add(txtValue2);

        centerPanel.add(lblValue3);
        centerPanel.add(txtValue3);

        pane.add(centerPanel, BorderLayout.CENTER);

        //BUTTON PANEL
        btnCalculate = new JButton("Calculate");
        btnClear = new JButton("Clear");
        btnExit = new JButton("Exit");

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(btnCalculate);
        buttonPanel.add(btnClear);
        buttonPanel.add(btnExit);

        //RESULT AREA
        txtResult = new JTextArea(8, 30);
        txtResult.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(txtResult);

        JPanel southPanel = new JPanel(new BorderLayout());

        southPanel.add(buttonPanel, BorderLayout.NORTH);
        southPanel.add(scrollPane, BorderLayout.CENTER);

        pane.add(southPanel, BorderLayout.SOUTH);

        btnCalculate.addActionListener(this);
        btnClear.addActionListener(this);
        btnExit.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == btnCalculate)
        {
            try
            {
                double v1 =
                        Double.parseDouble(txtValue1.getText());

                double v2 =
                        Double.parseDouble(txtValue2.getText());

                double v3 =
                        Double.parseDouble(txtValue3.getText());

                String category =
                        cboCategory.getSelectedItem().toString();

                DataSet data = new DataSet();

                if(category.equals("Bank"))
                {
                    data.add(new BankAccount(v1));
                    data.add(new BankAccount(v2));
                    data.add(new BankAccount(v3));
                }

                else if(category.equals("Coin"))
                {
                    data.add(new Coin(v1,"Quarter"));
                    data.add(new Coin(v2,"Dime"));
                    data.add(new Coin(v3,"Nickel"));
                }

                else if(category.equals("Student"))
                {
                    data.add(new Student("Ali",v1));
                    data.add(new Student("Alex",v2));
                    data.add(new Student("Maria",v3));
                }

                DecimalFormat df =
                        new DecimalFormat("0.00");

                Measurable max = data.getMax();

                String highestName = max.getName();

                if(highestName.equals(""))
                {
                    highestName = "N/A";
                }

                txtResult.setText(
                        "Category : " + category + "\nAverage : " + df.format(data.getAverage()) + "\nMaximum : " + df.format(max.getMeasure()) + "\nHighest Name : " + highestName);
            }

            catch(NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers!", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        else if(e.getSource() == btnClear)
        {
            txtValue1.setText("");
            txtValue2.setText("");
            txtValue3.setText("");

            txtResult.setText("");
        }

        else if(e.getSource() == btnExit)
        {
            System.exit(0);
        }
    }

    public static void main(String[] args)
    {
        new DataSetGUI();
    }
}

//References: Copilot