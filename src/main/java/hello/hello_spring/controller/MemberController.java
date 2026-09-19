package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberservice;

    @Autowired // 멤버서비스를 스프링이 스프링 컨테이너에 있는 거를 연결시켜줌
    public MemberController(MemberService memberservice) {
        this.memberservice = memberservice;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberservice.join(member);

        return "redirect:/";
    }

    @GetMapping("members")
    public String list (Model model){
        List<Member> members = memberservice.findMembers();
        model.addAttribute("members", members);
        return "members/listMembers";
    }

}
