package org.soneech.truereview.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;


@Entity
@Table
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User author;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "review_item_id", referencedColumnName = "id")
    private ReviewItem reviewItem;

    @NotNull
    @Min(1)
    @Max(5)
    private Short rating;

    @Size(max = 1000)
    private String advantages;

    @Size(max = 1000)
    private String disadvantages;

    @Size(max = 1000)
    private String note;
}
