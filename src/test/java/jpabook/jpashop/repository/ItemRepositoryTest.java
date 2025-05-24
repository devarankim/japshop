package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ItemRepositoryTest {

    @Autowired ItemRepository itemRepository;

    @Test
    public void 아이템저장() throws Exception {
        Book book = new Book();
        //Category categories = new Category();

        //categories.setName();
        book.setName("아란자서전");
        book.setIsbn("123");
        book.setAuthor("김아란");
        book.setPrice(12000);
        book.setStockQuantity(20);

        itemRepository.save(book);
        //Long saveId =
       // book.setCategories(categories);



    }
}