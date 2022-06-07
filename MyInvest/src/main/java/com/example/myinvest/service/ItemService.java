package com.example.myinvest.service;

import com.example.myinvest.config.CurrentUser;
import com.example.myinvest.entity.ItemEntity;
import com.example.myinvest.entity.PackageEntity;
import com.example.myinvest.entity.UserEntity;
import com.example.myinvest.model.receive.ItemReceiveModel;
import com.example.myinvest.model.response.ApiResponse;
import com.example.myinvest.repository.ItemRepository;
import com.example.myinvest.repository.PackageRepository;
import com.example.myinvest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService implements BaseService {

    private final UserRepository userRepository;
    private final PackageRepository packageRepository;
    private final ItemRepository itemRepository;
    private final CurrentUser currentUser;

    @Autowired
    public ItemService(UserRepository userRepository, PackageRepository packageRepository, UserService userService, ItemRepository itemRepository, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.packageRepository = packageRepository;
        this.itemRepository = itemRepository;
        this.currentUser = currentUser;
    }

    public ApiResponse addItem(String packageName,ItemReceiveModel itemReceiveModel) {

        UserEntity currentUser = this.currentUser.getCurrentUser();
        List<PackageEntity> packagesList = userRepository.findById(currentUser.getId()).get().getPackagesList();

        for (PackageEntity ps:packagesList) {
            if(ps.getName().equals(packageName)){
                ItemEntity itemEntity
                        = new ItemEntity(itemReceiveModel.getName(),
                        itemReceiveModel.getDescription(),
                        itemReceiveModel.getPaymentType(),
                        itemReceiveModel.getPrice(),
                        itemReceiveModel.getDate());

                List<ItemEntity> itemsList = ps.getItemsList();
                itemsList.add(itemEntity);

                itemRepository.save(itemEntity);
                return ITEM_ADD_SUC;
            }
        }

        return PACKAGE_NOT_FOUND;

    }

    public ApiResponse getItemList(String username,String packageName) {
        List<PackageEntity> packagesList = userRepository.findByUsername(username).get().getPackagesList();
        PackageEntity myPackageEntity = packagesList.stream()
                .filter(p -> packageName.equals(p.getName()))
                .findAny()
                .orElse(null);
        assert myPackageEntity != null;
        ITEM_LIST.setData(myPackageEntity.getItemsList());
        return ITEM_LIST;
    }
}
