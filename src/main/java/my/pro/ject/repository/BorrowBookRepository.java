package my.pro.ject.repository;

import my.pro.ject.domain.BorrowBook;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowBookRepository extends CrudRepository<BorrowBook, Long> {

}
