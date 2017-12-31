package personal.criva.springhibernateexample.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import personal.criva.springhibernateexample.model.dao.ICustomerDao;
import personal.criva.springhibernateexample.model.vo.CustomerVo;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private ICustomerDao customerDao;

    @Transactional
    public CustomerVo saveCustomer(CustomerVo customer) {

	CustomerVo savedCustomer = null;

	try {

	    savedCustomer = customerDao.save(customer);
	} catch (Exception e) {

	    System.err.println(e.getMessage());
	    e.printStackTrace();
	}

	return savedCustomer;
    }

    @Transactional
    public CustomerVo updateCustomer(CustomerVo customer) {

	CustomerVo updatedCustomer = null;

	try {

	    updatedCustomer = customerDao.update(customer);
	} catch (Exception e) {

	    System.err.println(e.getMessage());
	    e.printStackTrace();
	}

	return updatedCustomer;
    }

    @Transactional
    public void deleteCustomer(CustomerVo customer) {

	try {

	    customerDao.delete(customer);
	} catch (Exception e) {

	    System.err.println(e.getMessage());
	    e.printStackTrace();
	}
    }

    @Transactional
    public void deleteCustomers(List<CustomerVo> customers) {

	try {

	    customerDao.delete(customers);
	} catch (Exception e) {

	    System.err.println(e.getMessage());
	    e.printStackTrace();
	}
    }

    @Transactional
    public void deleteAllCustomers() {

	try {

	    customerDao.deleteAll();
	} catch (Exception e) {

	    System.err.println(e.getMessage());
	    e.printStackTrace();
	}
    }

    @Transactional
    public void deleteCustomerById(Long id) {

	try {

	    customerDao.deleteById("id", id);
	} catch (Exception e) {

	    System.err.println(e.getMessage());
	    e.printStackTrace();
	}
    }

    @Transactional
    public CustomerVo findCustomerById(Long id) {

	CustomerVo foundCustomer = null;

	try {

	    foundCustomer = customerDao.findById(id);
	} catch (Exception e) {

	    System.err.println(e.getMessage());
	    e.printStackTrace();
	}

	return foundCustomer;
    }

    @Transactional
    public List<CustomerVo> findAllCustomerById(List<Long> ids) {
	
	List<CustomerVo> foundCustomers = null;
	
	try {

	    foundCustomers = customerDao.findAllById("id", ids);
	} catch (Exception e) {

	    System.err.println(e.getMessage());
	    e.printStackTrace();
	}

	return foundCustomers;
    }
    
    @Transactional
    public List<CustomerVo> findAllCustomers() {
	
	List<CustomerVo> foundCustomers = null;
	
	try {
	    
	    foundCustomers = customerDao.findAll("fullName");
	} catch (Exception e) {
	    
	    System.err.println(e.getMessage());
	    e.printStackTrace();
	}
	
	return foundCustomers;
    }

    @Transactional
    public Long countCustomersByPhoneNumber(String phoneNumber) {
	
	Long customers = null;

	try {

	    customers = customerDao.countByAttribute("phoneNumber", phoneNumber);
	} catch (Exception e) {

	    System.err.println(e.getMessage());
	    e.printStackTrace();
	}

	return customers;
    }
    
    @Transactional
    public List<CustomerVo> findCustomerByPhoneNumberOrderedByFullName(String phoneNumber) {

	List<CustomerVo> foundCustomers = null;

	List<String> orderByAttributes = new ArrayList<String>();
	orderByAttributes.add("fullName");
	
	try {

	    foundCustomers = customerDao.findByAttribute("phoneNumber", phoneNumber, orderByAttributes);
	} catch (Exception e) {

	    System.err.println(e.getMessage());
	    e.printStackTrace();
	}

	return foundCustomers;
    }

    @Transactional
    public List<CustomerVo> findCustomerByPhoneNumberAndEmailOrderedByFullName(String phoneNumber, String email) {
	
	List<CustomerVo> foundCustomers = null;

	List<String> attributesNames = new ArrayList<String>();
	List<Object> values = new ArrayList<Object>();
	List<String> orderByAttributes = new ArrayList<String>();
	
	attributesNames.add("phoneNumber");
	attributesNames.add("email");
	
	values.add(phoneNumber);
	values.add(email);
	
	orderByAttributes.add("fullName");
	
	try {

	    foundCustomers = customerDao.findByAttributes(attributesNames, values, orderByAttributes);
	} catch (Exception e) {

	    System.err.println(e.getMessage());
	    e.printStackTrace();
	}

	return foundCustomers;
    }
    
    

}
