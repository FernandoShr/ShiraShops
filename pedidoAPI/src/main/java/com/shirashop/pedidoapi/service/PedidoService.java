package com.shirashop.pedidoapi.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.shirashop.pedidoapi.constants.StatusPedido;
import com.shirashop.pedidoapi.entities.DetalhePedido;
import com.shirashop.pedidoapi.entities.product.Product;
import com.shirashop.pedidoapi.exceptions.EntityNotFoundException;
import com.shirashop.pedidoapi.exceptions.InvalidFieldException;
import com.shirashop.pedidoapi.feignclients.ProductFeignClient;
import com.shirashop.pedidoapi.feignclients.UserFeignClient;
import com.shirashop.pedidoapi.repository.DetalhePedidoRepository;
import com.shirashop.pedidoapi.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shirashop.pedidoapi.dto.DetalhePedidoInput;
import com.shirashop.pedidoapi.dto.PedidoInput;
import com.shirashop.pedidoapi.dto.ProductOrder;
import com.shirashop.pedidoapi.dto.modelMapper.ProductMapper;
import com.shirashop.pedidoapi.entities.Pedido;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private DetalhePedidoRepository dpRepository;
	
	@Autowired
	private ProductFeignClient productFeignClient;
	@Autowired
	private UserFeignClient userFeignClient;
	
	@Autowired
	private ProductMapper prodMapper;
	
	// *** MÉTODOS DE CONSULTA
	
	/**
	 * Resgata todos os pedidos registrados
	 * @return
	 */
	public List<Pedido> findAll() {
		log.info("[PedidoService] : in findAll");
		
		List<Pedido> pedidos = (List<Pedido>) pedidoRepository.findAll();
		
		log.info("[PedidoService] : end findAll");
		return pedidos;
	}
	
	/**
	 * Resgata todos os pedidos de um usuário
	 * @param idUsuario
	 * @return
	 */
	public List<Pedido> findAllByUserId(Integer idUsuario){
		log.info("[PedidoService] : in findAllByUserId");
		
		// Verifica se o usuário existe
		userFeignClient.consultingId(idUsuario);
		
		// Encontra os pedidos realizados pelo usuário
		List<Pedido> pedidosUser = pedidoRepository.findByIdUsuario(idUsuario);
		
		log.info("[PedidoService] : end findAllByUserId");
		return pedidosUser;
	}
	
	/**
	 * Resgata um pedido específico
	 * @param idPedido
	 * @return
	 */
	public Pedido findById(Integer idPedido) {
		log.info("[PedidoService] in findById");
		
		if(idPedido == null) {
			throw new EntityNotFoundException(
					"Desculpe, não foi possível encontrar um pedido com este id. "
					+ "Verifique e tente novamente.");
		}
		
		Pedido pedido =  pedidoRepository.findById(idPedido).orElseThrow(
				()-> new EntityNotFoundException(
						"Desculpe, não foi possível encontrar um pedido com este id. "
						+ "Verifique e tente novamente."));
		
		log.info("[PedidoService] end findById");
		return pedido;
	}
	
	/**
	 * Resgata todos os Pedidos com um status específico
	 * @param status
	 * @return
	 */
	public List<Pedido> findByStatus(StatusPedido status){
		
		if (status == null) {
			throw new InvalidFieldException("Este status não existe. "
					+ "Por favor, tente novamente.");
		}
		
		List<Pedido> statusPedido = pedidoRepository.findByStatusPedido(status);
		
		return statusPedido;
	}
	
	// *** MÉTODOS CREATE ***
	
	/**
	 * Insere o pedido no banco de dados
	 * @param pedidoInput
	 * @return o pedido salvo
	 */
	public Pedido insertPedido(PedidoInput pedidoInput) {
		log.info("[PedidoService] in insertPedido");
		
		// Verifica se o usuário existe
		userFeignClient.consultingId(pedidoInput.getIdUsuario());
		
		// Entidade Pedido a ser salva
		Pedido pedido = new Pedido();
		
		// *** RELACIONA AO USUARIO ***
		pedido.setIdUsuario(pedidoInput.getIdUsuario());
		
		// ***** SALVA A DATA ATUAL ********
		// Data de criação do Produto
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		pedido.setDataCriacao(dtf.format(LocalDateTime.now()));
		
		// **** DEFININDO STATUS ****
		pedido.setStatusPedido(StatusPedido.AGPAGAMENTO);
		
		// **** CALCULO DO TOTAL PEDIDO ****
		List<DetalhePedidoInput> detalhesPedidos = pedidoInput.getDetalhePedido();
		
		pedido.setTotalPedido(new BigDecimal(0.0));
		pedido.setDetalhePedido(new ArrayList<DetalhePedido>());
		for(DetalhePedidoInput detalhePedido : detalhesPedidos) {
			
			// *** ENCONTRA O PRODUTO ***
			Product produto = productFeignClient.consultingId(
					detalhePedido.getIdProduto()).getBody();
			// Retira o stock do produto
			productFeignClient.subtraiEstoque(produto, detalhePedido.getQuantidade());
			
			// *** DEFINE DETALHE PEDIDO DE PEDIDO ***
			DetalhePedido dp = new DetalhePedido();
			dp.setQuantidade(detalhePedido.getQuantidade());
			dp.setProduto(prodMapper.toProductDTO(produto));
			dp.setSubTotal(new BigDecimal(detalhePedido.getQuantidade() * produto.getValor_unitario()));
			
			// Adiciona à Lista de pedido
			pedido.getDetalhePedido().add(dp);
			// Adiciona o Subtotal de DetalhePedido em TotalPedido
			pedido.setTotalPedido(pedido.getTotalPedido().add(dp.getSubTotal()));
			
		}
		
		dpRepository.saveAll(pedido.getDetalhePedido());
		pedidoRepository.save(pedido);
		
		log.info("[PedidoService] end insertPedido");
		return pedido;
	}
	
	// **** MÉTODOS DE UPDATE ***
	
	/**
	 * Atualiza o status do pedido para EFETUADO
	 * @param pedido Pedido a ser atualizado
	 */
	public void statusToEfetuado(Pedido pedido) {
		log.info("[PedidoService] in statusToEfetuado");
		
		// Verifica se o pedido existe
		Pedido efetPedido = findById(pedido.getIdPedido());
		
		// Pedido precisa não estar cancelado
		if(efetPedido.getStatusPedido() == StatusPedido.CANCELADO) {
			throw new InvalidFieldException("Não foi possível terminar a operação "
					+ "pois o pedido foi cancelado.");
		}
		
		// Atualiza o pedido
		efetPedido.setStatusPedido(StatusPedido.EFETUADO);
		
		// *** SALVA A DATA DE MODIFICAÇÃO ***
 		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		efetPedido.setDataModificacao(dtf.format(LocalDateTime.now()));
		
		// Salva o pedido
		pedidoRepository.save(efetPedido);
		
		log.info("[PedidoService] end statusToEfetuado");
	}
	
	/**
	 * Atualiza o status do pedido para PROCESSADO
	 * @param pedido Pedido a ser atualizado
	 */
	public void statusToProcessado(Pedido pedido) {
		log.info("[PedidoService] in statusToProcessado");
		
		// Verifica se o pedido existe
		Pedido procPedido = findById(pedido.getIdPedido());
		
		// Pedido precisa não estar cancelado
		if(procPedido.getStatusPedido() == StatusPedido.CANCELADO) {
			throw new InvalidFieldException("Não foi possível terminar a operação "
					+ "pois o pedido foi cancelado.");
		}
		
		// Atualiza pedido
		procPedido.setStatusPedido(StatusPedido.PROCESSADO);
		
		// *** SALVA A DATA DE MODIFICAÇÃO ***
 		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		procPedido.setDataModificacao(dtf.format(LocalDateTime.now()));
		
		// Salva a atualização
		pedidoRepository.save(procPedido);
		
		log.info("[PedidoService] end statusToProcessado");
	}
	
	/**
	 * Atualiza o status do pedido para CONCLUIDO
	 * @param pedido Pedido a ser atualizado
	 */
	public void statusToConcluido(Pedido pedido) {
		log.info("[PedidoService] in statusToConcluido");
		
		// Verifica se existe o pedido
		Pedido concluiPedido = findById(pedido.getIdPedido());
		
		// Pedido precisa não estar cancelado
		if(concluiPedido.getStatusPedido() == StatusPedido.CANCELADO) {
			throw new InvalidFieldException("Não foi possível terminar a operação "
					+ "pois o pedido foi cancelado.");
		}
		
		// Atualiza o pedido
		concluiPedido.setStatusPedido(StatusPedido.CONCLUIDO);
		
		// *** SALVA A DATA DE MODIFICAÇÃO ***
 		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		concluiPedido.setDataModificacao(dtf.format(LocalDateTime.now()));
		
		// Salva a atualização
		pedidoRepository.save(concluiPedido);
		
		log.info("[PedidoService] end statusToConcluido");
	}
	
	
	// **** MÉTODOS DE DELEÇÃO ****
	
	public void cancelaPedido(Pedido pedido) {
		log.info("[PedidoService] in cancelaPedido");
		
		// Verifica se o pedido existe
		Pedido cancelPedido = findById(pedido.getIdPedido());
		
		// Pedido precisa estar no estado Aguardando Pagamento para poder ser cancelado
		if(cancelPedido.getStatusPedido() != StatusPedido.AGPAGAMENTO) {
			throw new InvalidFieldException(
					"Não é possível cancelar o pedido.\n"
					+ "Só é possível cancelar pedidos antes de "
					+ "efetuar o pagamento.");
		}
		
		// Cancela o Pedido
		cancelPedido.setStatusPedido(StatusPedido.CANCELADO);

		// *** SALVA A DATA DE MODIFICAÇÃO ***
 		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		cancelPedido.setDataModificacao(dtf.format(LocalDateTime.now()));
		
		// Retorna o estoque do produto
		List<ProductOrder> produtos = new ArrayList<>();
		for (DetalhePedido det : cancelPedido.getDetalhePedido()) {
			ProductOrder prodOrder = prodMapper.toProductOrder(det.getProduto());
			prodOrder.setQuantidade(det.getQuantidade());
			produtos.add(prodOrder);
		}
		
		productFeignClient.retornaEstoque(produtos);
		// Salva a atualização
		pedidoRepository.save(cancelPedido);
		
		log.info("[PedidoService] end cancelaPedido");
	}
	
}
;