package com.books.controller.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.books.common.member.Admin;
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
	List markList;
	
	@RequestMapping(value="/member/mypage/{member_id}",method=RequestMethod.GET)
	public ModelAndView markAll(HttpServletRequest request, @PathVariable("member_id") String member_id) {
		System.out.println("markAll ȣ���ߴٰ�");
		member = (Member) request.getAttribute("member");
		System.out.println(member+"�޾Ҵٰ�");
		
		try {
			markList = bookmarkService.selectByMember(Integer.parseInt(member_id));
			System.out.println(markList.size()+"����Ʈ����\n"+member_id);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/mypage");
		mav.addObject("markList", markList);
		return mav;
	}
	
	// ���� ������ �̵� �뵵
	@RequestMapping(value = "/admin/main", method = RequestMethod.GET)
	public ModelAndView showMain(HttpServletRequest request) {
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





