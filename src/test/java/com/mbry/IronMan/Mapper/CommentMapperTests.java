package com.mbry.IronMan.Mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mbry.IronMan.entity.CommentEntity;

@SpringBootTest
public class CommentMapperTests {
	
	@Autowired
	CommentMapper commentMapper;
	
	@Test
	public void queryCommentByCardId() {
		CommentEntity[] ces = commentMapper.queryCommentByCardId("1", 0, 2);
		System.out.println(ces.length);
		System.out.println(ces[1].getCommentId());
	}

	@Test
	public void queryRepliesByBelongId() {
		CommentEntity[] ces = commentMapper.queryRepliesByBelongId("3");
		System.out.println(ces.length);
		System.out.println(ces[0].getCommentId());
	}

	@Test
	public void insertComment() {
		CommentEntity ce = new CommentEntity(null, "6", "6", "6", "6", "6", "6");
		commentMapper.insertComment(ce);
	}

	@Test
	public void deleteComment() {
		commentMapper.deleteComment(null, "6");
	}

}
