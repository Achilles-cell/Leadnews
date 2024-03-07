package com.heima.model.article.dtos;

import com.heima.model.article.pojos.ApArticle;
import lombok.Data;

/**
 * ClassName: ArticleDto
 * Package: com.heima.model.article.dtos
 * Description:
 *
 * @Author 风雅颂
 * @Create 2024/1/25 12:13
 * @Version 1.0
 */
@Data
public class ArticleDto extends ApArticle {

    /**
     * 文章内容
     */
    private String content;
}
