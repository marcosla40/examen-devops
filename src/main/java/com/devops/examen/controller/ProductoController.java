package com.devops.examen.controller;

import com.devops.examen.entity.Producto;
import com.devops.examen.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping(value = "/productos")
    public Producto addProducto(@RequestBody Producto producto) {
        return productoService.addProducto(producto);
    }

    @DeleteMapping(value = "/producto/{productoId}")
    public void deleteProducto(@PathVariable Long productoId) {
        productoService.eliminarProductoById(productoId);
    }

    @PutMapping(value = "/producto/{productoId}")
    public Producto modificarProducto(@PathVariable Long productoId, @RequestBody Producto producto) {
        return productoService.modificarProducto(productoId, producto);
    }

    @GetMapping(value = "/productos")
    public List<Producto> getProductos(@RequestParam(defaultValue = "0.0") Float precio,
                                       @RequestParam(defaultValue = "") String categoria) {
        /*
            - Si no se indica ni precio ni categoría -> obtener todos los productos.
            - Si se indica el precio -> obtener los productos con ese precio.
            - Si se indica la categoria -> obtener los productos con esa categoria.
         */
        if(precio == 0.0 && categoria == "") {
            return productoService.findAllProductos();
        } else if (precio == 0) {
            return productoService.findByPrecio(precio);
        }
        return productoService.findByCategoria(categoria);
    }

    @GetMapping(value = "/producto/{productoId}")
    public Optional<Producto> getProducto(@PathVariable Long productoId) {
        return productoService.findProducto(productoId);
    }

}