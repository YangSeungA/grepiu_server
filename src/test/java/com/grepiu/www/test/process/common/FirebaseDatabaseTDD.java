package com.grepiu.www.test.process.common;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.grepiu.springboot.test.process.config.LocalBaseConfig;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 *
 * 파이어 베이스 TDD
 *
 */
@Slf4j
public class FirebaseDatabaseTDD extends LocalBaseConfig {

  @Override
  public void setUp() {

  }

  /**
   *
   * 데이터 저장
   *
   */
  @Test
  public void save() {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("grepiu.menu");

    DatabaseReference usersRef = ref.child("users");

    Map<String, Object> users = new HashMap<>();
    users.put("test","test");

    usersRef.setValueAsync(users);
  }
}
