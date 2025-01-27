package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_001_AccountRegistration_BulkTest extends BaseClass{

	@Test
	public void verify_account_registration() throws InterruptedException {
		
		for (int i = 1; i <= 3; i++) {
			logger.info("***** Starting TC001_AccountRegistrationTest  ****");
			logger.debug("This is a debug log message");
			try {
				HomePage hp = new HomePage(driver);
				hp.clickMyAccount();
				logger.info("Clicked on MyAccount Link.. ");

				hp.clickRegister();
				logger.info("Clicked on Register Link.. ");

				AccountRegistrationPage regpage = new AccountRegistrationPage(driver);

				logger.info("Providing customer details...");

				String fname = randomeString().toUpperCase();
				logger.info("First Name :" + fname);
				regpage.setFirstName(fname);

				String lname = randomeString().toUpperCase();
				regpage.setLastName(lname);
				logger.info("Last Name :" + lname);

				String email = randomeString() + "@gmail.com";
				regpage.setEmail(email);// randomly generated the email
				logger.info("Email :" + email);

				String phone = randomeNumber();
				regpage.setTelephone(phone);
				logger.info("Phone Number :" + phone);

				String password = randomAlphaNumeric();

				regpage.setPassword(password);
				regpage.setConfirmPassword(password);
				logger.info("Password :" + password);

				regpage.setPrivacyPolicy();
				regpage.clickContinue();

				logger.info("Validating expected message..");
				String confmsg = regpage.getConfirmationMsg();
				System.out.println(confmsg);
				if (confmsg.equals("Your Account Has Been Created!")) {
					Assert.assertTrue(true);
				} else {
					logger.error("Test Failed...");
					logger.debug("Debug logs..");
					Assert.assertTrue(false);
				}

				Assert.assertEquals(confmsg, "Your Account Has Been Created!", "Confirmation message mismatch");
				logger.info("Test passed");

				Thread.sleep(5000);
				
				MyAccountPage macc=new MyAccountPage(driver);
				macc.clickLogout();
				
				
			} catch (Exception e) {
				logger.error("Test failed: " + e.getMessage());
				//logger.debug("Debug logs..");
				Assert.fail("Test failed: " + e.getMessage());
			} finally {
				logger.info("***** Finished TC001_AccountRegistrationTest *****");
			} 
		}

	}

}
