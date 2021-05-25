package com.mall.controller.backend;

import com.mall.common.ServerResponse;
import com.mall.pojo.Carousel;
import com.mall.service.ICarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/carousel/")
public class CarouselController {

	@Autowired
	private ICarouselService iCarouselService;

	@RequestMapping(value = "list.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse list(HttpSession session,  @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10")int pageSize) {

		return iCarouselService.selectList(pageNum, pageSize);
	}

	@RequestMapping(value = "add.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse add(HttpSession session, Carousel carousel) {

		return iCarouselService.insertItem(carousel);
	}

	@RequestMapping(value = "delete.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse delete(HttpSession session, Integer carouselId) {

		return iCarouselService.deleteItem(carouselId);
	}

	@RequestMapping(value = "update.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse update(HttpSession session, Carousel carousel) {

		return iCarouselService.updateItem(carousel);
	}



}
