package my.pro.ject.repository;

import my.pro.ject.domain.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {
    Member findMemberByMemberId(String memberId);
}
