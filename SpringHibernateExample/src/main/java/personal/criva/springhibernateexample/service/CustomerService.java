package personal.criva.springhibernateexample.service;

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
    public List<CustomerVo> findCustomerByPhoneNumber(String phoneNumber) {
	
	List<CustomerVo> foundCustomer = null;

	try {

	    foundCustomer = customerDao.findByProperty("phoneNumber", phoneNumber);
	} catch (Exception e) {

	    System.err.println(e.getMessage());
	    e.printStackTrace();
	}

	return foundCustomer;
    }
}