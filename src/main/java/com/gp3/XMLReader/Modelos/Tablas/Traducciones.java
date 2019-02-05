package com.gp3.XMLReader.Modelos.Tablas;

import org.apache.commons.text.StringEscapeUtils;
import org.jsoup.Jsoup;

public class Traducciones {

    private int idTraduccion;
    private int idAlojamiento;
    private String idioma = "";
    private String tipo = "";
    private String resumen = "";
    private String descripcion = "";

    public Traducciones() {

    }

    public Traducciones(int idTraduccion, int idAlojamiento, String idioma, String tipo, String resumen, String descripcion) {
        this.idTraduccion = idTraduccion;
        this.idAlojamiento = idAlojamiento;
        this.idioma = idioma;
        this.tipo = tipo;
        this.resumen = cleanString(resumen);
        this.descripcion = cleanString(descripcion);
    }

    public int getIdTraduccion() {
        return idTraduccion;
    }

    public void setIdTraduccion(int idTraduccion) {
        this.idTraduccion = idTraduccion;
    }

    public int getIdAlojamiento() {
        return idAlojamiento;
    }

    public void setIdAlojamiento(int idAlojamiento) {
        this.idAlojamiento = idAlojamiento;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = cleanString(idioma);
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = cleanString(tipo);
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = cleanString(resumen);
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = cleanString(descripcion);
    }

    public static String cleanString(String text){
        return Jsoup.parse(StringEscapeUtils.unescapeHtml4(text)).text();
    }
}
