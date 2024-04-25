package com.shirashop.discountapi.infra.dataprovider;

import com.shirashop.discountapi.cross.enums.TipoDesconto;
import com.shirashop.discountapi.cross.exceptions.customexcep.desconto.DescontoAlreadyActiveException;
import com.shirashop.discountapi.cross.exceptions.customexcep.desconto.DescontoAlreadyDeactivateException;
import com.shirashop.discountapi.cross.exceptions.customexcep.desconto.DescontoNotFoundException;
import com.shirashop.discountapi.domain.gateway.DescontoGateway;
import com.shirashop.discountapi.domain.model.DescontoModel;
import com.shirashop.discountapi.infra.entity.DescontoEntity;
import com.shirashop.discountapi.infra.mapper.MapperInfraDesconto;
import com.shirashop.discountapi.infra.repository.DescontoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataProviderDesconto implements DescontoGateway {

    private final MapperInfraDesconto descontoMapper;

    private final DescontoRepository descontoRepository;

    @Override
    public DescontoModel findDescontoByIdGateway(Long descontoId) {

        log.info("Searching Desconto by ID: " +descontoId+ "...");
        return descontoMapper.entityToModel(
                descontoRepository.findById(descontoId)
                            .orElseThrow(() -> new DescontoNotFoundException(
                                "Não foi possível encontrar um Desconto de ID: " +descontoId+ "\nPor favor, tente novamente.",
                                "Desconto ID: " +descontoId+ " not found")));
    }

    @Override
    public List<DescontoModel> findAllDescontoByTipoDescontoGateway(TipoDesconto tipoDesconto) {

        log.info("Searching for All Descontos by type: " +tipoDesconto+ " ...");

        List<DescontoEntity> allByTipoDesconto = descontoRepository.findAllByTipoDesconto(tipoDesconto);

        log.info("List generated with: " +allByTipoDesconto.size()+ " desconto(s)");

        // Verifica se os descontos estão vencidos
        allByTipoDesconto.stream()
                .filter(d-> d.getDataExpiracao().isBefore(LocalDate.now()))
                .forEach(d->d.setIsActive(false));
        // Salva as modificações
        descontoRepository.saveAll(allByTipoDesconto);

        return descontoMapper.entityToModelList(allByTipoDesconto);
    }

    @Override
    @Transactional
    public DescontoModel saveDescontoGateway(DescontoModel descontoReceived) {

        log.info("Saving new Desconto in MySQL DB... (ID: "+descontoReceived.getIdDesconto()+")");
        return descontoMapper.entityToModel(
                descontoRepository.save(
                        descontoMapper.modelToEntity(descontoReceived)));
    }

    @Override
    @Transactional
    public DescontoModel updateDescontoGateway(DescontoModel descontoReceived) {

        // Verifica se existe Desconto no DB
        DescontoModel descontoFounded = findDescontoByIdGateway(descontoReceived.getIdDesconto());

        log.info("Updating Desconto (ID: " +descontoReceived.getIdDesconto()+ "...");
        return descontoMapper.entityToModel(
                descontoRepository.save(
                        descontoMapper.modelToEntityUpdate(descontoReceived, descontoFounded)));
    }

    @Override
    public DescontoModel reactivateDescontoGateway(Long descontoId) {
        DescontoModel descontoFounded = findDescontoByIdGateway(descontoId);

        log.info("Reactivating Desconto (ID: "+descontoFounded.getIdDesconto()+
                ", Active: " +descontoFounded.getIsActive().toString().toUpperCase()+ ")...");

        if(Boolean.FALSE.equals(descontoFounded.getIsActive())){
            descontoFounded.setIsActive(Boolean.TRUE);
        }
        else{
            throw new DescontoAlreadyActiveException("Este Desconto já está ativo",
                    "Desconto already active! (ID : " +descontoFounded.getIdDesconto() +")");
        }

        return descontoMapper.entityToModel(
                descontoRepository.save(
                        descontoMapper.modelToEntity(descontoFounded)));
    }

    @Override
    public DescontoModel deactivateDescontoGateway(Long descontoId) {
        DescontoModel descontoFounded = findDescontoByIdGateway(descontoId);

        log.info("Deactivating Desconto (ID: "+descontoFounded.getIdDesconto()+
                ", Active: " +descontoFounded.getIsActive().toString().toUpperCase()+ ")...");

        if(Boolean.TRUE.equals(descontoFounded.getIsActive())){
            descontoFounded.setIsActive(Boolean.FALSE);
        }
        else{
            throw new DescontoAlreadyDeactivateException("Este Desconto já está inativo",
                    "Desconto already deactivate! (ID : " +descontoFounded.getIdDesconto() +")");
        }

        return descontoMapper.entityToModel(
                descontoRepository.save(
                        descontoMapper.modelToEntity(descontoFounded)));
    }
}