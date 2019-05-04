package com.books.controller.book;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.books.common.search.BookSearch;
import com.books.common.search.BookSerachMapping;
import com.books.model.domain.book.Book;
import com.books.model.domain.book.Review;
import com.books.model.domain.member.Member;
import com.books.model.service.book.ReviewService;

@Controller
public class ReviewController {
	@Autowired
	BookSerachMapping mapping;
	@Autowired
	BookSearch bookSearch;
	@Autowired
	private ReviewService reviewService;
	
	//���� ���� �������� �̵�
	//@RequestMapping(value="/book/reviews/{isbn}",method=RequestMethod.GET)
	//public String insertPage(HttpServletRequest request,@PathVariable("isbn") String isbn) {
	//	Review review = new Review();
	//	review.setIsbn(isbn);
	//	review.setMember((Member)request.getSession().getAttribute("member"));
	//	return "/review/review_write.jsp?isbn="+isbn;		
	//}
	@RequestMapping(value="/book/reviews/{isbn}",method=RequestMethod.GET)
	public ModelAndView insertPage(HttpServletRequest request,@PathVariable("isbn") String isbn) {
		List<Book> detailList = mapping.mapping((bookSearch.search(isbn)));	
		ModelAndView mav = new ModelAndView();
		mav.setViewName("review/review_write");
		mav.addObject("isbn", isbn);
		mav.addObject("detailList",detailList);
		return mav;	
	}
	//���� 1�� ���
	@RequestMapping(value="/review/write",method=RequestMethod.POST)
	public String insert(Review review) {
		System.out.println("���� ��Ʈ�ѷ� ��� ��û�޼��� �۵�");
		reviewService.insert(review);
		return "redirect:/review/list";
	}
	
	//���� ��Ϻ����û
	@RequestMapping(value="/review/list",method=RequestMethod.GET)
	public ModelAndView selectAll() {
		System.out.println("���� ��Ʈ�ѷ� ��Ϻ��� ��û");
		List<Review> reviewList = reviewService.selectAll();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("review/reviewlist");
		return mav;
	}
}
