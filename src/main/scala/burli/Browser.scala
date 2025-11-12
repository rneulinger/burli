package burli

import com.microsoft.playwright.*


//object LaunchBrowserExample {
//  def main(args: Array[String]): Unit = {
//    try {
//      val playwright = Playwright.create
//      try {
//        val browser = playwright.chromium.launch(new BrowserType.LaunchOptions().setHeadless(false)) // headed mode)
//        val page = browser.newPage
//        page.navigate("http://172.26.47.18")
//        System.out.println("Page title: " + page.title)
//        browser.close()
//      } finally if (playwright != null) playwright.close()
//    }
//  }
//}
