package my.pro.ject.service;

import lombok.RequiredArgsConstructor;
import my.pro.ject.domain.Member;
import my.pro.ject.repository.MemberRepository;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
@RequiredArgsConstructor
public class MemberService {
    @NotNull
    private final MemberRepository memberRepository;

    public Member findMember(String memberId) {
        return memberRepository.findMemberByMemberId(memberId);
    }

    public void saveMember(Member member) {
        memberRepository.save(member);
    }
}