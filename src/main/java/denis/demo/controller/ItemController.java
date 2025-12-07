package denis.demo.controller;

import denis.demo.dto.ItemRequestDto;
import denis.demo.dto.ItemResponseDto;
import denis.demo.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ItemResponseDto create(@RequestBody @Valid ItemRequestDto dto) {
        return itemService.create(dto);
    }

    @GetMapping("/{id}")
    public ItemResponseDto getById(@PathVariable Long id) {
        return itemService.getById(id);
    }

    @GetMapping
    public List<ItemResponseDto> getAll() {
        return itemService.getAll();
    }

    @PutMapping("/{id}")
    public ItemResponseDto update(@PathVariable Long id,
                                  @RequestBody @Valid ItemRequestDto dto) {
        return itemService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        itemService.delete(id);
    }
}
