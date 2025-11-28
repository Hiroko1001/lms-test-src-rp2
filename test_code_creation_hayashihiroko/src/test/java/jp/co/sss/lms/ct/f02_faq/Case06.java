package jp.co.sss.lms.ct.f02_faq;

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
 * 結合テスト よくある質問機能
 * ケース06
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース06 カテゴリ検索 正常系")
public class Case06 {

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

		//スクリーンショットを撮影
		getEvidence(new Object() {
		});

		//ログイン画面のタイトルを比較
		final WebElement title = webDriver.findElement(By.xpath("//*[@id=\"main\"]/h2"));
		assertEquals(title.getText(), "ログイン");
	}

	@Test
	@Order(2)
	@DisplayName("テスト02 初回ログイン済みの受講生ユーザーでログイン")
	void test02() {
		// ログインが出来ていることを確認

		//ログインIDにDBに登録されている「StudentAA01」を入力
		webDriver.findElement(By.name("loginId")).sendKeys("StudentAA01");
		//パスワードにDBに登録されている「StudentAA01a」を入力
		webDriver.findElement(By.name("password")).sendKeys("StudentAA01a");

		//ログインボタンを押下する
		webDriver.findElement(By.className("btn-primary")).click();

		//コース詳細画面のスクリーンショットを撮影
		getEvidence(new Object() {
		});

		//コース詳細画面が出てるかどうかを確認、比較
		final WebElement course = webDriver.findElement(By.xpath("//*[@id=\"main\"]/div/ol/li"));
		assertEquals(course.getText(), "コース詳細");

	}

	@Test
	@Order(3)
	@DisplayName("テスト03 上部メニューの「ヘルプ」リンクからヘルプ画面に遷移")
	void test03() {
		//ヘルプ画面に遷移されていることを確認

		//上記タブにある機能を押下する
		webDriver.findElement(By.className("dropdown-toggle")).click();
		//機能タブ内にあるヘルプを押下する
		webDriver.findElement(By.linkText("ヘルプ")).click();

		//ヘルプ画面のスクリーンショットを撮影
		getEvidence(new Object() {
		});

		//ヘルプ画面が出ているかどうかを確認、比較
		final WebElement help = webDriver.findElement(By.xpath("//*[@id=\"main\"]/h2"));
		assertEquals(help.getText(), "ヘルプ");
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「よくある質問」リンクからよくある質問画面を別タブに開く")
	void test04() {
		//よくある質問画面のタブが生成され遷移されていることを確認

		//よくある質問を押下する
		webDriver.findElement(By.linkText("よくある質問")).click();

		//よくある質問のタブを押下する
		Object[] windowHandles = webDriver.getWindowHandles().toArray();
		webDriver.switchTo().window((String) windowHandles[1]);

		//よくある質問の画面のスクリーンショットを撮影
		getEvidence(new Object() {
		});

		//よくある質問画面が出ているかどうかを確認、比較
		final WebElement question = webDriver.findElement(By.xpath("//*[@id=\"main\"]/h2"));
		assertEquals(question.getText(), "よくある質問");
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 カテゴリ検索で該当カテゴリの検索結果だけ表示")
	void test05() {
		//該当キーワードを含む検索結果だけが表示されることを確認

		//Keywordに検索結果が出てくる「研修」を入力
		webDriver.findElement(By.name("keyword")).sendKeys("研修");

		//検索を押下する
		webDriver.findElement(By.xpath("//*[@id=\"main\"]/div[1]/form/fieldset/div[2]/div/input[1]")).click();

		//検索結果が出ている画面のスクリーンショットを撮影
		getEvidence(new Object() {
		});

		//検索結果が出ているかどうかを確認、比較
		final WebElement result = webDriver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/thead/tr/th"));
		assertEquals(result.getText(), "検索結果");

	}

	@Test
	@Order(6)
	@DisplayName("テスト06 検索結果の質問をクリックしその回答を表示")
	void test06() {
		// 検索結果の質問を押下し、その回答が表示される。

		//検索結果から出た質問を押下する
		webDriver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[1]/td")).click();

		// 各画面表示時に10秒待機する(暗黙的)
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		//よくある質問の画面のスクリーンショットを撮影
		getEvidence(new Object() {
		});

		//回答が出ているかどうかを確認、比較
		final WebElement result = webDriver.findElement(By.xpath("//*[@id=\"answer-h[${status.index}]\"]"));
		assertEquals(result.getText(), "A");

	}

}
