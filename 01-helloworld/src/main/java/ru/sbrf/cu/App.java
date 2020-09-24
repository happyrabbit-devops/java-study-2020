package ru.sbrf.cu;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Hello");
        arrayList.add(" World!");
        StringBuilder sb = new StringBuilder();
        for (Object item:
             arrayList) {
            sb.append(item.toString());
        }
        System.out.println(sb);
    }
}
