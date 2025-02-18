import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Venda {
    private Produtos produto;
    private String vendedor;
    private String cliente;
    private LocalDateTime dataHora;

    public Venda(Produtos produto, String vendedor, String cliente) {
        this.produto = produto;
        this.vendedor = vendedor;
        this.cliente = cliente;
        this.dataHora = LocalDateTime.now();
    }

    public String getVendedor() {
        return vendedor;
    }

    public String getCliente() {
        return cliente;
    }

    public Produtos getProduto() {
        return produto;
    }

    public String getDataHoraFormatada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return dataHora.format(formatter);
    }
}
