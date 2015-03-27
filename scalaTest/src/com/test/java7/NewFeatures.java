package com.test.java7;

import org.junit.Test;
import sun.text.resources.CollationData_ar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Java 7 new features
 * reference: http://jaxenter.com/java-7-the-top-8-features-103625.html
 * Created by kinwu on 2015/2/9.
 */
public class NewFeatures {

    @Test
    public void try_with_resources() {
    //More readable code and easy to write.
    //Automatic resource management.
    //Number of lines of code is reduced.
    //No need of finally block just to close the resources.
    //We can open multiple resources in try-with-resources statement separated by a semicolon. For example, we can write following code:
    //
    //    try (
    //      BufferedReader br = new BufferedReader(new FileReader(C:\\journaldev.txt"));
    //      java.io.BufferedWriter writer = java.nio.file.Files.newBufferedWriter(FileSystems.getDefault().getPath("C:\\journaldev.txt"), Charset.defaultCharset())) {
    //      System.out.println(br.readLine());
    //    } catch (IOException e) {
    //      e.printStackTrace();
    //    }
    //
    // When multiple resources are opened in try-with-resources, it closes them in the reverse order to avoid any dependency issue. You can extend my resource program to prove that.

        try (BufferedReader reader = new BufferedReader(new FileReader("D:/test/Noname1.txt"))) {
            while (reader.ready()) {
                System.out.println(reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void strings_in_switch() {
    //String in switch case make code more readable by removing the multiple if-else-if chained conditions.
    //String in switch is case sensitive, the output of example confirms it.
    //Switch case uses String.equals() method to compare the passed value with case values, so make sure to add a NULL check to avoid NullPointerException.
    //According to Java 7 documentation for Strings in Switch, java compiler generates more efficient byte code for String in Switch statement than chained if-else-if statements.
    //Make sure to use it only when you know that it will be used with Java 7 else it will throw Exception.

        String s = "a";
        switch (s) {
            case "a":
                System.out.println("this is a");
                break;
            case "b":
                System.out.println("this is b");
                break;
            case "c":
                System.out.println("this is c");
                break;
            default:
                System.out.println("default");
        }
    }

    @Test
    public void underline_in_numeric() {
    // Underscores can be placed only between digits.
    // You can’t put underscores next to decimal places, L/F suffix or radix prefix. So 3._14, 110_L, 0x_123 are invalid and will cause compilation error.
    // Multiple underscores are allowed between digits, so 12___3 is a valid number.
    // You can’t put underscores at the end of literal. So 123_ is invalid and cause compile time error.
    // When you place underscore in the front of a numeric literal, it’s treated as an identifier and not a numeric literal. So don’t confuse with it.
    //  int _10 = 0;
    //  int x = _10;

        int numeric = 1_000_000;
        System.out.println(numeric);
    }

    @Test
    public void binary_integral_literals(){
        //binary
        int b = 0b100000;
        System.out.println("b = " + b);

        //octonary
        int o = 040;
        System.out.println("o = " + o);

        //hexadecimal
        int h = 0x20;
        System.out.println("h = " + h);
    }

    @Test
    public void generic_instance_creation(){
//        With Java 5 and 6
        Map<String, List<String>> retVal1 = new HashMap<String, List<String>>();

//        With Java 7
        Map<String, List<String>> retVal = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        retVal.put("1",list);

        retVal1.put("1",list);
        System.out.println(retVal);
    }

    @Test
    public void testClassLoader() throws Exception {
        /* BootStrap ClassLoader load jre/lib/rt.jar */
        ClassLoader loader = System.class.getClassLoader();
        System.out.println(null != loader ? loader.getClass().getName()
                : loader);

        /* ExtClassLoader load jre/lib/ext/*.jar */
        System.out.println(CollationData_ar.class.getClassLoader().getClass()
                .getName());

        /* AppClassLoader load ClassPath */
        System.out.println(this.getClass().getClassLoader().getClass()
                .getName());
    }
}
