package personal.criva.springhibernateexample.model.dao;

import org.springframework.stereotype.Repository;

import personal.criva.springhibernateexample.model.vo.CustomerVo;

@Repository
public class CustomerDao extends AbstractGenericDao<CustomerVo, Long> implements ICustomerDao {
    
}
