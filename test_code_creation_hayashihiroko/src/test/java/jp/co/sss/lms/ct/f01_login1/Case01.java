package jp.co.sss.lms.ct.f01_login1;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * 結合テスト ログイン機能①
 * ケース01
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース01 ログイン画面への遷移")
public class Case01 {


	/** 前処理 */
	@BeforeAll
	static void before() {

		System.setProperty("webdriver.chrome.driver", "lib\\chromedriver.exe");
		createDriver();
	}

	/** 後処理 */
	@AfterAll
	static void after() {
		
		System.setProperty("webdriver.chrome.driver", "lib\\chromedriver.exe");
		closeDriver();

	}

	@Test
	@Order(1)
	@DisplayName("テスト01 トップページURLでアクセス")
	void test01() {
		//トップページが開かれログイン画面が出ていることを確認
		
		// トップページの画面を開く
		goTo("http://localhost:8080/lms");

		// 各画面表示時に10秒待機する(暗黙的)
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		//スクリーンショットを撮影
		getEvidence(new Object() {
		});
		
		//ログイン画面が出てるかどうかを確認、比較
		final WebElement title = webDriver.findElement(By.xpath("//*[@id=\"main\"]/h2"));
		assertEquals(title.getText(), "ログイン");
	}

}
