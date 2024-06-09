package com.personal.healthyfoodandbeverages.controller;

import com.personal.healthyfoodandbeverages.constant.APIUrl;
import com.personal.healthyfoodandbeverages.dto.request.MenuRequest;
import com.personal.healthyfoodandbeverages.dto.request.SearchMenuRequest;
import com.personal.healthyfoodandbeverages.dto.response.CommonResponse;
import com.personal.healthyfoodandbeverages.dto.response.MenuResponse;
import com.personal.healthyfoodandbeverages.dto.response.PagingResponse;
import com.personal.healthyfoodandbeverages.entity.Menu;
import com.personal.healthyfoodandbeverages.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.MENU)
public class MenuController {

    private final MenuService menuService;

    @PostMapping(produces = "application/json")
    public ResponseEntity<CommonResponse<MenuResponse>> createNewMenu (
            @RequestBody MenuRequest menuRequest){

        // 1. Membuat objek menu dari service
        MenuResponse newMenu = menuService.createNewMenu(menuRequest);

        // 2. Membuat objek CommonResponse untuk melakukan Response

        CommonResponse<MenuResponse> response = CommonResponse.<MenuResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("You have created new menu succesfully!!!")
                .data(newMenu)
                .build();

        // 3. Mengembalikan Response Entity

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);

    }

    @GetMapping(path = APIUrl.PATH_VAR_MENU_ID, produces = "application/json")
    public ResponseEntity<CommonResponse<Menu>> getMenuById(
            @PathVariable String menuId
    ) {
        // 1. Membuat objek Menu Response
        Menu menu = menuService.getMenuById(menuId);

        // 2. Membuat objek Common Response untuk mengisi data response
        CommonResponse<Menu> response = CommonResponse.<Menu>builder()
                .statusCode(HttpStatus.OK.value())
                .message(menuId + "'s data was already retrieved")
                .data(menu)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<CommonResponse<Page<Menu>>> getAllMenusV2 (
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "sortBy", defaultValue = "menuId") String sortBy,
            @RequestParam(name = "direction", defaultValue = "ASC") String direction,
            @RequestParam(name = "menuName", required = false) String menuName
    ){
        // 1. Membuat objek SearchMenuRequest untuk mencari Menu semuanya
        SearchMenuRequest searchMenuRequest = SearchMenuRequest.builder()
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .direction(direction)
                .menuName(menuName)
                .build();

        // 2. Membuat objek Page Menu
        Page<Menu> allMenu = menuService.getAllMenus(searchMenuRequest);

        // 3. Membuat objek paging
        PagingResponse pagingResponse = PagingResponse.builder()
                .page(allMenu.getPageable().getPageNumber() + 1)
                .size(allMenu.getPageable().getPageSize())
                .totalPages(allMenu.getTotalPages())
                .totalElements(allMenu.getTotalElements())
                .hasNext(allMenu.hasNext())
                .hasPrevious(allMenu.hasPrevious())
                .build();

        // 4. Membuat objek Common Response untuk response
        CommonResponse<Page<Menu>> response = CommonResponse.<Page<Menu>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Retrieved all data successfully")
                .data(allMenu)
                .paging(pagingResponse)
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping(path = APIUrl.UPDATE_MENU_PRICE, produces = "application/json")
    public ResponseEntity<CommonResponse<String>> updateByMenuName(
            @RequestParam(name = "menuPrice") Long newMenuPrice,
            @RequestParam(name = "menuName") String menuName
    ){

        // 1. Memanggil service untuk melakukan update data menu
        String dataUpdate = menuService.updatePriceByMenuName(menuName, newMenuPrice);

        // 2. Membuat Common Response

        CommonResponse<String> response = CommonResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message(menuName + "'s price has been updated successfully")
                .data(dataUpdate)
                .build();

        return ResponseEntity.ok(response);

    }

    @DeleteMapping(path = APIUrl.PATH_VAR_MENU_ID, produces = "application/json")
    public ResponseEntity<CommonResponse<String>> deleteMenuById (
            @PathVariable String menuId
    ){
        // 1. Memanggil service untuk menghapus objek menu
        String dataDelete = menuService.deleteByMenuId(menuId);

        // 2. Membuat Common Response
        CommonResponse<String> response = CommonResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message(menuId + "'s data has been deleted from menu list")
                .data(dataDelete)
                .build();

        return ResponseEntity.ok(response);
    }
}
