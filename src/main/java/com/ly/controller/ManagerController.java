package com.ly.controller;


import com.ly.model.Administrator;
import com.ly.model.Book;
import com.ly.model.Category;
import com.ly.model.User;
import com.ly.service.ManagerService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/manage")
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    //登录
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam("username")String username, @RequestParam("password")String password, HttpServletRequest request, Model model){
        Administrator admin = managerService.login(username,password);
        HttpSession session = request.getSession();
        if (admin.getPassword().equals(password)){
            System.out.println("管理员登录成功");
            System.out.println(admin);
            session.setAttribute("admin",admin);
            return "message";
        }else {
            model.addAttribute("state","无此用户，请联系管理员！！  <a href='http://localhost:8080/index.jsp'>返回首页</a>");
            return "loginError";
        }
    }
    //注销
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
    //修改信息
    @RequestMapping(value ="managerInformation",method = RequestMethod.POST)
    public void managerInformation(@RequestParam("username")String username,@RequestParam("name")String name, @RequestParam("sex")String sex,@RequestParam("tel")String tel,HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws IOException {
        managerService.managerInformation(username, name, sex, tel);
        System.out.print("admin's username is :"+username);
        Administrator admin = (Administrator) session.getAttribute("admin");
        admin.setName(name);
        admin.setSex(sex);
        admin.setTel(tel);
        session.setAttribute("admin", admin);
        resp.getWriter().write("<div style='text-align: center;margin-top: 260px'><img src='" + req.getContextPath()
                + "/img/duigou.png'/>done!</div>");
    }
    //修改密码
    @RequestMapping(value ="managerPassword",method = RequestMethod.POST)
    public void managerPassword(@RequestParam("username")String username,@RequestParam("password")String password,@RequestParam("repassword")String repassword,HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws IOException {
        managerService.managerPassword(username, password);
        resp.getWriter().write("<div style='text-align: center;margin-top: 260px'><img src='" + req.getContextPath()
                + "/img/duigou.png'/>done!</div>");
    }
    //添加admin
    @RequestMapping(value ="addAdmin",method = RequestMethod.POST)
    public void addAdmin(@RequestParam("username")String username,@RequestParam("name")String name, @RequestParam("password")String password,@RequestParam("sex")String sex,@RequestParam("tel")String tel,HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws IOException {
        Administrator admin = new Administrator(username, password, name, sex, tel);
        managerService.addAdmin(admin);
        resp.getWriter().write("<div style='text-align: center;margin-top: 260px'><img src='" + req.getContextPath()
                + "/img/duigou.png'/>done!</div>");
    }

    @RequestMapping(value = "wxys",method = RequestMethod.GET)
    public String wxys(HttpServletRequest request,HttpSession session){
        List<Book> books = managerService.wxys();// 文学艺术类书籍
        session.setAttribute("books",books);
        System.out.println(books);
        return "booksList";
    }
    //人文社科书籍
    @RequestMapping(value = "rwsk",method = RequestMethod.GET)
    public String rwsk(HttpServletRequest request,HttpSession session){
        List<Book> books = managerService.rwsk();
        session.setAttribute("books",books);
        return "booksList";
    }
    //少儿童书书籍
    @RequestMapping(value = "sets",method = RequestMethod.GET)
    public String sets(HttpServletRequest request,HttpSession session){
        List<Book> books = managerService.sets();
        session.setAttribute("books",books);
        return "booksList";
    }
    //教育考试书籍
    @RequestMapping(value = "jyks",method = RequestMethod.GET)
    public String jyks(HttpServletRequest request,HttpSession session){
        List<Book> books = managerService.jyks();
        session.setAttribute("books",books);
        return "booksList";
    }
    //经济金融书籍
    @RequestMapping(value = "jjjr",method = RequestMethod.GET)
    public String jjjr(HttpServletRequest request,HttpSession session){
        List<Book> books = managerService.jjjr();
        session.setAttribute("books",books);
        return "booksList";
    }
    //科学技术
    @RequestMapping(value = "kxjs",method = RequestMethod.GET)
    public String kxjs(HttpServletRequest request,HttpSession session){
        List<Book> books = managerService.kxjs();
        session.setAttribute("books",books);
        return "booksList";
    }
    //后台删除书籍
    @RequestMapping(value = "delBook&book_id={book_id}",method = RequestMethod.GET)
    public void delItem(@PathVariable String book_id, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        managerService.delBook(book_id);
        response.getWriter().write("<div style='text-align: center;margin-top: 260px'><img src='" + request.getContextPath()
                + "/img/duigou.png'/>done！</div>");
    }

    //后台编辑书籍
    @RequestMapping(value = "editBook",method = RequestMethod.POST)
    public void editBook(@RequestParam("book_id") String book_id,
                         @RequestParam("book_name")String book_name,
                         @RequestParam("book_author")String book_author,
                         @RequestParam("book_press")String book_press,
                         @RequestParam("book_desc")String book_desc,
                         @RequestParam("book_price")Double book_price,
                         @RequestParam("book_kunumber")Integer book_kunumber,
                         HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        managerService.editBook(book_id, book_name, book_author, book_press, book_desc, book_price, book_kunumber);
        response.getWriter().write("<div style='text-align: center;margin-top: 260px'><img src='" + request.getContextPath()
                + "/img/duigou.png'/>done！</div>");
    }

    @RequestMapping(value = "editBookUI&book_id={book_id}",method = RequestMethod.GET)
    public String editBookUI(@PathVariable String book_id,HttpServletRequest req, HttpServletResponse resp,HttpSession session){
        Book book = findBookById(book_id);
        List<Category> category = managerService.findAllCategory();
        HashMap map = new HashMap();
        map.put("book", book);
        map.put("category", category);
        session.setAttribute("map", map);
        return "editBook";
    }

    private Book findBookById(String book_id) {
        return managerService.findBookById(book_id);
    }

    //书籍信息维护
    @RequestMapping(value = "categoryList",method = RequestMethod.GET)
    public String categoryList(HttpSession session){
        List<Category> categories = managerService.findAllCategory();
        session.setAttribute("categorys", categories);
        return "categorysList";
    }
    @RequestMapping(value = "editCategoryUI&category_id={category_id}",method = RequestMethod.GET)
    public String editCategoryUI(@PathVariable String category_id, HttpSession session){
        Category category = managerService.findCategoryById(category_id);
        session.setAttribute("category", category);
        return "editCategory_01";
    }

    @RequestMapping(value = "editCategory",method = RequestMethod.POST)
    public void editCategory(@RequestParam("category_id")String category_id,
                               @RequestParam("category_name")String category_name,
                               @RequestParam("category_desc")String category_desc,
                               HttpServletResponse response,
                               HttpServletRequest request,
                               HttpSession session) throws IOException {
        Category category = new Category(category_id,category_name,category_desc);
        managerService.editCategory(category);
        response.getWriter().write("<div style='text-align: center;margin-top: 260px'><img src='" + request.getContextPath()
                + "/img/duigou.png'/>done!</div>");
    }

    @RequestMapping(value = "delCategory&category_id={category_id}",method = RequestMethod.GET)
    private void delCategory(@PathVariable String category_id, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        managerService.delCategory(category_id);
        resp.getWriter().write("<div style='text-align: center;margin-top: 260px'><img src='" + req.getContextPath()
                + "/img/duigou.png'/>done!</div>");
    }

    @RequestMapping(value = "addCategory",method = RequestMethod.POST)
    private void addCategory(@RequestParam("category_name")String category_name,
                             @RequestParam("category_desc")String category_desc,
                             HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String category_id = UUID.randomUUID().toString();
        Category category = new Category(category_id,category_name,category_desc);
        managerService.addCategory(category);// 调用添加方法
        resp.getWriter().write("<div style='text-align: center;margin-top: 260px'><img src='" + req.getContextPath()
                + "/img/duigou.png'/>done!</div>");
    }




    //添加书籍
    @RequestMapping(value = "addBookUI",method = RequestMethod.GET)
    private  String addBookUI(HttpServletRequest req, HttpServletResponse resp,HttpSession session) {
        List<Category> categorys = managerService.findAllCategory();
        session.setAttribute("cs", categorys);
        return "addBook";
    }
    @RequestMapping(value = "addBook",method = RequestMethod.POST)
    private void addBook(HttpServletRequest req, HttpServletResponse resp) throws FileUploadException, IOException, FileUploadException {
        // 判断表单是不是multipart/form-data类型
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if (!isMultipart) {
            throw new RuntimeException("表单上传类型有误！！");
        }
        // 创建工厂用来 解析请求内容
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 构建核心类对象
        ServletFileUpload sfu = new ServletFileUpload(factory);
        List<FileItem> items = new ArrayList<FileItem>();
        items = sfu.parseRequest(req);
        System.out.println("items are"+items);
        Book book = new Book();
        String book_id = UUID.randomUUID().toString();
        book.setBook_id(book_id);
        for (FileItem item : items) {
            if (item.isFormField()) {
                // 普通字段：把数据分装到book对象中
                processFormField(item, req, book);
            } else {
                // 上传字段：上传
                processUplodFiled(item, req, book);
            }
        }
        System.out.println("books "+book);
        // 把书籍信息保存到数据库中
        managerService.addBook(book);
        resp.getWriter().write("<div style='text-align: center;margin-top: 260px'><img src='" + req.getContextPath()
                + "/img/done!'/>添加成功！</div>");
    }

    // 处理文件上传
    private void processUplodFiled(FileItem item, HttpServletRequest request, Book book) {
        try {
            ServletContext servletContext = request.getServletContext();
            // 存放路径：不要放在web-inf中
            // 01.获取存放文件的真实目录
            String dirImages = servletContext.getRealPath("/img");
            // 02. 通过io存文件
            // 03. 生成文件名 （用户上传图片， 图片名可能重复）
            String FieldName = item.getFieldName();// 输入框的name值
            String name = item.getName();
            String fileType = name.substring(name.lastIndexOf(".") + 1);
            String fileName = UUID.randomUUID().toString();// 生成用不重复的文件名
            // 生成文件夹名
            Date time = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String strTime = simpleDateFormat.format(time);
            // path属性filename
            String path = strTime;// 存放到book对象中
            String filename = fileName + "." + fileType;
            // fileDir：图片最终存在于fileDir
            File fileDir = new File(dirImages, path + File.separator + filename);
            // InputStream inputStream = item.getInputStream(); 从请求 对象中 通过流的方式
            // 把 上传的文件加载到 内存中 构建输出流
            File parentDir = new File(dirImages, path);// 父目录
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }
            book.setFilename(filename);
            book.setPath(path);
            System.out.println("books are"+book);
            InputStream inputStream = item.getInputStream();
            FileOutputStream fos = new FileOutputStream(fileDir);
            int len = 0;
            while ((len = inputStream.read()) != -1) {
                fos.write(len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 把Fielditem中的数据封装到book中
    private void processFormField(FileItem item, HttpServletRequest req, Book book) {
        try {
            // item每一个item对象代表一个输入框
            // 01. 获取input输入框的 name 的值 根据规范 输入框 的name的取值， 与 javabean 中的 属性名一致
            String inputName = item.getFieldName();
            String inputValue = item.getString("UTF-8");
            System.out.println("inputNames are "+inputName);
            System.out.println("inputValues are "+inputValue);
            switch (inputName){
                case "book_name":book.setBook_name(inputValue);break;
                case "book_author":book.setBook_author(inputValue);break;
                case "book_press":book.setBook_press(inputValue);break;
                case "book_desc":book.setBook_desc(inputValue);break;
                case "book_price":book.setBook_price(Double.parseDouble(inputValue));break;
                case "book_kunumber":book.setBook_kunumber(Integer.parseInt(inputValue));break;
            }
            if (inputName.equals("categoryid")) {// 分类单独处理
                // 获取到选择的分类的id，根据这个id取到分类的信息
                String categoryid = item.getString();
                System.out.println("categoryid=" + categoryid);
                Category category = managerService.findCategoryById(categoryid);
                System.out.println(category);
                book.setCategory(category);
            } else {
                BeanUtils.copyProperties(book, inputName, inputValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //用户信息管理
    @RequestMapping(value = "findUsers",method = RequestMethod.GET)
    public String findUsers(HttpServletRequest req, HttpServletResponse resp)  {
        List<User> list = managerService.findUsers();
        HttpSession session = req.getSession();
        session.setAttribute("users", list);
        return "managerUsers";
    }

    //销售情况
    @RequestMapping(value = "sales",method = RequestMethod.GET)
    public String sales(HttpServletRequest req, HttpServletResponse resp,HttpSession session){
        List<Book> sales = managerService.sales();
        session.setAttribute("sales", sales);
        return "sales_01";
    }
}
