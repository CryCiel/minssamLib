package com.books.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AdviceName;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.servlet.mvc.method.annotation.ViewNameMethodReturnValueHandler;

/*xml�� ������̼Ǳ�� aop�� ó������� �����ϸ� ����, ��ȣ���� ���� �����ڰ� ���ϴ� ����� �����ϸ� �ȴ�.
 * 
 */
@Aspect
public class AnnotationAdminSessionAspect {
	//xml�� ���⶧���� ��ġ(pointcut)�� ��(advice)�� �ڹ��ڵ忡 ����
	@Pointcut("execution(public * com.itbank.controller..*(..))")
	public void checkMember() {}//���̵� ����
	//public void checkBoard() {}//���̵� ����
	//public void checkProduct() {}//���̵� ����
	//���� �����ڵ�
	//�α����� �ʿ��� ������������ ȣ�⸸ ó���ؾ��Ѵ�
	@Around("checkMember()")
	public String loginCheck(ProceedingJoinPoint joinPoint) throws Throwable {
		//���üũ������������ ���縦 üũ�ϴ� ��쿡�� ����
		//�Ǵܱ���?? �ᱹ HttpServletRequest�� �Ѿ������ ����
		String viewName=null;
		HttpServletRequest request=null;
		Object[] objArray = joinPoint.getArgs();//�޼��� ȣ��� ���޵� �Ű������� ��ȯ
		for(Object obj :objArray) {//��� �Ű����� ����(������Ʈ ��ü���� ����)
			 if(obj instanceof HttpServletRequest) {
				 request=(HttpServletRequest)obj;
			 }
		}
		//�α����� �ʿ��� �޼��� ȣ��ø� ���� üũ
		if(request!=null) {
			if(request.getSession().getAttribute("admin")==null) {
				viewName="admin/login/error";
			}else {
				viewName=(String)joinPoint.proceed();
				String methodName = joinPoint.getSignature().getName();
				System.out.println("�α��� �ʿ�:ȣ��� ���� �޼����"+methodName+",�޼����� ��ȯ ���� "+viewName);
			}
		}else {
			viewName=(String) joinPoint.proceed();
			String methodName = joinPoint.getSignature().getName();
			System.out.println("�α��� ���ʿ�:ȣ��� ���� �޼����"+methodName+",�޼����� ��ȯ ���� "+viewName);
		}
		
		
		return viewName;		
	}
}







