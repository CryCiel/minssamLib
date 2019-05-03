package com.books.controller.member;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.books.common.SecurityBean;
import com.books.exception.EditFailException;
import com.books.exception.LoginFailException;
import com.books.exception.RegistFailException;
import com.books.model.domain.member.Member;
import com.books.model.service.member.MemberService;



@Controller
//@RequestMapping("/rest")//����
public class MemberController {
	@Autowired
	private MemberService memberService;
	@Autowired
	SecurityBean security;
	
	@RequestMapping(value="/member/regist",method=RequestMethod.POST)
	public String regist(Member member) {
		
		member.setPass(security.textToHash(member.getPass()));
		//System.out.println(member.getPass());
		memberService.insert(member);

		return "redirect:/index.jsp";
	}
	@RequestMapping(value="/member/edit",method=RequestMethod.POST)
	@ResponseBody
	public String edit(Member member, HttpServletRequest request) {
		member.setPass(security.textToHash(member.getPass()));
		memberService.update(member);
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("alert('������ �����Ǿ����ϴ�.');");
		sb.append("location.href='/member/mypage/{currentPage}';");
		sb.append("</script>");
		return sb.toString();
	}
	
	@RequestMapping(value="/member/login", method = RequestMethod.POST)
	public String login(Member member, HttpServletRequest request) {
		
		String viewName=null;
		member.setPass(security.textToHash(member.getPass()));
		Member obj = memberService.loginCheck(member);
		// ���ǿ� ���!
		String prev = request.getHeader("referer");
		
		
		if(obj==null) {
			viewName="member/login/loginfail";
		}else {
			request.getSession().setAttribute("member", obj);			
			viewName="redirect:"+prev;
		}	
		return viewName;
	}
	
	@RequestMapping(value="/member/logout", method = RequestMethod.GET)
	@ResponseBody
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		String prev = request.getHeader("referer");
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("alert('�α׾ƿ� �Ǿ����ϴ�.');");
		sb.append("location.href='"+prev+"';");
		sb.append("</script>");
		return sb.toString();
	}
	
	
	@RequestMapping(value="/member/modify",method=RequestMethod.GET)
	public ModelAndView select(int member_id) {
		System.out.println("�Ѿ�� member_id:" + member_id);
		Member member = memberService.select(member_id);
		ModelAndView mav = new ModelAndView("member/detail");
		mav.addObject("member",member);
		return mav;
	}
	
	@RequestMapping(value="/rest/member/idCheck", method = RequestMethod.POST)
	@ResponseBody
	public String idCheck(String id) {
		//ȸ�������Ҷ� �ߺ��� üũ����
		String result;
		Member member = memberService.idCheck(id);
		//System.out.println("������̵�"+member.getId());
		if(member==null) {
			result="�ߺ�����";
			System.out.println(result);
		}else {
			result="�ߺ�����";
			System.out.println(result);
		}
		//System.out.println("id="+id);
		//System.out.println("member="+member);
		System.out.println("�����°�"+result);
		return result;
	}
	
	@RequestMapping(value="/rest/member/emailCheck", method = RequestMethod.POST)
	@ResponseBody
	public String emailCheck(String email) {
		//ȸ�������Ҷ� �ߺ��� üũ����
		String result;
		System.out.println("�̸���:"+email);
		Member member = memberService.emailCheck(email);
		//System.out.println("������̵�"+member.getId());
		if(member==null) {
			result="�ߺ�����";
			//System.out.println(result);
		}else {
			result="�ߺ�����";
			//System.out.println(result);
		}
		//System.out.println("id="+id);
		//System.out.println("member="+member);
		//System.out.println("�����°�"+result);
		return result;
	}	
	
	@RequestMapping(value="/rest/member/passCheck", method = RequestMethod.POST)
	@ResponseBody
	public String passCheck(String pass) {
		//ȸ�������Ҷ� �ߺ��� üũ����
		String result;
		pass = security.textToHash(pass);
		Member member = memberService.passCheck(pass);
		if(member==null) {
			result="��ġ��������";
		}else {
			result="��ġ��";
		}
		return result;
	}	
	
	@RequestMapping(value="/rest/member/findId", method = RequestMethod.POST)
	@ResponseBody
	public String findId(Member member) {
		//ȸ�������Ҷ� �ߺ��� üũ����
		String result;
		Member obj = memberService.findId(member);
		//System.out.println("������̵�"+member.getId());
		if(obj!=null) {
			result = obj.getId();
		}else {
			result="��ġ�ϴ� ���̵� �����ϴ�.";
		}
		System.out.println("�����°�"+result);
		return result;
	}
	
	@RequestMapping(value="/rest/member/resetPass",method=RequestMethod.POST)
	@ResponseBody
	public String resetPass(Member member) {

		memberService.resetPass(member);
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("alert('��й�ȣ�� ����Ǿ����ϴ�.');");
		sb.append("location.href='/'");
		sb.append("</script>");
		return sb.toString();
	}
	
	
	@ExceptionHandler(RegistFailException.class)
	@ResponseBody
	public String handleRegistFail(RegistFailException e) {
		
		return "{\"resultCode\":0,\"msg\":\""+e.getMessage()+"\"}";
	}

	@ExceptionHandler(LoginFailException.class)
	@ResponseBody
	public String handleLoginFail(LoginFailException e) {
		
		return "{\"resultCode\":0,\"msg\":\""+e.getMessage()+"\"}";
	}
	
	@ExceptionHandler(EditFailException.class)
	@ResponseBody
	public String handleEditFail(EditFailException e) {
		
		return "{\"resultCode\":0,\"msg\":\""+e.getMessage()+"\"}";
	}
}



