package com.shirashop.discountapi.cross.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "productapi", path = "/product")
public interface ProductFeignClient {
    // TODO Refatorar IDs API de produto

    @GetMapping(path = "/id/{id_produto}")
    public void findProdById(@PathVariable("id_produto") Long idProduto);

    @GetMapping(path="/categoria/id/{id_categoria}")
    public void findCatById(@PathVariable("id_categoria") Long idCategoria);

    @GetMapping(path="/subcategoria/id/{id_sub_categoria}")
    public void findSubcatById(@PathVariable("id_sub_categoria") Long idSubcategoria);
}