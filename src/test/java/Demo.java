import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

public class Demo {
    @Test
    public void testJson(){
        when().get("http://localhost:8000/lotto.json")
        .then()
            .body("store.book.category",hasItems("reference"))
            .body("store.book[0].author",equalTo("Nigel Rees"))
            //.body("store.book.findAll { book -> book.price >= 5 && book.price <= 15 }",equalTo(""))
            .body("store.book.find { book -> book.price == 8.95f }.price",equalTo(8.95f));

    }

    @Test
    public void testXML(){
        when().get("http://localhost:8000/shopping.xml")
        .then()
                .body("shopping.category.item[0].name",equalTo("Chocolate"))
                .body("shopping.category.item.size()",equalTo(5))
                .body("shopping.category.findAll { it.@type == 'groceries' }.size()",equalTo(1))
                .body("**.findAll { it.@type == 'groceries' }.size()",equalTo(1));
    }

    @Test
    public void getHtml(){
        //设置代理
        //System.getProperties().setProperty("http.proxyHost", "127.0.0.1");
        //System.getProperties().setProperty("http.proxyPort", "12639");
        //RestAssured.proxy("localhost",12639);
        given().proxy(12639)
            .log().all()
            .queryParam("wd","mp3")
        .when()
            .get("http://www.baidu.com/s")
        .then()
            .log().all()
            .statusCode(200)
            .body("html.head.title",equalTo("mp3_百度搜索"));
    }
}
