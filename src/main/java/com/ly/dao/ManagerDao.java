package com.ly.dao;

import com.ly.model.Administrator;
import com.ly.model.Book;
import com.ly.model.Category;
import com.ly.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ManagerDao {
    Administrator login(@Param("username") String username,@Param("password") String password);

    void managerInformation(Administrator admin);

    void managerPassword(Administrator admin);

    List<Category> findAllCategory();

    Category findCategoryById(@Param("categoryid") String categoryid);

    void addBook(Book book);

    void addCategory(Category category);

    List<Book> wxys();

    List<Book> rwsk();

    List<Book> sets();

    List<Book> jjjr();

    List<Book> kxjs();

    List<Book> jyks();

    Book findBookById(String book_id);

    void delBook(String book_id);

    void editBook(@Param("book_id") String book_id, @Param("book_name")String book_name, @Param("book_author")String book_author,@Param("book_press") String book_press, @Param("book_desc")String book_desc,
                  @Param("book_price")double book_price, @Param("book_kunumber")Integer book_kunumber);

    void editCategory(Category category);

    void delCategory(@Param("category_id") String category_id);

    List<User> findUsers();

    void addAdmin(Administrator admin);

    List<Book> sales();
}

