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
        System.getProperties().setProperty("http.proxyHost", "127.0.0.1");
        System.getProperties().setProperty("http.proxyPort", "12639");

        given()
            .log().all()
            //.queryParam("Cookie","BIDUPSID=B75A2DC397643B88B2AA2F4F7F2E6212; PSTM=1592450157; BAIDUID=43D6E1494B1E9A446080B0A6615DEAAE:FG=1; BD_UPN=12314753; MCITY=-257%3A; BDORZ=B490B5EBF6F3CD402E515D22BCDA1598; BD_HOME=1; delPer=0; BD_CK_SAM=1; PSINO=6; COOKIE_SESSION=9194_0_8_4_2_9_0_0_8_3_0_0_107_0_0_0_1594020051_0_1594093684%7C9%231138_170_1594011576%7C9; H_PS_PSSID=1428_32125_32140_32045_32230_32256; H_PS_645EC=9a5ebsgDV9b7KSOyeGBCP13tkxmlXQ7XM6DBCfRo53zW%2BfoB4OW1d5UUWJU; ZD_ENTRY=baidu")
        .when()
            .get("https://www.baidu.com/")
        .then()
            .log().all()
            .statusCode(200);
            //.body("html.head.title",equalTo("mp3_百度搜索"));
    }
}
