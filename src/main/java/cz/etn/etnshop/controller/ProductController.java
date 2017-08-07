package cz.etn.etnshop.controller;

import cz.etn.etnshop.model.ProductModel;
import cz.etn.etnshop.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/product")
public class ProductController {

	private final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("product/list");
		modelAndView.addObject("products", productService.getProducts());
		return modelAndView;
	}

	@RequestMapping("/add")
	public ModelAndView create() {
		ModelAndView modelAndView = new ModelAndView("product/create");
		modelAndView.addObject("product", new ProductModel());
		return modelAndView;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("userForm") @Validated ProductModel productModel,
							 BindingResult result, Model model,
							 final RedirectAttributes redirectAttributes) {
		logger.debug("save() : {}", productModel);
		productService.save(productModel);
		ModelAndView list = this.list();
		return list;
	}
}
