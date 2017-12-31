package personal.criva.springhibernateexample.model.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public abstract class AbstractGenericDao<T extends Serializable, K> implements IGenericDao<T, K> {

    private static final String attributesValuesSizeException = "'values' parameter size can not be less than 'attributesNames' parameter size.";

    @PersistenceContext
    private EntityManager entityManager;

    private final Class<T> entityClass;

    @SuppressWarnings("unchecked")
    public AbstractGenericDao() {

	this.entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
		.getActualTypeArguments()[0];
    }

    public T save(T entity) {

	try {

	    entityManager.persist(entity);

	    entityManager.refresh(entity);
	} catch (Exception e) {

	    System.err.println(e.getMessage());
	    e.printStackTrace();
	}

	return entity;

    }

    public T update(T entity) {

	T updatedEntity = null;

	try {

	    updatedEntity = entityManager.merge(entity);
	} catch (Exception e) {

	    System.err.println(e.getMessage());
	    e.printStackTrace();
	}

	return updatedEntity;
    }

    public void delete(T entity) {

	try {

	    entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
	} catch (Exception e) {

	    System.err.println(e.getMessage());
	    e.printStackTrace();
	}
    }

    public void delete(Collection<T> entities) {

	for (T entity : entities) {

	    delete(entity);
	}
    }

    public void deleteAll() {

	StringBuilder queryString = new StringBuilder("DELETE FROM " + entityClass.getName());

	try {

	    entityManager.createQuery(queryString.toString()).executeUpdate();
	} catch (Exception e) {

	    System.err.println(e.getMessage());
	    e.printStackTrace();
	}
    }

    public void deleteById(String idAttributeName, Object id) {

	try {

	    StringBuilder queryString = new StringBuilder(
		    "DELETE FROM " + entityClass.getName() + " e WHERE e.id = :id");
	    entityManager.createQuery(queryString.toString()).setParameter(idAttributeName, id).executeUpdate();
	} catch (Exception e) {

	    System.err.println(e.getMessage());
	    e.printStackTrace();
	}
    }

    public T getReference(Object id) {

	T referencedEntity = null;

	try {

	    referencedEntity = entityManager.getReference(entityClass, id);
	} catch (Exception e) {

	    System.err.println(e.getMessage());
	    e.printStackTrace();
	}

	return referencedEntity;
    }

    public T findById(Object id) {

	T foundEntity = null;

	try {

	    foundEntity = entityManager.find(entityClass, id);
	} catch (Exception e) {

	    System.err.println(e.getMessage());
	    e.printStackTrace();
	}

	return foundEntity;
    }

    @SuppressWarnings("unchecked")
    public List<T> findAllById(String idAttributeName, List<K> values) {

	List<T> queryResult = null;
	Query query = createQueryByIds(idAttributeName, values);

	try {

	    queryResult = query.getResultList();
	} catch (Exception e) {

	    System.err.println(e.getMessage());
	    e.printStackTrace();
	}

	return queryResult;
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {

	List<T> queryResult = null;

	try {

	    StringBuilder queryString = new StringBuilder("FROM " + entityClass.getName());
	    queryResult = entityManager.createQuery(queryString.toString()).getResultList();
	} catch (Exception e) {

	    System.err.println(e.getMessage());
	    e.printStackTrace();
	}

	return queryResult;
    }

    public List<T> findAll(String orderByAttribute) {

	List<T> queryResult = null;

	try {

	    StringBuilder queryString = new StringBuilder(
		    "SELECT e FROM " + entityClass.getName() + " e ORDER BY e." + orderByAttribute);
	    entityManager.createQuery(queryString.toString()).getResultList();
	} catch (Exception e) {

	    System.err.println(e.getMessage());
	    e.printStackTrace();
	}

	return queryResult;

    }

    public Long countByAttribute(String attributeName, Object value) {

	Long countResult = null;

	try {

	    countResult = (Long) createCountQueryByAttribute(attributeName, value).getResultList().get(0);

	} catch (Exception e) {

	    System.err.println(e.getMessage());
	    e.printStackTrace();
	}

	return countResult;
    }

    public List<T> findByAttribute(String attributeName, Object value) {

	return findByAttribute(attributeName, value, null);
    }

    @SuppressWarnings("unchecked")
    public List<T> findByAttribute(String attributeName, Object value, List<String> orderByAttributes) {

	List<T> queryResult = null;

	try {

	    queryResult = createQueryByAttribute(attributeName, value, orderByAttributes).getResultList();
	} catch (Exception e) {

	    System.err.println(e.getMessage());
	    e.printStackTrace();
	}

	return queryResult;
    }

    public List<T> findByAttributes(List<String> attributesNames, List<Object> values) {

	return findByAttributes(attributesNames, values, null);
    }

    @SuppressWarnings("unchecked")
    public List<T> findByAttributes(List<String> attributesNames, List<Object> values, List<String> orderByAttributes) {

	List<T> queryResult = null;

	try {

	    queryResult = createQueryByAttributes(attributesNames, values, orderByAttributes).getResultList();
	} catch (Exception e) {

	    System.err.println(e.getMessage());
	    e.printStackTrace();
	}

	return queryResult;
    }

    private Query createQueryByIds(String idAttributeName, List<K> values) {

	Query query = null;
	StringBuilder queryString = new StringBuilder(
		"SELECT e FROM " + entityClass.getName() + " e WHERE e." + idAttributeName + " IN (:values)");

	try {

	    query = entityManager.createQuery(queryString.toString());
	    query.setParameter("values", values);
	} catch (Exception e) {

	    System.err.println(e.getMessage());
	    e.printStackTrace();
	}

	return query;
    }

    private Query createCountQueryByAttribute(String attributeName, Object value) {

	Query query = null;
	StringBuilder queryString = new StringBuilder(
		"SELECT COUNT(1) FROM " + entityClass.getName() + " e WHERE e." + attributeName + " = :value");

	try {

	    query = entityManager.createQuery(queryString.toString());
	    query.setParameter("value", value);
	} catch (Exception e) {

	    System.err.println(e.getMessage());
	    e.printStackTrace();
	}

	return query;
    }

    private Query createQueryByAttribute(String attributeName, Object value, List<String> orderByAttributes) {

	Query query = null;
	StringBuilder queryString = new StringBuilder(
		"SELECT e FROM " + entityClass.getName() + " e WHERE e." + attributeName + " = :value");

	queryString.append(createOrderByStatement(orderByAttributes));
	try {

	    query = entityManager.createQuery(queryString.toString());
	    query.setParameter("value", value);
	} catch (Exception e) {

	    System.err.println(e.getMessage());
	    e.printStackTrace();
	}

	return query;
    }

    private Query createQueryByAttributes(List<String> attributesNames, List<Object> values,
	    List<String> orderByAttributes) {

	if (values.size() < attributesNames.size()) {

	    throw new IllegalArgumentException(attributesValuesSizeException);
	}

	Query query = null;
	StringBuilder queryString = new StringBuilder("SELECT e FROM " + entityClass.getName() + " e WHERE ");

	for (int i = 0; i < attributesNames.size(); i++) {

	    if (i != 0) {

		queryString.append(" AND ");
	    }

	    queryString.append("e.");
	    queryString.append(attributesNames.get(i));
	    queryString.append(" = ?");
	    queryString.append(i + 1);
	}

	queryString.append(createOrderByStatement(orderByAttributes));

	try {

	    query = entityManager.createQuery(queryString.toString());
	    
	    for (int i = 0; i < attributesNames.size(); i++) {
		
		query.setParameter(i + 1, values.get(i));
	    }
	} catch (Exception e) {

	    System.err.println(e.getMessage());
	    e.printStackTrace();
	}

	return query;
    }

    private String createOrderByStatement(List<String> orderByAttributes) {

	StringBuilder orderByString = new StringBuilder();

	if (orderByAttributes != null && orderByAttributes.size() > 0) {

	    orderByString.append(" ORDER BY ");

	    for (int i = 0; i < orderByAttributes.size(); i++) {

		if (i != 0) {

		    orderByString.append(" ,");
		}

		orderByString.append(" e.");
		orderByString.append(orderByAttributes.get(i));
	    }
	}

	return orderByString.toString();
    }
}
