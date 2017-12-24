package personal.criva.springhibernateexample.model.vo;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "customer")
public class CustomerVo implements Serializable{

    private static final long serialVersionUID = 2920868381012043094L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fullName", nullable = false)
    private String fullName;

    @Column(name = "identity_document", unique = true)
    private String identityDocument;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date", nullable = false)
    private Calendar birthDate;

    @Deprecated
    public CustomerVo() {
	
    }

    public CustomerVo(String fullName, String identityDocument, String email, 
	    String phoneNumber, Calendar birthDate) {
	
	this.fullName = fullName;
	this.identityDocument = identityDocument;
	this.email = email;
	this.phoneNumber = phoneNumber;
	this.birthDate = birthDate;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getFullName() {
	return fullName;
    }

    public void setFullName(String fullName) {
	this.fullName = fullName;
    }

    public String getIdentityDocument() {
	return identityDocument;
    }

    public void setIdentityDocument(String identityDocument) {
	this.identityDocument = identityDocument;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getPhoneNumber() {
	return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
    }

    public Calendar getBirthDate() {
	return birthDate;
    }

    public void setBirthDate(Calendar birthDate) {
	this.birthDate = birthDate;
    }

}
