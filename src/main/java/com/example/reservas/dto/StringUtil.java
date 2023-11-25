package com.example.reservas.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StringUtil {

    public static boolean isEmptyOrNull(String valor){
        if(valor==null){
            return true;
        }
        if(valor.trim().equals("")){
            return true;
        }
        return false;
    }

    public static boolean isZeroOrNull(Integer valor){
        if(valor==null){
            return true;
        }
        if(valor<=0){
            return true;
        }
        return false;
    }

    public static boolean isZeroOrNull(BigInteger valor){
        if(valor==null){
            return true;
        }
        if(valor.equals(new BigInteger("0"))){
            return true;
        }
        return false;
    }

    public static boolean isZeroOrNull(BigDecimal valor){
        if(valor==null){
            return true;
        }
        if(valor.equals(new BigDecimal("0"))){
            return true;
        }
        return false;
    }

    public static String toSearchContain(String valor){
        if(valor==null){
            return "";
        }
        if(valor.trim().equals("")){
            return "";
        }
        valor="%"+valor.trim()+"%";
        return valor;

    }

    public static Boolean isNumber(String valor){
        if(isEmptyOrNull(valor)){
            return false;
        }
        int i=0;
        if(valor.charAt(0)=='-'){
            if(valor.length()>1){
                i++;
            }else {
                return false;
            }
        }
        for(;i<valor.length();i++){
            if(!Character.isDigit(valor.charAt(i))){
                return false;
            }
        }
        return true;
    }
    public static Boolean isNumberPositivo(String valor){
        if(isEmptyOrNull(valor)){
            return false;
        }

        for(int i=0;i<valor.length();i++){
            if(!Character.isDigit(valor.charAt(i))){
                return false;
            }
        }
        return true;
    }

    public static Boolean inMaxCharacteres(String valor, Integer cantidad){
        if(isEmptyOrNull(valor)){
            if(isEmptyOrNull(valor) && cantidad==0){
                return true;
            }else return false;
        }
        if(valor.length()<=cantidad){
            return true;
        }else return false;

    }

    public static String defaultIsBlank(String value, String defaultValue) {
        if(value == null || value.trim().equals(""))
            return defaultValue;

        return value;
    }

    public static String obtainMonthYear(Date fechaI) {
        SimpleDateFormat formatoMes = new SimpleDateFormat("MMMM", new Locale("es", "ES"));

        // Obtener el mes y el año de la fecha
        String mes = formatoMes.format(fechaI);
        int anio = fechaI.getYear() + 1900; // El método getYear() devuelve los años desde 1900

        // Construir la cadena con el formato requerido
        String resultado = mes + "_" + anio;

        return resultado;
    }
}
