public interface ProductoService {

    List<Producto> findAllProductos();
    Optional<Producto> findProducto(Long id);
    List<Producto> findByCategoria(String categoria);
    List<Producto> findByPrecio(float precio);

    Producto addProducto(Producto producto);
    void eliminarProductoById(Long productoId);
    Producto modificarProducto(Long productoId, Producto producto);
}
