package com.dubic.farm.repositories;

import com.dubic.farm.models.Farm;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface FarmRepo extends CrudRepository<Farm, String > {

}
