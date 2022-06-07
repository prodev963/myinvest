package com.example.myinvest.service;

import com.example.myinvest.config.CurrentUser;
import com.example.myinvest.entity.PackageEntity;
import com.example.myinvest.entity.UserEntity;
import com.example.myinvest.model.receive.PackageReceiveModel;
import com.example.myinvest.model.response.ApiResponse;
import com.example.myinvest.model.response.PackageResponse;
import com.example.myinvest.repository.PackageRepository;
import com.example.myinvest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PackageService implements BaseService {

    private final UserRepository userRepository;
    private final PackageRepository packageRepository;
    private final UserService userService;
    private final CurrentUser currentUser;

    @Autowired
    public PackageService(UserRepository userRepository, PackageRepository packageRepository, UserService userService, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.packageRepository = packageRepository;
        this.userService = userService;
        this.currentUser = currentUser;
    }

    public ApiResponse addPackage(PackageReceiveModel packageReceiveModel) {

        UserEntity currentUser = this.currentUser.getCurrentUser();

        List<PackageEntity> packagesList = userRepository.findById(currentUser.getId()).get().getPackagesList();
        PackageEntity myPackageEntity = packagesList.stream()
                .filter(p -> packageReceiveModel.getName().equals(p.getName()))
                .findAny()
                .orElse(null);

        if (myPackageEntity != null)
            return PACKAGE_EXIST;

        PackageEntity packageEntity
                = new PackageEntity();

        packageEntity.setName(packageReceiveModel.getName());
        packageEntity.setType(packageReceiveModel.getType());

        packagesList.add(packageEntity);

        packageRepository.save(packageEntity);
        return PACKAGE_ADD_SUC;
    }

    public ApiResponse getPackageList(String username) {
        List<PackageEntity> packagesList = userRepository.findByUsername(username).get().getPackagesList();

        List<PackageResponse> packageResponses=new ArrayList<>();
        for (PackageEntity p:packagesList) {
            packageResponses.add(new PackageResponse(p.getName(), p.getType()));
        }
        PACKAGE_LIST.setData(packageResponses);
        return PACKAGE_LIST;
    }
}
