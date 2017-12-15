package personal.criva.springhibernateexample;

import java.util.Calendar;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import personal.criva.springhibernateexample.config.ApplicationContextProvider;
import personal.criva.springhibernateexample.config.SpringAppConfig;
import personal.criva.springhibernateexample.model.vo.CustomerVo;
import personal.criva.springhibernateexample.service.ICustomerService;

public class Main {

    ICustomerService customerService = ApplicationContextProvider.getApplicationContext()
	    .getBean(ICustomerService.class);

    public Main() {

//	Calendar birthDate = Calendar.getInstance();
//	birthDate.clear();
//
//	birthDate.set(Calendar.YEAR, 2000);
//	birthDate.set(Calendar.MONTH, 1);
//	birthDate.set(Calendar.DATE, 1);
//
//	CustomerVo customer = new CustomerVo(1l ,"Test", "222", "test@email.com", "222", birthDate);
//	
//	CustomerVo customer2 = customerService.findCustomerById(1l);
//	
//	customer2.setFullName("TesteCerto2");
//
//	System.out.println(customerService.updateCustomer(customer2).getId());
	
	for(CustomerVo customer : customerService.findCustomerByPhoneNumber("333")) {
	    
	    System.out.println(customer.getId());
	}
    }

    public static void main(String[] args) {

	new Main();

	System.out.println("Done!");
    }

}
