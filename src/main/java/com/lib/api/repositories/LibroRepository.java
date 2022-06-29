package com.lib.api.repositories;

import com.lib.api.entities.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, String> {
    @Query(
            value = "SELECT l FROM Libro l WHERE l.Titulo LIKE %:nombre%"
    )
    List<Libro> findByNombre(@Param("nombre")  String nombre);

    @Query(
            value = "select l.isbn, l.titulo, l.descripcion, l.stock, l.precio_unitario, g.nombre  \n" +
                    "\tfrom geolibreria.libro l \n" +
                    "\t\tjoin geolibreria.libros_generos lg \n" +
                    "\t\t\ton l.isbn = lg.isbn\n" +
                    "\t\tjoin geolibreria.genero g \n" +
                    "\t\t\ton g.id_genero = lg.id_genero \n" +
                    "where g.nombre like %:genero%",
            nativeQuery = true
    )
    List<Libro> findByGenero(@Param("genero") String genero);

    @Query(
            value = "select l.isbn, l.titulo, l.descripcion, l.stock, l.precio_unitario, a.nombre  \n" +
                    "\tfrom geolibreria.libro l \n" +
                    "\t\tjoin geolibreria.libros_autores la \n" +
                    "\t\t\ton l.isbn = la.isbn \n" +
                    "\t\tjoin geolibreria.autor a  \n" +
                    "\t\t\ton la.id_autor = a.id_autor \n" +
                    "where a.nombre like %:autor% \n" +
                    "order by l.titulo ",
            nativeQuery = true
    )
    List<Libro> findByAutor(@Param("autor") String autor);

    @Query(
            value = "select l.isbn, l.titulo, l.descripcion, l.stock, l.precio_unitario, e.nombre  \n" +
                    "from geolibreria.libro l \n" +
                    "\tjoin geolibreria.libros_editoriales le \n" +
                    "\t\ton l.isbn = le.isbn \n" +
                    "\tjoin geolibreria.editorial e  \n" +
                    "\t\ton le.id_editorial = e.id_editorial \n" +
                    "where e.nombre like %:editorial% \n" +
                    "order by l.titulo",
            nativeQuery = true
    )
    List<Libro> findByEditorial(@Param("editorial") String editorial);
}
