package com.shirashop.discountapi.infra.repository;

import com.shirashop.discountapi.infra.entity.CupomEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CupomRepository extends CrudRepository <CupomEntity, Long> {

    Optional<CupomEntity> findByCodigo (String cupomCodigo);

    List<CupomEntity> findAllByAtivo (Boolean ativo);
}