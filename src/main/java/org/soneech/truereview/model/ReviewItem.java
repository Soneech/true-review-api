package org.soneech.truereview.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
public class ReviewItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 200)
    private String name;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @OneToMany(mappedBy = "reviewItem")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Review> reviews;

    @Transient
    private int reviewsCount;

    @Transient
    private float middleRating;

    public ReviewItem(String name, Category category) {
        this.name = name;
        this.category = category;
    }
}
