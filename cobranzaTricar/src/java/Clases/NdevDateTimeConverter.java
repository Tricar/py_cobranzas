/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.Locale;
import java.util.TimeZone;

/**
 *
 * @author Ricardo
 */
public class NdevDateTimeConverter extends javax.faces.convert.DateTimeConverter {

    public NdevDateTimeConverter() {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        setTimeZone(TimeZone.getDefault());
        setPattern(pattern);
        setLocale(new Locale("es"));
    }

}
