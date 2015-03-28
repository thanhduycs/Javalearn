package javacore;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.Properties;

public class HowJavaLoadPropertiesFile {
    
    ArrayList<String> arrayList;
    LinkedList<String> linkedList;
    
    
    public static void main(String[] args) {
        System.out.println("=========== SYSTEN PROPERTIES ===========");
        Properties props = System.getProperties();
        Enumeration<Object> e = props.keys();
        while(e.hasMoreElements())
        {
            Object k = e.nextElement();
            System.out.printf("++ %s = %s\n", k, props.get(k));
        }
        
        
        
        Class<?>[] loadClasses = new HowJavaLoadPropertiesFile().getClass().getClasses();
        
        for (int i = 0; i < loadClasses.length; i++) {
            Class<?> class1 = loadClasses[i];
            System.out.println(class1.getSimpleName());
        }
        
        URL uri = ClassLoader.getSystemResource("java.util.LinkedList");
        System.out.println(uri);
    }
}
