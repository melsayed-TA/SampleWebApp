package TestPackage;

import com.shaft.driver.SHAFT;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Home;

//Do not forget to configure the project for parallel execution before running this test class!

public class TestClass3_MethodLevelParallelExecution {
    private static ThreadLocal<SHAFT.GUI.WebDriver> driver   = new ThreadLocal<>();
    private static ThreadLocal<SHAFT.TestData.JSON> testData = new ThreadLocal<>();
    
    //Test1
    @Epic("SHAFT Web GUI Template")
    @Story("Google Basic Validations")
    @TmsLink("TC-001")
    @Description("Given I am on the Home page,\nThen the browser title should be 'Google'.")
    @Test(description = "Check that Home Page Title is correct.")
    public void checkHomePageTitleIsCorrect() {
    	new Home(driver.get()).navigate().verifyBrowserTitleIsCorrect();
    }

    //Test2
    @Epic("SHAFT Web GUI Template")
    @Story("Google Basic Validations")@TmsLink("TC-002")
    @Description("Given I am on the Home page,\nWhen I search for a valid query,\nThen the result stats will show some data (will not be empty).")
    @Test(description = "Check that Result Stats is not empty after searching for a query.")
    public void checkResultStatsIsNotEmptyAfterSearchingForAQuery() {     
        new Home(driver.get()).navigate().searchForQuery(testData.get().getTestData("searchQuery")).assertResultStatsIsNotEmpty();
    }
    
    //Test3 
    @Epic("SHAFT Web GUI Template")
    @Story("Google Basic Validations")@TmsLink("TC-003")
    @Description("Given I am on the Home page,\nWhen I search for a valid query,\nThen the result stats will show some data (will not be empty).")
    @Test(description = "Check that Result Stats is not empty after searching for a query2.")
    public void checkResultStatsIsNotEmptyAfterSearchingForAQuery2() {
        new Home(driver.get()).navigate().searchForQuery(testData.get().getTestData("searchQuery2")).assertResultStatsIsNotEmpty();
    }

    
   /* @BeforeClass(description = "Setup Test Data.")
    public void beforeClass(){
    	//testData = new ThreadLocal<>();
    	//testData = new SHAFT.TestData.JSON("simpleJSON.json");
        testData.set(new SHAFT.TestData.JSON("simpleJSON.json"));

    }*/

    @BeforeMethod(description = "Setup Browser instance.")
    public void beforeMethod() {
    	//Instantiations (driver & Testdata)
    	driver.set(new SHAFT.GUI.WebDriver());					
    	testData.set(new SHAFT.TestData.JSON("simpleJSON.json"));
    }

    @AfterMethod(description = "Teardown Browser instance.")
    public void afterMethod() {
        driver.get().quit();
    }
}
