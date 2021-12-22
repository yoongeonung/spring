package jp.ac.hal.yoongeonung.spring_mvc_practice1.web.basic;

import jp.ac.hal.yoongeonung.spring_mvc_practice1.domain.Item;
import jp.ac.hal.yoongeonung.spring_mvc_practice1.domain.ItemParamDTO;
import jp.ac.hal.yoongeonung.spring_mvc_practice1.domain.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute ItemParamDTO params, Model model) {
        Item item = new Item(params.getItemName(), params.getPrice(), params.getQuantity());
        Item savedItem = itemRepository.save(item);
        System.out.println(savedItem.getId());
        model.addAttribute("item", savedItem);
        return "basic/item";
    }

    @PostConstruct
    public void init() {
        itemRepository.save(new Item("Item1", 2000, 100));
        itemRepository.save(new Item("Item2", 1000, 200));
    }
}
