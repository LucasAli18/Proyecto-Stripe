package com.stripe.stripepayments.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.stripe.stripepayments.common.entities.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {

}
