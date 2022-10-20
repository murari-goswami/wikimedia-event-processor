package com.mmg.repository;

import com.arangodb.springframework.repository.ArangoRepository;
import com.mmg.entity.Character;

public interface CharacterRepository extends ArangoRepository<Character, String> {

}
