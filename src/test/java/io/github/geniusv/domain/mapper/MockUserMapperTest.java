package io.github.geniusv.domain.mapper;

import io.github.geniusv.domain.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.*;

/**
 * Copyright 2017 GeniusV
 * All rights reserved.
 * Created by GeniusV on 11/20/17.
 */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class MockUserMapperTest {
    @Mock
    UserMapper userMapper;
    @Mock
    User user;

    @Test
    public void testUserMapper() {
        when(userMapper.selectByPrimaryKey(8L)).thenReturn(user);

        User ans = userMapper.selectByPrimaryKey(8L);
        verify(userMapper).selectByPrimaryKey(8L);
    }
}
