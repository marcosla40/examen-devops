package com.devops.examen.service;

import com.devops.examen.entity.Producto;
import com.devops.examen.repository.ProductoRepository;
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
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class ProductoServiceImplTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoServiceImpl productoService;

    @Test
    void testFindAllProductos() {
        List<Producto> lista = new ArrayList<>();
        lista.add(new Producto());
        Mockito.when(productoRepository.findAll()).thenReturn(lista);

        List<Producto> resultado = productoService.findAllProductos();

        assertEquals(1, resultado.size());
        Mockito.verify(productoRepository).findAll();
    }

    @Test
    void testFindProducto() {
        Producto producto = new Producto();
        producto.setId(2L);
        Mockito.when(productoRepository.findById(2L)).thenReturn(Optional.of(producto));

        Optional<Producto> resultado = productoService.findProducto(2L);

        assertEquals(2L, resultado.get().getId());
        Mockito.verify(productoRepository).findById(2L);
    }

    @Test
    void testFindByCategoria() {
        List<Producto> lista = new ArrayList<>();
        Mockito.when(productoRepository.findByCategoria("Electronica")).thenReturn(lista);

        List<Producto> resultado = productoService.findByCategoria("Electronica");

        assertEquals(lista, resultado);
        Mockito.verify(productoRepository).findByCategoria("Electronica");
    }

    @Test
    void testFindByPrecio() {
        List<Producto> lista = new ArrayList<>();
        Mockito.when(productoRepository.findByPrecio(100.0f)).thenReturn(lista);

        List<Producto> resultado = productoService.findByPrecio(100.0f);

        assertEquals(lista, resultado);
        Mockito.verify(productoRepository).findByPrecio(100.0f);
    }

    @Test
    void testFindByPrecioAndCategoria() {
        List<Producto> lista = new ArrayList<>();
        Mockito.when(productoRepository.findByPrecioAndCategoria(50.0f, "Hogar")).thenReturn(lista);

        List<Producto> resultado = productoService.findByPrecioAndCategoria(50.0f, "Hogar");

        assertEquals(lista, resultado);
        Mockito.verify(productoRepository).findByPrecioAndCategoria(50.0f, "Hogar");
    }

    @Test
    void testAddProducto() {
        Producto producto = new Producto();
        Mockito.when(productoRepository.save(producto)).thenReturn(producto);

        Producto resultado = productoService.addProducto(producto);

        assertEquals(producto, resultado);
        Mockito.verify(productoRepository).save(producto);
    }

    @Test
    void testEliminarProductoById() {
        Producto producto = new Producto();
        producto.setId(2L);
        Mockito.when(productoRepository.findById(2L)).thenReturn(Optional.of(producto));

        productoService.eliminarProductoById(2L);

        Mockito.verify(productoRepository).findById(2L);
        Mockito.verify(productoRepository).delete(producto);
    }

    @Test
    void testModificarProducto_Existe() {
        Producto producto = new Producto();
        producto.setId(1L);
        Mockito.when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));
        Mockito.when(productoRepository.save(producto)).thenReturn(producto);

        Producto resultado = productoService.modificarProducto(1L, producto);

        assertEquals(producto, resultado);
        Mockito.verify(productoRepository).save(producto);
    }

    @Test
    void testModificarProducto_NoExiste() {
        Producto producto = new Producto();
        Mockito.when(productoRepository.findById(1L)).thenReturn(Optional.empty());

        Producto resultado = productoService.modificarProducto(1L, producto);

        assertNull(resultado);
        Mockito.verify(productoRepository, Mockito.times(0)).save(producto);
    }
}