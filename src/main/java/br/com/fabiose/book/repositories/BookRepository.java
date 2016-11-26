/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fabiose.book.repositories;

import br.com.fabiose.book.models.Book;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author fabioestrela
 */

@Repository
public interface BookRepository extends CrudRepository<Book, Integer>{

    @Override
    Book save(Book book);

    @Override
    void delete(Integer id);

    @Override
    List<Book> findAll();
    
    @Query("select b from Book b join b.user u where u.id = ?1")
    List<Book> findBookByUser(Integer idUser);
    
}
