package com.ly.service.impl;


import com.ly.dao.ManagerDao;
import com.ly.model.Administrator;
import com.ly.model.Book;
import com.ly.model.Category;
import com.ly.model.User;
import com.ly.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("managerService")
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ManagerDao managerDao;
    public Administrator login(String username, String password) {
        return managerDao.login(username, password);
    }


    public void managerInformation(String username, String name, String sex, String tel) {
        Administrator admin = new Administrator(username, null, name, sex, tel);
        managerDao.managerInformation(admin);
    }


    public void managerPassword(String username, String password) {
        Administrator admin = new Administrator(username, password, null, null, null);
        managerDao.managerPassword(admin);
    }


    public List<Category> findAllCategory() {
        return managerDao.findAllCategory();
    }


    public Category findCategoryById(String categoryid) {
        return managerDao.findCategoryById(categoryid);
    }


    public void addBook(Book book) {
        book.setBook_id(UUID.randomUUID().toString());
        managerDao.addBook(book);

    }


    public void addCategory(Category category) {
        category.setCategory_id(UUID.randomUUID().toString());
        managerDao.addCategory(category);
    }

    public List<Book> wxys() {
        return managerDao.wxys();
    }

    public List<Book> rwsk() {
        return managerDao.rwsk();
    }


    public List<Book> sets() {
        return managerDao.sets();
    }


    public List<Book> jjjr() {
        return managerDao.jjjr();
    }


    public List<Book> kxjs() {
        return managerDao.kxjs();
    }


    public List<Book> jyks() {
        return managerDao.jyks();
    }


    public Book findBookById(String book_id) {
        return managerDao.findBookById(book_id);
    }


    public void delBook(String book_id) {
        managerDao.delBook(book_id);
    }


    public void editBook(String book_id, String book_name, String book_author, String book_press, String book_desc,
                         double book_price, Integer book_kunumber) {
        managerDao.editBook(book_id, book_name, book_author, book_press, book_desc, book_price, book_kunumber);
    }


    public void editCategory(Category category) {
        managerDao.editCategory(category);
    }


    public void delCategory(String category_id) {
        managerDao.delCategory(category_id);
    }


    public List<User> findUsers() {
        return managerDao.findUsers();
    }


    public void addAdmin(Administrator admin) {
        managerDao.addAdmin(admin);
    }


    public List<Book> sales() {
        return managerDao.sales();
    }

}
