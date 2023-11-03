import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
// scanner va lire le contenu de fichier.csv
        Scanner scanner = new Scanner(new File("dpt2018.csv"));
// stringBuilder va stocker le contenu de fichier.csv
        if (scanner.hasNextLine()){
            scanner.nextLine();
        }
        StringBuilder stringBuilder = new StringBuilder();

        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine())
                    .append("\n");
        }
        scanner.close();
// Les deux lignes suivantes vont ensuite produire un String[] contenant
// les donneees du fichier CSV

        String data = stringBuilder.toString();
        String[] lines = data.split("\n");
        int incompleteDataCount = 0;

        for (String line : lines) {
            String[] parts = line.split(";");
            String annais = parts[2];
            String dpt = parts[3];

            if ("XXXX".equals(annais) || "XX".equals(dpt)) {
                    incompleteDataCount++;
            }

        }
        data=data.replace("XXXX","-1");
        data=data.replace("XX","-1");
        lines = data.split("\n");
        System.out.println(lines.length+" "+incompleteDataCount);
    }
}
