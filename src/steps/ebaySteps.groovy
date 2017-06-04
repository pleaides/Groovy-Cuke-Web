this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

import java.util.concurrent.TimeUnit
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

def WebDriver driver = new ChromeDriver()
driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS)

Given(~/^A new customer is on the ebay main page$/) { ->
  driver.get("http://www.ebay.com")
}

When(~/^the customer searches for a new item e\.g\. "([^"]*)"$/) { String arg1 ->
  searchField = driver.findElement(By.id("gh-ac"))
  searchField.sendKeys(arg1)
  searchField.submit()
}

When(~/^the customer clicks on the Auction filter$/) { ->
    driver.findElement(By.xpath(".//*[@id='cbelm']/div[1]/div[2]/span")).click()
}

When(~/^the customer clicks on the Buy it now filter$/) { ->
  driver.findElement(By.xpath(".//*[@id='cbelm']/div[1]/div[2]/a[2]")).click()
}
When(~/^the customer selects the time ending soonest sort$/) { ->
  def dropdown = driver.findElement(By.id("DashSortByContainer"))
  dropdown.click()
  driver.findElement(By.linkText("Time: ending soonest"))
}

Then(~/^the first result should have free postage$/) { ->
  shippingtext = driver.findElement(By.xpath(".//*[starts-with(@id, 'item')]/ul[1]/li[3]/span/span/span"))
  assert (shippingtext.text == "Free international shipping")
}

Then(~/^the result should show the number of bids$/) {  ->
  numberofbids = driver.findElement(By.xpath(".//*[starts-with(@id, 'item')]/ul[1]/li[2]/span"))
  assert (numberofbids.text != ~/^bids$/)
}

Then(~/^the result should show the buy it now option$/) { ->
  buyitnow = driver.findElement(By.xpath(".//*[starts-with(@id, 'item')]/ul[1]/li[2]/span"))
  assert (buyitnow.text == "Buy It Now")
}

Then(~/^the given price is shown$/) { ->
  priceshown = driver.findElement(By.xpath(".//*[starts-with(@id, 'item')]/ul[1]/li[1]/span/span"))
  assert ( priceshown.text != "" )
}
