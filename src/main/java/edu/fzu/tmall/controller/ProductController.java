package edu.fzu.tmall.controller;

import com.opensymphony.xwork2.ActionContext;
import edu.fzu.tmall.dao.CategoryDAO;
import edu.fzu.tmall.pojo.Category;
import edu.fzu.tmall.pojo.Product;
import edu.fzu.tmall.service.ProductService;
import edu.fzu.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.WebParam;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    @Qualifier("categorySpringImpl")
    private CategoryDAO categoryDAO;


    @RequestMapping("/listProduct")
    public String listProduct(@RequestParam(required = false) Page page,
                       Model model) {
        if (page == null) {
            page = new Page(0,10);
        }
        int total = productService.total();
        page.setTotal(total);
        List<Product> products= productService.listByPage(page);
        model.addAttribute("products", products);
        return "admin/product/listProduct";
    }

    @RequestMapping("/listProductByPage")
    public String listProduct(@RequestParam(value = "start", required = false) Integer start,
                              Model model) {
        if (start == null || start == 0) {
            start = 0;
        }
        Page page = new Page(start, 10);
        int total = productService.total();
        page.setTotal(total);
        List<Product> products= productService.listByPage(page);
        for (Product product : products) {
            System.out.println(product);
        }
        model.addAttribute("products", products);
        model.addAttribute("page", page);
        return "admin/product/listProduct";
    }

    @RequestMapping("/deleteProduct")
    public String deleteProduct() {

        return "admin/product/listProduct";
    }

    @RequestMapping("/editProduct")
    public String editProduct(@RequestParam("id") Integer id,
                              Model model) {
        Product product = productService.get(id);
        List<Category> categories = categoryDAO.list();
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        return "admin/product/editProduct";
    }
}
