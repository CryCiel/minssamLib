package com.books.controller.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
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
import com.books.model.domain.book.Book;
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
	
	//Member member;
	Pager pager=new Pager();
	@RequestMapping(value="/member/mypage/{currentPage}",method=RequestMethod.GET)
	public ModelAndView markAll(HttpServletRequest request, @PathVariable("currentPage") String currentPage) {
		List<Bookmark> userBookmarkList;
		Member member = (Member) request.getSession().getAttribute("member");
		ModelAndView mav = new ModelAndView();
		
		JSONObject json=new JSONObject();
		try {
			userBookmarkList = bookmarkService.selectByMember(member.getMember_id());
			for(int i=0; i<userBookmarkList.size(); i++) {
				String isbn = userBookmarkList.get(i).getIsbn();
				//System.out.println(isbn);
				userBookmarkList.get(i).setBook(mapping.mapping(bookSearch.search(isbn)).get(0));
				/*
				 * json.put("bookmark_id", userBookmarkList.get(i).getBookmark_id());
				 * json.put("isbn", userBookmarkList.get(i).getIsbn()); json.put("image",
				 * userBookmarkList.get(i).getBook().getImage()); json.put("title",
				 * userBookmarkList.get(i).getBook().getTitle()); json.put("bookmark_date",
				 * userBookmarkList.get(i).getBookmark_date());
				 */
			}
			//pager.init(request, userList.size());
			//if(userList.size()>0) {
				
			//}
		} catch (NullPointerException e) {
			mav.setViewName("member/login/error");
			//e.printStackTrace();
			return mav;
		}
		
		mav.setViewName("member/mypage");
		mav.addObject("userBookmarkList", userBookmarkList);
		mav.addObject("json",json);
		return mav;
	}
	
	/*
	 * @RequestMapping(value="/member/mypage/{currentPage}",
	 * method=RequestMethod.DELETE)
	 * 
	 * @ResponseBody public String delete(@PathVariable("bookmark_id") int
	 * bookmark_id) { bookmarkService.delete(bookmark_id);
	 * 
	 * return "{\"resultCode\":1,\"msg\":\"��������\"}"; }
	 */
	// ���� ������ �̵� �뵵
	@RequestMapping(value = "/admin/main", method = RequestMethod.GET)
	public ModelAndView adminMain(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Member member = (Member) request.getSession().getAttribute("member");
		request.getSession().setAttribute("auth", member.getAuth());
		if(commonAdmin.adminCheck(member.getAuth())) { // �������� �ѹ� �� Ȯ���ؼ�
			mav.setViewName("admin/adminMain"); // �����̸� ������ ���� ��������
		}else {
			mav.setViewName("/"); // �ƴϸ� �ε����� �̵�
		}
		return mav;		
	}
}





