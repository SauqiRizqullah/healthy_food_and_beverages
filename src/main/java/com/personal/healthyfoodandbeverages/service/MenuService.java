package com.personal.healthyfoodandbeverages.service;

import com.personal.healthyfoodandbeverages.dto.request.MenuRequest;
import com.personal.healthyfoodandbeverages.dto.request.SearchMenuRequest;
import com.personal.healthyfoodandbeverages.dto.response.MenuResponse;
import com.personal.healthyfoodandbeverages.entity.Menu;
import org.springframework.data.domain.Page;

public interface MenuService {
    //1. Create New Menu
    MenuResponse createNewMenu(MenuRequest menuRequest);

    //2. Get Menu by Id
    Menu getMenuById(String menuId);

    //3. Get All Menu
    Page<Menu> getAllMenus(SearchMenuRequest searchMenuRequest);

    //4. Delete byMenuId
    String deleteByMenuId (String menuId);

    //5. Update Price by Menu Name
    String updatePriceByMenuName (String menuName, Long newMenuPrice);


}
