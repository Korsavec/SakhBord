package com.sakhbord.bord.config;

import com.sakhbord.bord.models.activation.NotActivatedUser;
import com.sakhbord.bord.models.user.User;
import com.sakhbord.bord.service.jpa.ServiceJpa;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component
public class SchedulerNotActivatedUser {

    private final
    ServiceJpa serviceJpa;


    public SchedulerNotActivatedUser(ServiceJpa serviceJpa) {
        this.serviceJpa = serviceJpa;
    }



    //        @Scheduled(fixedDelay = 86_400_000) // 1 день 86_400_000
    @Scheduled(cron = "0 0 0/23 * * *") // Каждый день в 23 часа "0 0 0/23 * * *"
    public void deleteNotActivatedUser() {

        // Тут хранятся все записи NotActivatedUser
        List<NotActivatedUser> notActivatedUsers = new ArrayList<>();

        // Тут получаем все запись из базы данных NotActivatedUser
        serviceJpa.findAllDateDeletionUser().forEach(notActivatedUsers::add);

        // Фильтруем и присваиваем в эту переменную те сущности, которые нужно удалить
        List<NotActivatedUser> entities1 = notActivatedUsers
                .stream()
                .filter(NotActivatedUser::isActive).toList();


        // Фильтруем и присваиваем в эту переменную те сущности, которые нужно удалить вместе с аккаунтом
        List<NotActivatedUser> entities2 = notActivatedUsers
                .stream()
                .filter(notActivatedUser -> Instant.now().isAfter(notActivatedUser.getDateDeletionUser()))
                .filter(notActivatedUser -> !notActivatedUser.isActive()).toList();

        // Тут хранятся ID аккаунтов которые нужно удалить
        List<Long> longList = new ArrayList<>();

        // Получаем ID
        entities2.forEach(notActivatedUser -> longList.add(notActivatedUser.getUser().getId()));

        // Сюда присваиваем все аккаунты которые нужно удалить
        List<User> userList =  serviceJpa.findAllUserById(longList);


        if (!entities1.isEmpty()){
            serviceJpa.deleteAllUser(entities1);
        } else if (!entities2.isEmpty()){
            serviceJpa.deleteListUser(userList);
        }



    }


}
