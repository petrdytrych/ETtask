package cz.etn.etnshop.controller;

import cz.etn.etnshop.model.ProductModel;
import cz.etn.etnshop.model.SearchModel;
import cz.etn.etnshop.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
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

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		logger.debug("list()");
		ModelAndView modelAndView = new ModelAndView("product/list");
		modelAndView.addObject("search", new SearchModel());
		modelAndView.addObject("products", productService.getProducts());
		return modelAndView;
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public ModelAndView list(@Validated SearchModel search) {
		logger.debug("list() : {}", search);
		ModelAndView modelAndView = new ModelAndView("product/list");
		modelAndView.addObject("search", search);
        if (StringUtils.isEmpty(search.getText())) {
            modelAndView.addObject("products", productService.getProducts());
        } else {
            modelAndView.addObject("products", productService.findByFulltext(search));
        }
		return modelAndView;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView create() {
		logger.debug("add()");
		ModelAndView modelAndView = new ModelAndView("product/edit");
		modelAndView.addObject("product", new ProductModel());
		return modelAndView;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable("id") int id, Model model) {
		logger.debug("edit() : {}", id);
		ProductModel product = productService.findById(id);
		ModelAndView modelAndView;
		if (product != null) {
			modelAndView = new ModelAndView("product/edit");
			modelAndView.addObject("product", product);
		} else {
			modelAndView = new ModelAndView("redirect:/product/list");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Validated ProductModel productModel,
							 BindingResult result, Model model,
							 final RedirectAttributes redirectAttributes) {
		logger.debug("save() : {}", productModel);
		productService.save(productModel);
		return "redirect:/product/list";
	}
}
