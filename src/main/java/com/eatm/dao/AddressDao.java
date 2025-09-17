package com.eatm.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eatm.entity.Address;

@Component
public class AddressDao {
	@Autowired
	private EntityManager entityManager;
	@Autowired
	private EntityTransaction entityTransaction;
	
	public Address saveAddress(Address address)
	{
		entityTransaction.begin();
		entityManager.persist( address);
		entityTransaction.commit();
		return  address;
	}
	
	public Address updateAddress(Address  address)
	{
		entityTransaction.begin();
		entityManager.merge( address);
		entityTransaction.commit();
		return  address;
	}
	
	public Address deleteAddress(Address  address)
	{
		entityTransaction.begin();
		entityManager.remove( address);
		entityTransaction.commit();
		return  address;
	}

}
