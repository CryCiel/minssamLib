package com.books.controller.member;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.books.exception.RegistFailException;
import com.books.model.domain.member.Member;
import com.books.model.service.member.MemberService;


@Controller
//@RequestMapping("/rest")//����
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="/rest/members",method=RequestMethod.POST)
	@ResponseBody//����� ViewResolver�� �������� ����... ���� jsp�� ���յǴ����� ����.. ��ȯ�� ��ü�� �� ���䵥�����̴�
	public String regist(Member member) {
		memberService.insert(member);
		return "{\"resultCode\":1,\"msg\":\"��ϼ���\"}";
	}
	
	@RequestMapping(value="/member/login", method = RequestMethod.POST)
	public String login(Member member, HttpServletRequest request) {

		Member obj = memberService.loginCheck(member);
		// ���ǿ� ���!
		request.getSession().setAttribute("member", obj);

		return "redirect:/index.jsp";
	}
	
	
	@ExceptionHandler(RegistFailException.class)
	@ResponseBody
	public String handleRegistFail(RegistFailException e) {
		
		return "{\"resultCode\":0,\"msg\":\""+e+"\"}";
	}
	

}
