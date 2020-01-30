package com.vivek.tests.scottishpower.spsmartmeterreader.repository;

import org.springframework.stereotype.Repository;

import com.vivek.tests.scottishpower.spsmartmeterreader.repository.entity.SmartAccount;

import org.springframework.data.repository.CrudRepository;


@Repository
public interface SmartMeterRepository extends CrudRepository<SmartAccount,Long> {

}
