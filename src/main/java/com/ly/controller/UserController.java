package com.ly.controller;

import com.ly.model.*;
import com.ly.service.UserService;


import com.sun.deploy.security.WSeedGenerator;
import com.sun.org.apache.bcel.internal.generic.FADD;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    public UserService userService;
    //用户登录
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request, Model model){
//        System.out.println("userService:"+userService);//User user = userService.selectUserByUserName(username);

        User user = userService.selectUserByUserName(username);
        System.out.println(user);
        if(user!=null){
            if(user.getPassword().equals(password)){
                System.out.println("登录成功");
                HttpSession session = request.getSession();
                session.setAttribute("user",user);

                model.addAttribute("state","登录成功 <a href='" + request.getContextPath() + "/index.jsp'>回到主页</a>");
                return "loginSuccess";
            }
            else{
                System.out.println("用户名或密码错误");
                model.addAttribute("failure", "用户名或密码错误！");
                return "loginError";
            }
        }else {
            System.out.println("用名不存在");
            model.addAttribute("state", "failure");
            model.addAttribute("message", "该用户不存在");
            return "loginError";
        }
    }

    //用户注册
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam("username") String username, @RequestParam("password") String password,@RequestParam("sex")String sex,@RequestParam("name")String name,@RequestParam("tel")String tel,@RequestParam("address")String address, HttpServletRequest request, Model model) {
        System.out.println("输入的信息"+username+password);
        String id = UUID.randomUUID().toString();
        User user = new User();

        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setSex(sex);
        user.setTel(tel);
        user.setName(name);
        user.setAddress(address);

        boolean flag = userService.insertUser(user);
        System.out.println(flag);
        return "loginSuccess";
    }
    //用户注销
    @RequestMapping(value ="/layout",method = RequestMethod.GET)
    @ResponseBody
    private void layout(HttpServletRequest req, HttpServletResponse resp) {
        try {
            HttpSession session = req.getSession();
            session.removeAttribute("user");// 获取session对象，从session中移除登陆信息
            resp.sendRedirect("http://localhost:8080/index.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //用户修改密码
    @RequestMapping(value ="/changePassword",method = RequestMethod.POST)
    public void changePassword(@RequestParam("username")String username,@RequestParam("password")String password,@RequestParam("repassword")String repassword,HttpServletRequest request,HttpServletResponse response,Model model) throws IOException {
        userService.changePassword(username,password);

        response.getWriter().write("<div style='text-align: center;margin-top: 260px'><img src='" + request.getContextPath()
                + "/img/duigou.png'/>done!</div>");
    }
    //文学艺术书籍
    @RequestMapping(value = "wxys",method = RequestMethod.GET)
    public String wxys(HttpServletRequest request,HttpSession session){
        List<Book> books = userService.wxys();// 文学艺术类书籍
        session.setAttribute("books",books);
        System.out.println(books);
        return "showBook";
    }
    //人文社科书籍
    @RequestMapping(value = "rwsk",method = RequestMethod.GET)
    public String rwsk(HttpServletRequest request,HttpSession session){
        List<Book> books = userService.rwsk();
        session.setAttribute("books",books);
        System.out.println(books);
        return "showBook";
    }
    //少儿童书书籍
    @RequestMapping(value = "sets",method = RequestMethod.GET)
    public String sets(HttpServletRequest request,HttpSession session){
        List<Book> books = userService.sets();
        session.setAttribute("books",books);
        System.out.println(books);
        return "showBook";
    }
    //教育考试书籍
    @RequestMapping(value = "jyks",method = RequestMethod.GET)
    public String jyks(HttpServletRequest request,HttpSession session){
        List<Book> books = userService.jyks();
        session.setAttribute("books",books);
        return "showBook";
    }
    //经济金融书籍
    @RequestMapping(value = "jjjr",method = RequestMethod.GET)
    public String jjjr(HttpServletRequest request,HttpSession session){
        List<Book> books = userService.jjjr();
        session.setAttribute("books",books);
        System.out.println(books);
        return "showBook";
    }
    //科学技术
    @RequestMapping(value = "kxjs",method = RequestMethod.GET)
    public String kxjs(HttpServletRequest request,HttpSession session){
        List<Book> books = userService.kxjs();
        session.setAttribute("books",books);
        System.out.println(books);
        return "showBook";
    }

    //书籍详情
    @RequestMapping(value = "bookDetail/book_id={book_id}",method = RequestMethod.GET)
    public String bookDetail(@PathVariable String book_id, HttpServletRequest request,HttpServletResponse response){
        Book book = findBookById(book_id);
        request.setAttribute("book",book);
        return "particulars";
    }
    private Book findBookById(String book_id) {
        Book book = userService.findBookById(book_id);
        return book;
    }
    //添加书籍进购物车
    @RequestMapping(value = "addCart/book_id={book_id}",method = RequestMethod.GET)
    public void addCart(@PathVariable String book_id,HttpServletRequest request,HttpServletResponse response){
        Book book = findBookById(book_id);
        HttpSession session = request.getSession();
        Cart cart = (Cart)session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        cart.addBook(book);
    }
    //删除购物车书籍
    @RequestMapping(value = "delItem/book_id={book_id}",method = RequestMethod.GET)
    public String delItem(@PathVariable String book_id,HttpServletRequest request,HttpServletResponse response,HttpSession session){
        Cart cart = (Cart) session.getAttribute("cart");
        cart.getItmes().remove(book_id);
        return "showCart";
    }
    //展示收藏夹
    @RequestMapping(value = "showFavorite",method = RequestMethod.GET)
    public String showFavorite(HttpSession session){
        User user = (User)session.getAttribute("user");
        String user_id= user.getId();
        List<Favorite> favorites = userService.findFavoriteByUserId(user_id);

        //由于不好解决一表多映射的问题，我选择遍历list<favorite>，给每一个list都根据findBookByBookId（）来分别加上book类，
        for( int i = 0 ; i < favorites.size() ; i++) {//内部不锁定，效率最高，但在多线程要考虑并发操作的问题。
            Book book_old  = favorites.get(i).getBook();
            Book book_new = findBookById(book_old.getBook_id());
            favorites.get(i).setBook(book_new);
            favorites.get(i).setUser(user);
        }
        session.setAttribute("favorite",favorites);
        return "favorite";
    }
    //添加书籍进收藏夹
    @RequestMapping(value = "addFavorite",method = RequestMethod.GET)
    public void addFavorite(@RequestParam("book_id") String book_id,HttpSession session){
        User user = (User) session.getAttribute("user");
        Book book = userService.findBookById(book_id);
        String user_id = user.getId();
        Favorite favorite = new Favorite();
        favorite.setUser(user);
        boolean isExit = userService.findFavorite(user_id,book_id);
        if(isExit == false){
            favorite.setBook(book);
            userService.addFavorite(user_id,book_id);
        }
    }
    //删除收藏书籍
    @RequestMapping(value = "delFavorite",method = RequestMethod.GET)
    private String delFavorite(@Param("book_id")String book_id, HttpSession session){
        userService.delFavorite(book_id);
        List<Favorite> favorList = (List<Favorite>) session.getAttribute("favorite");
        Iterator<Favorite> iterator = favorList.iterator();
        while(iterator.hasNext()){
            Favorite favorite = iterator.next();
            if(book_id.equals(favorite.getBook().getBook_id())) {
                iterator.remove();
            }
        }
        return "favorite";
    }

    //查询书籍
    @RequestMapping(value = "search",method = RequestMethod.POST)
    private String search(@RequestParam("search")String search ,HttpServletRequest req, HttpServletResponse resp,HttpSession session) {
        System.out.print("searchkey is ："+search);
        List<Book> searchmessage = userService.search(search);
        session.setAttribute("books", searchmessage);
        return "showBook";
    }
    @RequestMapping(value = "changeNum/num={num}&book_id={book_id}",method = RequestMethod.GET)
    private String changeNum(@PathVariable String num,@PathVariable String book_id, HttpServletRequest req, HttpServletResponse resp) {
        // 取出购物车
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        CartItem item = cart.getItmes().get(book_id);
        item.setQuantity(Integer.parseInt(num));
        return "showCart";

    }
}
