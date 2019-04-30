package com.books.controller.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.books.common.Pager;
import com.books.common.member.Admin;
import com.books.exception.AccountNotFoundException;
import com.books.model.domain.member.Member;
import com.books.model.service.member.BookmarkService;
import com.books.model.service.member.MemberService;

@Controller
public class MypageController {

	@Autowired
	private BookmarkService bookmarkService;
	
	@Autowired 
	private MemberService memberService;
	
	@Autowired
	private Admin commonAdmin;
	
	Member member;
	List userList;
	List markList;
	Pager pager=new Pager();
	//{currentPage}
	//, @PathVariable("currentPage") String currentPage
	@RequestMapping(value="/member/mypage",method=RequestMethod.GET)
	public ModelAndView markAll(HttpServletRequest request) {
		member = (Member) request.getSession().getAttribute("member");
		
		//String currentPage = (String)request.getAttribute("currentPaged");
		ModelAndView mav = new ModelAndView();
		try {
			userList = bookmarkService.selectByMember(member.getMember_id());
			//userList.size()
			System.out.println(userList);
		} catch (NullPointerException e) {
			e.printStackTrace();
			mav.setViewName("member/login/error");
			return mav;
		}
		
		mav.setViewName("member/mypage");
		mav.addObject("userList", userList);
		return mav;
	}
	
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





