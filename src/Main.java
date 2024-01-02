import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static boolean isLetter(Character ch){
        return !(ch.toString().toLowerCase().equals(ch.toString().toUpperCase()));
    }
    public static void main(String[] args) {
        // Часть А
        try(Scanner scanPoem = new Scanner(new File("Poem.txt"))) {
            FileWriter writer = new FileWriter("PoemResult.txt");
            Map<Character,Integer> letters = new HashMap<>();
            Map<String,Integer> words = new HashMap<>();
            while (scanPoem.hasNextLine()) {
                String str = scanPoem.nextLine();
                char[] strChar = str.toCharArray();
                for (char ch:strChar){
                    if (!(letters.containsKey(ch)))
                        letters.put(ch,1);
                    else
                        letters.put(ch,letters.get(ch)+1);
                }
                String[] strSpl = str.split(" |\\;|\\,|\\:|\\…");
                for (String elem: strSpl){
                    if (elem.length()>1) {
                        if (!(words.containsKey(elem)))
                            words.put(elem, 1);
                        else
                            words.put(elem, words.get(elem) + 1);
                    }
                }
            }
            scanPoem.close();

            for (Map.Entry elem: letters.entrySet()){
                writer.write(elem.getKey().toString()+"="+elem.getValue().toString() + "\n");
            }
            for (Map.Entry elem: words.entrySet()){
                writer.write(elem.getKey().toString()+"="+elem.getValue().toString() + "\n");
            }
            writer.close();
        }
        catch (Exception e){
            System.out.println("Error " + e);
        }
        //Часть C
        try {
            File dir = new File("newPath");
            dir.mkdirs();
            File file = new File("newPath\\newFile.txt");
            file.createNewFile();
            FileWriter writer = new FileWriter("newPath\\newFile.txt");

            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите путь к java файлу");
            Scanner javaProgramm = new Scanner(new File(scanner.nextLine()));
            while (javaProgramm.hasNextLine()){
                String str = javaProgramm.nextLine();
                str.replaceAll("[\\s]{2,}", " ");
                writer.write(str + "\n");
            }
            writer.close();
        }
        catch (Exception e){
            System.out.println("Error " + e);
        }
    }
}