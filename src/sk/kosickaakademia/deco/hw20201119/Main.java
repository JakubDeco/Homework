package sk.kosickaakademia.deco.hw20201119;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        HashSet<String> hSnames=new HashSet<>();
        ArrayList<String> aLnames=new ArrayList<>();

        fillCollection(hSnames);
        fillCollection(aLnames);

        // a) vypise na obrazovku pocet mien (Duplicity ignotujeme)
        System.out.println("Nuber of unique elements: "+hSnames.size());

        // b) vypise vsetky mena
        printCollection(hSnames);//bez duplikatov
        System.out.println();

        // c) vypiste zoznam mien usporiadany podla abecedy
        ArrayList<String> namesAsc = new ArrayList<>(hSnames);
        namesAsc.sort(null);
        printCollection(namesAsc);
        System.out.println();
        System.out.println();

        // d) vypiste najdlhsie meno
        longestElement(hSnames);
        System.out.println();

        // e) vypiste meno ktore sa opakuje najviac krat
        mostFrequentElement(aLnames);
    }

    private static void longestElement(Collection<String> collection) {
        int elementLength=0;
        HashSet<String> finalElements=new HashSet<>();

        for (String help : collection) {
            int helpLength=help.length();
            if (helpLength > elementLength) {
                finalElements.clear();
                elementLength = helpLength;
                finalElements.add(help);
            }
            //if there are more than one elements of the same frequency
            if (helpLength == elementLength) {
                finalElements.add(help);
            }
        }
        System.out.println("Element/s " + finalElements
                + " is/are the longest(" + elementLength + " characters) in this collection.");
    }

    public static void mostFrequentElement(ArrayList<String> arrList) {
        int elementFrequency=0;
        HashSet<String> finalElements=new HashSet<>();

        for (String help : arrList) {
            int helpFreq= Collections.frequency(arrList,help);
            if (helpFreq > elementFrequency) {
                finalElements.clear();
                elementFrequency = helpFreq;
                finalElements.add(help);
            }
            //if there are more than one elements of the same frequency
            if (helpFreq == elementFrequency) {
                finalElements.add(help);
            }
        }
        if (elementFrequency>1){
            System.out.println("Element/s " + finalElements
                    + " is/are the most frequent(" + elementFrequency + "times) in this collection.");
        }
        else System.out.println("Collection doesn't contain duplicate elements.");
    }

    public static void printCollection(java.util.Collection<String> names) {
        System.out.println();
        for (String help: names) {
            System.out.print(help+" ");
        }
    }

    public static void fillCollection(Collection<String> collection){
        try {
            FileReader fileReader  = new FileReader("resources/names.txt");
            BufferedReader bufferedReader=new BufferedReader(fileReader);

            String line;
            while ((line=bufferedReader.readLine())!=null){
                collection.add(line);
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
