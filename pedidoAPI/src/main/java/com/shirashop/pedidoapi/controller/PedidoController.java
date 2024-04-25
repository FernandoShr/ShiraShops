package com.shirashop.pedidoapi.controller;

import java.util.List;

import com.shirashop.pedidoapi.exceptions.EmptyListException;
import com.shirashop.pedidoapi.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shirashop.pedidoapi.constants.StatusPedido;
import com.shirashop.pedidoapi.dto.PedidoInput;
import com.shirashop.pedidoapi.dto.PedidoOutput;
import com.shirashop.pedidoapi.dto.modelMapper.PedidoMapper;
import com.shirashop.pedidoapi.entities.Pedido;
import com.shirashop.pedidoapi.service.PedidoService;

import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;



@RestController
@RequestMapping(path = "/pedido")
@Slf4j
public class PedidoController {
	
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private PedidoMapper pedMapper;
	
	
	// **** FALLBACK METHOD *****
	
	public ResponseEntity<String> returnFallBackMessage(FeignException.ServiceUnavailable e){
		log.warn("Unavailable Service: "+ e.contentUTF8());
		return new ResponseEntity<String>("Serviço Indisponível! "
				+ "Por favor, tente novamente mais tarde.",HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	// **** MÉTODOS DE CONSULTA ****
	
	/**
	 * Consulta todos os pedidos já feitos
	 * @return ResponseEntity com pedidos encontrados e código 200
	 */
	@GetMapping
	public ResponseEntity<List<PedidoOutput>> findAll (){
		log.info("Searching for all Orders - in findAll");
		
		// Resgata os pedidos
		List<Pedido> pedidos = pedidoService.findAll();
		
		// Verifica se há pedidos ou não
		if(pedidos.size() == 0) {
			throw new EntityNotFoundException(
					"Não há pedidos cadastrados");
		}
		
		// Converte para a DTO de exibição
		List<PedidoOutput>pedidosOut = pedMapper.toListPedidoOutput(pedidos);
		
		log.info("All orders founded - end findAll");
		return new ResponseEntity<>(pedidosOut, HttpStatus.OK);	
	}
	
	/**
	 * Consulta todos os pedidos
	 * @param idUsuario
	 * @return
	 */
	@GetMapping(path="/user/id/{idUsuario}")
	public ResponseEntity<List<PedidoOutput>> findAllByUserId(@PathVariable("idUsuario") Integer idUsuario){
		log.info("Searching all user's orders - in findAllByUserId");
		
		// Encontra os pedidos realizados por um usuario
		List<Pedido> pedidosUser = pedidoService.findAllByUserId(idUsuario);
		
		// Converte para o DTO
		List<PedidoOutput> pedidosOut = pedMapper.toListPedidoOutput(pedidosUser);
		
		if(pedidosOut.size() == 0) {
			throw new EmptyListException("Usuário não possui nenhum pedido!");
		}
		
		log.info("All user's orders founded - end findAllByUserId");
		return new ResponseEntity<>(pedidosOut, HttpStatus.OK);
	}
	
	/**
	 * Consulta um Pedido pelo seu Id
	 * @param idPedido
	 * @return ResponseEntity com o pedido e status 200 OK
	 */
	@GetMapping(path="/id/{idPedido}")
	public ResponseEntity<PedidoOutput> findById (@PathVariable("idPedido") Integer idPedido){
		log.info("Searching for an order by id... - in findById");
		
		Pedido pedido = pedidoService.findById(idPedido);
		
		PedidoOutput pedidoOut = pedMapper.toPedidoOutput(pedido);
		
		log.info("Order founded - end findById");
		return new ResponseEntity<>(pedidoOut,HttpStatus.OK);
	}
	
	/**
	 * Consulta todos os Pedidos a partir do status
	 * @param status
	 * @return ResponseEntity com a lista de pedidos e código 200 OK
	 */
	@GetMapping(path="/status/{status}")
	public ResponseEntity<List<PedidoOutput>> findByStatus(@PathVariable("status") String status){
		log.info("Searching orders by status -  in findByStatus");
		
		List<Pedido> pedidos = pedidoService.findByStatus(StatusPedido.getByValor(status));
		
		List<PedidoOutput> pedidosOutput = pedMapper.toListPedidoOutput(pedidos);
		
		log.info("Orders founded - end findByStatus");
		return new ResponseEntity<List<PedidoOutput>>(pedidosOutput,HttpStatus.OK);
	}
	
	
	// **** MÉTODOS DE CREATE ****
	
	/**
	 * Registra o Pedido na base de dados
	 * @param pedido
	 * @return ResponseEntity<String> com uma mensagem de conclusão
	 * e status 200 OK
	 */
	@CircuitBreaker(name="Unavailable", fallbackMethod = "returnFallBackMessage")
	@PostMapping
	public ResponseEntity<String> insertPedido(@RequestBody PedidoInput pedido){
		log.info("Registering an order... - in insertPedido");
		
		pedidoService.insertPedido(pedido);
		
		log.info("Order registered - end insertPedido");
		return new ResponseEntity<>("Pedido registrado com sucesso!", HttpStatus.OK);
	}
	
	// **** MÉTODOS UPDATE ****
	
	/**
	 * <p>
	 * Atualiza o status do pedido para EFETUADO
	 * </p>
	 * @param pedido Objeto Pedido
	 * @return ResponseEntity<String> com uma mensagem de conclusão
	 * e status 200 OK
	 */
	@PutMapping(path="/efetuado")
	public ResponseEntity<String> statusToEfetuado(@RequestBody Pedido pedido){
		log.info("Changing status - in statusToEfetuado");
		
		pedidoService.statusToEfetuado(pedido);

		log.info("Status changed - end statusToEfetuado");
		return new ResponseEntity<String>("Pagamento Efetuado!", HttpStatus.OK);
	}
	
	/**
	 * Atualiza o status do pedido para PROCESSADO
	 * @param pedido Objeto Pedido
	 * @return ResponseEntity<String> com uma mensagem de conclusão
	 * e status 200 OK
	 */
	@PutMapping(path="/processado")
	public ResponseEntity<String> statusToProcessado(@RequestBody Pedido pedido){
		log.info("Changing status - in statusToProcessado");
		
		pedidoService.statusToProcessado(pedido);
		
		log.info("Status changed - end statusToProcessado");
		return new ResponseEntity<String>("Pedido Processado!", HttpStatus.OK);
	}
	
	/**
	 * Atualiza status do pedido para CONCLUIDO (4)
	 * @param pedido Objeto pedido
	 * @return ResponseEntity<String> com uma mensagem de conclusão
	 * e status 200 OK
	 */
	@PutMapping(path="/concluido")
	public ResponseEntity<String> statusToConcluido(@RequestBody Pedido pedido){
		log.info("Changing status - in statusToConcluido");
		
		pedidoService.statusToConcluido(pedido);
		
		log.info("Status changed - end statusToConcluido");
		return new ResponseEntity<String>("Pedido concluído!", HttpStatus.OK);
	}
	
	
	// **** MÉTODOS DE DELEÇÃO ****
	/**
	 * Altera o Status do pedido para CANCELADO (0)<br>
	 * Deleta apenas os pedidos no Status AGPAGAMENTO (1)
	 * @param pedidoIn Objeto pedido
	 * @return ResponseEntity<String> com uma mensangem de conclusão 
	 * e status 200 OK
	 */
	@DeleteMapping(path="/cancelado")
	public ResponseEntity<String> deletePedido (@RequestBody Pedido pedidoIn){
		log.info("Changing status - in deletePedido");
//		Pedido pedido = pedMapper.toPedido(pedidoIn);
		
		pedidoService.cancelaPedido(pedidoIn);
		
		log.info("Status changed - end deletePedido");
		return new ResponseEntity<>("Pedido cancelado com sucesso!", HttpStatus.OK);
	}
	
}
