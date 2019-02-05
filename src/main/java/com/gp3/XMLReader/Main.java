package com.gp3.XMLReader;

import com.gp3.XMLReader.Controlador.XMLReader;
import com.gp3.XMLReader.GUI.VentanaImportar;
import com.gp3.XMLReader.Modelos.Conexion.Conexion;
import com.gp3.XMLReader.Modelos.Tablas.Alojamientos;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.sql.SQLException;

public class Main {

    private static VentanaImportar window;
    private static Conexion conn;

    public static void main(String[] args) {
        conn = new Conexion("gp3", "IFZWx5dEG12yt8QW", "reto_gp3", "kasserver.synology.me", "3307");

        EventQueue.invokeLater(() -> {
            try {
                conn.openConexion();
                window = new VentanaImportar();
                setListeners();
                window.getFrame().setVisible(true);

                Runtime.getRuntime().addShutdownHook(new Thread(Main::close));

            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog (null, e.getMessage(), "Error: " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
            } catch (Exception e){
                e.printStackTrace();
            }
        });

    }

    private static void setListeners(){

        window.getBtnAbrir().addActionListener(e->{
            JFileChooser fc = new JFileChooser();

            fc.setAcceptAllFileFilterUsed(false);
            fc.addChoosableFileFilter(new FileNameExtensionFilter("Extensible Markup Language (.xml)", "xml"));
            int result = fc.showOpenDialog(window.getFrame());

            if(result==JFileChooser.APPROVE_OPTION){
                window.getTxtInputDir().setText(fc.getSelectedFile().getPath());
            }
        });

        window.getBtnImportarDatos().addActionListener(e->{
            window.getProgressBar().setValue(0);
            window.getProgressBar().setVisible(true);
            window.getTxtProgreso().setText("Importando...");
            window.getTxtProgreso().setVisible(true);
            window.getBtnImportarDatos().setEnabled(false);

            new Thread(() -> {
                XMLReader reader = new XMLReader(window.getTxtInputDir().getText());
                reader.readAll();

                window.getProgressBar().setMaximum(reader.getAlojamientos().size());

                int prog = 1;
                for (Alojamientos aloj: reader.getAlojamientos()) {
                    window.getTxtProgreso().setText("Importando: "+prog+" / "+reader.getAlojamientos().size());
                    conn.insertAlojamiento(aloj);
                    window.getProgressBar().setValue(prog++);
                }
                JOptionPane.showMessageDialog (null, "Se han importado " + reader.getAlojamientos().size()+" alojamientos", "Success", JOptionPane.INFORMATION_MESSAGE);
                window.getBtnImportarDatos().setEnabled(true);
            }).start();
        });
    }

    public static void limpiarXML(){
        try {
            FileReader fr = new FileReader("G:\\DAM2\\RetoDAM\\data\\alojamientos_rural_eus_copia.xml");
            BufferedReader bf = new BufferedReader(fr);
            FileWriter fw = new FileWriter("G:\\DAM2\\RetoDAM\\data\\alojamientos_rural_eus_copia_limpio.xml");
            BufferedWriter bw = new BufferedWriter(fw);
            String line;
            int i = 1;
            while((line= bf.readLine())!=null) {

                if(line.contains("row num=")) {
                    System.out.println(line.replaceAll("(\\d?\\d?\\d)", String.valueOf(i)));
                    bw.write(line.replaceAll("(\\d?\\d?\\d)", String.valueOf(i)));
                    bw.newLine();
                    i++;
                    continue;
                }
                bw.write(line);
                bw.newLine();

            }

            bw.close();
            fw.close();
            fr.close();
            bf.close();
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void close(){
        conn.closeConexion();
    }
}
