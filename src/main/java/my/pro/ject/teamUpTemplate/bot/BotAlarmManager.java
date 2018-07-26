package my.pro.ject.teamUpTemplate.bot;

import lombok.RequiredArgsConstructor;
import my.pro.ject.domain.BorrowBook;
import my.pro.ject.domain.Member;
import my.pro.ject.pojo.v1.MemberGetV1;
import my.pro.ject.pojo.v3.V3Room;
import my.pro.ject.repository.BookRepository;
import my.pro.ject.repository.MemberRepository;
import my.pro.ject.service.BookService;
import my.pro.ject.service.MemberService;
import my.pro.ject.teamUpTemplate.V1.V1Template;
import my.pro.ject.teamUpTemplate.V3.V3Template;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import my.pro.ject.domain.Book;

import javax.validation.constraints.NotNull;
import java.util.LinkedHashMap;

@RequiredArgsConstructor
@Component
public class BotAlarmManager {
    @NotNull
    private final V3Template v3Template;
    @NotNull
    private final V1Template v1Template;
    @NotNull
    private final BotTokenManager botTokenManager;
    @NotNull
    private final MemberRepository memberRepository;
    @NotNull
    private final BookRepository bookRepository;

    public void sendLoginAlarm(Member member) {
        int roomNum = getRoomNum(member);

        v3Template.sendMessage(roomNum, "도서 대여 시스템에 로그인되셨습니다.");
    }

    public void sendBorrowAlarm(BorrowBook borrowBook) {
        int roomNum = getRoomNum(borrowBook.getMember());

        Book book = borrowBook.getBook();

        String message = "책 대여가 완료 되었습니다.\n" +
                "책 이름: " + book.getBookName() + "\n" +
                "책 아이디: " + book.getBookId() + "\n" +
                "반납일: " + borrowBook.getBorrowDate().plusDays(14);

        v3Template.sendMessage(roomNum, message);
    }

    public void sendReturnAlarm(Member member, Book book) {
        int roomNum = getRoomNum(member);

        String message = "책 반납이 완료 되었습니다.\n" +
                "책 이름: " + book.getBookName() + "\n" +
                "책 아이디: " + book.getBookId();

        v3Template.sendMessage(roomNum, message);
    }

    private int getRoomNum(Member member) {
        ResponseEntity<V3Room> roomResObjResponseEntity = v3Template.createRoom(getTeamNum(), member);
        return (int) roomResObjResponseEntity.getBody().getRoom();
    }

    private int getTeamNum() {
        ResponseEntity<MemberGetV1> memberResponse = v1Template.getUserHttpCommunication(botTokenManager.getBotToken());

        LinkedHashMap<String, Integer> linkedHashMap = (LinkedHashMap<String, Integer>)memberResponse.getBody().getTeams()[0];
        return linkedHashMap.get("index");
    }
}