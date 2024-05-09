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

    @Query(nativeQuery = true, value = "SELECT * FROM review_item ri WHERE ri.category_id = :categoryId")
    List<ReviewItem> findAllByCategory(long categoryId);

    @Query(nativeQuery = true, value = "SELECT EXISTS (SELECT r.id FROM review r WHERE r.review_item_id = :itemId)")
    boolean checkThatItemHasReviews(long itemId);

    Optional<ReviewItem> findByName(String name);
}
