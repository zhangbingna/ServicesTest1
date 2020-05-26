import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;
import static org.hamcrest.Matchers.equalTo;
public class GetTest {
    @Test
    void testToken(){
        RestAssured.given().log().all()
                .queryParam("corpid","ww416484273c17f7d7")
                .queryParam("corpsecret","Z_-szDWP5SehZZPPQk1zF02MJIMSDwnxW8MJni8QysM")
            .when().get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
            .then().log().all().statusCode(200).body("errcode",equalTo(0));
    }
}
