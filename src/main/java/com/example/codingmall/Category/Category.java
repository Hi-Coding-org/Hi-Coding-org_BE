package com.example.codingmall.Category;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id; // 카테고리 식별번호

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> children = new ArrayList<>();

    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    @JsonCreator
    public Category(@JsonProperty("id") Long id,
                    @JsonProperty("name") String name,
                    @JsonProperty("parent") Category parent,
                    @JsonProperty("createDate") LocalDateTime createDate,
                    @JsonProperty("updateDate") LocalDateTime updateDate) {
        this.id = id;
        this.name = name;
        this.parent = parent;
        this.createDate = createDate != null ? createDate : LocalDateTime.now();
        this.updateDate = updateDate;
    }

    public Category(String name, Category parent, LocalDateTime updateDate) {
        this.name = name;
        this.parent = parent;
        this.updateDate = updateDate;
    }

}
