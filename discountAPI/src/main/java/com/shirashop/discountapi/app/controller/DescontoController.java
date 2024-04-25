package com.shirashop.discountapi.app.controller;

import com.shirashop.discountapi.app.dto.desconto.DescontoRequest;
import com.shirashop.discountapi.app.dto.desconto.DescontoResponse;
import com.shirashop.discountapi.app.service.desconto.DescontoService;
import com.shirashop.discountapi.cross.enums.TipoDesconto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/discount/desconto")
@RequiredArgsConstructor
@Slf4j
public class DescontoController {

    private final DescontoService descontoService;

    // ***** QUERY METHODS*****

    @GetMapping(path = "/id/{descontoId}")
    public ResponseEntity<DescontoResponse> findById(@PathVariable("descontoId") Long descontoId){
        log.info("Find Desconto by Id request received! (ID: " + descontoId + ")");

        DescontoResponse descontoResponse = descontoService.findDescontoByIdService(descontoId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .header("StatusMessage", "Desconto encontrado com sucesso!")
                .body(descontoResponse);
    }

    @GetMapping(path = "/type/{tipoDesconto}")
    public ResponseEntity<List<DescontoResponse>> findAllByTipoDesconto(@PathVariable("tipoDesconto")TipoDesconto tipoDesconto){
        log.info("Find All By TipoDesconto request received! (TipoDesconto: " +tipoDesconto+ ")");

        List<DescontoResponse> descontoResponseList = descontoService.findAllDescontoByTipoDescontoService(tipoDesconto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .header("StatusMessage", "Lista gerada com sucesso!")
                .body(descontoResponseList);
    }

    // ***** CREATE METHODS ******

    @PostMapping(path = "/save")
    public ResponseEntity<DescontoResponse> saveDesconto (@RequestBody @Valid DescontoRequest descontoRequest){
        log.info("Save new Desconto request received! (" +descontoRequest.toString()+ ")");

        DescontoResponse descontoResponse = descontoService.saveDescontoService(descontoRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("StatusMessage", "Desconto criado com sucesso!")
                .body(descontoResponse);
    }

    // ***** UPDATE METHODS *****

    @PutMapping(path = "/update")
    public ResponseEntity<DescontoResponse> updateDesconto (@RequestBody DescontoRequest descontoRequest){
        log.info("Update Desconto request received! (" +descontoRequest.toString()+")");

        DescontoResponse descontoResponse = descontoService.updateDescontoService(descontoRequest);

        return ResponseEntity
                .status(HttpStatus.OK)
                .header("StatusMessage", "Desconto atualizado com sucesso!")
                .header("Message:","Utilize os endpoints específicos para atualizar o status ativo")
                .body(descontoResponse);
    }

    @PutMapping(path = "reactivate/{descontoId}")
    public ResponseEntity<DescontoResponse> reactivateDesconto (@PathVariable("descontoId") Long descontoId) {
        log.info("Reactivate Desconto request received! (ID: " +descontoId+ ")");

        DescontoResponse descontoResponse = descontoService.reactivateDescontoService(descontoId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .header("StatusMessage", "Desconto reativado com sucesso!")
                .body(descontoResponse);
    }

    @PutMapping(path = "/deactivate/{descontoId}")
    public ResponseEntity<DescontoResponse> deactivateDesconto (@PathVariable("descontoId") Long descontoId) {
        log.info("Deactivate Desconto request received! (ID: " +descontoId+ ")");

        DescontoResponse descontoResponse = descontoService.deactivateDescontoService(descontoId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .header("StatusMessage", "Desconto desativado com sucesso!")
                .body(descontoResponse);
    }
}