package personal.criva.springhibernateexample;

import personal.criva.springhibernateexample.config.ApplicationContextProvider;
import personal.criva.springhibernateexample.model.vo.CustomerVo;
import personal.criva.springhibernateexample.service.ICustomerService;

public class Main {

    ICustomerService customerService = ApplicationContextProvider.getApplicationContext()
	    .getBean(ICustomerService.class);

    public Main() {
	
	for(CustomerVo customer : customerService.findCustomerByPhoneNumber("333")) {
	    
	    System.out.println(customer.getId());
	}
    }

    public static void main(String[] args) {

	new Main();

	System.out.println("Done!");
    }

}
