package com.shirashop.discountapi.infra.dataprovider;

import com.shirashop.discountapi.cross.exceptions.customexcep.cupom.CupomAlreadyActiveException;
import com.shirashop.discountapi.cross.exceptions.customexcep.cupom.CupomAlreadyDeactivateException;
import com.shirashop.discountapi.cross.exceptions.customexcep.cupom.CupomNotFoundException;
import com.shirashop.discountapi.domain.gateway.CupomGateway;
import com.shirashop.discountapi.domain.model.CupomModel;
import com.shirashop.discountapi.infra.entity.CupomEntity;
import com.shirashop.discountapi.infra.mapper.MapperInfraCupom;
import com.shirashop.discountapi.infra.repository.CupomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataProviderCupom implements CupomGateway {

    private final MapperInfraCupom cupomMapper;

    private final CupomRepository cupomRepository;

    @Override
    public CupomModel findCupomByIdGateway(Long cupomId) {
        log.info("Searching Cupom by ID: " + cupomId + "...");

        return cupomMapper.entityToModel(
                cupomRepository.findById(cupomId)
                        .orElseThrow(() -> new CupomNotFoundException(
                                "Não foi possível encontrar um Cupom de ID: " + cupomId + "\nPor favor, tente novamente.",
                                "Cupom ID: " + cupomId + " not found")));
    }

    @Override
    public CupomModel findCupomByCodigoGateway(String cupomCodigo) {
        log.info("Searching Cupom by Codigo " + cupomCodigo + "...");

        return cupomMapper.entityToModel(
                cupomRepository.findByCodigo(cupomCodigo)
                        .orElseThrow(() -> new CupomNotFoundException(
                                "Não foi possível encontrar um Cupom de Codigo: " + cupomCodigo + "\nPor favor, tente novamente.",
                                "Cupom Codigo: " + cupomCodigo + " not found")));
    }
    @Override
    public List<CupomModel> findAllCupomByAtivoGateway(Boolean isActive) {

        log.info("Searching for All Cupom with ativo: " + isActive.toString().toUpperCase());

        List<CupomEntity> allByAtivo = cupomRepository.findAllByAtivo(isActive);

        log.info("List generated with "+ allByAtivo.size() +" cupom(s)");

        return cupomMapper.entityToModelList(allByAtivo);
    }
    @Override
    @Transactional
    public CupomModel saveCupomGateway(CupomModel cupomReceived) {

        log.info("Saving new Cupom in MySQL DB... (Code: " + cupomReceived.getCodigo() + ")");
        return cupomMapper.entityToModel(
                cupomRepository.save(
                        cupomMapper.modelToEntity(cupomReceived)));
    }

    @Override
    @Transactional
    public CupomModel updateCupomGateway(CupomModel cupomReceived) {

        //Verifica se existe cupom no Banco de Dados
        CupomModel cupomFounded = findCupomByCodigoGateway(cupomReceived.getCodigo());

        log.info("Updating Cupom (Code: " + cupomReceived.getCodigo() + ")...");
        return cupomMapper.entityToModel(
                cupomRepository.save(
                        cupomMapper.modelToEntityUpdate(cupomReceived, cupomFounded)));
    }

    @Override
    public CupomModel reactivateCupomGateway(Long cupomId) {
        CupomModel cupomFounded = findCupomByIdGateway(cupomId);
        log.info("Reactivating Cupom (ID: "+ cupomFounded.getId() +", Code: "+ cupomFounded.getCodigo() +")...");

        // Valida se o cupom está inativo
        if (Boolean.FALSE.equals(cupomFounded.getAtivo())){
            cupomFounded.setAtivo(true);
        }
        else{
            throw new CupomAlreadyActiveException("Este Cupom já está ativo",
                    "Cupom already Active (ID: "+ cupomFounded.getId() +", Code: "+ cupomFounded.getCodigo() +")");
        }

        return cupomMapper.entityToModel(
                cupomRepository.save(
                        cupomMapper.modelToEntity(cupomFounded)));
    }

    @Override
    public CupomModel deactivateCupomGateway(Long cupomId) {
        CupomModel cupomFounded = findCupomByIdGateway(cupomId);
        log.info("Deactivating Cupom (Id: "+ cupomFounded.getId() +", Code: "+ cupomFounded.getCodigo() +")...");

        // Valida se o cupom está realmente ativo
        if (Boolean.TRUE.equals(cupomFounded.getAtivo())){
            cupomFounded.setAtivo(false);
        }
        else{
            throw new CupomAlreadyDeactivateException("Este Cupom já está desativado",
                    "Cupom already deactivate (ID: "+ cupomFounded.getId() +", Code: "+ cupomFounded.getCodigo() +")");
        }

        return cupomMapper.entityToModel(
                cupomRepository.save(
                        cupomMapper.modelToEntity(cupomFounded)));
    }
}
