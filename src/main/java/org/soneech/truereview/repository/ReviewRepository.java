package org.soneech.truereview.repository;

import org.soneech.truereview.model.Review;
import org.soneech.truereview.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    int countByAuthor(User author);

    @Query(nativeQuery = true, value = "SELECT * FROM review WHERE user_id = :userId")
    List<Review> findUserReviews(long userId);

    @Query(nativeQuery = true, value = "SELECT * FROM review WHERE category_id = :categoryId")
    List<Review> findReviewsForCategory(long categoryId);
}
