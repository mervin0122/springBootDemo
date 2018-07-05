package com.cn.bee;

import com.cn.bee.elastic.bean.Article;
import com.cn.bee.elastic.bean.Book;
import com.cn.bee.elastic.repository.BookRepository;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot03ElasticApplicationTests {

	@Autowired
	JestClient jestClient;

	@Autowired
	BookRepository bookRepository;

	@Test
	public void contextLoads() {
		//1.给ES中索引（保存）一个文档
		Article article = new Article();
		article.setId(1);
		article.setTitle("好消息");
		article.setAuthor("mervin");
		article.setContent("Hello World");
		//构建一个索引功能
		Index index = new Index.Builder(article).index("mervin").type("news").build();
		try {
			jestClient.execute(index);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//测试搜索
	@Test
	public void search() {
		//查询表达式
		String json = "{\n" +
				"    \"query\": {\n" +
				"        \"match\": {\n" +
				"            \"content\": \"hello\"\n" +
				"        }\n" +
				"    }\n" +
				"}";
		//构建搜索功能
		Search search = new Search.Builder(json).addIndex("mervin").addType("news").build();
		//执行
		try {
			SearchResult reult = jestClient.execute(search);
			System.out.println(reult.getJsonString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test02() {
		Book book = new Book();
		book.setId(1);
		book.setBookName("SpringBoot");
		book.setAuthor("李四");
		bookRepository.index(book);
	}

	@Test
	public void test03() {
		for (Book book : bookRepository.findByBookNameLike("Spring")) {
			System.out.println(book);
		}
	}
}
