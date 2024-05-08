package org.soneech.truereview.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private String name;

    @OneToMany(mappedBy = "reviewItem")
    private List<Review> reviews;

    @Transient
    private int reviewsCount;

    @Transient
    private float middleRating;

    public ReviewItem(String name) {
        this.name = name;
    }
}
