package com.lovo.uploadsystem.dao;

import org.springframework.data.repository.CrudRepository;

import com.lovo.uploadsystem.entity.EventTypeEntity;


public interface IEventTypeDao extends CrudRepository<EventTypeEntity, String>{

}
