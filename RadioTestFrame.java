package matrixGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class RadioTestFrame extends JFrame {

    private class cellAction implements ActionListener {
        int col;
        int i;
        int j;
        int row;

        public cellAction(int i, int j, int col, int row) {
            this.i = i;
            this.j = j;
            this.col = col;
            this.row = row;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (j < col - 1) {
                cells[i][j + 1].requestFocusInWindow();
            }
            else {
                if (i < row - 1 && j < col) {
                    cells[i + 1][0].requestFocusInWindow();
                }
                else {
                    btnAction.requestFocusInWindow();
                }
            }

        }
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private JButton btnAction;

    private JTextField[][] cells;
    private int col;
    private JComboBox<Integer> colBox;
    private JSlider colSlider;
    private JPanel contentPane;
    private JPanel field1Label;
    private JPanel field2Label;
    private JPanel field3Label;
    private int[][] intCells;
    private JPanel matrixPanel;
    private JComboBox<Integer> multBox;
    private final ButtonGroup operationsGroup = new ButtonGroup();
    private JRadioButton rbCreateGrid;
    private JRadioButton rbCreateMatrix;
    private JRadioButton rbMultAddRows;
    private JRadioButton rbMultRow;
    private JRadioButton rbSwapRows;
    private int row;
    private JComboBox<Integer> rowBox;
    private JSlider rowSlider;

    private Matrix worker;

    /**
     * Create the frame.
     */
    public RadioTestFrame() {
        setTitle("Matrix Row Operations");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 550);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnFile = new JMenu("File");
        menuBar.add(mnFile);

        JMenuItem mntmNew = new JMenuItem("New");
        mntmNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                newAction();
            }

        });
        mnFile.add(mntmNew);

        JMenuItem mntmOpen = new JMenuItem("Open");
        mntmOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                JOptionPane.showMessageDialog(matrixPanel, "Coming soon!");
            }
        });
        mnFile.add(mntmOpen);

        JMenuItem mntmSave = new JMenuItem("Save");
        mntmSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(matrixPanel, "Coming soon!");

            }
        });
        mnFile.add(mntmSave);

        JSeparator separator = new JSeparator();
        mnFile.add(separator);

        JMenuItem mntmExit = new JMenuItem("Exit");
        mntmExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
        mnFile.add(mntmExit);

        JMenu mnHelp = new JMenu("Help");
        menuBar.add(mnHelp);

        JMenuItem mntmAbout = new JMenuItem("About");
        mntmAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(matrixPanel,
                        "Created by: Nic Jackson \n Version: 5.27.13");

            }
        });
        mnHelp.add(mntmAbout);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel operationsPanel = new JPanel();
        contentPane.add(operationsPanel, BorderLayout.WEST);
        GridBagLayout gbl_operationsPanel = new GridBagLayout();
        gbl_operationsPanel.columnWidths = new int[] { 91, 0 };
        gbl_operationsPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
        gbl_operationsPanel.columnWeights = new double[] { 1.0,
                Double.MIN_VALUE };
        gbl_operationsPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
                0.0, Double.MIN_VALUE };
        operationsPanel.setLayout(gbl_operationsPanel);

        field1Label = new JPanel();
        field1Label.setBorder(new TitledBorder(UIManager
                .getBorder("TitledBorder.border"), "Rows",
                TitledBorder.LEADING, TitledBorder.TOP, null, null));
        GridBagConstraints gbc_field1Label = new GridBagConstraints();
        gbc_field1Label.fill = GridBagConstraints.BOTH;
        gbc_field1Label.insets = new Insets(0, 0, 5, 0);
        gbc_field1Label.gridx = 0;
        gbc_field1Label.gridy = 0;
        operationsPanel.add(field1Label, gbc_field1Label);
        GridBagLayout gbl_field1Label = new GridBagLayout();
        gbl_field1Label.columnWidths = new int[] { 91, 0 };
        gbl_field1Label.rowHeights = new int[] { 0, 0, 0, 0 };
        gbl_field1Label.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
        gbl_field1Label.rowWeights = new double[] { 0.0, 0.0, 0.0,
                Double.MIN_VALUE };
        field1Label.setLayout(gbl_field1Label);

        rowBox = new JComboBox<Integer>();
        rowBox.setModel(new DefaultComboBoxModel<Integer>(new Integer[] { 3, 4,
                5, 6, 7, 8, 9, 10 }));
        GridBagConstraints gbc_rowBox = new GridBagConstraints();
        gbc_rowBox.insets = new Insets(0, 0, 5, 0);
        gbc_rowBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_rowBox.gridx = 0;
        gbc_rowBox.gridy = 0;
        field1Label.add(rowBox, gbc_rowBox);

        rowSlider = new JSlider();
        rowSlider.setVisible(false);
        rowSlider.setMajorTickSpacing(1);
        rowSlider.setPaintTicks(true);
        rowSlider.setPaintLabels(true);
        rowSlider.setSnapToTicks(true);
        GridBagConstraints gbc_rowSlider = new GridBagConstraints();
        gbc_rowSlider.fill = GridBagConstraints.HORIZONTAL;
        gbc_rowSlider.insets = new Insets(0, 0, 5, 0);
        gbc_rowSlider.gridx = 0;
        gbc_rowSlider.gridy = 1;
        field1Label.add(rowSlider, gbc_rowSlider);

        field2Label = new JPanel();
        field2Label.setBorder(new TitledBorder(UIManager
                .getBorder("TitledBorder.border"), "Columns",
                TitledBorder.LEADING, TitledBorder.TOP, null, null));
        GridBagConstraints gbc_field2Label = new GridBagConstraints();
        gbc_field2Label.fill = GridBagConstraints.BOTH;
        gbc_field2Label.insets = new Insets(0, 0, 5, 0);
        gbc_field2Label.gridx = 0;
        gbc_field2Label.gridy = 1;
        operationsPanel.add(field2Label, gbc_field2Label);
        GridBagLayout gbl_field2Label = new GridBagLayout();
        gbl_field2Label.columnWidths = new int[] { 91, 0 };
        gbl_field2Label.rowHeights = new int[] { 0, 0, 0, 0 };
        gbl_field2Label.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
        gbl_field2Label.rowWeights = new double[] { 0.0, 0.0, 0.0,
                Double.MIN_VALUE };
        field2Label.setLayout(gbl_field2Label);

        colSlider = new JSlider();
        colSlider.setVisible(false);
        colSlider.setMajorTickSpacing(1);
        colSlider.setPaintLabels(true);
        colSlider.setPaintTicks(true);
        colSlider.setSnapToTicks(true);
        GridBagConstraints gbc_colSlider = new GridBagConstraints();
        gbc_colSlider.insets = new Insets(0, 0, 5, 0);
        gbc_colSlider.gridx = 0;
        gbc_colSlider.gridy = 0;
        field2Label.add(colSlider, gbc_colSlider);

        colBox = new JComboBox<Integer>();
        colBox.setModel(new DefaultComboBoxModel<Integer>(new Integer[] { 3, 4,
                5, 6, 7, 8, 9, 10 }));
        GridBagConstraints gbc_colBox = new GridBagConstraints();
        gbc_colBox.insets = new Insets(0, 0, 5, 0);
        gbc_colBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_colBox.gridx = 0;
        gbc_colBox.gridy = 1;
        field2Label.add(colBox, gbc_colBox);

        field3Label = new JPanel();
        field3Label.setVisible(false);
        field3Label.setBorder(new TitledBorder(UIManager
                .getBorder("TitledBorder.border"), "Multiply Value",
                TitledBorder.LEADING, TitledBorder.TOP, null, null));
        GridBagConstraints gbc_field3Label = new GridBagConstraints();
        gbc_field3Label.fill = GridBagConstraints.BOTH;
        gbc_field3Label.insets = new Insets(0, 0, 5, 0);
        gbc_field3Label.gridx = 0;
        gbc_field3Label.gridy = 2;
        operationsPanel.add(field3Label, gbc_field3Label);
        GridBagLayout gbl_field3Label = new GridBagLayout();
        gbl_field3Label.columnWidths = new int[] { 91, 0 };
        gbl_field3Label.rowHeights = new int[] { 0, 0 };
        gbl_field3Label.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
        gbl_field3Label.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
        field3Label.setLayout(gbl_field3Label);

        multBox = new JComboBox<Integer>();
        multBox.setModel(new DefaultComboBoxModel<Integer>(new Integer[] { 2,
                3, 4, 5, 6, 7, 8, 9, 10 }));
        GridBagConstraints gbc_multBox = new GridBagConstraints();
        gbc_multBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_multBox.gridx = 0;
        gbc_multBox.gridy = 0;
        field3Label.add(multBox, gbc_multBox);

        btnAction = new JButton("Go");
        btnAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedButton();
            }
        });
        GridBagConstraints gbc_btnAction = new GridBagConstraints();
        gbc_btnAction.fill = GridBagConstraints.BOTH;
        gbc_btnAction.insets = new Insets(0, 0, 5, 0);
        gbc_btnAction.gridx = 0;
        gbc_btnAction.gridy = 3;
        operationsPanel.add(btnAction, gbc_btnAction);

        JPanel radioPanel = new JPanel();
        radioPanel.setBorder(new TitledBorder(UIManager
                .getBorder("TitledBorder.border"), "Operations",
                TitledBorder.LEADING, TitledBorder.TOP, null, null));
        GridBagConstraints gbc_radioPanel = new GridBagConstraints();
        gbc_radioPanel.fill = GridBagConstraints.BOTH;
        gbc_radioPanel.gridx = 0;
        gbc_radioPanel.gridy = 4;
        operationsPanel.add(radioPanel, gbc_radioPanel);
        GridBagLayout gbl_radioPanel = new GridBagLayout();
        gbl_radioPanel.columnWidths = new int[] { 93, 0 };
        gbl_radioPanel.rowHeights = new int[] { 23, 23, 23, 23, 23, 0 };
        gbl_radioPanel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
        gbl_radioPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
                Double.MIN_VALUE };
        radioPanel.setLayout(gbl_radioPanel);

        rbCreateMatrix = new JRadioButton("Create Matrix");
        operationsGroup.add(rbCreateMatrix);
        rbCreateMatrix.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createMatrixAction();
            }
        });

        rbCreateGrid = new JRadioButton("Create Grid");
        rbCreateGrid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createGridAction();
            }
        });
        operationsGroup.add(rbCreateGrid);
        rbCreateGrid.setSelected(true);
        GridBagConstraints gbc_rbCreateGrid = new GridBagConstraints();
        gbc_rbCreateGrid.anchor = GridBagConstraints.NORTH;
        gbc_rbCreateGrid.fill = GridBagConstraints.HORIZONTAL;
        gbc_rbCreateGrid.insets = new Insets(0, 0, 5, 0);
        gbc_rbCreateGrid.gridx = 0;
        gbc_rbCreateGrid.gridy = 0;
        radioPanel.add(rbCreateGrid, gbc_rbCreateGrid);
        GridBagConstraints gbc_rbCreateMatrix = new GridBagConstraints();
        gbc_rbCreateMatrix.anchor = GridBagConstraints.NORTHWEST;
        gbc_rbCreateMatrix.insets = new Insets(0, 0, 5, 0);
        gbc_rbCreateMatrix.gridx = 0;
        gbc_rbCreateMatrix.gridy = 1;
        radioPanel.add(rbCreateMatrix, gbc_rbCreateMatrix);

        rbSwapRows = new JRadioButton("Swap Rows");
        rbSwapRows.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swapRowsAction();
            }
        });
        operationsGroup.add(rbSwapRows);
        GridBagConstraints gbc_rbSwapRows = new GridBagConstraints();
        gbc_rbSwapRows.anchor = GridBagConstraints.NORTHWEST;
        gbc_rbSwapRows.insets = new Insets(0, 0, 5, 0);
        gbc_rbSwapRows.gridx = 0;
        gbc_rbSwapRows.gridy = 2;
        radioPanel.add(rbSwapRows, gbc_rbSwapRows);

        rbMultRow = new JRadioButton("Multiply Row");

        rbMultRow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                multRowAction();

            }
        });
        operationsGroup.add(rbMultRow);
        GridBagConstraints gbc_rbMultRow = new GridBagConstraints();
        gbc_rbMultRow.anchor = GridBagConstraints.NORTHWEST;
        gbc_rbMultRow.insets = new Insets(0, 0, 5, 0);
        gbc_rbMultRow.gridx = 0;
        gbc_rbMultRow.gridy = 3;
        radioPanel.add(rbMultRow, gbc_rbMultRow);

        rbMultAddRows = new JRadioButton("MultAdd Rows");
        rbMultAddRows.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                multAddRowsAction();

            }
        });
        operationsGroup.add(rbMultAddRows);
        GridBagConstraints gbc_rbMultAddRows = new GridBagConstraints();
        gbc_rbMultAddRows.anchor = GridBagConstraints.NORTHWEST;
        gbc_rbMultAddRows.gridx = 0;
        gbc_rbMultAddRows.gridy = 4;
        radioPanel.add(rbMultAddRows, gbc_rbMultAddRows);

        matrixPanel = new JPanel();
        contentPane.add(matrixPanel, BorderLayout.CENTER);
        matrixPanel.setLayout(new GridLayout(1, 0, 0, 0));
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    RadioTestFrame frame = new RadioTestFrame();
                    frame.setVisible(true);
                    frame.newAction();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public int[][] createArray(int rows, int cols) {
        intCells = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                intCells[i][j] = getValue(cells[i][j]);
                if (getValue(cells[i][j]) == 0) {
                    cells[i][j].setText("0");
                }
            }
        }
        return intCells;
    }

    public JButton getBtnAction() {
        return btnAction;
    }

    public JComboBox<Integer> getColBox() {
        return colBox;
    }

    public JSlider getColSlider() {
        return colSlider;
    }

    public JPanel getField1Label() {
        return field1Label;
    }

    public JPanel getField2Label() {
        return field2Label;
    }

    public JPanel getField3Label() {
        return field3Label;
    }

    public JPanel getMatrixPanel() {
        return matrixPanel;
    }

    public JRadioButton getRbCreateGrid() {
        return rbCreateGrid;
    }

    public JRadioButton getRbCreateMatrix() {
        return rbCreateMatrix;
    }

    public JRadioButton getRbMultAddRows() {
        return rbMultAddRows;
    }

    public JRadioButton getRbMultRow() {
        return rbMultRow;
    }

    public JRadioButton getRbSwapRows() {
        return rbSwapRows;
    }

    public JComboBox<Integer> getRowBox() {
        return rowBox;
    }

    public JSlider getRowSlider() {
        return rowSlider;
    }

    public int getValue(JTextField cell) {
        int value = 0;
        if (isNumeric(cell.getText())) {
            value = Integer.parseInt(cell.getText());
        }
        return value;
    }

    public void initEntryGrid(int rows, int cols) {
        matrixPanel.removeAll();
        matrixPanel.setLayout(new GridLayout(rows, cols));
        cells = new JTextField[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j] = new JTextField();
                cells[i][j].setHorizontalAlignment(JTextField.CENTER);
                cells[i][j].addActionListener(new cellAction(i, j, cols, rows));
                matrixPanel.add(cells[i][j]);
            }
        }
        matrixPanel.updateUI();
    }

    public boolean isNumeric(String str) {
        try {
            @SuppressWarnings("unused")
            int d = Integer.parseInt(str);
        }
        catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private int getValue(JComboBox<Integer> box) {
        int value = (int) box.getSelectedItem();
        return value;
    }

    private void multAddRow(int[][] matrix, int row2, int mult) {
        for (int i = 0; i < col; i++) {
            cells[row2][i].setText(String.valueOf(matrix[row2][i]));
        }
    }

    private void multRow(int[][] matrix, int row1, int mult) {
        for (int i = 0; i < col; i++) {
            cells[row1][i].setText(String.valueOf(matrix[row1][i]));
        }
    }

    private void selectedButton() {
        if (rbCreateGrid.isSelected()) {
            row = getValue(rowBox);
            col = getValue(colBox);
            initEntryGrid(row, col);
            cells[0][0].requestFocusInWindow();
            rbCreateMatrix.setSelected(true);
            createMatrixAction();
            rbCreateGrid.setEnabled(false);
            rowSlider.setMinimum(1);
            rowSlider.setMaximum(row);
            colSlider.setMinimum(1);
            colSlider.setMaximum(col);
            rowSlider.setValue(1);
            colSlider.setValue(1);
        }
        else if (rbCreateMatrix.isSelected()) {
            worker = new Matrix(createArray(row, col));
            rbSwapRows.setSelected(true);
            swapRowsAction();
            rbCreateMatrix.setEnabled(false);
            toggleEditable();
        }
        else if (rbSwapRows.isSelected()) {
            int row1 = rowSlider.getValue();
            int row2 = colSlider.getValue();
            worker.swapRows(row1, row2);
            updateGUI(1, row1, row2, 0);

        }
        else if (rbMultRow.isSelected()) {
            int row1 = rowSlider.getValue();
            int mult = getValue(multBox);
            worker.multRow(row1, mult);
            updateGUI(2, row1, 0, mult);
        }
        else if (rbMultAddRows.isSelected()) {
            int row1 = rowSlider.getValue();
            int row2 = colSlider.getValue();
            int mult = getValue(multBox);
            worker.multRowAdd(row1, mult, row2);
            updateGUI(3, row1, row2, mult);
        }
    }

    private void swapRows(int[][] matrix, int row1, int row2) {
        for (int c = 0; c < col; c++) {
            cells[row1][c].setText(String.valueOf(matrix[row1][c]));
            cells[row2][c].setText(String.valueOf(matrix[row2][c]));
        }
    }

    private void toggleEditable() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                cells[i][j].setEditable(false);
            }
        }
    }

    private void updateGUI(int action, int row1, int row2, int mult) {
        row1--;
        row2--;
        int[][] matrix = worker.getMatrix();
        switch (action) {
            case 1:
                swapRows(matrix, row1, row2);
                break;
            case 2:
                multRow(matrix, row1, mult);
                break;
            case 3:
                multAddRow(matrix, row2, mult);
                break;

        }
    }

    protected void createGridAction() {
        field1Label.setVisible(true);
        field2Label.setVisible(true);
        field3Label.setVisible(false);
        rowSlider.setVisible(false);
        colSlider.setVisible(false);
        rowBox.setVisible(true);
        colBox.setVisible(true);
        field1Label.setBorder(new TitledBorder("Rows"));
        field2Label.setBorder(new TitledBorder("Columns"));
        rbCreateMatrix.setEnabled(false);
        rbMultAddRows.setEnabled(false);
        rbMultRow.setEnabled(false);
        rbSwapRows.setEnabled(false);

    }

    protected void createMatrixAction() {
        field1Label.setVisible(false);
        field2Label.setVisible(false);
        field3Label.setVisible(false);
        rbCreateMatrix.setEnabled(true);
    }

    protected void multAddRowsAction() {
        field1Label.setVisible(true);
        field2Label.setVisible(true);
        field3Label.setVisible(true);
        rowSlider.setVisible(true);
        colSlider.setVisible(true);
        rowBox.setVisible(false);
        colBox.setVisible(false);
        field1Label.setBorder(new TitledBorder("Row 1"));
        field2Label.setBorder(new TitledBorder("Row 2"));
    }

    protected void multRowAction() {
        field1Label.setVisible(true);
        field2Label.setVisible(false);
        field3Label.setVisible(true);
        rowSlider.setVisible(true);
        rowBox.setVisible(false);
        field1Label.setBorder(new TitledBorder("Row"));
    }

    protected void newAction() {
        rbCreateGrid.setEnabled(true);
        rbCreateMatrix.setEnabled(true);
        matrixPanel.removeAll();
        rbCreateGrid.setSelected(true);
        createGridAction();
    }

    protected void swapRowsAction() {
        field1Label.setVisible(true);
        field2Label.setVisible(true);
        field3Label.setVisible(false);
        rowSlider.setVisible(true);
        colSlider.setVisible(true);
        rowBox.setVisible(false);
        colBox.setVisible(false);
        field1Label.setBorder(new TitledBorder("Row 1"));
        field2Label.setBorder(new TitledBorder("Row 2"));
        rbSwapRows.setEnabled(true);
        rbMultRow.setEnabled(true);
        rbMultAddRows.setEnabled(true);

    }
}
