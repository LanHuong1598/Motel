package com.example.intergration.motel.repository;

import com.example.intergration.motel.beans.Stats;
import com.example.intergration.motel.beans.UserGroup;
import com.example.intergration.motel.beans.UserService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface StatsRepository extends JpaRepository<UserGroup, Integer>{
    @Query("select u from Stats u where u.date <= ?2 and u.date >=?1 " )
    List<Stats> findAllByDate(Date timestart, Date timeend);
}
