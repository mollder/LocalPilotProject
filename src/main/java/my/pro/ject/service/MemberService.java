package my.pro.ject.service;

import my.pro.ject.domain.Member;
import my.pro.ject.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public Member findMember(String memberId) {
        return memberRepository.findMemberByMemberId(memberId);
    }

    public void saveMember(Member member) {
        memberRepository.save(member);
    }
}