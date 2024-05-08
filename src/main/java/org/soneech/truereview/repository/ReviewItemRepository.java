package org.soneech.truereview.repository;

import org.soneech.truereview.model.ReviewItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewItemRepository extends JpaRepository<ReviewItem, Long> {
    @Query("SELECT r FROM ReviewItem r WHERE REPLACE(LOWER(r.name), ' ', '') LIKE CONCAT('%', :name, '%')")
    List<ReviewItem> searchReviewItemByNameSubstr(String name);

    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM review r WHERE r.review_item_id = :itemId")
    int getReviewsCount(long itemId);

    @Query(nativeQuery = true, value = "SELECT AVG(r.rating) FROM review r WHERE r.review_item_id = :itemId")
    float getMiddleRating(long itemId);

    @Query(nativeQuery = true, value = "SELECT ri.id, ri.name FROM review_item ri JOIN review r " +
            "ON ri.id = r.review_item_id WHERE r.category_id = :categoryId")
    List<ReviewItem> findAllForCategory(long categoryId);

    Optional<ReviewItem> findByName(String name);
}
