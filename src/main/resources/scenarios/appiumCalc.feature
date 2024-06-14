@appium 
Feature: Appium Example Feature 
#Sample Test Scenario Description

@appium1 @db @arati 
Scenario: Appium Example 
	Given I start application by id "com.db.pbc.mybank.be.beta" 
	And I am using an AppiumDriver 
	#	When clear Calculator1 
	Then I close application by id "com.db.pbc.mybank.be.beta" 
	Given I start application by id "com.db.pbc.mybank.be.beta" 
	When clear Calculator 
	Then I close application by id "com.db.pbc.mybank.be.beta" 
	#	And add "6" to "9" 
	#	Then result should be "15" 
	
	
	
@appium1 @db @arati @drag 
Scenario: Appium Example 2 
#	Given I start application by id "com.db.pbc.mybank.be.beta" 
#	And I am using an AppiumDriver 
#	When clear Calculator1 
	When clear Calculator 
	#	And add "6" to "9" xxxx
	#	Then result should be "15" 
	
	
@appium2 @coba 
Scenario: Appium Example 2 
#	Given I start application by name "Perfecto Expense Tracker" 
	Then I perform an audit of the accessibility on tag application screen "calc" 
	And I am using an AppiumDriver 
	When clear Calculator 
	And add "6" to "9" 
	Then I perform an audit of the accessibility on tag application screen "calc2" 
	Then result should be "15" 
	
	
	
@bat 
Scenario Outline: Search bat 
	Given I switch to driver "perfectoRemote" 
	#	When I search for the "quantum perfecto" 
	#	Then it should have the "Quantum Framework" in search results 
	Examples: {"priority":2} 
	
	
	
	@multiDriver 
	Scenario: MultiDriverDemo 
		Given I start application by name "Calculator" 
		And I am using an AppiumDriver 
		When clear Calculator 
		And add "6" to "9" 
		Then result should be "15" 
		
	@SIABest 
	Scenario: Login to Best App 
		Given I start application by id "com.sia.bestuat" 
		When I enter the credentials 
		And i view flight view 
		Then i logout of the application 
		And I close application by id "com.sia.bestuat" 
		
		
		
	@TwoDevicesDemo3 
	Scenario: Two Device Demo - VM and Mobile 
		Given I switch to driver "perfectoRemote" 
		And I open browser to webpage "https://www.perfecto.io" 
		And I switch to driver "perfectodeviiRemote" 
		Given I start application by name "Calculator" 
		#	And I am using an AppiumDriver 
		#   And I switch to native context
		#    And I switch to "NATIVE_APP" context
		When clear Calculator 
		And add "6" to "9" 
		Then result should be "15" 
		# When I open browser to webpage "https://www.yahoo.com"
		And I switch to driver "perfectoRemote" 
		And I open browser to webpage "https://www.google.com" 
		# And I switch to driver "perfectodeviiRemote"
		#Then I open browser to webpage "https://www.msn.com"
		
		
	@cobaTest @ella
	Scenario: Verify skip button 
	#	   Given I enter the credentials for Coba App
		Then i verify the skip button 
		#	Then i verify content 
		
		
	@rolex 
	Scenario: Verify Expense tracker Login 
	#		Given I start application by id "io.perfecto.expense.tracker" 
		#Then I should see expense tracker Native login screens 
		When I enter "test@perfecto.com" and "test123" in native login screens 
	#	Then I should see expense tracker home screen 
	#	When I enter expense details and save 
		#		Then I should see error popup soon 
		#Given I start application by id "io.perfecto.expense.tracker" 
	
	@clickIssue	
	Scenario: verify click issue 
		Given i login to DB app and perform registeration process 
		
		
		
		
	