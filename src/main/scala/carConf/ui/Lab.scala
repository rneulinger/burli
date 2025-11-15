package carConf.ui

import burli.*
import carConf.ui.Main
import com.microsoft.playwright.*
import com.microsoft.playwright.options.*

object Lab extends Main( ){
  //lazy val C1 = new Main()
  //lazy val C2 = new Main()
  //lazy val `/` = this
  // file:///C:/Projects/burli/carconfigWeb/html/CarConfig.htm?lang=en

  @main def clickMains() = {
    dump()
    SpecialsTab.click
    AccessoriesTab.click
    VehiclesTab.click

    goto(Menu.File)
    goto(Menu.File)
    goto(Menu.File_Reset)

    goto(Menu.Options)
    goto(Menu.Options)
    goto(Menu.Options_Vehicles)

    def clickButton( name:String ) ={
      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(name)).click();
    }
    def clickOK() = clickButton( "OK")
    def clickCancel() = clickButton( "Cancel")

    VehiclesDialog.VehicleName.flash()
    clickCancel()

    goto(Menu.Options_Specials)
    clickOK()
    goto(Menu.Options_Accessories)
    clickOK()

    goto( Menu.PurchaseOrder)
    goto( Menu.PurchaseOrder)
    goto( Menu.PurchaseOrder_ViewSelectedDetails)
    clickOK()
    goto( Menu.PurchaseOrder_SendOrder)
    clickCancel()

    goto( Menu.Help)
    goto( Menu.Help)
    goto( Menu.Help_Info)
    clickOK()
    goto( Menu.Help_Buggy)
    goto( Menu.Help_LoadTestingModule)

    println( atoms )
    for(  frm <- frms ){
      println( frm._1)
      for ( atom <- frm._2.atoms ){
        println("\t" + atom._1 + " " + atom._2)
      }
    }
    close()
  }
}

import com.microsoft.playwright.*;

object FlashElement {
  def main(args:Array[String]):Unit = {
      val playwright = Playwright.create()
      val browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
      val page = browser.newPage();
      page.navigate("https://example.com");

      // Replace with your actual selector
      val selector = "h1";

      // Inject JavaScript to flash the element
      page.evalOnSelector(selector, "element => {" +
        "element.style.transition = 'background-color 0.3s ease';" +
        "element.style.backgroundColor = 'yellow';" +
        "setTimeout(() => element.style.backgroundColor = '', 500);" +
        "}");
  }
}



