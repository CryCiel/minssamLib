package com.books.controller.book;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.books.model.domain.book.Score;
import com.books.model.service.book.ScoreService;

@Controller
public class ScoreController {
	@Autowired
	ScoreService scoreService;
	
	//bookDetail���� ������ ���� ǥ��
	@RequestMapping(value="/book/scores",method=RequestMethod.GET)
	@ResponseBody
	public String showAvgScoreByIsbn(@RequestParam("isbn") String isbn) {
		System.out.println("bookDetail���� �ش絵�� ���� ���� ��û");
		System.out.println("�Ѱܹ��� isbn : "+isbn);
		List<Score> scoreList = scoreService.selectByIsbn(isbn);
		StringBuilder sb = new StringBuilder();
		
		//���� ���̺귯��(google json simple)�� ǥ���ؼ� ���ڿ� ������� �ʰ� ǥ��
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for(int i=0;i<scoreList.size();i++) {
			Score score = scoreList.get(i);
			JSONObject obj = new JSONObject();//dto�� 1:1�����ϴ� obj
			obj.put("score_id", score.getScore_id());
			obj.put("isbn", score.getIsbn());
			obj.put("member_id",score.getMember().getMember_id());
			obj.put("score", score.getScore());
			obj.put("regdate", score.getRegdate());
			jsonArray.add(obj);
		}
		json.put("scoreList", jsonArray);
		return json.toString();
	}
	
	//bookDetail���� ������ ���� ���
	@RequestMapping(value="/book/scores",method=RequestMethod.POST)
	@ResponseBody
	public String registScore(Score score) {
		System.out.println("bookDetail���� å ���� ��� ��û ��Ʈ�ѷ� ȣ��");
		scoreService.insert(score);
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"result\":1");
		sb.append("}");
		
		return sb.toString();
	}
	
	//bookDetail���� ���� ������Ʈ
	@RequestMapping(value="/book/scores",method=RequestMethod.PUT)
	@ResponseBody
	public String updateAvgScore(Score score) {
		scoreService.update(score);
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"result\":1");
		sb.append("}");
		return sb.toString();
	}
}
