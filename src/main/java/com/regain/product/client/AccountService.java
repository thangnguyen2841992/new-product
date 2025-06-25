package com.regain.product.client;

import com.regain.product.model.dto.AccountDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "account-service", url = "http://localhost:8080", fallback = IAccountService.class)
public interface AccountService {
    @GetMapping("/account/findAccountByAccountId/{accountId}")
    ResponseEntity<AccountDTO> findAccountByAccountId(@PathVariable Long accountId);
}
