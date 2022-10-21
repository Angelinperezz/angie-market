package com.angiemarket.persistence.crud;

import com.angiemarket.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {
    /*Hay que especificarle a la interfaz que va a heredar un CrudRepository de tipo lista
    * y en esa lista va a recibir el nombre de la clase y el tipo de llave primaria dicha clase*/
    // De manera nativa seria asi
    // @Query(value = "SELECT * FROM productos WHERE id_categoria = ?", nativeQuery = true)
    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);
    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);


}
