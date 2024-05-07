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

    Optional<ReviewItem> findByName(String name);
}
