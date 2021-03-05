package com.ly.dao;


import com.ly.model.Book;
import com.ly.model.Favorite;
import com.ly.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserDao {
    User selectUserByUserName(@Param("username") String username);
    boolean insertUser(User user);
    void changePassword(User user);
    List<Book> wxys();
    List<Book> rwsk();
    List<Book> sets();
    List<Book> jjjr();
    List<Book> kxjs();
    List<Book> jyks();
    Book findBookById(@Param("book_id") String book_id);

    void addFavorite(@Param("favorite_id") String string, @Param("user_id") String user_id, @Param("book_id")String book_id);

    List<Favorite> findFavoriteByUserId(@Param("user_id")String user_id);

    boolean findFavorite(@Param("user_id") String user_id,@Param("book_id") String book_id);

    void delFavorite(String book_id);

    List<Book> search(@Param("search") String search);
}
