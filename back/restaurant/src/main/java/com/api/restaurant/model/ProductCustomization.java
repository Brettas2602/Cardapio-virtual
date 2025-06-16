package com.api.restaurant.model;

import java.util.Date;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_customizations")
public class ProductCustomization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(name = "selection_type", nullable = false)
    private String selectionType;
    
    @Column(name = "max_selections")
    private Integer maxSelections;
    
    @Column(name = "is_required", nullable = false)
    private Boolean isRequired;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "product_id", nullable = false, referencedColumnName = "id")
    private Product product;

    @OneToMany(mappedBy = "productCustomizations", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("id ASC")
    private Set<ProductCustomizationValue> customizationValues;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSelectionType() {
        return selectionType;
    }

    public void setSelectionType(String selectionType) {
        this.selectionType = selectionType;
    }

    public Integer getMaxSelections() {
        return maxSelections;
    }

    public void setMaxSelections(Integer maxSelections) {
        this.maxSelections = maxSelections;
    }

    public Boolean getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(Boolean isRequired) {
        this.isRequired = isRequired;
    }

    public Set<ProductCustomizationValue> getCustomizationValues() {
        return customizationValues;
    }

    public void setCustomizationValues(Set<ProductCustomizationValue> customizationValues) {
        this.customizationValues = customizationValues;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
