package com.jiaming.admin.book;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jiaming.Book.service.BookService;
import com.jiaming.entity.Book;

@Controller
@RequestMapping("/book1")
public class AdminBookController {

	@Autowired
	private BookService bookService;

	// 左边条目查询书
	@GetMapping("/getBooksByCid/{cid}.action")
	public String getBooksByCid(@PathVariable String cid, Model model) {

		List<Book> books = bookService.getBooksByCid(cid);
		model.addAttribute("books", books);

		return "adminjsps/admin/book/list";
	}

	// 查询
	@GetMapping("/getBookByBid/{bid}.action")
	public String getBookByBid(@PathVariable String bid, Model model) {

		Book book = bookService.getBooksByBid(bid);
		model.addAttribute("book", book);
		return "adminjsps/admin/book/desc";
	}

	// 查询
	@PostMapping("/getBooksBySearch.action")
	public String getBooksBySearch(String keywords, Model model) {

		List<String> keyword_list = new ArrayList<String>();
		// ABC 123 xxx 张
		// 获取 keywords 然后 抽词s:空白 S 表示 非空白 \\S+
		// 创建了一个 正则对象
		Pattern pattern = Pattern.compile("\\S+");
		// 匹配对象
		Matcher matcher = pattern.matcher(keywords);
		// find() 在 keywords字符串中 按照 pattern \\S+ 找到了匹配的结果 返回 true
		while (matcher.find()) {
			// 获取 当前匹配规则的 串
			String str = matcher.group();
			keyword_list.add(str);

		}
		System.out.println(keyword_list);
		List<Book> books = bookService.getBooksBySearch(keyword_list);
		model.addAttribute("books", books);
		return "adminjsps/admin/book/list";
	}

	// 高级搜索1
	@GetMapping("/getBooksByGj.action")
	public String getBooksByGj(Model model, Book book) {

		if (book.getAuthor() != null) {

			try {
				book.setAuthor(new String(book.getAuthor().getBytes("iso8859-1"), "utf-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (book.getPress() != null) {
			try {
				book.setPress(new String(book.getPress().getBytes("iso8859-1"), "utf-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		List<Book> books = bookService.getBooksByGj(book);
		model.addAttribute("books", books);

		return "adminjsps/admin/book/list";
	}

	// 高级搜索2
	@PostMapping("/getBooksByGj.action")
	public String getBooksByGj2(Model model, Book book) {

		List<Book> books = bookService.getBooksByGj(book);
		model.addAttribute("books", books);

		return "adminjsps/admin/book/list";
	}

}
