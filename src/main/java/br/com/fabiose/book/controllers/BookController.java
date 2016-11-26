package br.com.fabiose.book.controllers;

/**
 *
 * @author fabioestrela
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.fabiose.book.services.BookService;
import br.com.fabiose.book.models.Book;
import br.com.fabiose.book.singleton.UserSingleton;
import br.com.fabiose.book.util.AuthorityUtil;
import br.com.fabiose.book.util.MessageUtil;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public String save(@ModelAttribute Book book, Model model) {
        MessageUtil messageUtil = new MessageUtil();
        try {
            book.setUser(UserSingleton.getInstance().getUser());
            bookService.save(book);
            messageUtil.setType("alert alert-success");
            messageUtil.setMessage("Book " + book.getTitle() + " inserted!");
        } catch (Exception e) {
            messageUtil.setType("alert alert-danger");
            messageUtil.setMessage(e.getMessage());
        } finally {
            try {
                model.addAttribute("books", bookService.findBookByUser(UserSingleton.getInstance().getUser().getId()));
            } catch (Exception e) {
            }
            model.addAttribute("book", new Book());
            model.addAttribute("msg", messageUtil);
            model.addAttribute("adminAuthority", AuthorityUtil.checkAdminAuthority());
        }
        return "book";
    }
    
    @RequestMapping("/book/delete/{id}")
    public String delete(@PathVariable Integer id, Model model) {
        MessageUtil messageUtil = new MessageUtil();
        try {
            bookService.delete(id);
            messageUtil.setType("alert alert-success");
            messageUtil.setMessage("Book deleted!");
        } catch (Exception e) {
            messageUtil.setType("alert alert-danger");
            messageUtil.setMessage(e.getMessage());
        } finally {
            try {
                model.addAttribute("books", bookService.findBookByUser(UserSingleton.getInstance().getUser().getId()));
            } catch (Exception e) {
            }
            model.addAttribute("book", new Book());
            model.addAttribute("msg", messageUtil);
            model.addAttribute("adminAuthority", AuthorityUtil.checkAdminAuthority());
        }
        return "book";
    }

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public String list(Model model) {
        try {
            model.addAttribute("books", bookService.findBookByUser(UserSingleton.getInstance().getUser().getId()));
            model.addAttribute("book", new Book());
            model.addAttribute("adminAuthority", AuthorityUtil.checkAdminAuthority());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "book";
    }
}
