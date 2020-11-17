package sk.kosickaakademia.deco.hw20201117;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        createFixedFile("git.txt");
    }

    private static void createFixedFile(String filename) {//works only with .txt files
        try {
            //open original file
            FileReader fr=new FileReader("resources/"+filename);
            BufferedReader br=new BufferedReader(fr);

            //creating new file to write to
            String[] help=filename.split("[.]");
            File fixed=new File("output/"+help[0]+"Fixed."+help[1]);
            if (fixed.exists()) {
            System.out.println("In directory "+fixed.getParent()+" already exists "+fixed.getName()+" file.");
            return;
            }
            fixed.createNewFile();
            //

            //changing the characters and writing to new file
            FileWriter fw=new FileWriter(fixed);
            int a;
            do {
                a=br.read();
                char b= (char) a;
                if (b==63){//?=63; I=73; Y=89; i=105; y=121
                    b=0;
                    continue;
                    //todo osetrit o pripad ked ? je na konci opytovacej vety
                }
                else if (b==73) b=89;
                else if (b==89) b=73;
                else if (b==105) b=121;
                else if (b==121) b=105;

                System.out.print(b);//delete when finished
                fw.write(b);
            }while (a!=-1);
            //

            fw.close();
            fr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
