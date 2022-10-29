package com.api.miniproject.repository;

import com.api.miniproject.domain.Item;
import com.api.miniproject.repository.jpa.ItemRepositoryJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>, ItemRepositoryJPA {

    // TODO: 내장된 findAll 왜 안되는 걸까
    @Query(value = "SELECT * FROM ITEM_TB", nativeQuery = true)
    List<Item> findAll();

    @Query(value = "SELECT * FROM ITEM_TB WHERE USER_ID = :userId", nativeQuery = true)
    List<Item> findUserItems(@Param("userId") Long userId);
}
