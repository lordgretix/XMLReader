package com.gp3.XMLReader.Modelos.Conexion;

import com.gp3.XMLReader.Modelos.Tablas.Alojamientos;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;

public class Conexion {

    private Connection conexion;
    private String user = "root";
    private String password = "";
    private String database = "";
    private String host = "localhost";
    private String port = "3306";

    public Conexion() {
    }

    public Conexion(String user, String password, String database, String host, String port) {
        this.user = user;
        this.password = password;
        this.database = database;
        this.host = host;
        this.port = port;
        this.nativeLookAndFeel();
    }

    public void openConexion() throws SQLException {
        try{
            conexion = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", this.user, this.password);
        }catch (CommunicationsException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error: " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
    }

    public void closeConexion() {
        try {
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertAlojamiento(Alojamientos alojamiento) {

        try {
            PreparedStatement st = this.conexion.prepareStatement("INSERT INTO `alojamientos` " +
                    "(nombre, telefono, direccion, email, web, certificado, club, restaurante, tienda, autocaravana, capacidad, cod_postal, latlong, cod_poblacion, firma) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE " +
                    "nombre = ?, telefono = ?, direccion = ?, email = ?, web = ?, certificado = ?, club = ?, restaurante = ?, tienda = ?, autocaravana = ?, capacidad = ?, cod_postal = ?, latlong = ?, cod_poblacion = ?, firma = ?");

            int i = 1;
            int x = i + 15;

            //Nombre
            st.setString(i++, alojamiento.getNombre());
            st.setString(x++, alojamiento.getNombre());

            //Telefono
            st.setString(i++, alojamiento.getTelefono());
            st.setString(x++, alojamiento.getTelefono());

            //Dieccion
            st.setString(i++, alojamiento.getDireccion());
            st.setString(x++, alojamiento.getDireccion());

            //Email
            st.setString(i++, alojamiento.getEmail());
            st.setString(x++, alojamiento.getEmail());

            //Web
            st.setString(i++, alojamiento.getWeb());
            st.setString(x++, alojamiento.getWeb());

            //Certificado
            st.setBoolean(i++, alojamiento.isCertificado());
            st.setBoolean(x++, alojamiento.isCertificado());

            //Club
            st.setBoolean(i++, alojamiento.isClub());
            st.setBoolean(x++, alojamiento.isClub());

            //Restaurante
            st.setBoolean(i++, alojamiento.isRestaurante());
            st.setBoolean(x++, alojamiento.isRestaurante());

            //Tienda
            st.setBoolean(i++, alojamiento.isTienda());
            st.setBoolean(x++, alojamiento.isTienda());

            //Autocaravana
            st.setBoolean(i++, alojamiento.isAutocarabana());
            st.setBoolean(x++, alojamiento.isAutocarabana());

            //Capacidad
            st.setInt(i++, alojamiento.getCapacidad());
            st.setInt(x++, alojamiento.getCapacidad());

            //Codigo postal
            st.setInt(i++, alojamiento.getCodPostal());
            st.setInt(x++, alojamiento.getCodPostal());

            //Latitud y longitud
            st.setString(i++, alojamiento.getLatlong());
            st.setString(x++, alojamiento.getLatlong());

            //Codigo de poblacion
            st.setInt(i++, alojamiento.getCodPoblacion());
            st.setInt(x++, alojamiento.getCodPoblacion());

            //Firma
            st.setString(i, alojamiento.getFirma());
            st.setString(x, alojamiento.getFirma());

            st.execute();

            // Traduccion
            st = this.conexion.prepareStatement("INSERT INTO `traducciones` (alojamiento, idioma, tipo, resumen, descripcion) " +
                    "VALUES ((SELECT id FROM `alojamientos` WHERE firma = ? ), ?, ?, ?, ?) ON DUPLICATE KEY UPDATE " +
                    "tipo = ?, resumen = ?, descripcion = ?");

            int y = 1;
            int z = y+5;

            //Firma
            st.setString(y++, alojamiento.getFirma());

            //Idioma
            st.setString(y++, alojamiento.getIdioma());

            //Tipo
            st.setString(y++, alojamiento.getTipo());
            st.setString(z++, alojamiento.getTipo());

            //Resumen
            st.setString(y++, alojamiento.getResumen());
            st.setString(z++, alojamiento.getResumen());

            //Descripcion
            st.setString(y, alojamiento.getDescripcion());
            st.setString(z, alojamiento.getDescripcion());

            st.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error: " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
    }

    private void nativeLookAndFeel(){

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    public Connection getConexion() {
        return conexion;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

}
