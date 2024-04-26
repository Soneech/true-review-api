package org.soneech.truereview.repository;

import org.soneech.truereview.model.Review;
import org.soneech.truereview.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    int countByAuthor(User author);
}
