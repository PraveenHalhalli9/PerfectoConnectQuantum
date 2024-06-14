package com.listeners	;



import org.openqa.selenium.Capabilities;


import com.qmetry.qaf.automation.ui.webdriver.QAFWebDriverCommandAdapter;

public class dataListeners extends QAFWebDriverCommandAdapter {

	static int counter;
	
    @Override

    public void beforeInitialize(Capabilities desiredCapabilities) {
    	
    	
    	System.out.println("#####################");
    	counter++;
     	
    	if(counter<=1) {
    		
    		//add your code for sending files to azure 
    		
    		System.out.println("file already intialized, no need further actions");
    		
    	}

      
    	
    
    }

}
