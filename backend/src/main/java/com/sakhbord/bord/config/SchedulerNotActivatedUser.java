package com.sakhbord.bord.config;

import com.sakhbord.bord.models.activation.NotActivatedUser;
import com.sakhbord.bord.models.user.User;
import com.sakhbord.bord.repository.service.ServiceJpa;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class SchedulerNotActivatedUser {

    private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";

    private final
    ServiceJpa serviceJpa;


    public SchedulerNotActivatedUser(ServiceJpa serviceJpa) {
        this.serviceJpa = serviceJpa;
    }



    //        @Scheduled(fixedDelay = 86_400_000) // 1 день 86_400_000
    @Scheduled(cron = "0 0 0/23 * * *") // Каждый день в 23 часа "0 0 0/23 * * *"
//    @Scheduled(fixedRate = 10000 ) // микросекунды 1000 = 1 секунда
    public void deleteNotActivatedUser() {


        // Тут хранятся все записи NotActivatedUser
        List<NotActivatedUser> notActivatedUsers = new ArrayList<>();





        // Тут получаем все запись из базы данных NotActivatedUser и присваевем в переменную
        // notActivatedUsers которая выше
        serviceJpa.findAllDateDeletionUser().forEach(notActivatedUser -> {

            // Обрезаем дату с 1900-01-03 00:00:00.000000 до 1900-01-03 00:00:00
            // так как, если в базе будут миллисекунды все нули (1900-01-03 00:00:00.000000)
            // то, база вернёт дату не (1900-01-03 00:00:00.000000) а (1900-01-03 00:00:00)
            // и из-за этого формат даты вызывает исключение, потому что дата не подходит по паттерну
            String date = notActivatedUser.getDateDeletionUser().substring(0,19);
            notActivatedUser.setDateDeletionUser(date);
            notActivatedUsers.add(notActivatedUser);

        });

        // Фильтруем и присваиваем в эту переменную те сущности NotActivatedUser, которые нужно удалить
        List<NotActivatedUser> entities1 = notActivatedUsers
                .stream()
                .filter(NotActivatedUser::isActive).toList();

        // Фильтруем и присваиваем в эту переменную те сущности, которые нужно удалить вместе с User-ом
        List<NotActivatedUser> entities2 = notActivatedUsers
                .stream()
                .filter(notActivatedUser -> {

                    DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern(FORMAT);
                    LocalDateTime dateTime1 = LocalDateTime.parse(notActivatedUser.getDateDeletionUser(), formatter1);

                    // Возвращает true или false
                    return dateTime1.isBefore(LocalDateTime.now().minusDays(1));




                })
                .filter(notActivatedUser -> !notActivatedUser.isActive()).toList();

        // Тут хранятся ID аккаунтов (User) которые нужно удалить
        List<Long> longList = new ArrayList<>();

        // Получаем ID
        entities2.forEach(notActivatedUser -> longList.add(notActivatedUser.getUser().getId()));

        // Сюда присваиваем все аккаунты которые нужно удалить
        List<User> userList =  serviceJpa.findAllUserById(longList);


        if (!entities1.isEmpty()){
            serviceJpa.deleteAllUser(entities1);
        }

        if (!entities2.isEmpty()){
            serviceJpa.deleteListUser(userList);
        }


    }



}
