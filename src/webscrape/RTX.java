package webscrape;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;

public class RTX implements Runnable {
  private Thread t;
  private final String threadName;

  RTX( String name) {
    threadName = name;
    System.out.println("Creating " +  threadName );
  }

  public void run() {
    System.setProperty("webdriver.chrome.driver", "location of webdriver");
    ChromeDriver driver = new ChromeDriver();
    driver.get(threadName);



    boolean displayed = false;
    JavascriptExecutor jse = (JavascriptExecutor)driver;
    Long old_height = (Long) jse.executeScript("return window.pageYOffset;");

try {
  do {
    var buttons = driver.findElements(By.xpath("//div[@class='row mx-0']"));
    if(buttons.isEmpty()){
      driver.close();
      run();
    }
    for (int i = 0; i < 3; i++) {
      try {
        jse.executeScript(" triggerScroll()");
      }
      catch (JavascriptException e){
        continue;
      }
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    List<String> hrefs = new ArrayList<>();


    for (var item : buttons) {
      if (item.getText().contains("Add to Cart")) {

        var href = item.findElement(By.tagName("a")).getAttribute("href");
        hrefs.add(href);

      }
    }

    System.out.println(hrefs);
    if (hrefs.isEmpty()) {
      driver.navigate().refresh();
      continue;
    }


    Emailer.sendFromGMail(hrefs.toString()); //use your own email service

    hrefs.clear();
    buttons.clear();
    driver.navigate().refresh();


  } while (true);
}
catch(Exception e){
  driver.close();
  run();
}
  }

  public void start () {
    System.out.println("Starting " +  threadName );
    if (t == null) {
      t = new Thread (this, threadName);
      t.start ();
    }
  }
}
