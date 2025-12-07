package denis.demo.service;

import denis.demo.dto.ItemRequestDto;
import denis.demo.dto.ItemResponseDto;
import denis.demo.model.Item;
import denis.demo.repo.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemResponseDto create(ItemRequestDto dto) {
        Item item = Item.builder()
                .name(dto.getName())
                .build();
        item = itemRepository.save(item);
        return new ItemResponseDto(item.getId(), item.getName());
    }

    public ItemResponseDto getById(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item not found - id = " + id));
        return new ItemResponseDto(item.getId(), item.getName());
    }

    public List<ItemResponseDto> getAll() {
        return itemRepository.findAll().stream()
                .map(i -> new ItemResponseDto(i.getId(), i.getName()))
                .toList();
    }

    public ItemResponseDto update(Long id, ItemRequestDto dto) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item not found - id = " + id));
        item.setName(dto.getName());
        item = itemRepository.save(item);
        return new ItemResponseDto(item.getId(), item.getName());
    }

    public void delete(Long id) {
        if (!itemRepository.existsById(id)) {
            throw new EntityNotFoundException("Item not found - id = " + id);
        }
        itemRepository.deleteById(id);
    }
}
