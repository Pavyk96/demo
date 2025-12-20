package denis.demo.controller;

import denis.demo.dto.ItemRequestDto;
import denis.demo.dto.ItemResponseDto;
import denis.demo.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Items", description = "CRUD-операции над Item")
@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @Operation(summary = "Создать Item")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Item успешно создан"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации")
    })
    @PostMapping
    public ItemResponseDto create(@RequestBody @Valid ItemRequestDto dto) {
        return itemService.create(dto);
    }

    @Operation(summary = "Получить Item по id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Item найден"),
            @ApiResponse(responseCode = "404", description = "Item не найден")
    })
    @GetMapping("/{id}")
    public ItemResponseDto getById(@PathVariable Long id) {
        return itemService.getById(id);
    }

    @Operation(summary = "Получить все Item")
    @ApiResponse(responseCode = "200", description = "Список Item")
    @GetMapping
    public List<ItemResponseDto> getAll() {
        return itemService.getAll();
    }

    @Operation(summary = "Обновить Item по id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Item обновлён"),
            @ApiResponse(responseCode = "404", description = "Item не найден")
    })
    @PutMapping("/{id}")
    public ItemResponseDto update(@PathVariable Long id,
                                  @RequestBody @Valid ItemRequestDto dto) {
        return itemService.update(id, dto);
    }

    @Operation(summary = "Удалить Item по id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Item удалён"),
            @ApiResponse(responseCode = "404", description = "Item не найден")
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        itemService.delete(id);
    }
}
