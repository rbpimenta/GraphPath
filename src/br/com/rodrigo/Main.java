package br.com.rodrigo;

import br.com.rodrigo.model.Item;
import br.com.rodrigo.model.Repo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    private static final int SIZE_PATH_WANTED = 1;
    private static final int NO_RAIZ = 17;

    private static final List<Integer> indexAnalisados = Arrays.asList(

    );

    static List<Repo> repoMap = new ArrayList<>();
    static List<Object> bestSolution = new ArrayList<>();
    static final String FILENAME = "src/br/com/rodrigo/repo2.csv";
//  static final String FILENAME = "src/br/com/rodrigo/repo_dependencies.csv";
//  static final String FILENAME = "src/br/com/rodrigo/example1.csv";

    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            int lineCount = 0;

            while ((line = bufferedReader.readLine()) != null) {
                if (lineCount == 0) {
                    criarRepositorios(line);
                    lineCount++;
                    continue;
                }
                checkInputOutputs(line, lineCount - 1);
                lineCount++;
            }

            printRepoMap();

            System.out.println("######################");
            System.out.println();
            System.out.print("Escolha o nó raiz: ");
            int noRaiz = NO_RAIZ;

            System.out.println("######################");
            System.out.println();

//            solution(repoMap.get(noRaiz), null);
            findPath(repoMap.get(noRaiz), new ArrayList<>());

            System.out.println("######################");
            System.out.println();

            System.out.print("Printar caminho maiores do que: ");
            int sizePathWanted = SIZE_PATH_WANTED;

            System.out.println("Caminhos maiores do que " + sizePathWanted);
            paths.forEach((index, path) -> {
                if (path.size() > sizePathWanted && !indexAnalisados.contains(index) && index < 58) {
                    System.out.println("index: " + index + " | caminho: " + path);
                }
            });

            System.out.println("######################");
            System.out.println();
            System.out.println("Maior(es) caminho(s)");

            int maiorTamanho = 0;
            int maiorTamanhoIndex = 0;

            for (Map.Entry<Integer, List<String>> entry : paths.entrySet()) {
                Integer pathIndex = entry.getKey();
                List<String> path = entry.getValue();

                if (path.size() > maiorTamanho) {
                    maiorTamanho = path.size();
                    maiorTamanhoIndex = pathIndex;
                }
            }

            System.out.println("Index = " + maiorTamanhoIndex);
            System.out.println("Caminho = " + paths.get(maiorTamanhoIndex));

        } catch (IOException e) {
            System.out.println("File not Found");
            e.printStackTrace();
        }
    }

    public static String tst = " # # # # # # ";
    public static int tstIndex = Main.tst.length();

    static Map<Integer, List<String>> paths = new HashMap<>();
    public static int pathsFound = 0;

    public static Map<Integer, List<Item>> allSolutions = new HashMap<>();

    public static Item biggestItemPath;
    public static int biggestLenghtPath = 1;

    private static void findPath (Repo repoAtual, List<String> caminho) {
        // Coloca o nó atual no caminho
        caminho.add(repoAtual.toString());

        if (repoAtual.getOutputs().isEmpty()) {
            // Se estivermos num nó folha devemos adicionar o caminho ao mapa de caminhos possíveis,
            paths.put(pathsFound, caminho);

            // aumentar o contados de caminhos
            pathsFound++;
        }

        for (Repo repo : repoAtual.getOutputs()) {
            findPath(repo, new ArrayList<>(caminho));
        }
//        System.out.println(paths);
    }

    private static void solution(Repo repoAtual, Item oldItem) {
        if (repoAtual.getOutputs().isEmpty()) {
            return;
        }

        List<Item> solutions = new ArrayList<>();

        for (Repo repo : repoAtual.getOutputs()) {

            // Adicionar o repo atual como o no atual do item de solução e o próximo nó como uma das saídas
            Item item = new Item(repoAtual, repo);

            if (oldItem != null) {
                oldItem.setProximoItem(item);
            }

            solutions.add(item);

            tstIndex -= 2;
            Main.solution(repo, item);
            tstIndex += 2;
        }

        if (oldItem == null) {
            System.out.println(repoAtual);
            System.out.println(solutions);
        }
    }

    private static void printRepoMap() {
        repoMap.forEach(repo -> {
            String output = repo.getIndex() + " - " + repo.getName() + " | " + repo.getInputs().size() + " | " + repo.getOutputs().size();
            System.out.println(output);
        });
    }

    private static void checkInputOutputs(String line, int lineCount) {
        String[] caminhos = line.split(";");
        System.out.println("######################");
        System.out.println("Analysing lines from repo: " + repoMap.get(lineCount));
        int index = 0;

        for (String caminho : caminhos) {
            if (Integer.parseInt(caminho) == 1) {
                repoMap.get(lineCount).getOutputs().add(
                  repoMap.get(index)
                );
                System.out.println("Repo = " + repoMap.get(lineCount).getName());
                System.out.println("Output been added = " + repoMap.get(index));
                System.out.println("Inputs = " + repoMap.get(lineCount).getInputs());
                System.out.println("Outputs = " + repoMap.get(lineCount).getOutputs());
                System.out.println("----------------------");

                repoMap.get(index).getInputs().add(
                  repoMap.get(lineCount)
                );
                System.out.println("Repo = " + repoMap.get(index).getName());
                System.out.println("Input been added = " + repoMap.get(lineCount));
                System.out.println("Inputs = " + repoMap.get(index).getInputs());
                System.out.println("Outputs = " + repoMap.get(index).getOutputs());
                System.out.println("----------------------");
                System.out.println("######################");
            }

            index++;
        }
    }

    private static void criarRepositorios(String line) {
        String[] names = line.split(";");
        int index = 0;
        for (String name : names) {
            repoMap.add(new Repo(name, index));
            index++;
        }
    }
}
