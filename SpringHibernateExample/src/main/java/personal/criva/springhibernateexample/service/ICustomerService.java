package personal.criva.springhibernateexample.service;

import java.util.List;

import personal.criva.springhibernateexample.model.vo.CustomerVo;

public interface ICustomerService {

    public CustomerVo saveCustomer(CustomerVo customer);

    public CustomerVo updateCustomer(CustomerVo customer);

    public void deleteCustomer(CustomerVo customer);
    
    public void deleteCustomers(List<CustomerVo> customers);
    
    public void deleteAllCustomers();
    
    public void deleteCustomerById(Long id);
    
    public CustomerVo findCustomerById(Long id);
    
    public List<CustomerVo> findAllCustomerById(List<Long> id);
    
    public List<CustomerVo> findAllCustomers();
    
    public Long countCustomersByPhoneNumber(String phoneNumber);
    
    public List<CustomerVo> findCustomerByPhoneNumberOrderedByFullName(String phoneNumber);
    
    public List<CustomerVo> findCustomerByPhoneNumberAndEmailOrderedByFullName(String phoneNumber, String email);
    
    
}
