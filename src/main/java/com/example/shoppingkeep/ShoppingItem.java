package com.example.shoppingkeep;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "shopping_items")
public class ShoppingItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne @JoinColumn(name = "note_id", nullable = false)
    private Note note;
    private String name;
    @Column(name = "is_in_cart")
    private Boolean isInCart = false;
    private Integer price;
    @Column(name = "is_tax_included")
    private Boolean isTaxIncluded = false;
    @Column(name = "specific_tax_rate")
    private Integer specificTaxRate;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Note getNote() { return note; }
    public void setNote(Note note) { this.note = note; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Boolean getIsInCart() { return isInCart; }
    public void setIsInCart(Boolean isInCart) { this.isInCart = isInCart; }
    public Integer getPrice() { return price; }
    public void setPrice(Integer price) { this.price = price; }
    public Boolean getIsTaxIncluded() { return isTaxIncluded; }
    public void setIsTaxIncluded(Boolean isTaxIncluded) { this.isTaxIncluded = isTaxIncluded; }
    public Integer getSpecificTaxRate() { return specificTaxRate; }
    public void setSpecificTaxRate(Integer specificTaxRate) { this.specificTaxRate = specificTaxRate; }
}