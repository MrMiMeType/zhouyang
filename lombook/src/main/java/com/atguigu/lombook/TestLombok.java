package com.atguigu.lombook;

import com.sun.istack.internal.NotNull;
import lombok.*;
import lombok.experimental.Accessors;


@NoArgsConstructor
@AllArgsConstructor
//加了这个注解就所有的都是标配，不加这个可以自定义get/set等！
@Data
@Accessors(chain = true)
class Book{
    @NotNull private int id;
    @Getter private String bookName;
    @Getter @Setter private String author;
}
public class TestLombok {
    public static void main(String[] args) {
        Book book = new Book();
        book.setId(1001).setBookName("三国演义").setAuthor("罗贯中");
        System.out.println(book);
    }
}
