package com.mtr.psn.service.user;

import java.util.List;
import com.mtr.psn.model.user.BankAccount;

public interface BankAccountService {

	public BankAccount selectById(Long id)throws Exception;

	public List<BankAccount> selectAll(BankAccount bankAccount)throws Exception;

	public int psn_insert(BankAccount bankAccount)throws Exception;

	public int psn_update(BankAccount bankAccount)throws Exception;

	public int psn_delete(BankAccount bankAccount)throws Exception;

}
