package personal.criva.springhibernateexample;

import java.util.ArrayList;
import java.util.List;

import personal.criva.springhibernateexample.config.ApplicationContextProvider;
import personal.criva.springhibernateexample.service.ICustomerService;

public class Main {

    ICustomerService customerService = ApplicationContextProvider.getApplicationContext()
	    .getBean(ICustomerService.class);

    public Main() {
	
	
//	Calendar date = Calendar.getInstance();
//	date.set(2000, 2, 25);

//	CustomerVo customer = new CustomerVo("Test2", "222", "test@email.com", "000", date);
	
	List<Long> ids = new ArrayList<Long>();
	
	ids.add(10L);
	ids.add(11L);
	
	customerService.findAllCustomerById(ids);
    }

    public static void main(String[] args) {

	new Main();

	System.out.println("Done!");
    }

}
