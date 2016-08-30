package de.lengsfeld.vr;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by robert on 30.08.16.
 */
public class Tryout {

    public static void main(String[] args) {
        // String to be scanned to find the pattern.
        StringBuilder directory = new StringBuilder("/resources/cemeteries/");

        String line = "c_id19_g_id144asdx_1386244170.jpg";
        String pattern = "([a-z]_id[0-9]*)";
        pattern = "([0-9]+)";
        directory.append(method(line,pattern));
        directory.append("/" + line);
        String dirpath = directory.toString();
        System.out.println(dirpath);

        method(line,pattern);

        pattern = "(g_id[0-9]*)";
        method(line,pattern);

        pattern = "([0-9]*.jpg)";
        method(line,pattern);
    }

    public static String method(String line, String pattern) {
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object.
        Matcher m = r.matcher(line);
        int count = m.groupCount();
        System.out.println(m.groupCount());
        System.out.println(m);
        if (m.find( )) {
            for(int i= 0; i < count; i++) {
                System.out.println("Found value: " + m.group(i));
            }
        } else {
            System.out.println("NO MATCH");
        }
        return m.group(0);
    }
}
