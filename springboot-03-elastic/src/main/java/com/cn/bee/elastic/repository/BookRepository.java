package com.cn.bee.elastic.repository;

import com.cn.bee.elastic.bean.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * Created by yyzc on 2018/7/4.
 */
public interface BookRepository extends ElasticsearchRepository<Book,Integer> {
    public List<Book> findByBookNameLike(String bookName);
}
