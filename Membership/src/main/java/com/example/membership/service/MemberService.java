package com.example.membership.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.membership.domain.Member;
import com.example.membership.repository.MemberRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MemberService {
	
	private final MemberRepository memberRepository;
	
	public Member join(Member member) {
		return memberRepository.save(member);
	}
	
	public List<Member> getMembers() {
		return memberRepository.findAllByOrderByPhone();
	}
	
	public void updatePoint(String phone, int point) {
		Member member = memberRepository.findById(phone).get();
		member.setPoint(member.getPoint() + point);
		this.join(member);
	}
}
