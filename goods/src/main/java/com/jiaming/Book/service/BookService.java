package com.jiaming.Book.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiaming.entity.Book;
import com.jiaming.mapper.BookMapper;

@Service
public class BookService {

	@Autowired
	private BookMapper bookMapper;

	// 左边条目查询书
	public List<Book> getBooksByCid(String cid) {

		return bookMapper.selectBooksByCid(cid);
	}

	// 查询
	public Book getBooksByBid(String bid) {
		// TODO Auto-generated method stub
		return bookMapper.selectByPrimaryKey(bid);
	}

	// 查询
	/**
	 * @param keyword_list
	 * @return
	 */
	public List<Book> getBooksBySearch(List<String> keyword_list) {
		int size = keyword_list.size();
		List<Book> books = null;
		switch (size) {
		case 0:
			// 查询所有
			return bookMapper.selectAll();

		case 1:
			// 有可能是 书名的一部分， 还有可能 是 作者 一部分， 还有可能 出版社
			books = bookMapper.selectBooksLikeBnameOrAuthorOrPress(keyword_list.get(0));
			Set<Book> set = new HashSet<Book>(books);
			books = new ArrayList<Book>(set);
			return books;
		case 2:
		default:
			//
			books = bookMapper.selectBooksLikeBnameOrAuthor(keyword_list.get(0), keyword_list.get(1));
			Set<Book> set2 = new HashSet<Book>(books);
			books = new ArrayList<Book>(set2);
			return books;
		}
	}

	// 高级搜索
	public List<Book> getBooksByGj(Book book) {
		// TODO Auto-generated method stub
		return bookMapper.selectBooksByGj(book);
	}
	
	public List<Book> getBooksByExample(Book book) {
		// TODO Auto-generated method stub
		
		return bookMapper.selectBooksByExample(book);
	}
}
