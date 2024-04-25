package com.shirashop.discountapi.infra.repository;

import com.shirashop.discountapi.cross.enums.TipoDesconto;
import com.shirashop.discountapi.infra.entity.DescontoEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DescontoRepository extends CrudRepository<DescontoEntity, Long> {

    List<DescontoEntity> findAllByTipoDesconto(TipoDesconto tipoDesconto);

}