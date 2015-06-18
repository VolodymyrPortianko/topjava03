package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;
import ru.javawebinar.topjava.web.meal.UserMealRestController;
import ru.javawebinar.topjava.web.user.AdminUserRestController;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

/**
 * User: gkislin
 * Date: 22.08.2014
 */
public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try(ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println(Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminUserRestController adminUserController = appCtx.getBean(AdminUserRestController.class);
            System.out.println(adminUserController.create(new User(1, "userName", "email", "password", Role.ROLE_ADMIN)));

            //testing UserMeal
            System.out.println();
            User user = new User(1, "userName", "email", "password", Role.ROLE_ADMIN);
            UserMeal userMeal = new UserMeal(0,"Завтрак", LocalDateTime.of(2015, Month.JUNE, 17, 10, 0),1000,user);
            UserMealRestController userMealRestController = appCtx.getBean(UserMealRestController.class);
            System.out.println(userMealRestController.create(userMeal));

            System.out.println(userMealRestController.getAllMealWithExceed());
            System.out.println(userMealRestController.getFilteredByUserIdDateRangeWithExceed(
                    LocalDateTime.of(2015, Month.JUNE, 15, 10, 0),
                    LocalDateTime.of(2015, Month.JUNE, 18, 10, 0),
                    LocalTime.of(10, 0),
                    LocalTime.of(12,0)
            ));
        }
    }
}