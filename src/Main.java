import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        partie1();
    }
    private static void partie1() throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("dpt2018.csv"));

        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
        StringBuilder stringBuilder = new StringBuilder();

        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine())
                    .append("\n");
        }
        scanner.close();

        String data = stringBuilder.toString();
        String[] lines = data.split("\n");
        int incompleteDataCount = 0;

        String[] nomClaudeEtc = {"THIBAUT", "CLAUDE", "JÉRÔME", "HERVÉ", "PATRICK", "GUILLAUME"};
        int[] nbPrenoms = {0, 0, 0, 0, 0, 0};

        int nbNais = 0;

        int nbAns = 0;
        int nbJean = 0;
        int minAn = 2018;
        int nbMarie = 0;


        int year = 0;
        StringBuilder camilleStringBuilder = new StringBuilder();

        ArrayList<String> nomsHomme=new ArrayList<>();
        ArrayList<String> nomsFemme=new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split(";");
            String annais = parts[2];
            String dpt = parts[3];
            int gender = Integer.parseInt(parts[0]);
            String name = parts[1];

            if ("XXXX".equals(annais) || "XX".equals(dpt)) {
                incompleteDataCount++;
            }

            if (!annais.equals("XXXX") && Integer.parseInt(annais) < minAn) {
                minAn = Integer.parseInt(annais);

            }

            if (annais.equals("2016") && dpt.equals("75")) nbNais+=Integer.parseInt(parts[4]);

            if (gender == 1) {
                switch (name) {
                    case "THIBAUT":
                        nbPrenoms[0] += Integer.parseInt(parts[4]);
                        break;
                    case "CLAUDE":
                        nbPrenoms[1] += Integer.parseInt(parts[4]);
                        break;
                    case "JÉRÔME":
                        nbPrenoms[2] += Integer.parseInt(parts[4]);
                        break;
                    case "HERVÉ":
                        nbPrenoms[3] += Integer.parseInt(parts[4]);
                        break;
                    case "PATRICK":
                        nbPrenoms[4] += Integer.parseInt(parts[4]);
                        break;
                    case "GUILLAUME":
                        nbPrenoms[5] += Integer.parseInt(parts[4]);
                        break;
                    case "JEAN":
                        nbJean += Integer.parseInt(parts[4]);
                        break;
                    case "CAMILLE":
                        camilleStringBuilder.append(line).append("\n");
                        break;
                }

                if (!nomsHomme.contains(name)){
                    nomsHomme.add(name);
                }

            } else {
                if (name.equals("MARIE")) {
                    nbMarie+=Integer.parseInt(parts[4]);
                } else if (name.equals("CAMILLE")) {
                    camilleStringBuilder.append(line).append("\n");
                }
                if (!nomsFemme.contains(name)){
                    nomsFemme.add(name);
                }
            }
        }

        String dataCamille=camilleStringBuilder.toString();
        String[] camilleLines=dataCamille.split("\n");
        for (int i = 1900; i < 2019; i++) {
            int nbCamilleHomme = 0;
            int nbCamilleFemmes = 0;
            for (String line:camilleLines) {
                String[] parts= line.split(";");
                if (!parts[2].equals("XXXX") && i == Integer.parseInt(parts[2])){
                    if(parts[0].equals("1")){
                        nbCamilleHomme+=Integer.parseInt(parts[4]);
                    }else{
                        nbCamilleFemmes+=Integer.parseInt(parts[4]);
                    }
                }
            }
            if (nbCamilleFemmes>nbCamilleHomme){
                year=i;
                break;
            }

        }


        nbAns = 2018 - minAn;
        int moyJean = nbJean / nbAns;
        int moyMarie = nbMarie / nbAns;
        data = data.replace("XXXX", "-1");
        data = data.replace("XX", "-1");
        lines = data.split("\n");
        System.out.println("nb de lignes: " + lines.length + "\ndonnées incomplètes: " + incompleteDataCount +
                "\nnombre d'hommes: " + nomsHomme.size() + "\nnombre de prenoms femmes: " + nomsFemme.size());
        System.out.println("nb naissances paris 2016:" + nbNais + "\nmoyenne de Jean/ans sur le recensement:" + moyJean);
        System.out.println("moyenne de Marie/ans sur le recensement:" + moyMarie);
        System.out.println(year);
        for (int i = 0; i < nbPrenoms.length; i++) {
            System.out.println("Nombre de " + nomClaudeEtc[i] + ": " + nbPrenoms[i]);
        }
        if (year==0){
            System.out.println("il n'y a pas d'années ou le camille est dominant chez les femmes");
        } else{
            System.out.println("le prenom camille est dominant chez les femmes a partir de "+year);
        }
    }
    private void partie2()throws FileNotFoundException{

    }
}
