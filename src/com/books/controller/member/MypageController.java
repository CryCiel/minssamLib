package com.books.controller.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.books.common.Pager;
import com.books.common.member.Admin;
import com.books.common.search.BookSearch;
import com.books.common.search.BookSerachMapping;
import com.books.model.domain.member.Bookmark;
import com.books.model.domain.member.Member;
import com.books.model.service.member.BookmarkService;

@Controller
public class MypageController {

	@Autowired
	private BookmarkService bookmarkService;
	@Autowired
	private BookSerachMapping mapping;
	@Autowired
	private BookSearch bookSearch;
	@Autowired
	private Admin commonAdmin;
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	Pager pager=new Pager();
	
	@RequestMapping(value="/member/mypage",method=RequestMethod.GET)
	public ModelAndView markAll(HttpServletRequest request) {
		List<Bookmark> userBookmarkList;
		Member member = (Member) request.getSession().getAttribute("member");
		ModelAndView mav = new ModelAndView();
		try {
			
			userBookmarkList = bookmarkService.selectByMember(member.getMember_id());
			 /* for(int i=0; i<userBookmarkList.size(); i++) { String isbn =
			 * userBookmarkList.get(i).getIsbn();
			 * userBookmarkList.get(i).setBook(mapping.mapping(bookSearch.search(isbn)).get(
			 * 0)); }
			 */
			System.out.println("동");
			System.out.println(userBookmarkList.get(0).getBookmark_id());
			System.out.println("작"); 
		} catch (NullPointerException e) {
			mav.setViewName("member/login/error");
			return mav;
		}
		mav.setViewName("member/mypage");
		System.out.println("작동1");
		return mav;
	}
	
	//bookmark 비동기로 리스트 표현
	@RequestMapping(value="/member/mypage/bookmark", method=RequestMethod.GET)
	@ResponseBody
	public List<Bookmark> bookMarkList(HttpServletRequest request){
		Member member = (Member)request.getSession().getAttribute("member");
		List<Bookmark> userBookmarkList;
		userBookmarkList = bookmarkService.selectByMember(member.getMember_id());
		for(int i=0; i<userBookmarkList.size(); i++) {
			String isbn = userBookmarkList.get(i).getIsbn();
			userBookmarkList.get(i).setBook(mapping.mapping(bookSearch.search(isbn)).get(0));
		}
		
		System.out.println("작동2");
		return userBookmarkList;
	}
	
	
	@RequestMapping(value="/member/mypage/bookmark/{bookmark_id}", method=RequestMethod.DELETE)
	@ResponseBody
	public String deleteBookmark(@PathVariable("bookmark_id") int bookmark_id) {
		bookmarkService.delete(bookmark_id);
		
		return null;
	}
	
	
	// 어드민 페이지 이동 용도
	@RequestMapping(value = "/admin/main", method = RequestMethod.GET)
	public ModelAndView adminMain(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Member member = (Member) request.getSession().getAttribute("member");
		request.getSession().setAttribute("auth", member.getAuth());
		if(commonAdmin.adminCheck(member.getAuth())) { // 어드민인지 한번 더 확인해서
			mav.setViewName("admin/adminMain"); // 어드민이면 관리자 메인 페이지로
		}else {
			mav.setViewName("/"); // 아니면 인덱스로 이동
		}
		return mav;		
	}
}


