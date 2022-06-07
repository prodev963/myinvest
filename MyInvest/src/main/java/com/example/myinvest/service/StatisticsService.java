package com.example.myinvest.service;

import com.example.myinvest.config.CurrentUser;
import com.example.myinvest.entity.ItemEntity;
import com.example.myinvest.entity.PackageEntity;
import com.example.myinvest.entity.UserEntity;
import com.example.myinvest.model.response.ApiResponse;
import com.example.myinvest.repository.ItemRepository;
import com.example.myinvest.repository.PackageRepository;
import com.example.myinvest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;


@Service
public class StatisticsService implements BaseService {

    private final UserRepository userRepository;
    private final PackageRepository packageRepository;
    private final ItemRepository itemRepository;
    private final CurrentUser currentUser;

    @Autowired
    public StatisticsService(UserRepository userRepository, PackageRepository packageRepository, ItemRepository itemRepository, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.packageRepository = packageRepository;
        this.itemRepository = itemRepository;
        this.currentUser = currentUser;

    }

    public ApiResponse getUserStats(String username) {
        List<PackageEntity> packagesList = userRepository.findByUsername(username).get().getPackagesList();
        USER_STAT.setData(getFinalStats(1, packagesList, LocalDate.now().getYear()));
        return USER_STAT;
    }

    public ApiResponse getStatistics(int age, String gender, int y) {
        List<PackageEntity> packageEntities = new ArrayList<>();
        for (UserEntity searchUser : searchUsers(age, gender)) {
            packageEntities.addAll(searchUser.getPackagesList());
        }
        ALL_STATS.setData(getFinalStats(userRepository.count(), packageEntities, y));
        return ALL_STATS;
    }

    public List<UserEntity> searchUsers(int age, String gender) {
        if (age != -1 && gender != null) {
            return userRepository.findAll();
            //return userRepository.findAllByBirthdayContainsAndGender(LocalDate.now().getYear() - age, gender);
        } else if (age != -1) {
            return userRepository.findAllByBirthdayContains(LocalDate.now().getYear() - age);
        } else if (gender != null) {
            return userRepository.findAllByGender(gender);
        } else {
            return userRepository.findAll();
        }

//        select avg(t.wage) from
//                users t where t.userID <> 1 AND T.birthday = myBirthday and t.age = myAge;
    }

    public Map<Month, BigDecimal[]> getFinalStats(long userCount, List<PackageEntity> packageEntities, int y) {
        Map<Month, BigDecimal[]> resultMap = new HashMap<>();
        BigDecimal[] resultData = {BigDecimal.valueOf(0), BigDecimal.valueOf(0)};

        for (PackageEntity packageEntity : packageEntities) {
            if (packageEntity.getType().equals("kirim")) {
                for (ItemEntity itemEntity : packageEntity.getItemsList()) {
                    if (itemEntity.getDate().getYear() == y) {
                        if (resultMap.containsKey(itemEntity.getDate().getMonth())) {
                            resultMap.get(itemEntity.getDate().getMonth())[0] =
                                    resultMap.get(itemEntity.getDate().getMonth())[0].add(itemEntity.getPrice());
                        } else {
                            resultData[0] = itemEntity.getPrice();
                            resultMap.put(itemEntity.getDate().getMonth(), resultData);
                        }
                        resultMap.put(itemEntity.getDate().getMonth(), resultMap.get(itemEntity.getDate().getMonth()));
                    }
                }
            } else if (packageEntity.getType().equals("chiqim")) {
                for (ItemEntity itemEntity : packageEntity.getItemsList()) {
                    if (resultMap.containsKey(itemEntity.getDate().getMonth())) {
                        resultMap.get(itemEntity.getDate().getMonth())[1] =
                                resultMap.get(itemEntity.getDate().getMonth())[1].add(itemEntity.getPrice());
                    } else {
                        resultData[1] = itemEntity.getPrice();
                        resultMap.put(itemEntity.getDate().getMonth(), resultData);
                    }
                    resultMap.put(itemEntity.getDate().getMonth(), resultMap.get(itemEntity.getDate().getMonth()));
                }
            }
        }

        for (Month m : resultMap.keySet()) {
            resultMap.get(m)[0] = resultMap.get(m)[0].divide(BigDecimal.valueOf(userCount));
            resultMap.get(m)[1] = resultMap.get(m)[1].divide(BigDecimal.valueOf(userCount));
        }

        return resultMap;
    }

    public ApiResponse getStatisticsPage(){
        List<PackageEntity> packageEntities = new ArrayList<>();
        for (UserEntity searchUser : userRepository.findAll()) {
            packageEntities.addAll(searchUser.getPackagesList());
        }
        ALL_STATS.setData(getFinalStats(userRepository.count(), packageEntities,2022));
        return ALL_STATS;
    }
}
