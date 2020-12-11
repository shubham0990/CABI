package pra_package;

import java.io.IOException;


import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import config.PRA_Base;
import library.Utility;
import pra_child_classes.Banner_footer_PRA;
import pra_child_classes.ByPest_categorization;
import pra_child_classes.ByPest_initiation;
import pra_child_classes.Bypest_Probability_of_entry;
import pra_child_classes.Delete_bypest_pra;
import pra_child_classes.Home;
import pra_child_classes.Login;
import pra_child_classes.PRA_Home_tab;
import pra_child_classes.Previous_PRA;
import pra_child_classes.Regulated_Pest_list;
import pra_child_classes.Testcases_for_Home_report;
import pra_child_classes.Title_verification;

public class PRA_Home extends PRA_Base{

	public static ExtentSparkReporter htmlReporter = new ExtentSparkReporter("./PRA_Report/home.html");
	public static ExtentReports extent = new ExtentReports();
	public static ExtentTest logger1,logger2,logger3,logger4,logger5,logger6,logger7,logger8,logger9,logger10,logger18,logger19,logger25,logger26;
	
	@BeforeSuite
	void initialization_browser_opening() throws InterruptedException, IOException
	{
		System.out.println(getobject("url"));
		initialzation(getobject("url"));
		Testcases_for_Home_report testcase=new Testcases_for_Home_report();
		testcase.testcase_creation();
	}
	@Test(priority=1)
	void title_verification() throws InterruptedException
	{
		//Title_verification title=new Title_verification();
		Title_verification.website_title_verification("Pest Risk Analysis Tool");
	}
	@Test(priority=2)
	void login_Test() throws InterruptedException, IOException
	{
        Login log=PageFactory.initElements(wd, Login.class);
        log.login_to_cpc(getobject("cpc_username"),getobject("cpc_password"));
	    log.login_to_mycabi(getobject("mycabi_username"),getobject("mycabi_password"));
	}

	
	//@Test(priority=3)
	void Team_creation() throws Throwable
	{
		Home ho=PageFactory.initElements(wd, Home.class);
		ho.Teamcreation_for_PRA();
		
		
	}
	//@Test(priority = 3)
	void Banner_footer() throws InterruptedException
	{
		Banner_footer_PRA bf=PageFactory.initElements(wd, Banner_footer_PRA.class);
		bf.banner();
	    bf.footer();
		
	}
	//@Test(priority = 3)
	void PRA_Home_Test() throws InterruptedException
	{
		PRA_Home_tab hometab=PageFactory.initElements(wd, PRA_Home_tab.class);
		hometab.PRA_Home();
		hometab.View_PRA();
		hometab.View_Report();
	}
	//@Test(priority = 3)
	void Previous_PRA_Test() throws InterruptedException
	{
		Previous_PRA previous=PageFactory.initElements(wd, Previous_PRA.class);
		//previous.previousPRA();
		//previous.View_Bypathway_PRA();
		//previous.View_Report_from_previous_PRA();
		previous.filter();
		
	}
	//@Test(priority = 3)
	void Regulated_Pest_Test() throws InterruptedException
	{
		Regulated_Pest_list regulated=PageFactory.initElements(wd, Regulated_Pest_list.class);
		regulated.add_pest();
		regulated.edit_record();
		regulated.cleanup_pest();
		regulated.pagination();
		regulated.regulated_filter();
	}
	@Test(priority = 3)
	void By_pest_initiation() throws InterruptedException, IOException
	{
		ByPest_initiation bypest=PageFactory.initElements(wd, ByPest_initiation.class);
		bypest.By_pest_initiation_pra(getobject("pestname"),getobject("country_area_at_risk"),getobject("suggested_title_for_pra"),getobject("pra_start_month"),getobject("pra_start_date"),getobject("pra_due_month"),getobject("pra_due_date"),getobject("pra_area"));
	    bypest.re_edit_initiation_form(getobject("suggested_title_for_pra"));
	}
	@Test(priority = 4)
	void By_pest_cattegorization_tab() throws InterruptedException, IOException
	{
		ByPest_categorization bypestcat=PageFactory.initElements(wd, ByPest_categorization.class);
		bypestcat.categorization(getobject("suggested_title_for_pra"));
	}
	@Test(priority = 5)
	void By_pest_Probabity_of_entry() throws InterruptedException, IOException
	{
	Bypest_Probability_of_entry probability=PageFactory.initElements(wd, Bypest_Probability_of_entry.class);
	probability.riskassesmenttab(getobject("suggested_title_for_pra"));
	probability.add_pathway("Plants for planting");
	probability.check_tab_risk_management("Plants for planting");
	probability.add_pathway("Seeds for planting");
	probability.check_tab_risk_management("Seeds for planting");
	
		
	}
	//@Test(priority = 6)
	void  By_pest_delete_PRA() throws InterruptedException, IOException
	{
		Delete_bypest_pra deletePRA=PageFactory.initElements(wd, Delete_bypest_pra.class);
		deletePRA.delete_PRA(getobject("suggested_title_for_pra"));
	}
	
	
	@AfterMethod
	void fail_testcase(ITestResult result)
	{
		try {
			if(ITestResult.FAILURE==result.getStatus())
			{
			String temp=Utility.attachscreenshotreport(wd, result.getName());
		
			logger26.fail("Testcase name"+ result.getName());
			logger26.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		    }}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
	
		
	}
	@AfterSuite
	void teardown_browerclosing()
	{
		teardown();
		extent.flush();
	}
}
