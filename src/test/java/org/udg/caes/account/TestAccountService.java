package org.udg.caes.account;

import mockit.Expectations;
import mockit.Verifications;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mockit.Tested;
import mockit.Mocked;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAccountService {

  Account acc1;
  Account acc2;

  @BeforeEach
  void setup() {
    acc1 = new Account("acc1", 400);
    acc2 = new Account("acc2", 200);
  }

  @Test
  void testTransfer(@Tested AccountService as, @Mocked AccountManager am)  {

    new Expectations() {{
      am.findAccount("acc1"); result = acc1;
      am.findAccount("acc2"); result = acc2;
    }};

    as.setAccountManager(am);
    as.transfer("acc1", "acc2", 100);

    assertEquals(acc1.getBalance(), acc2.getBalance());

  }
}