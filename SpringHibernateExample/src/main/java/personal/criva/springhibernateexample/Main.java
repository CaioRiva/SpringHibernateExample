package personal.criva.springhibernateexample;

import java.util.List;

import personal.criva.springhibernateexample.config.ApplicationContextProvider;
import personal.criva.springhibernateexample.model.vo.CustomerVo;
import personal.criva.springhibernateexample.service.ICustomerService;

public class Main {

    ICustomerService customerService = ApplicationContextProvider.getApplicationContext()
	    .getBean(ICustomerService.class);

    public Main() {

	List<CustomerVo> customers = customerService.findAllCustomers();

	for (CustomerVo customer : customers) {

	    customerService.deleteCustomerById(customer.getId());
	}
    }

    public static void main(String[] args) {

	new Main();

	System.out.println("Done!");
    }

}
