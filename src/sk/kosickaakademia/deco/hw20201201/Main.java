package sk.kosickaakademia.deco.hw20201201;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Integer> arrayListA=new ArrayList<>();
        List<Integer> arrayListB=new ArrayList<>();
        List<Integer> arrayListC=new ArrayList<>();

        //load numbers from files to ArrayList
        loadList("resources/a.txt",arrayListA);
        loadList("resources/b.txt",arrayListB);

        //compare arrayListA and arrayListB; common numbers are stored in arrayListC
        arrayListC=compareLists(arrayListA, arrayListB);

        // 1. Vypisat na obrazovky tie cisla zo suboru a, ktore nie su v subore b.
        System.out.println("Cisla zo suboru a, ktore nie su v subore b:");
        arrayListA.removeAll(arrayListC);
        printList(arrayListA);

        // 2. Vypisat zo suboru b tie cisla, ktore sa nenachadzaju v subore a.
        System.out.println();
        System.out.println("Vypisat zo suboru b tie cisla, ktore sa nenachadzaju v subore a:");
        arrayListB.removeAll(arrayListC);
        printList(arrayListB);

        // 3. vytvorit subor c.txt a vlozit tam v lubovolnom poradi vsetky cisla zo suboru a aj b.
        //  duplicity nie su povolene.
        arrayListC.addAll(arrayListA);
        arrayListC.addAll(arrayListB);
        arrayListC.sort(null);
        loadFileWithList("output/c.txt",arrayListC);

        // 4. Cisla zo suboru c.txt treba vy;isat na obrazovky usporiadane vzostupne.
        System.out.println();
        System.out.println("4. Cisla zo suboru c.txt treba vy;isat na obrazovky usporiadane vzostupne.");
        printList(arrayListC);

    }

    private static void loadFileWithList(String s, List<Integer> arrayListC) {
        File file=new File(s);
        try {
            FileWriter fileWriter=new FileWriter(file);
            int count=0;
            for (Integer i :
                    arrayListC) {
                if (count>=10){
                    fileWriter.write('\n');
                    count=0;
                }
                fileWriter.write (Integer.toString(i));
                fileWriter.write(" ");
                count++;
            }
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printList(List<Integer> arrayListA) {
        for (Integer i :
                arrayListA) {
            System.out.print(i+" ");
        }
    }

    public static List<Integer> compareLists(List<Integer> arrayListA, List<Integer> arrayListB) {
        ArrayList<Integer> shared=new ArrayList<>();
        for (Integer i :
                arrayListA) {
            if (arrayListB.contains(i)) {
                shared.add(i);

            }
        }
        return shared;
    }

    private static void loadList(String s, List<Integer> arrayListA) {
        try {
            FileReader fileReader=new FileReader(s);
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            String line;

            while ((line=bufferedReader.readLine())!=null){
                String[] tempStringArr=line.split(" ");
                for (String i : tempStringArr) {
                    if (i.equals(""))continue;
                    String trimed=i.trim();

                    arrayListA.add(Integer.parseInt(trimed));
                }
            }
            fileReader.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
