import java.io.*;
import java.util.Scanner;

public class Atv_java {

    public static void main(String[] args) {
        String caminhoA = "C:\\Users\\Public\\Documents\\rYANN.txt";

        System.out.println("Caminho do arquivo: " + caminhoA);
        Resumo(caminhoA);
    }

    public static void Resumo(String caminhoB) {
        File in = new File(caminhoB);

        if (!in.exists()) {
            System.out.println("Erro: O arquivo não foi encontrado.");
            return;
        } else if (!in.isFile()) {
            System.out.println("Erro: O caminho não é um arquivo válido.");
            return;
        } else {
            System.out.println("Arquivo encontrado: " + caminhoB);
        }

        File pastaSaida = new File(in.getParent(), "out");
        if (!pastaSaida.exists()) {
            if (!pastaSaida.mkdirs()) {
                System.out.println("Erro: Não foi possível criar a pasta de saída.");
                return;
            }
        }

        File out = new File(pastaSaida, "summary.csv");

        try (
                BufferedReader reader = new BufferedReader(new FileReader(in));
                BufferedWriter writer = new BufferedWriter(new FileWriter(out))
        ) {
            String linha;

            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");

                if (dados.length == 3) {
                    try {

                        String item = dados[0].trim();
                        double preco = Double.parseDouble(dados[1].trim());
                        int quantidade = Integer.parseInt(dados[2].trim());
                        double total = preco * quantidade;


                        writer.write(String.format("%s,%.2f\n", item, total));
                    } catch (NumberFormatException e) {
                        System.out.println("Erro ao converter valores numéricos: " + linha);
                    }
                } else {
                    System.out.println("Linha inválida ignorada: " + linha);
                }
            }
            System.out.println("Resumo gerado com sucesso em: " + out.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Erro ao ler ou escrever o arquivo: " + e.getMessage());
        }
    }
}
