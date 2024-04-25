package com.shirashop.discountapi.testObjects;

import com.shirashop.discountapi.app.dto.cupom.CupomRequest;
import com.shirashop.discountapi.app.dto.cupom.CupomResponse;
import com.shirashop.discountapi.domain.model.CupomModel;
import com.shirashop.discountapi.infra.entity.CupomEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class CupomTestObjects {

    static final LocalDate dataCriacao = LocalDate.parse("2023-08-15");
    static final LocalDate dataExpiracao = LocalDate.parse("2023-09-17");

    public static final CupomModel CUPOM_MODEL =
            new CupomModel(Long.parseLong("1"),
                    "10OFF",
                    new BigDecimal("0.1"),
                    10,
                    new BigDecimal(100),
                    new BigDecimal("20.5"),
                    dataCriacao,
                    dataExpiracao,
                    Boolean.TRUE);
    public static final CupomModel CUPOM_MODEL_2 =
            new CupomModel(Long.parseLong("2"),
                    "20OFF",
                    new BigDecimal("0.2"),
                    20,
                    new BigDecimal(200),
                    new BigDecimal("30.5"),
                    dataCriacao,
                    dataExpiracao,
                    Boolean.TRUE);
    public static final CupomModel CUPOM_MODEL_TO_REACTIVATE =
            new CupomModel(Long.parseLong("2"),
                    "20OFF",
                    new BigDecimal("0.2"),
                    20,
                    new BigDecimal(200),
                    new BigDecimal("30.5"),
                    dataCriacao,
                    dataExpiracao,
                    Boolean.FALSE);
    public static final CupomModel CUPOM_MODEL_TO_DEACTIVATE =
            new CupomModel(Long.parseLong("2"),
                    "20OFF",
                    new BigDecimal("0.2"),
                    20,
                    new BigDecimal(200),
                    new BigDecimal("30.5"),
                    dataCriacao,
                    dataExpiracao,
                    Boolean.TRUE);
    public static final CupomModel CUPOM_MODEL_INVALID =
            new CupomModel(Long.parseLong("-1"),
                    " ",
                    new BigDecimal("-2"),
                    -10,
                    new BigDecimal(-100),
                    new BigDecimal("-20.5"),
                    null,
                    null,
                    null);
    public static final CupomEntity CUPOM_ENTITY =
            new CupomEntity(Long.parseLong("1"),
                    "10OFF",
                    new BigDecimal("0.1"),
                    10,
                    new BigDecimal(100),
                    new BigDecimal("20.5"),
                    dataCriacao,
                    dataExpiracao,
                    Boolean.TRUE);
    public static final CupomEntity CUPOM_ENTITY_2 =
            new CupomEntity(Long.parseLong("2"),
                    "20OFF",
                    new BigDecimal("0.2"),
                    20,
                    new BigDecimal(200),
                    new BigDecimal("30.5"),
                    dataCriacao,
                    dataExpiracao,
                    Boolean.TRUE);
    public static final CupomEntity CUPOM_ENTITY_TO_DEACTIVATE =
            new CupomEntity(Long.parseLong("2"),
                    "20OFF",
                    new BigDecimal("0.2"),
                    20,
                    new BigDecimal(200),
                    new BigDecimal("30.5"),
                    dataCriacao,
                    dataExpiracao,
                    Boolean.TRUE);
    public static final CupomEntity CUPOM_ENTITY_TO_REACTIVATE =
            new CupomEntity(Long.parseLong("2"),
                    "20OFF",
                    new BigDecimal("0.2"),
                    20,
                    new BigDecimal(200),
                    new BigDecimal("30.5"),
                    dataCriacao,
                    dataExpiracao,
                    Boolean.FALSE);
    public static final CupomResponse CUPOM_RESPONSE =
            new CupomResponse(Long.parseLong("1"),
                    "10OFF",
                    new BigDecimal("0.1"),
                    10,
                    new BigDecimal(100),
                    new BigDecimal("20.5"),
                    dataCriacao,
                    dataExpiracao,
                    Boolean.TRUE);
    public static final CupomResponse CUPOM_RESPONSE_2 =
            new CupomResponse(Long.parseLong("2"),
                    "20OFF",
                    new BigDecimal("0.2"),
                    20,
                    new BigDecimal(200),
                    new BigDecimal("30.5"),
                    dataCriacao,
                    dataExpiracao,
                    Boolean.TRUE);
    public static final CupomRequest CUPOM_REQUEST =
            new CupomRequest(
                    "20OFF",
                    new BigDecimal("0.2"),
                    20,
                    new BigDecimal(200),
                    new BigDecimal("30.5"),
                    dataExpiracao);

    public static final List<CupomModel> CUPOM_MODEL_LIST = Arrays.asList(CUPOM_MODEL, CUPOM_MODEL_2);
    public static final List<CupomEntity> CUPOM_ENTITY_LIST = Arrays.asList(CUPOM_ENTITY, CUPOM_ENTITY_2);
    public static final List<CupomResponse> CUPOM_RESPONSE_LIST = Arrays.asList(CUPOM_RESPONSE, CUPOM_RESPONSE_2);

    ResponseEntity<CupomResponse> RESP_ENT_CUPOM_RESPONSE =
            ResponseEntity
                    .status(HttpStatus.CREATED)
                    .header("StatusMessage","Cupom cadastrado com sucesso!")
                    .body(CUPOM_RESPONSE);
}