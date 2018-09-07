package com.robertoeugenio.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.robertoeugenio.cursomc.domain.Categoria;
import com.robertoeugenio.cursomc.domain.Cidade;
import com.robertoeugenio.cursomc.domain.Cliente;
import com.robertoeugenio.cursomc.domain.Endereco;
import com.robertoeugenio.cursomc.domain.Estado;
import com.robertoeugenio.cursomc.domain.Pagamento;
import com.robertoeugenio.cursomc.domain.PagamentoComBoleto;
import com.robertoeugenio.cursomc.domain.PagamentoComCartao;
import com.robertoeugenio.cursomc.domain.Pedido;
import com.robertoeugenio.cursomc.domain.Produto;
import com.robertoeugenio.cursomc.domain.enums.EstadoPagamento;
import com.robertoeugenio.cursomc.domain.enums.TipoCliente;
import com.robertoeugenio.cursomc.repositories.CategoriaRepository;
import com.robertoeugenio.cursomc.repositories.CidadeRepository;
import com.robertoeugenio.cursomc.repositories.ClienteRepository;
import com.robertoeugenio.cursomc.repositories.EnderecoRepository;
import com.robertoeugenio.cursomc.repositories.EstadoRepository;
import com.robertoeugenio.cursomc.repositories.PagamentoRepository;
import com.robertoeugenio.cursomc.repositories.PedidoRepository;
import com.robertoeugenio.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired // para inicicar salvando
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtorepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");// instanciados ]

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3)); // fazendo as associaçoes de mao dupla
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1)); // fazendo as associaçoes de mao dupla

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));// criar lista automatica com elementos que eu quiser
		produtorepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);//instaciando
		
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393")); // instanciando
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303 ", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2)); // isntanciando
		
		clienteRepository.saveAll(Arrays.asList(cli1));
	    enderecoRepository.saveAll(Arrays.asList(e1, e2));	
	    
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	    
	    Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
	    Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e1);
	    
	    Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);//instanciando pgto
	    ped1.setPagamento(pagto1); //pedido do pgto1
	    
	    Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00 "), null);//instanciando pgto
	    ped2.setPagamento(pagto2);
	
	    cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
	    //para salvar esses objetos temos que criar o repository
	    
	    pedidoRepository.saveAll(Arrays.asList(ped1, ped2)); //gravando os dados
	    pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2)); //gravando os dados
	    
	}
}
