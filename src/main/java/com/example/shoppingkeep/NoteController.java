package com.example.shoppingkeep;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private ShoppingItemRepository shoppingItemRepository; // ★アイテム用のリポジトリを追加

    // ① メモ一覧（TOP画面）の表示
    @GetMapping("/")
    public String index(Model model) {
        List<Note> notes = noteRepository.findAll();
        model.addAttribute("notes", notes);
        return "index";
    }

    // ② 新しいメモ（カード）の追加処理
    @PostMapping("/notes/add")
    public String addNote(@RequestParam("title") String title) {
        if (title != null && !title.trim().isEmpty()) {
            Note note = new Note();
            note.setTitle(title);
            noteRepository.save(note);
        }
        return "redirect:/";
    }

    // ③ ★カード内に買い物アイテムを追加する処理
    @PostMapping("/items/add")
    public String addItem(@RequestParam("noteId") Long noteId, @RequestParam("itemName") String itemName) {
        Note note = noteRepository.findById(noteId).orElse(null);
        if (note != null && itemName != null && !itemName.trim().isEmpty()) {
            ShoppingItem item = new ShoppingItem();
            item.setNote(note);
            item.setName(itemName);
            shoppingItemRepository.save(item); // アイテムを保存
        }
        return "redirect:/";
    }

    // ④ ★カゴ入れ状態と金額を非同期（裏側）で更新する処理
    @PostMapping("/items/update")
    @ResponseBody // 画面遷移せず、データだけをやり取りする
    public String updateItem(
            @RequestParam("itemId") Long itemId,
            @RequestParam("isInCart") Boolean isInCart,
            @RequestParam(value = "price", required = false) Integer price) {
        
        ShoppingItem item = shoppingItemRepository.findById(itemId).orElse(null);
        if (item != null) {
            item.setIsInCart(isInCart);
            item.setPrice(price);
            shoppingItemRepository.save(item);
            return "success";
        }
        return "error";
    }
}