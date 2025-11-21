public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByPrecio(float precio);
    List<Producto> findByCategoria(String categoria);
}
