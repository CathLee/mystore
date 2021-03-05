package com.ly.service.impl;

import com.ly.dao.UserDao;
import com.ly.model.Book;
import com.ly.model.Favorite;
import com.ly.model.User;
import com.ly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public User selectUserByUserName(String username) {
        return userDao.selectUserByUserName(username);
    }

    public boolean insertUser(User user) {
        return userDao.insertUser(user);
    }
    public void changePassword(String username,String password){
        User user = new User();
        user.setId(null);
        user.setUsername(username);
        user.setPassword(password);
        user.setSex(null);
        user.setTel(null);
        user.setName(null);
        user.setAddress(null);
        userDao.changePassword(user);
    }
    //文学艺术
    public List<Book> wxys(){
        return userDao.wxys();
    }
    //人文社科
    public List<Book> rwsk(){
        return userDao.rwsk();
    }
    //少儿图书
    public List<Book> sets(){
        return userDao.sets();
    }
    //教育考试
    public List<Book> jyks(){
        return userDao.jyks();
    }
    //经济金融
    public List<Book> jjjr(){
        return userDao.jjjr();
    }
    //科学技术
    public List<Book> kxjs(){
        return userDao.kxjs();
    }

    public Book findBookById(String book_id) {
        return userDao.findBookById(book_id);
    }

    public void addFavorite(String user_id, String book_id) {
        userDao.addFavorite(UUID.randomUUID().toString(), user_id, book_id);
    }


    public List<Favorite> findFavoriteByUserId(String user_id) {
        return userDao.findFavoriteByUserId(user_id);
    }


    public boolean findFavorite(String user_id, String book_id) {
        return userDao.findFavorite(user_id, book_id);
    }

    public void delFavorite(String book_id){
        userDao.delFavorite(book_id);
    }

    public List<Book> search(String search) {
        return userDao.search(search);
    }

}
