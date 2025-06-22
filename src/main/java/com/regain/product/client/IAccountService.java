package com.regain.product.client;

import com.regain.product.model.AccountDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class IAccountService implements AccountService{
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public ResponseEntity<AccountDTO> findAccountByAccountId(Long accountId) {
        logger.error("Notification service is slow");
        return null;
    }
}
