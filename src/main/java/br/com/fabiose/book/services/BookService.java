/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fabiose.book.services;

import br.com.fabiose.book.models.Book;
import br.com.fabiose.book.repositories.BookRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author fabioestrela
 */
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book save(Book book) throws Exception{
        return bookRepository.save(book);
    }
    
    public void delete(Integer id) throws Exception{
        bookRepository.delete(id);
    }
    
    public List<Book> listAll() throws Exception{
        return bookRepository.findAll();
    }

    public List<Book> findBookByUser(Integer idUser){
        return bookRepository.findBookByUser(idUser);
    }
}
