package com.shirashop.discountapi.cross.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TipoDesconto {

    PROD("PROD"),
    CAT("CAT"),
    SUBCAT("SUBCAT");

    private final String tipo;

}

