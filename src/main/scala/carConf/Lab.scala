package carConf

import com.microsoft.playwright.*
import com.microsoft.playwright.options.*

import burli.*

object Lab extends Main( ){
  //lazy val C1 = new Main()
  //lazy val C2 = new Main()
  //lazy val `/` = this
  // file:///C:/burli/carconfigWeb/html/CarConfig.htm?lang=en

  @main def clickMains() = {
    Specials.click
    Accessories.click
    Vehicles.click

//    page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("Model")).click();
//    page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("ID").setExact(true)).click();
//    page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("Price").setExact(true)).click();
//    page.locator("#VehicleTablePanel").click();
//    page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("I5")).click();
//    page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("M5")).click();
//    page.locator("#VehicleTable").getByRole(AriaRole.CELL, new Locator.GetByRoleOptions().setName("$29,000.00")).click();
//    page.getByText("Base price").click();
//    page.locator("#BasePrice_input").click();
//    page.getByText("Specials price").click();
//    page.locator("#SpecialPrice_input").click();
//    page.getByText("Accessories price", new Page.GetByTextOptions().setExact(true)).click();
//    page.locator("#AccessoryPrice_input").click();
//    page.locator("#DiscountValue_input").click();
//    page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("-5%")).click();
//    page.getByText("$27,550.00").click();


    goto(Menu.File)
    goto(Menu.File)
    goto(Menu.File_Reset)

    goto(Menu.Options)
    goto(Menu.Options)
    goto(Menu.Options_Vehicles)
    page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Cancel")).click();
    goto(Menu.Options_Specials)
    page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("OK")).click();
    goto(Menu.Options_Accessories)
    page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("OK")).click();

    goto( Menu.PurchaseOrder)
    goto( Menu.PurchaseOrder)
    goto( Menu.PurchaseOrder_ViewSelectedDetails)
    page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("OK")).click();
    goto( Menu.PurchaseOrder_SendOrder)
    page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Cancel")).click();

    goto( Menu.Help)
    goto( Menu.Help)
    goto( Menu.Help_Info)
    page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("OK")).click();
    goto( Menu.Help_Buggy)
    goto( Menu.Help_LoadTestingModule)

    close()
  }
}
