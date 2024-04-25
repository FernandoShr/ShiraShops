package com.shirashop.discountapi.app.controller;

import com.shirashop.discountapi.app.dto.cupom.CupomRequest;
import com.shirashop.discountapi.app.dto.cupom.CupomResponse;
import com.shirashop.discountapi.app.service.cupom.CupomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/discount/cupom")
@RequiredArgsConstructor
@Slf4j
public class CupomController {
    private final CupomService cupomService;

    // ***** QUERY METHODS ******
    @GetMapping(path = "/id/{idCupom}")
    public ResponseEntity<CupomResponse> findById(@PathVariable("idCupom") Long cupomId){
        log.info("Find Cupom By Id request received! (ID: " + cupomId +")");

        CupomResponse cupomResponse = cupomService.findCupomByIdService(cupomId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .header("StatusMessage", "Cupom encontrado com sucesso!")
                .body(cupomResponse);
    }
    @GetMapping(path = "/codigo/{cupomCodigo}")
    public ResponseEntity<CupomResponse> findByCodigo(@PathVariable("cupomCodigo") String cupomCodigo){
        log.info("Find Cupom By Codigo request received! (Codigo: " + cupomCodigo +")");

        CupomResponse cupomResponse = cupomService.findCupomByCodigoService(cupomCodigo);

        return ResponseEntity
                .status(HttpStatus.OK)
                .header("StatusMessage", "Cupom encontrado com sucesso!")
                .body(cupomResponse);
    }
    @GetMapping(path = "/all/{isActive}")
    public ResponseEntity<List<CupomResponse>> findAllByAtivo (@PathVariable("isActive") Boolean isActive){
        log.info("Find All Cupom By ativo: "+ isActive.toString().toUpperCase() +" request received!");

        List<CupomResponse> cupomResponseList = cupomService.findAllCupomByAtivoService(isActive);

        return ResponseEntity
                .status(HttpStatus.OK)
                .header("StatusMessage", "Lista gerada com sucesso!")
                .body(cupomResponseList);
    }

    // ***** CREATE METHODS ******
    @PostMapping(path = "/save")
    public ResponseEntity<CupomResponse> saveCupom(@RequestBody @Valid CupomRequest cupomRequest) {
        log.info("Save Cupom request received! (" + cupomRequest.toString() +")");

        CupomResponse cupomResponse = cupomService.saveCupomService(cupomRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("StatusMessage","Cupom cadastrado com sucesso!")
                .body(cupomResponse);
    }

    // ***** UPDATE METHODS *****
    @PutMapping(path = "/update")
    public ResponseEntity<CupomResponse> updateCupom(@RequestBody @Valid CupomRequest cupomRequest){
        log.info("Edit Cupom request received! (" + cupomRequest.toString() + ")");

        CupomResponse cupomResponse = cupomService.updateCupomService(cupomRequest);

        return ResponseEntity
                .status(HttpStatus.OK)
                .header("StatusMessage", "Cupom atualizado com sucesso!")
                .header("Message:", "Utilize os endpoints específicos para atualizar o status ativo")
                .body(cupomResponse);
    }
    @PutMapping(path = "/reactivate/{cupomId}")
    public ResponseEntity<CupomResponse> reactivateCupom(@PathVariable("cupomId") Long cupomId){
        log.info("Reactivate Cupom request received! (ID: "+ cupomId +")");

        CupomResponse cupomResponse = cupomService.reactivateCupomService(cupomId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .header("StatusMessage", "Cupom reativado com sucesso!")
                .body(cupomResponse);
    }
    @PutMapping(path = "/deactivate/{cupomId}")
    public ResponseEntity<CupomResponse> deactivateCupom(@PathVariable("cupomId") Long cupomId) {
        log.info("Deactivate Cupom request received! (ID: "+ cupomId +")");

        CupomResponse cupomResponse = cupomService.deactivateCupomService(cupomId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .header("StatusMessage", "Cupom desativado com sucesso!")
                .body(cupomResponse);
    }
}