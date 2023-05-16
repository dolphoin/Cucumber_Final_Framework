package stepDefinitions;

import cucumber.api.java.en.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.AddcustomerPage;
import pageObjects.LoginPage;

public class Steps extends BaseClass {

    /*public WebDriver driver;  // Moved To Base Class
    public LoginPage lp;*/

    @Given("User Launch Chrome browser")
    public void user_Launch_Chrome_browser() {
        logger= Logger.getLogger("Cucumber_Final_Framework");
        PropertyConfigurator.configure("log4j.properties");

        System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//Drivers/chromedriver");
       driver = new ChromeDriver();
        lp = new LoginPage(driver);
        logger.info("************* Launching Browser *****************");



    }

    @When("User opens URL {string}")
    public void user_opens_URL(String url) {
        logger.info("************* Opening URL  *****************");
        driver.get(url);
        driver.manage().window().maximize();

    }

    @When("User enters Email as {string} and Password as {string}")
    public void user_enters_Email_as_and_Password_as(String email, String password) {
        logger.info("************* Prvding user and password *****************");
        lp.setUserName(email);
        lp.setPassword(password);

    }

    @When("Click on Login")
    public void click_on_Login() {
        logger.info("************* click on login *****************");
        lp.clickLogin();

    }

    @Then("Page Title should be {string}")
    public void page_Title_should_be(String title) {
        if(driver.getPageSource().contains("Login was unsuccessful"))
        {
            logger.info("************* Login failed *****************");
            driver.close();
            Assert.assertTrue(false);
        }
        else
        {
            logger.info("************* Login Passed *****************");
            Assert.assertEquals(title, driver.getTitle());
        }


    }

    @When("User click on Log out link")
    public void user_click_on_Log_out_link() throws InterruptedException {
        logger.info("************* clciking on logout *****************");
        lp.clickLogout();
        Thread.sleep(3000);

    }

    @Then("close browser")
    public void close_browser() {
        logger.info("************* cloding browser *****************");
        driver.quit();
    }




    //Customer feature step definitions..........................................

    @Then("User can view Dashboad")
    public void user_can_view_Dashboad() {

        addCust=new AddcustomerPage(driver);
        logger.info("********* Verifying Dashboad page title after login successful **************");
        Assert.assertEquals("Dashboard / nopCommerce administration",addCust.getPageTitle());
    }

    @When("User click on customers Menu")
    public void user_click_on_customers_Menu() throws InterruptedException {
        Thread.sleep(3000);
        logger.info("********* Clicking on customer main menu **************");
        addCust.clickOnCustomersMenu();
    }

    @When("click on customers Menu Item")
    public void click_on_customers_Menu_Item() throws InterruptedException {
        Thread.sleep(2000);
        logger.info("********* Clicking on customer sub menu **************");
        addCust.clickOnCustomersMenuItem();
    }

    @When("click on Add new button")
    public void click_on_Add_new_button() throws InterruptedException {
        addCust.clickOnAddnew();
        Thread.sleep(2000);
    }

    @Then("User can view Add new customer page")
    public void user_can_view_Add_new_customer_page() {
        Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());
    }

    @When("User enter customer info")
    public void user_enter_customer_info() throws InterruptedException {
        String email = randomestring() + "@gmail.com";
        addCust.setEmail(email);
        addCust.setPassword("test123");
        // Registered - default
        // The customer cannot be in both 'Guests' and 'Registered' customer roles
        // Add the customer to 'Guests' or 'Registered' customer role
        addCust.setCustomerRoles("Guest");
        Thread.sleep(3000);

        addCust.setManagerOfVendor("Vendor 2");
        addCust.setGender("Male");
        addCust.setFirstName("Pavan");
        addCust.setLastName("Kumar");
        addCust.setDob("7/05/1985"); // Format: D/MM/YYY
        addCust.setCompanyName("busyQA");
        addCust.setAdminContent("This is for testing.........");
    }

    @When("click on Save button")
    public void click_on_Save_button() throws InterruptedException {
        logger.info("********* Saving customer details **************");
        addCust.clickOnSave();
        Thread.sleep(2000);
    }

    @Then("User can view confirmation message {string}")
    public void user_can_view_confirmation_message(String string) {
        Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
                .contains("The new customer has been added successfully"));
    }


}
