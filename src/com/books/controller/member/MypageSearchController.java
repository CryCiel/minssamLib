package com.books.controller.member;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.books.common.Pager;
import com.books.common.search.BookSearch;
import com.books.common.search.BookSerachMapping;
import com.books.model.domain.member.Member;
import com.books.model.service.member.SearchbookService;

@Controller
public class MypageSearchController {

	@Autowired
	BookSerachMapping mapping;
	@Autowired
	BookSearch bookSearch;
	@Autowired
	SearchbookService searchbookService;
	Pager pager=new Pager();
	ArrayList searchHistory;
	
	/*
	 * @RequestMapping(value="/member/searchHistory",method=RequestMethod.GET)
	 * public ModelAndView searchAll(HttpServletRequest request) { Member member=
	 * (Member)request.getSession().getAttribute("member"); String currentPage =
	 * (String)request.getAttribute("currentPage"); ModelAndView mav = new
	 * ModelAndView("member/searchHistory"); try {
	 * 
	 * searchHistory=(ArrayList)
	 * searchbookService.selectByMember(member.getMember_id()).;
	 * 
	 * 
	 * if(searchHistory.size()>0) { //����¡ pager.init(Integer.parseInt(currentPage),
	 * searchHistory.get(0).); } } catch (NullPointerException e) { ArrayList
	 * searchHistory = new ArrayList(); e.printStackTrace(); }
	 * 
	 * mav.addObject("searchHistory",searchHistory);
	 * 
	 * return mav; }
	 */
	
}