package com.ly.service;

import com.ly.model.Book;
import com.ly.model.Favorite;
import com.ly.model.User;

import java.util.List;

public interface UserService {
    User selectUserByUserName(String username);
    boolean insertUser(User user);
    void changePassword(String username,String password);
    List<Book> wxys();
    List<Book> rwsk();
    List<Book> sets();
    List<Book> jjjr();
    List<Book> kxjs();
    List<Book> jyks();
    Book findBookById(String book_id);

    void addFavorite(String user_id, String book_id);

    List<Favorite> findFavoriteByUserId(String user_id);

    boolean findFavorite(String user_id, String book_id);

    void delFavorite(String book_id);

    List<Book> search(String search);

}
