package ru.javawebinar.topjava.service;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.base.UserServiceTest;

/**
 * Created by VPortianko on 10.07.2015.
 */
@ActiveProfiles("jdbc")
public class JdbcUserServiceTest extends UserServiceTest {
}