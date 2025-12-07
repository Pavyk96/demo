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
        try {
            return itemService.create(dto);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @GetMapping("/{id}")
    public ItemResponseDto getById(@PathVariable Long id) {
        try {
            return itemService.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @GetMapping
    public List<ItemResponseDto> getAll() {
        try {
            return itemService.getAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @PutMapping("/{id}")
    public ItemResponseDto update(@PathVariable Long id,
                                  @RequestBody @Valid ItemRequestDto dto) {
        try {
            return itemService.update(id, dto);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        try {
            itemService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
