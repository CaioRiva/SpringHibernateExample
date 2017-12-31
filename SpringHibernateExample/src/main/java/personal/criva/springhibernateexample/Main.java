package personal.criva.springhibernateexample;

import personal.criva.springhibernateexample.config.ApplicationContextProvider;
import personal.criva.springhibernateexample.service.ICustomerService;

public class Main {

    ICustomerService customerService = ApplicationContextProvider.getApplicationContext()
	    .getBean(ICustomerService.class);

    public Main() {
	
//	Calendar date = Calendar.getInstance();
//	date.set(2000, 2, 25);
//
//	CustomerVo customer = new CustomerVo("Test3", "333", "test@email.com", "111", date);
//	
//	customerService.saveCustomer(customer);
	
	customerService.findCustomerByPhoneNumberAndEmailOrderedByFullName("111", "test@email.com");
    }

    public static void main(String[] args) {

	new Main();

	System.out.println("Done!");
    }

}
