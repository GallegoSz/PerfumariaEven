import java.util.ArrayList;
import javax.swing.JOptionPane;

public class PerfumariaEven {
    public static void main(String[] args) {
        ArrayList<Produtos> estoque = new ArrayList<>();
        ArrayList<Venda> vendas = new ArrayList<>();

        // - Estoque Inicial
        estoque.add(new CalvinKlein("CK One Eau de Toilette Unissex", 200));
        estoque.add(new CalvinKlein("Obsession Eau de Parfum Feminino", 380));
        estoque.add(new Dior("J'adore Eau de Parfum Feminino", 943.11));
        estoque.add(new Dior("Fahrenheit Eau de Toilette Masculino", 789.62));
        estoque.add(new Natura("Amó Eau de Toilette Feminino", 119.90));
        estoque.add(new Natura("Essencial Oud Eau de Parfum Masculino", 80));
        estoque.add(new Chanel("Bleu de Chanel Parfum Masculino", 999));
        estoque.add(new Chanel("Chance Eau de Parfum Feminino", 1503.23));
        // - Estoque Inicial

        JOptionPane.showMessageDialog(null, "Gerenciador de estoque da Perfumaria Even!");

        String response;
        while (true) {
            response = JOptionPane.showInputDialog("\nO que você gostaria de fazer? (Insira apenas números)" +
                    "\n1 - Mostrar o Estoque" +
                    "\n2 - Adicionar um produto ao estoque" +
                    "\n3 - Remover um produto do estoque" +
                    "\n4 - Efetuar a venda de um produto" +
                    "\n5 - Visualizar as vendas");

            if (response == null) {
                if (ConfirmarSaida()) {
                    return;
                } else {
                    continue;
                }
            }

            if (!response.matches("[1-5]")) {
                JOptionPane.showMessageDialog(null, "Opção inválida! Por favor insira um número válido.");
                continue;
            }

            switch (response) {
                case "1":
                    mostrarEstoque(estoque);
                    break;
                case "2":
                    adicionarProduto(estoque);
                    break;
                case "3":
                    removerProduto(estoque);
                    break;
                case "4":
                    efetuarVenda(estoque, vendas);
                    break;
                case "5":
                    mostrarVendas(vendas);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida! Por favor insira um número válido.");
            }
        }
    }

    private static void mostrarEstoque(ArrayList<Produtos> estoque) {
        if (estoque.isEmpty()) {
            JOptionPane.showMessageDialog(null, "O estoque está vazio no momento!");
        } else {
            StringBuilder ProdutosList = new StringBuilder("Produtos no Estoque:\n");
            for (int i = 0; i < estoque.size(); i++) {
                Produtos produto = estoque.get(i);
                ProdutosList.append(i + 1).append(" - ").append(produto.getNome()).append("\n");

                if (produto instanceof CalvinKlein calvinKlein) {
                    ProdutosList.append(" (Marca: Calvin Klein").append("\nPreco: R$").append(calvinKlein.getPreco()).append(" )");
                } else if (produto instanceof Natura natura) {
                    ProdutosList.append(" (Marca: Natura").append("\nPreco: R$").append(natura.getPreco()).append(" )");
                } else if (produto instanceof Chanel chanel) {
                    ProdutosList.append(" (Marca: Chanel").append("\nPreco: R$").append(chanel.getPreco()).append(" )");
                } else if (produto instanceof Dior dior) {
                    ProdutosList.append(" (Marca Dior").append("\nPreco: R$").append(dior.getPreco()).append(" )");
                }
                ProdutosList.append("\n\n");
            }
            JOptionPane.showMessageDialog(null, ProdutosList.toString());
        }
    }

    private static void adicionarProduto(ArrayList<Produtos> estoque) {
        String type = JOptionPane.showInputDialog(null, "Informe a marca do produto ser adicionado (Calvin Klein, Natura, Chanel ou Dior):");
        if (type == null) {
            if (ConfirmarSaida()) {
                return;
            }
            return;
        }
        switch (type.toLowerCase()) {
            case "calvin klein":
                String Nome = JOptionPane.showInputDialog("Nome do produto:");
                double Preco = getValidarPreco(Nome);
                estoque.add(new CalvinKlein(Nome, Preco));
                break;
            case "natura":
                Nome = JOptionPane.showInputDialog("Nome do produto:");
                Preco = getValidarPreco(Nome);
                estoque.add(new Natura(Nome, Preco));
                break;
            case "chanel":
                Nome = JOptionPane.showInputDialog("Nome do produto:");
                Preco = getValidarPreco(Nome);
                estoque.add(new Chanel(Nome, Preco));
                break;
            case "dior":
                Nome = JOptionPane.showInputDialog("Nome do produto:");
                Preco = getValidarPreco(Nome);
                estoque.add(new Dior(Nome, Preco));
                break;
            default:
                JOptionPane.showMessageDialog(null, "Marca inválida!");
        }

    }

    private static void removerProduto(ArrayList<Produtos> estoque) {
        if (estoque.isEmpty()) {
            JOptionPane.showMessageDialog(null, "O estoque está vazio no momento. Nada para remover.");
            return;
        }

        StringBuilder ProdutosList = new StringBuilder("Selecione um produto para remover:\n");
        for (int i = 0; i < estoque.size(); i++) {
            Produtos Produtos = estoque.get(i);
            ProdutosList.append(i + 1).append((" - ")).append(Produtos.getNome()).append("\n");
        }

        String input = JOptionPane.showInputDialog(ProdutosList.toString());
        if (input == null) {
            JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário.");
            return;
        }

        if (!input.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Entrada inválida. Insira um número válido.");
            return;
        }

        int index = Integer.parseInt(input) - 1;
        if (index < 0 || index >= estoque.size()) {
            JOptionPane.showMessageDialog(null, "Número de produto inválido. Operação cancelada.");
            return;
        }

        Produtos removerProduto = estoque.remove(index);
        JOptionPane.showMessageDialog(null, "Produto '" + removerProduto.getNome() + "' removido com sucesso!");
    }

    public static boolean ConfirmarSaida() {
        int ConfirmarSaida = JOptionPane.showConfirmDialog(null, "Você quer sair do programa?",
                "Confirmar Saída", JOptionPane.YES_NO_OPTION);
        return ConfirmarSaida == JOptionPane.YES_NO_OPTION;
    }

    private static double getValidarPreco(String productNome) {
        double Preco = -1;
        while (Preco <= 0) {
            try {
                String input = JOptionPane.showInputDialog("Entre com o preço do " + productNome + ":");
                if (input == null) {
                    JOptionPane.showMessageDialog(null, "Operação cancelada.");
                    return 0;
                }
                Preco = Double.parseDouble(input);
                if (Preco <= 0) {
                    JOptionPane.showMessageDialog(null, "Preco deve ser maior que zero. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida. Insira um número válido.");
            }
        }
        return Preco;
    }

    private static void efetuarVenda(ArrayList<Produtos> estoque, ArrayList<Venda> vendas) {
        String vendedorEscolhido;

        if (estoque.isEmpty()) {
            JOptionPane.showMessageDialog(null, "O Estoque está vazio! Não há produtos para vender.");
            return;
        } else {
            while (true) {
            String vendedor = JOptionPane.showInputDialog("\nVendedor responsável pela venda(Insira apenas números):" +
                    "\n1 - João Pedro Gallego de Souza" +
                    "\n2 - Daniel da Silva Caloi" +
                    "\n3 - Gabriel Dorneles de Souza" +
                    "\n4 - Giovanna Moura Lima" +
                    "\n5 - Grazielle Louise Garcia Rodrigues" +
                    "\n6 - Nayara Fortunato de Andrade");

            if (vendedor == null) {
                if (ConfirmarSaida()) {
                    return;
                } else {
                    continue;
                }
            }

            if (!vendedor.matches("[1-6]")) {
                JOptionPane.showMessageDialog(null, "Opção inválida! Por favor insira um número válido.");
                continue;
            }

            switch (vendedor) {
                case "1":
                        vendedorEscolhido = "João Pedro Gallego de Souza";
                        break;
                    case "2":
                        vendedorEscolhido = "Daniel da Silva Caloi";
                        break;
                    case "3":
                        vendedorEscolhido = "Gabriel Dorneles de Souza";
                        break;
                    case "4":
                        vendedorEscolhido = "Giovanna Moura Lima";
                        break;
                    case "5":
                        vendedorEscolhido = "Grazielle Louise Garcia Rodrigues";
                        break;
                    case "6":
                        vendedorEscolhido = "Nayara Fortunato de Andrade";
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opção inválida! Por favor insira um número válido.");
                        continue;
            }
            break;
        }
            StringBuilder ProdutosList = new StringBuilder("Selecione um produto para vender:\n");
            for (int i = 0; i < estoque.size(); i++) {
                Produtos Produtos = estoque.get(i);
                ProdutosList.append(i + 1).append((" - ")).append(Produtos.getNome()).append("\n");
            }

            String input = JOptionPane.showInputDialog(ProdutosList.toString());
            if (input == null) {
                JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário.");
                return;
            }

            if (!input.matches("\\d+")) {
                JOptionPane.showMessageDialog(null, "Entrada inválida. Insira um número válido.");
                return;
            }

            int index = Integer.parseInt(input) - 1;
            if (index < 0 || index >= estoque.size()) {
                JOptionPane.showMessageDialog(null, "Número de produto inválido. Operação cancelada.");
                return;
            }

            String cliente = JOptionPane.showInputDialog("Nome do cliente que comprou o produto:");

            Produtos vendido = estoque.remove(index);
            Venda novaVenda = new Venda(vendido, vendedorEscolhido, cliente);
            vendas.add(novaVenda);

            JOptionPane.showMessageDialog(null,
                    "Venda realizada: " + vendido.getNome() +
                            "\nCliente: " + cliente +
                            "\nVendedor: " + vendedorEscolhido +
                            "\nData e Hora: " + novaVenda.getDataHoraFormatada());
        }
    }

    private static void mostrarVendas(ArrayList<Venda> vendas) {
        if (vendas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma venda foi realizada ainda.");
            return;
        }

        StringBuilder lista = new StringBuilder("Vendas realizadas:\n");
        for (Venda venda : vendas) {
            lista.append("Produto: ").append(venda.getProduto().getNome())
                    .append(" - Cliente: ").append(venda.getCliente())
                    .append(" - Vendedor: ").append(venda.getVendedor())
                    .append(" - Data: ").append(venda.getDataHoraFormatada())
                    .append("\n");
        }
        JOptionPane.showMessageDialog(null, lista.toString());
    }
}