package com.shirashop.userapi.entities.fromPedido;

import java.math.BigDecimal;

import com.shirashop.userapi.entities.fromProduct.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalhePedido {

    private Integer idDetalhePedido;

    private Integer quantidade;

    //Calculado automaticamente
    private BigDecimal subTotal;

    private ProductDTO produto;


}
