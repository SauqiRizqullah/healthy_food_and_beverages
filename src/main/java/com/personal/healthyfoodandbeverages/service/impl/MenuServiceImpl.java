package com.personal.healthyfoodandbeverages.service.impl;

import com.personal.healthyfoodandbeverages.dto.request.MenuRequest;
import com.personal.healthyfoodandbeverages.dto.request.SearchMenuRequest;
import com.personal.healthyfoodandbeverages.dto.response.MenuResponse;
import com.personal.healthyfoodandbeverages.entity.Menu;
import com.personal.healthyfoodandbeverages.repository.MenuRepository;
import com.personal.healthyfoodandbeverages.service.MenuService;
import com.personal.healthyfoodandbeverages.specification.MenuSpecification;
import com.personal.healthyfoodandbeverages.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    private final ValidationUtil validationUtil;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public MenuResponse createNewMenu(MenuRequest menuRequest) {
        validationUtil.validate(menuRequest);
        // 1. Membuat objek menu

        Menu newMenu = Menu.builder()
                .menuName(menuRequest.getMenuName())
                .menuPrice(menuRequest.getMenuPrice())
                .build();

        // 2. Melakukan penyimpanan data di database (saveAndFlush)

        menuRepository.saveAndFlush(newMenu);

        // 3. Mengembalikan objek menjadi MenuResponse melalui parse

        return parseMenuToMenuResponse(newMenu);
    }

    @Transactional(readOnly = true)
    @Override
    public Menu getMenuById(String menuId) {
        return menuRepository.findById(menuId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Id was not found!!!"));
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Menu> getAllMenus(SearchMenuRequest searchMenuRequest) {
        // 1. Ketika nilai halaman 0, maka buatlah menjadi 1
        if(searchMenuRequest.getPage() <= 0){
            searchMenuRequest.setPage(1);
        }

        // 2. Membuat validasi pengurutan halaman dengan kolom - kolom yang tersedia
        String validSortBy;
        if("menuName".equalsIgnoreCase(searchMenuRequest.getSortBy()) || "menuPrice".equalsIgnoreCase(searchMenuRequest.getSortBy())) {
            validSortBy = searchMenuRequest.getSortBy();
        } else {
            validSortBy = "menuId";
        }

        // 3. Membuat aturan sortBy dengan objek sort
        Sort sort = Sort.by(Sort.Direction.fromString(searchMenuRequest.getDirection()), validSortBy);

        //4. Membuat objek halaman Pageable untuk membuat sebuah halaman
        Pageable pageable = PageRequest.of(searchMenuRequest.getPage() - 1, searchMenuRequest.getSize(), sort);

        //5. Menyelaraskan dengan rule query dari specification milik objek itu
        Specification<Menu> specification = MenuSpecification.getSpecification(searchMenuRequest);

        return menuRepository.findAll(specification,pageable);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String deleteByMenuId(String menuId) {
        Menu menu = menuRepository.findById(menuId).orElseThrow(() -> new RuntimeException("Menu Id tidak ditemukan"));
        menuRepository.delete(menu);
        return "We are so sad that " + menuId + "'s data is gone forever";
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String updatePriceByMenuName(String menuName, Long newMenuPrice) {
        // 1. Buatlah sebuah objek dengan ID menu yang ingin diupdate dengan harga baru dan harga menu awal
        Optional<Menu> currentMenu = menuRepository.getByMenuName(menuName);

        Menu validatedMenu;
        if (currentMenu.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu tidak ditemukan");
        } else {
            validatedMenu = currentMenu.get();
        }

        // 2. Update harga menu awal diganti dengan harga menu yang baru

        validatedMenu.setMenuPrice(newMenuPrice);

        validationUtil.validate(validatedMenu);


        // 3. Jangan lupa gunakan repository untuk melakukan query di belakang layar
        menuRepository.updateMenuPriceByMenuId(menuName, newMenuPrice);

        menuRepository.saveAndFlush(validatedMenu);

        return "The new price of " + menuName + " is " + newMenuPrice;
    }

    private MenuResponse parseMenuToMenuResponse(Menu newMenu) {
        // 1. Mengecek apakah id objek tersebut ada
        String id;
        if (newMenu.getMenuId() == null) {
            id = null;
        } else {
            id = newMenu.getMenuId();
        }

        return MenuResponse.builder()
                .menuId(id)
                .menuName(newMenu.getMenuName())
                .menuPrice(newMenu.getMenuPrice())
                .build();
    }
}
