package com.books.model.repository.member;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.books.model.domain.member.Bookmark;

@Repository
public class MybatisBookmarkDAO implements BookmarkDAO{
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public List<Bookmark> selectAll() {
		return sqlSessionTemplate.selectList("Bookmark.selectAll");
	}

	public List<Bookmark> selectByMember(int member_id) {
		return sqlSessionTemplate.selectOne("Bookmark.selectByMember");
	}

	public List<Bookmark> selectByIsbn(String isbn) {
		return sqlSessionTemplate.selectList("Bookmark.selectByIsbn");
	}

	public Bookmark select(int bookmark_id) {
		return sqlSessionTemplate.selectOne("Bookmark.select");
	}

	public int insert(Bookmark bookmark) {
		return sqlSessionTemplate.insert("Bookmark.insert");
	}

	public int update(Bookmark bookmark) {
		return sqlSessionTemplate.update("Bookmark.update", bookmark);
	}

	public int delete(int bookmark_id) {
		return sqlSessionTemplate.delete("Bookmark.delete");
	}
}
