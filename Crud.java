 package AtividadeCrud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Crud {

    public static void main(String[] args) {

        // Conexão local
System.out.println("Wilgner Mateus Silfer-0025265");
    	String url = "jdbc:mysql://junction.proxy.rlwy.net:54353/loja";
    	String usuariobanco = "root";
        String bancosenha = "LKxASiwlRBjMLpUPYTAVuHzFijzVlcSo";

        Scanner sc = new Scanner(System.in);

        try {

            Connection conexao = DriverManager.getConnection(
                url, usuariobanco, bancosenha
            );

            int opcao = -1;

            while (opcao != 0) {

                System.out.println("1 - Inserir | 2 - Listar | 3 - Atualizar | 4 - Deletar | 0 - Sair");

                opcao = sc.nextInt();
                sc.nextLine();

                switch (opcao) {

                case 1:

                    System.out.print("Código do produto: ");
                    int codigo = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Nome do produto: ");
                    String nome = sc.nextLine();

                    System.out.print("Preço do produto: ");
                    float preco = sc.nextFloat();

                    String sql = "INSERT INTO PRODUTO (CODIGO, NOME, PRECO) VALUES (?, ?, ?)";

                    PreparedStatement stmt = conexao.prepareStatement(sql);

                    stmt.setInt(1, codigo);
                    stmt.setString(2, nome);
                    stmt.setFloat(3, preco);

                    stmt.executeUpdate();
                    stmt.close();

                    System.out.println("Produto cadastrado com sucesso!");

                    break;

                    case 2:

                        System.out.println("Digite o código do produto:");
                        int codigo2 = sc.nextInt();

                        String sql2 = "SELECT * FROM PRODUTO WHERE CODIGO = ?";

                        PreparedStatement stmt2 = conexao.prepareStatement(sql2);

                        stmt2.setInt(1, codigo2);

                        ResultSet rs = stmt2.executeQuery();

                        while (rs.next()) {

                            System.out.println("Código: " + rs.getInt("codigo"));
                            System.out.println("Nome produto: " + rs.getString("nome"));
                            System.out.println("Preço: " + rs.getFloat("preco"));

                        }

                        rs.close();
                        stmt2.close();

                        break;

                    case 3:

                        System.out.println("Digite o código do produto para atualizar:");
                        int codPesquisa = sc.nextInt();

                        System.out.println("Digite o novo valor do produto:");
                        float novoPreco = sc.nextFloat();

                        String sqlUp = "UPDATE PRODUTO SET PRECO = ? WHERE CODIGO = ?";

                        PreparedStatement stmUp = conexao.prepareStatement(sqlUp);

                        stmUp.setFloat(1, novoPreco);
                        stmUp.setInt(2, codPesquisa);

                        int linhaAfetada = stmUp.executeUpdate();

                        if (linhaAfetada > 0) {

                            System.out.println("Preço atualizado com sucesso!");

                        } else {

                            System.out.println("Produto não localizado");

                        }

                        stmUp.close();

                        break;

                    case 4:

                        System.out.println("Digite o código do produto para deletar:");
                        int codDeleta = sc.nextInt();

                        String sqlDelete = "DELETE FROM PRODUTO WHERE CODIGO = ?";

                        PreparedStatement stmDelete = conexao.prepareStatement(sqlDelete);

                        stmDelete.setInt(1, codDeleta);

                        int linhasDeletadas = stmDelete.executeUpdate();

                        if (linhasDeletadas > 0) {

                            System.out.println("Produto deletado com sucesso!");

                        } else {

                            System.out.println("Produto não localizado!");

                        }

                        stmDelete.close();

                        break;

                    case 0:

                        System.out.println("Encerrando o programa...");

                        break;

                    default:

                        System.out.println("Opção inválida!!!");

                }

            }

            conexao.close();

        } catch (Exception e) {

            System.out.println("Erro de cadastro: " + e.getMessage());

        }

        sc.close();

    }

}
