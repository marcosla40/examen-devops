package com.devops.examen.controller;

import com.devops.examen.entity.Producto;
import com.devops.examen.service.ProductoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ProductoControllerTest {

    @Mock
    private ProductoService productoService;

    @InjectMocks
    private ProductoController productoController;

    @Test
    void testAddProducto() {
        Producto producto = new Producto();
        Mockito.when(productoService.addProducto(producto)).thenReturn(producto);

        Producto resultado = productoController.addProducto(producto);

        assertEquals(producto, resultado);
        Mockito.verify(productoService).addProducto(producto);
    }

    @Test
    void testDeleteProducto() {
        productoController.deleteProducto(1L);
        Mockito.verify(productoService).eliminarProductoById(1L);
    }

    @Test
    void testModificarProducto() {
        Producto producto = new Producto();
        Mockito.when(productoService.modificarProducto(1L, producto)).thenReturn(producto);

        Producto resultado = productoController.modificarProducto(1L, producto);

        assertEquals(producto, resultado);
        Mockito.verify(productoService).modificarProducto(1L, producto);
    }

    @Test
    void testGetProducto() {
        Producto producto = new Producto();
        Mockito.when(productoService.findProducto(1L)).thenReturn(Optional.of(producto));

        Optional<Producto> resultado = productoController.getProducto(1L);

        assertEquals(producto, resultado.get());
        Mockito.verify(productoService).findProducto(1L);
    }

    @Test
    void testGetProductos_Todos() {
        List<Producto> lista = new ArrayList<>();
        Mockito.when(productoService.findAllProductos()).thenReturn(lista);

        List<Producto> resultado = productoController.getProductos(0.0f, "");

        assertEquals(lista, resultado);
        Mockito.verify(productoService).findAllProductos();
    }

    @Test
    void testGetProductos_PorPrecio() {
        List<Producto> lista = new ArrayList<>();
        Mockito.when(productoService.findByPrecio(10.0f)).thenReturn(lista);

        List<Producto> resultado = productoController.getProductos(10.0f, "");

        assertEquals(lista, resultado);
        Mockito.verify(productoService).findByPrecio(10.0f);
    }

    @Test
    void testGetProductos_PorCategoria() {
        List<Producto> lista = new ArrayList<>();
        Mockito.when(productoService.findByCategoria("Test")).thenReturn(lista);

        List<Producto> resultado = productoController.getProductos(0.0f, "Test");

        assertEquals(lista, resultado);
        Mockito.verify(productoService).findByCategoria("Test");
    }
}