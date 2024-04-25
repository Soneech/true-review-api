package org.soneech.truereview.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Entity
@Table
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    @NotBlank
    @Column(name = "object_name")
    private String objectName;

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

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @OneToMany(mappedBy = "review")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Image> images;
}
