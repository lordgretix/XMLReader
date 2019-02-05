package com.gp3.XMLReader.GUI;

import javax.swing.*;
import java.awt.*;

public class VentanaImportar {

    private JFrame frame;
    private JTextField txtInputDir;
    private JButton btnImportarDatos;
    private JLabel txtSeleccionar;
    private JButton btnAbrir;
    private JProgressBar progressBar;
    private JLabel txtProgreso;

    /**
     * Create the application.
     */
    public VentanaImportar() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        this.nativeLookAndFeel();

        frame = new JFrame();
        frame.setBounds(100, 100, 450, 228);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        txtSeleccionar = new JLabel("Selecciona un *.xml para importar");
        txtSeleccionar.setFont(new Font("Tahoma", Font.PLAIN, 12));
        txtSeleccionar.setForeground(new Color(0, 0, 0));
        txtSeleccionar.setBounds(10, 11, 303, 20);
        frame.getContentPane().add(txtSeleccionar);

        txtInputDir = new JTextField();
        txtInputDir.setFont(new Font("Tahoma", Font.PLAIN, 12));
        txtInputDir.setBounds(10, 42, 303, 20);
        frame.getContentPane().add(txtInputDir);
        txtInputDir.setColumns(10);

        btnAbrir = new JButton("Abrir");
        btnAbrir.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnAbrir.setBounds(323, 41, 89, 23);
        frame.getContentPane().add(btnAbrir);

        btnImportarDatos = new JButton("Importar Datos");
        btnImportarDatos.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnImportarDatos.setBounds(150, 75, 150, 40);
        frame.getContentPane().add(btnImportarDatos);

        progressBar = new JProgressBar();
        progressBar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        progressBar.setValue(50);
        progressBar.setStringPainted(true);
        progressBar.setBounds(10, 148, 414, 30);
        progressBar.setVisible(false);
        frame.getContentPane().add(progressBar);

        txtProgreso = new JLabel("...");
        txtProgreso.setForeground(Color.BLACK);
        txtProgreso.setBounds(10, 126, 171, 20);
        txtProgreso.setVisible(false);
        frame.getContentPane().add(txtProgreso);
    }

    private void nativeLookAndFeel(){

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JTextField getTxtInputDir() {
        return txtInputDir;
    }

    public void setTxtInputDir(JTextField txtInputDir) {
        this.txtInputDir = txtInputDir;
    }

    public JButton getBtnImportarDatos() {
        return btnImportarDatos;
    }

    public void setBtnImportarDatos(JButton btnImportarDatos) {
        this.btnImportarDatos = btnImportarDatos;
    }

    public JLabel getTxtSeleccionar() {
        return txtSeleccionar;
    }

    public void setTxtSeleccionar(JLabel txtSeleccionar) {
        this.txtSeleccionar = txtSeleccionar;
    }

    public JButton getBtnAbrir() {
        return btnAbrir;
    }

    public void setBtnAbrir(JButton btnAbrir) {
        this.btnAbrir = btnAbrir;
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(JProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public JLabel getTxtProgreso() {
        return txtProgreso;
    }

    public void setTxtProgreso(JLabel txtProgreso) {
        this.txtProgreso = txtProgreso;
    }
}
