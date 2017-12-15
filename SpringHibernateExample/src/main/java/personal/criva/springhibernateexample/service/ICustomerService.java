package personal.criva.springhibernateexample.service;

import java.util.List;

import personal.criva.springhibernateexample.model.vo.CustomerVo;

public interface ICustomerService {

    public CustomerVo saveCustomer(CustomerVo customer);

    public CustomerVo updateCustomer(CustomerVo customer);

    public CustomerVo findCustomerById(Long id);
    
    public List<CustomerVo> findCustomerByPhoneNumber(String phoneNumber);
}
