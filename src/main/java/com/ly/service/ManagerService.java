package com.ly.service;

import com.ly.model.Administrator;
import com.ly.model.Book;
import com.ly.model.Category;
import com.ly.model.User;

import java.util.List;

public interface ManagerService {
    Administrator login(String username, String password);
    void managerInformation(String username, String name, String sex, String tel);

    void managerPassword(String username, String password);

    List<Category> findAllCategory();

    Category findCategoryById(String categoryid);

    void addBook(Book book);

    void addCategory(Category category);

    public List<Book> wxys();

    public List<Book> rwsk();

    public List<Book> sets();

    public List<Book> jjjr();

    public List<Book> kxjs();

    public List<Book> jyks();

    Book findBookById(String book_id);

    void delBook(String book_id);

    void editBook(String book_id, String book_name, String book_author, String book_press, String book_desc,
                  double book_price, Integer book_kunumber);

    void editCategory(Category category);

    void delCategory(String category_id);

    List<User> findUsers();

    void addAdmin(Administrator admin);

    List<Book> sales();

}
